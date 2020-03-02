package com.cloud.medical.records.client_app.util;

import android.text.TextUtils;

public class Validator {

    public static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
}
