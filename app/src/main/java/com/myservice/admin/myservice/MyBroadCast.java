package com.myservice.admin.myservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @author Yen Do
 * @owner Moca.vn
 * @since 12/20/2016
 */
public class MyBroadCast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Ban vua nhan duoc sms moi", Toast.LENGTH_LONG);
    }
}
