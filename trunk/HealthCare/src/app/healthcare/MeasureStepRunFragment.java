package app.healthcare;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MeasureStepRunFragment extends Fragment implements SensorEventListener {

	private SensorManager sensorManager;
	private boolean color = false;
	private TextView tvText;
	private long lastUpdate;
	private Context mContext;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_measure_step_run,
				container, false);
		
		mContext = getActivity();
		
		initView(rootView);
		return rootView;
	}

	private void initView(View view) {
		tvText = (TextView) view.findViewById(R.id.textView);
		tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));

		sensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		lastUpdate = System.currentTimeMillis();
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
			getAccelerometer(event);
		}

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
			Toast.makeText(mContext, "Device was shuffed", Toast.LENGTH_SHORT).show();
			if (color) {
				tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));

			} else {
				tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
			}
			color = !color;
		}
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {

	}

	@Override
	public void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
		sensorManager.registerListener(this,
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onPause() {
		// unregister listener
		super.onPause();
		sensorManager.unregisterListener(this);
	}
}
