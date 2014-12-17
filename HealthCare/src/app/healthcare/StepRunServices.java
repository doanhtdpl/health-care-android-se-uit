package app.healthcare;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

public class StepRunServices extends Service implements SensorEventListener{
	private SensorManager sensorManager;
	private boolean color = false;
	private long lastUpdate;
	public int step=0;
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate(){
		super.onCreate();
		
		
	}
	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		lastUpdate = System.currentTimeMillis();
		return startId;
	}
	@Override
	public void onDestroy(){
		super.onDestroy();
		sensorManager.unregisterListener(this);
	}
	
	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void onSensorChanged(SensorEvent arg0) {
		// TODO Auto-generated method stub
		getAccelerometer(arg0);
		
	}
	private void getAccelerometer(SensorEvent event) {
		float[] values = event.values;
		// Movement
		float x = values[0];
		float y = values[1];
		float z = values[2];

		float accelationSquareRoot = (x * x + y * y + z * z)
				/ (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
		long actualTime = System.currentTimeMillis();
		if (accelationSquareRoot >= 2) //
		{
			if (actualTime - lastUpdate < 200) {
				return;
			}
			lastUpdate = actualTime;
			
			if (color) {
				//tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
				Toast.makeText(this, "zo roi ne", Toast.LENGTH_SHORT).show();
			} else {
				//tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
				step+=1;
				//tvcountStep.setText(String.valueOf(step));
				Toast.makeText(this, "aaaaaaaaaaaanua roi ne", Toast.LENGTH_SHORT).show();
			}
			color = !color;
		}
	}
}
