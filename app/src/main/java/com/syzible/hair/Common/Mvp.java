package com.syzible.hair.Common;

import android.content.Context;
import android.view.View;

public interface Mvp {
    interface IView {
        Context getContext();

        View getView();
    }

    interface IPresenter<View>  {
        void attach(View view);

        void detach();
    }
}
