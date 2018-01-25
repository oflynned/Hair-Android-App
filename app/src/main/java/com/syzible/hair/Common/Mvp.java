package com.syzible.hair.Common;

import android.content.Context;

public interface Mvp {
    interface IView {
        Context getContext();
    }

    interface IPresenter<View>  {
        void attach(View view);

        void detach();
    }
}
