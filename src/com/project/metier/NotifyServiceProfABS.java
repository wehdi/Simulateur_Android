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

import com.project.simulaturandroid.Planning;
import com.project.simulaturandroid.R;

/**
 * Notifiation quand le prof est abs et le planning change
 * 
 * @author ProBook 450g2
 *
 */
public class NotifyServiceProfABS extends Service {

	class MyBinder extends Binder {
		Service getService() {
			return NotifyServiceProfABS.this;

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

		/**
		 * Creation du notification manager
		 */
		NotificationManager mgr = (NotificationManager) this
				.getSystemService(Context.NOTIFICATION_SERVICE);

		Notification note = new Notification(R.drawable.ic_notify_planning,
				"Modification planning", System.currentTimeMillis());

		/**
		 * Pending intent qui va s'ouvrir ares le click sur la notification
		 */

		PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, Planning.class), 0);

		/*
		 * PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
		 * intentPend, 0);
		 */

		note.contentIntent = pendingIntent;

		/**
		 * Envoi de la notification
		 */

		note.setLatestEventInfo(
				this,
				"Amenagement de l'heure",
				"Le prof est absent, un amenagement de l'emploi du temps a ete effectue",
				pendingIntent);

		mgr.notify(-1, note);
		/**
		 * Arreter le service pour ne pas qu'il boucle
		 */
		stopService(intent);

	}

}
