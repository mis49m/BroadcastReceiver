package tr.com.mis49m.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

// Broadcast Receiver class
public class MyBroadcastReceiver extends BroadcastReceiver {

	// Called on receive
	@Override
	public void onReceive(Context context, Intent intent) {
		// Read value from intent
		String intentData = intent.getStringExtra("msg");

		Toast.makeText(context,
				"My Broadcast Receiver. Message is " + intentData,
				Toast.LENGTH_LONG).show();
	}

}


