package app.healthcare;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MeasureStepRunFragment extends Fragment {

//	private SensorManager sensorManager;
	private TextView tvText;
	private TextView tvcountStep;
	private Button btnStartStepRun;
	private Button btnStopStepRun;
	SharedPreferences prefs;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_measure_step_run,
				container, false);
		
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		initView(rootView);
		return rootView;
	}
	
	@Override
	public void onResume() {
		super.onResume();
		// register this class as a listener for the orientation and
		// accelerometer sensors
//		sensorManager.registerListener(this,
//				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
//				SensorManager.SENSOR_DELAY_NORMAL);
		
		IntentFilter intentFilter = new IntentFilter(Constants.INTENT_GET_BEAT);
		getActivity().registerReceiver(mToturialReceiver, intentFilter);
	}
	
	@Override
	public void onPause() {
		// unregister listener
		super.onPause();
		getActivity().unregisterReceiver(mToturialReceiver);
	}
	
	/**
	 * 
	 */
	private BroadcastReceiver mToturialReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getStringExtra(Constants.ActionReceiver.ACTION_RECEIVER);
			if(action.equals(Constants.ActionReceiver.SHOW_TEXT)) {
				int step = intent.getIntExtra(Constants.ActionReceiver.ACTION_VALUE, 0);
				tvcountStep.setText(step+"");
			}
		}
	};

	private void initView(View view) {
		tvText = (TextView) view.findViewById(R.id.textView);
		tvText.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_bright));
		
		tvcountStep = (TextView) view.findViewById(R.id.tbxCountStepRun);
		tvcountStep.setText(getHighScore()+"");
		
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

	public void setHighScore(int score) {
		SharedPreferences.Editor settingsEditor = prefs.edit();
		settingsEditor.putInt(Constants.KEY_HISCORE, score);
		settingsEditor.commit();
	}

	public int getHighScore() {
		return prefs.getInt(Constants.KEY_HISCORE, 0);
	}
}
