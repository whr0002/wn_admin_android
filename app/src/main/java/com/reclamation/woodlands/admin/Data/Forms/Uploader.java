package com.reclamation.woodlands.admin.Data.Forms;

import android.app.ProgressDialog;
import android.util.Log;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reclamation.woodlands.admin.Activity.FormActivity;
import com.reclamation.woodlands.admin.DB.DAO;
import com.reclamation.woodlands.admin.DB.Table_Photo.PhotoDAO;
import com.reclamation.woodlands.admin.DB.Table_UserInfo.UI_DataSource;
import com.reclamation.woodlands.admin.DB.Table_UserInfo.UserInfo;

import org.apache.http.Header;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jimmy on 5/27/2015.
 */
public abstract class Uploader {

    public FormActivity mActivity;
    public AsyncHttpClient client;
    public ExecutorService executorService;

    public DAO mDao;
    public AtomicInteger totalCounter = new AtomicInteger();
    public AtomicInteger successCounter = new AtomicInteger();
    public int mTotal;
    public PhotoDAO photoDAO;
    public UserInfo userInfo;
    public static final String BASE_WEBSITE_URL = "http://reclamation.azurewebsites.net/rdata/";
    public static final String BASE_STORAGE_URL = "https://reclamation.blob.core.windows.net/";
    public ProgressDialog mProgressDialog;

    public Uploader(FormActivity a, int total, DAO dao, ProgressDialog progressDialog){

        mTotal = total;

        client = new AsyncHttpClient();
        executorService = Executors.newFixedThreadPool(10);
        client.setThreadPool(executorService);

        mDao = dao;
        photoDAO = new PhotoDAO(a);
        mActivity = a;
        mProgressDialog = progressDialog;

        UI_DataSource ui_dataSource = new UI_DataSource(a);
        ui_dataSource.open();
        userInfo = ui_dataSource.getUserInfo();
        ui_dataSource.close();
    }

    public void execute(final Form form){

        RequestParams params = getParams(form);

        if(params != null) {

            client.post(getUrl(), params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {

                    int ci = totalCounter.incrementAndGet();
                    mProgressDialog.setProgress(ci);

                    Log.i("debug", "In Success");
                    try {
                        String result = new String(bytes);
                        Log.i("debug", result);
                        if (result.equalsIgnoreCase("Form Submitted")) {

                            // Form is successfully submitted

//                            Log.i("debug", "Submitted "
//                                    + successCounter.incrementAndGet() + "/" + mTotal);
                            successCounter.incrementAndGet();

                            // delete data on device
                            mActivity.deleteForm(form);

                        }
                    } catch (Exception e) {
                    }

                    if(ci == mTotal){

                        // Refresh form list
                        mActivity.onDataSetChanged();

                        mProgressDialog.dismiss();
                        Toast.makeText(mActivity,
                                "Submitted " + successCounter + "/"+ mTotal,
                                Toast.LENGTH_LONG).show();
                    }


                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    int ci = totalCounter.incrementAndGet();
                    mProgressDialog.setProgress(ci);

                    Log.i("debug", "In Failure");
                    try {
                        String result = new String(bytes);
                        Log.i("debug", result);
                    } catch (Exception e) {
                    }


                    if(ci == mTotal){

                        // Refresh form list
                        mActivity.onDataSetChanged();

                        mProgressDialog.dismiss();
                        Toast.makeText(mActivity,
                                "Submitted " + successCounter + "/"+ mTotal,
                                Toast.LENGTH_LONG).show();
                    }

                }
            });
        }

    }


    public abstract String getUrl();
    public abstract RequestParams getParams(Form form);
}
