package cn.edu.nuaa.common;

import android.support.annotation.Nullable;

/**
 * Created by Meteor on 2017/12/23.
 */

public class Common {

    @Nullable
    public static String getParentaMethod() {
        StackTraceElement[] ste = Thread.currentThread().getStackTrace();
            return ste[3].getClassName()+"::"+ste[3].getMethodName();
    }
}
