package com.reclamation.woodlands.woodlandsreclamation.Data.Forms;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.reclamation.woodlands.woodlandsreclamation.DB.DAO;

import org.apache.http.Header;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Jimmy on 5/27/2015.
 */
public abstract class Uploader {

    public AsyncHttpClient client;
    public DAO mDao;
    public AtomicInteger successCounter;
    public int mTotal;

    public Uploader(int total, DAO dao){

        mTotal = total;
        client = new AsyncHttpClient();
        mDao = dao;
    }

    public void execute(final Form form){

        client.post(getUrl(), getParams(form), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                Log.i("debug", "In Success");
                try{
                    String result = new String(bytes);

                    if(result.equalsIgnoreCase("Form Submitted")){

                        // Form is successfully submitted

                        Log.i("debug", "Submitted "
                                + successCounter.incrementAndGet() + "/" + mTotal);

                        mDao.delete(form);

                    }
                }catch (Exception e){}


            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                Log.i("debug", "In Failure");
            }
        });

    }


    public abstract String getUrl();
    public abstract RequestParams getParams(Form form);
}
