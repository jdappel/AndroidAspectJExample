package com.jdappel.xpoint;

import android.content.Context;
import com.jdappel.example.BuildConfig;
import com.jdappel.example.MainActivity;
import com.jdappel.example.R;
import com.jdappel.kotlin.InfoActivity;
import com.jdappel.kotlin.ToastHelperKt;
import com.jdappel.kotlin.TextInfo;

/**
 * Created by archinamon on 19/02/16.
 */
privileged aspect JvmLangDecorator {

    /* Kotlin section */

    private boolean InfoActivity.inject = BuildConfig.DEBUG;

    pointcut kotlinImplicitInjector(InfoActivity activity): this(activity) && within(InfoActivity) && call(!private * *(..));
    pointcut toastInjectKt(Context ctx): this(ctx) && within(MainActivity) && call(private void toastFromKotlin());

    after(InfoActivity activity) returning: kotlinImplicitInjector(activity) {
        if (activity.inject) {
            final String target = thisJoinPoint.toString();
            System.out.println(String.format("Aspected kotlin JP{%s} was successfully executed!", target));
        }
    }

    void around(Context ctx): toastInjectKt(ctx) {
        ToastHelperKt.sendToast(ctx, new TextInfo(ctx.getString(R.string.something_happend_kotlin)));
    }

    /**
     * Around-advice introspects within anonymous pointcut
     */
    void around(Context ctx): this(ctx) && execution(private void MainActivity.toastFromAspectJ()) {
        ToastHelperKt.sendToast(ctx, new TextInfo(ctx.getString(R.string.something_happend_aspectj)));
    }
}
