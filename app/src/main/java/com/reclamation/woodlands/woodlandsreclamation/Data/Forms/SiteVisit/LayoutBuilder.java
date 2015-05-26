package com.reclamation.woodlands.woodlandsreclamation.Data.Forms.SiteVisit;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.reclamation.woodlands.woodlandsreclamation.R;

/**
 * Created by Jimmy on 5/26/2015.
 */
public class LayoutBuilder {

    private Context mContext;

    public LayoutBuilder(Context context){

        mContext = context;


    }

    public LinearLayout buildLayout(LinearLayout wrapper, String title){

        LinearLayout linearLayout = new LinearLayout(mContext);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setWeightSum(1);
        LinearLayout.LayoutParams layoutParams = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 160);
        linearLayout.setLayoutParams(layoutParams);


        // First row
        TextView textView = new TextView(mContext);
        textView.setText(title);
        textView.setTextSize(18);
        textView.setGravity(Gravity.CENTER);
        LinearLayout.LayoutParams textParam = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParam.gravity = Gravity.CENTER;
        textView.setLayoutParams(textParam);



        LinearLayout firstRow = new LinearLayout(mContext);
        firstRow.setBackgroundColor(mContext.getResources().getColor(R.color.lightBlue));

        LinearLayout.LayoutParams firstRowParam = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);

        firstRowParam.weight = 0.5f;

        firstRow.setLayoutParams(firstRowParam);



        // Second row wrapper
        LinearLayout innerLayout = new LinearLayout(mContext);
        innerLayout.setOrientation(LinearLayout.HORIZONTAL);
        innerLayout.setWeightSum(1);
        innerLayout.setTag(title);

        LinearLayout.LayoutParams innerParam = new LinearLayout
                .LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
        innerParam.weight = 0.5f;

        innerLayout.setLayoutParams(innerParam);

        // Pass or fail dropdown
        Spinner passFailSpinner = new Spinner(mContext);
        LinearLayout.LayoutParams spinnerParam = new LinearLayout
                .LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        spinnerParam.weight = 0.5f;
        passFailSpinner.setLayoutParams(spinnerParam);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter
                .createFromResource(mContext,R.array.pass_fail, R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        passFailSpinner.setAdapter(adapter);



        // Description
        EditText editText = new EditText(mContext);
        LinearLayout.LayoutParams editParam = new LinearLayout
                .LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        editParam.weight = 0.5f;
        editText.setLayoutParams(editParam);



        // Add children view to parent view
        firstRow.addView(textView);

        innerLayout.addView(passFailSpinner);
        innerLayout.addView(editText);

        linearLayout.addView(firstRow);
        linearLayout.addView(innerLayout);

        wrapper.addView(linearLayout);




        return innerLayout;
    }

}
