package com.syzible.hair.Common;

import android.content.Context;

public interface Mvp {
    interface View {
        Context getContext();
    }

    interface Presenter<View>  {
        void attach(View view);

        void detach();
    }
}
