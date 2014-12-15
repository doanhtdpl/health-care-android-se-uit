package app.healthcare;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MeasureStepRunFragment extends Fragment {
//implements SensorEventListener {

//	private SensorManager sensorManager;
	private boolean color = false;
	private TextView tvText;
	private long lastUpdate;
	private Context mContext;
	private TextView tvcountStep;
	private int step=0;
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
		tvcountStep = (TextView) view.findViewById(R.id.tbxCountStepRun);

		tvcountStep.setText(String.valueOf(step));
		getActivity().startService(new Intent(getActivity(), StepRunServices.class));
		
	}


	@Override
	public void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
//		sensorManager.registerListener(this,
//				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//				SensorManager.SENSOR_DELAY_NORMAL);
	}
	
	@Override
	public void onPause() {
		// unregister listener
		super.onPause();
	
	}
}
