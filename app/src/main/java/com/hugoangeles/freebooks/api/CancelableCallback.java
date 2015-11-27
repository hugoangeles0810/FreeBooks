package com.hugoangeles.freebooks.api;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.Response;

/**
 * Created by Hugo on 26/11/15.
 */
public abstract class CancelableCallback<T> implements Callback<T> {

    private static List<CancelableCallback> sCallbacks;

    private String mtag;
    private boolean isCanceled;

    static {
        sCallbacks = new ArrayList<>();
    }

    public static void cancel(String tag){
        if (tag != null) {
            for (CancelableCallback callback : sCallbacks) {
                if (tag.equals(callback.getTag())) {
                    callback.cancel();
                }
            }
        }
    }

    public static void cancelAll() {
        for (CancelableCallback callback : sCallbacks) {
            callback.cancel();
        }
    }

    public CancelableCallback (){
        isCanceled = false;
        sCallbacks.add(this);
    }

    public CancelableCallback (String tag){
        isCanceled = false;
        mtag = tag;
        sCallbacks.add(this);
    }

    public String getTag() {
        return mtag;
    }

    public void setTag(String mtag) {
        this.mtag = mtag;
    }

    public void cancel() {
        isCanceled = true;
        sCallbacks.remove(this);
    }

    @Override
    public void onResponse(Response<T> response) {
        if (!isCanceled)
            onCancelableResponse(response);
        sCallbacks.remove(this);
    }

    @Override
    public void onFailure(Throwable t) {
        if (!isCanceled)
            onCancelableFailure(t);
        sCallbacks.remove(this);
    }

    public abstract void onCancelableResponse(Response<T> response);

    public abstract void onCancelableFailure(Throwable t);

}
