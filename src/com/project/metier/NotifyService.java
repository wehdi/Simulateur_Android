package com.project.metier;

/**
 * @author ProBook 450g2
 * 
 * Class Service qui envoi une notification
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.project.simulaturandroid.OtherActivities;
import com.project.simulaturandroid.R;

public class NotifyService extends Service {

	class MyBinder extends Binder {
		Service getService() {
			return NotifyService.this;

		}
	}

	@Override
	public IBinder onBind(Intent intent) {

		return new MyBinder();
	}

	@Override
	public void onCreate() {
		super.onCreate();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		super.onStart(intent, startId);
		String message_titre;
		String message_corp_titre;
		String message_corp;
		/**
		 * 
		 */
		message_titre = intent.getStringExtra("titre");
		message_corp_titre = intent.getStringExtra("corp_titre");
		message_corp = intent.getStringExtra("corp");

		String event = message_corp.substring(0, message_corp.indexOf("|"));
		String event2 = message_corp.substring(message_corp.indexOf("|"));

		/**
		 * Creation du notification manager
		 */
		NotificationManager mgr = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification note = new Notification(R.drawable.ic_notify_event,
				message_titre, System.currentTimeMillis());

		/**
		 * Pending intent qui va s'ouvrir ares le click sur la notification
		 */

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, OtherActivities.class), 0);

		note.contentIntent = pendingIntent;

		/**
		 * Envoi de la notification
		 */

		note.setLatestEventInfo(this, message_corp_titre, event, pendingIntent);

		mgr.notify(-1, note);
		/**
		 * Arreter le service pour ne pas qu'il boucle
		 */
		stopService(intent);

	}

}
