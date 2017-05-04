package tr.com.mis49m.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SmsReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            SmsMessage smsMessage;

            if (Build.VERSION.SDK_INT >= 19) { //KITKAT
                SmsMessage[] msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
                smsMessage = msgs[0];
            } else {
                Bundle bundle = intent.getExtras();
                Object pdus[] = (Object[]) bundle.get("pdus");
                smsMessage = SmsMessage.createFromPdu((byte[]) pdus[0]);
            }

            String senderNum = smsMessage.getDisplayOriginatingAddress();
            String message = smsMessage.getDisplayMessageBody();

            Toast.makeText(context,"senderNum: " + senderNum + ", message: " + message,
                    Toast.LENGTH_LONG).show();
        }catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);
        }

    }
}
