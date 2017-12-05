package com.example.zeeshan.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by Zeeshan on 12/5/2017.
 */

public class IncomingCall extends BroadcastReceiver {

    //
//    private static int lastState = TelephonyManager.CALL_STATE_IDLE;
//    private static Date callStartTime;
//    private static boolean isIncoming;
//    private static String savedNumber;  //because the passed incoming is only valid in ringing


    Context mContext;

    @Override
    public void onReceive(Context mContext, Intent intent) {
        try {

            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);


            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                Toast.makeText(mContext, "Phone Is Ringing", Toast.LENGTH_LONG).show();
                // Your Code
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                Intent intent1=new Intent(mContext.getApplicationContext(),BoundService.class);
                mContext.getApplicationContext().startService(intent1);

                //                public void startService(View view) {
//                    startService(new Intent(getBaseContext(), MyService.class));
//                }
//                Toast.makeText(mContext, "Call Recieved", Toast.LENGTH_LONG).show();
                // Your Code
            }

            if (state.equals(TelephonyManager.EXTRA_STATE_IDLE)) {
                Intent intent1=new Intent(mContext.getApplicationContext(),BoundService.class);
                mContext.getApplicationContext().stopService(intent1);
                Toast.makeText(mContext, "Phone Is Idle", Toast.LENGTH_LONG).show();
                // Your Code

            }
        } catch (Exception e) {
            //your custom message
        }

    }
}