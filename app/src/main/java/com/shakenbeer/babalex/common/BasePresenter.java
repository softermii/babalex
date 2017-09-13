package com.shakenbeer.babalex.common;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by onos on 08.09.17.
 */

public class BasePresenter<T extends BaseView> {

    private static final Handler handler = new Handler(Looper.getMainLooper());
    private T mvpView;

    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    public void detachView() {
        this.mvpView = null;
    }

    public T getMvpView() {
        return mvpView;
    }

    public void onResume() {
    }

    public void onPause() {
    }

    boolean isViewAttached() {
        return mvpView != null;
    }

    protected void runOnUIThread(Runnable runnable) {
        if (getMvpView() != null) {
            handler.post(runnable);
        }
    }

}
