package app.healthcare;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class TimeTableTakeService extends Service {
	private static final int FM_NOTIFICATION_ID = 1;
	SharedPreferences prefs;
	boolean check = true;

	@Override
	public IBinder onBind(Intent intent) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate() {
		check = true;
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		int time = getTime(Constants.CHECK_TIME);
		if (Constants.getInstance().getTime().hour == time) {
			setTime(100, Constants.CHECK_TIME);
			if (getTime(Constants.TIME_COUNT) >= getTime(Constants.COUNT_TIME)) {
				if (check) {
					addNotification(intent);
					check = false;
					Toast.makeText(this, "Đã tới giờ uống thuốc",
							Toast.LENGTH_SHORT).show();
					setTime(getTime(Constants.COUNT_TIME + 1),
							Constants.COUNT_TIME);

				}
			}
		}

		return startId;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();

	}

	private void addNotification(Intent intent) {

		NotificationCompat.Builder builder = new NotificationCompat.Builder(
				this)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle("Thông báo uống thuốc")
				.setContentText(
						"Đã tới giờ uống thuốc rồi, đề nghị bạn uống thuốc đễ mau khỏi bệnh");

		Intent notificationIntent = intent;
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
				notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(contentIntent);

		// Add as notification
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.notify(FM_NOTIFICATION_ID, builder.build());
	}

	// Remove notification
	private void removeNotification() {
		NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(FM_NOTIFICATION_ID);
	}

	public void setTime(int score, String s) {

		SharedPreferences.Editor settingsEditor = prefs.edit();
		settingsEditor.putInt(s, score);
		settingsEditor.commit();
	}

	public int getTime(String s) {
		return prefs.getInt(s, 0);
	}
}
