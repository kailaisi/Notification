package com.kailaisii.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent mainIntent = new Intent(context, MainActivity.class);
        //将MainAtivity的launchMode设置成SingleTask, 或者在下面flag中加上Intent.FLAG_CLEAR_TOP,
        //如果Task栈中有MainActivity的实例，就会把它移到栈顶，把在它之上的Activity都清理出栈，
        //如果Task栈不存在MainActivity实例，则在栈顶创建
        mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //Intent[] intents = {mainIntent, detailIntent};
        context.startActivity(mainIntent);
    }
}