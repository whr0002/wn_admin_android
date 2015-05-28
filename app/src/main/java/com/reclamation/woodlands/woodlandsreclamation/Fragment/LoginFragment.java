package com.reclamation.woodlands.woodlandsreclamation.Fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo.UI_DataSource;
import com.reclamation.woodlands.woodlandsreclamation.DB.Table_UserInfo.UserInfo;
import com.reclamation.woodlands.woodlandsreclamation.R;

import org.apache.http.Header;
import org.json.JSONObject;

/**
 * Created by Jimmy on 5/8/2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{

    private static final String DEBUG = "debug";
    private Activity mActivity;
    private EditText usernameView;
    private EditText passwordView;
    private TextView nameView;
    private TextView roleView;

    private LinearLayout loginView;
    private LinearLayout logoutView;

    private ProgressDialog progressDialog;

    private static final String LOGIN_URL = "http://reclamation.azurewebsites.net/android/login";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        this.mActivity = this.getActivity();
        this.usernameView = (EditText) rootView.findViewById(R.id.username);
        this.passwordView = (EditText) rootView.findViewById(R.id.password);

        this.nameView = (TextView) rootView.findViewById(R.id.name);
        this.roleView = (TextView) rootView.findViewById(R.id.role);

        loginView = (LinearLayout) rootView.findViewById(R.id.loginView);
        logoutView = (LinearLayout) rootView.findViewById(R.id.logoutView);

        Button loginBtn = (Button) rootView.findViewById(R.id.login);
        Button logoutBtn = (Button) rootView.findViewById(R.id.logout);
        loginBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);

        checkUserInfo();

        return rootView;
    }

    private void checkUserInfo() {

        try {
            UI_DataSource dao = new UI_DataSource(mActivity);
            dao.open();

            UserInfo ui = dao.getUserInfo();
            dao.close();
            showLogoutView(ui);
        }catch (Exception e){

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                startLogin();
                break;

            case R.id.logout:
                logout();
                break;
        }
    }

    private void logout() {
        logoutView.setVisibility(View.GONE);
        usernameView.setText("");
        passwordView.setText("");
        loginView.setVisibility(View.VISIBLE);

        UI_DataSource dao = new UI_DataSource(mActivity);
        dao.open();
        dao.delete(dao.getUserInfo());
        dao.close();
    }

    private void startLogin() {

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();

        final UI_DataSource dao = new UI_DataSource(mActivity);
        dao.open();



        String email = usernameView.getText().toString().trim();
        String password = passwordView.getText().toString().trim();

        final UserInfo ui = new UserInfo(email,password, null);

        params.put("Email", email);
        params.put("Password", password);
        params.put("RememberMe", false);

        progressDialog = ProgressDialog.show(mActivity, "", "Signing in...", true);
        client.post(LOGIN_URL, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                try{
                    String result = new String(bytes);

                    if(result.contains("success")){
                        // Login successfully, store user info to database
                        processJSON(result, ui, dao);

                    }else{
                        Toast.makeText(mActivity, "Login failed", Toast.LENGTH_LONG).show();
                    }

                }catch (Exception e){}

                progressDialog.dismiss();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Toast.makeText(mActivity, "Login failed", Toast.LENGTH_LONG).show();
                progressDialog.dismiss();
            }
        });

    }

    private void processJSON(String json, UserInfo ui, UI_DataSource dao){
        loginView.setVisibility(View.GONE);
        logoutView.setVisibility(View.VISIBLE);

        try{
            JSONObject jsonObject = new JSONObject(json);
            String name = jsonObject.getString("Email");
            String role = jsonObject.getString("Role");

            UserInfo temp = new UserInfo(ui.username, ui.password, role);
            dao.delete(temp);
            dao.create(temp);
            dao.close();
            nameView.setText("Hello " + name);
            roleView.setText("You belong to " + role);

        }catch (Exception e){

        }

    }

    private void showLogoutView(UserInfo ui){
        if(ui != null){
            loginView.setVisibility(View.GONE);
            nameView.setText("Hello " + ui.username);
            roleView.setText("You belong to " + ui.role);
            logoutView.setVisibility(View.VISIBLE);

        }

    }
}
