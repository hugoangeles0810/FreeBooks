package com.hugoangeles.freebooks.presenter;

/**
 * Created by Hugo on 26/11/15.
 */
public interface Presenter <V> {

    void attachedView(V view);
    void detachView();
}
