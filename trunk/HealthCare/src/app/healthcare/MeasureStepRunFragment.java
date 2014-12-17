package app.healthcare;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MeasureStepRunFragment extends Fragment {

//	private SensorManager sensorManager;
	private TextView tvText;
	private TextView tvcountStep;
	private Button btnStartStepRun;
	private Button btnStopStepRun;
	private int step=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_measure_step_run,
				container, false);
		
		
		initView(rootView);
		return rootView;
	}

	private void initView(View view) {
		tvText = (TextView) view.findViewById(R.id.textView);
		tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		tvcountStep = (TextView) view.findViewById(R.id.tbxCountStepRun);
		
		tvcountStep.setText(String.valueOf(step));
		btnStartStepRun = (Button) view.findViewById(R.id.btnStartService);
		btnStartStepRun.setOnClickListener(new View.OnClickListener() {

			   @Override
			   public void onClick(View view) {
				   getActivity().startService(new Intent(getActivity(), StepRunServices.class));
			   }
			});
		btnStopStepRun = (Button) view.findViewById(R.id.btnStopService);
		btnStopStepRun.setOnClickListener(new View.OnClickListener() {

			   @Override
			   public void onClick(View view) {
				   getActivity().stopService(new Intent(getActivity(), StepRunServices.class));
			   }
			});
		
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
