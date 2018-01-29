package com.syzible.hair.Common;

import android.app.Activity;
import android.content.Context;
import android.view.View;

public interface Mvp {
    interface IView {
        Activity getActivity();

        Context getContext();

        View getView();
    }

    interface IPresenter<View> {
        void attach(View view);

        void detach();
    }
}
