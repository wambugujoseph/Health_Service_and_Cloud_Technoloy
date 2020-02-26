package com.cloud.medical.records.client_app.util;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton vInstance;
    private RequestQueue vRequestQueue;
    private static Context vContext;

    private VolleySingleton(Context context){
        //specify the application context
        vContext = context;
        //get the Request queue
        vRequestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton getInstance(Context context) {
        //if Instance is null the initialize new Instance
        if (vInstance == null){
            vInstance = new VolleySingleton(context);
        }
        //Returning VolleySingleton new Instance
        return vInstance;
    }

    public  RequestQueue getRequestQueue(){
        //if RequestQueue is null then initialize new requestQueue
        if (vRequestQueue == null){
            vRequestQueue = Volley.newRequestQueue(vContext.getApplicationContext());
        }
        //Return RequestQueue
        return vRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> request){
        //Add the specified request to the request queue
        getRequestQueue().add(request);
    }
}
