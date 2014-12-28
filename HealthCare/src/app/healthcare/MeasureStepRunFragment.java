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

public class MeasureStepRunFragment extends Fragment {

	private TextView tvcountStep;
	private TextView tvYesterdayStep;
	private TextView tvChiTieu;
	private Button btnStartStepRun;
	private Button btnStopStepRun;
	private Button btnResetStepRun;
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
			String action = intent
					.getStringExtra(Constants.ActionReceiver.ACTION_RECEIVER);
			if (action.equals(Constants.ActionReceiver.SHOW_TEXT)) {
				int step = intent.getIntExtra(
						Constants.ActionReceiver.ACTION_VALUE, 0);
				tvcountStep.setText(step + "");
			}
		}
	};

	private void initView(View view) {

		tvcountStep = (TextView) view.findViewById(R.id.tbxCountStepRun);
		tvcountStep.setText(getHighScore() + "");

		tvYesterdayStep = (TextView) view.findViewById(R.id.tbxYesterday);
		tvYesterdayStep.setText(String.valueOf(1000));
		tvChiTieu = (TextView) view.findViewById(R.id.tbxChiTieu);
		tvChiTieu.setText(String.valueOf(Constants.getInstance().TARGETS));

		btnStartStepRun = (Button) view.findViewById(R.id.btnStartService);
		btnStartStepRun.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				getActivity().startService(
						new Intent(getActivity(), StepRunServices.class));
				btnStartStepRun.setVisibility(View.INVISIBLE);
				btnResetStepRun.setVisibility(View.VISIBLE);
				btnStopStepRun.setVisibility(View.VISIBLE);
			}
		});
		btnStopStepRun = (Button) view.findViewById(R.id.btnStopService);
		btnStopStepRun.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View view) {
				getActivity().stopService(
						new Intent(getActivity(), StepRunServices.class));
				btnStartStepRun.setVisibility(View.VISIBLE);
				btnResetStepRun.setVisibility(View.VISIBLE);
				btnStopStepRun.setVisibility(View.INVISIBLE);
			}
		});
		btnResetStepRun = (Button) view.findViewById(R.id.btnReset);
		btnResetStepRun.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				getActivity().stopService(
						new Intent(getActivity(), StepRunServices.class));
				setHighScore(-1);
				getActivity().startService(
						new Intent(getActivity(), StepRunServices.class));
				btnStartStepRun.setVisibility(View.VISIBLE);
				btnResetStepRun.setVisibility(View.VISIBLE);
				btnStopStepRun.setVisibility(View.VISIBLE);
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
