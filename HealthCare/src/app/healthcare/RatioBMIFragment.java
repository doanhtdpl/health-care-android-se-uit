package app.healthcare;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RatioBMIFragment extends Fragment {
	CheckBox cbWHO;
	CheckBox cbIDIAndWPRO;
	EditText tbxHeight;
	EditText tbxWeight;
	TextView tbxImpact;
	TextView tbxResult;
	
	Button btnReinphut;
	Button btnCalculateBMI;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ratio_bmi,
				container, false);
		
		
		initView(rootView);
		
		
		return rootView;
	}
	
	private void initView(View rootView) {
		cbIDIAndWPRO = (CheckBox) rootView.findViewById(R.id.cbIDIAndWPRO);
		cbIDIAndWPRO.setOnCheckedChangeListener(listener);
		cbWHO = (CheckBox) rootView.findViewById(R.id.cbWHO);
		cbWHO.setChecked(true);
		cbWHO.setOnCheckedChangeListener(listener);
		tbxHeight = (EditText) rootView.findViewById(R.id.tbxHeight);
		tbxWeight = (EditText) rootView.findViewById(R.id.tbxWeight);
		btnCalculateBMI = (Button) rootView.findViewById(R.id.btnCalculateBMI);
		btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ShowToast")
			public void onClick(View v) {
            	if(tbxHeight.getText().length()==0){
					Toast.makeText(getActivity(), "Nhập thông số Chiều cao", Toast.LENGTH_SHORT).show();
				return;
				}
            	if(tbxWeight.getText().length()==0){
					Toast.makeText(getActivity(), "Nhập thông số cân nặng", Toast.LENGTH_SHORT).show();
					return;
				}
            	calculateBMI();
            }
        });
		btnReinphut = (Button) rootView.findViewById(R.id.btnReinput);
		btnReinphut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
		tbxResult = (TextView)rootView.findViewById(R.id.tbxResultBMI);
		tbxImpact = (TextView)rootView.findViewById(R.id.tbxImpact);

	}

	public void reInput(){
		tbxHeight.setText("");
		tbxWeight.setText("");
		tbxImpact.setText("Má»©c Ä‘á»™ áº£nh hÆ°á»Ÿng"); 
		tbxResult.setText("BMI");
		
	}
	
	public void calculateBMI() {
		float height = Float.parseFloat(tbxHeight.getText().toString());
		float weight = Float.parseFloat(tbxWeight.getText().toString());
		float ratioBMI = weight / (height * height);
		String result;
		if (cbWHO.isChecked()) {
			if (ratioBMI < 18.5) {
				result = "Thể trạng gầy, thiếu năng lượng";
				
			} else if (ratioBMI == 25) {
				result = "Bạn đang thừa cân";
			} else if (ratioBMI >= 18.5 && ratioBMI < 25) {
				result = "Thân hình bình thường";
				
			} else if (ratioBMI > 25 && ratioBMI < 30) {
				result = "Thừa cân - tiền béo phì";
				
			} else if (ratioBMI >= 30 && ratioBMI < 35) {
				result = "Béo phì cấp độ 1";
				
			} else if (ratioBMI >= 35 && ratioBMI < 40) {
				result = "Béo phì cấp độ 2";
						
			} else {
				result = "Béo phì cấp độ 3";
			}
		} else {
			if (ratioBMI < 18.5) {
				result = "Thể trạng gầy, thiếu năng lượng";
			} else if (ratioBMI == 23) {
				result = "Bạn đang thừa cân";
			} else if (ratioBMI >= 18.5 && ratioBMI < 23) {
				result = "Thân hình bình thường";
				
			} else if (ratioBMI > 23 && ratioBMI < 25) {
				result = "Thừa cân - tiền béo phì";
			} else if (ratioBMI >= 25 && ratioBMI < 30) {
				result = "Béo phì cấp độ 1";
			} else if (ratioBMI == 30) {
				result = "Béo phì cấp độ 2";
			} else {
				result = "Béo phì cấp độ 3";
			}
		}
		tbxResult.setText(String.valueOf(ratioBMI));
		tbxImpact.setText(result);
	}

	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
			if (isChecked) {
				switch (arg0.getId()) {
				case R.id.cbIDIAndWPRO:
					cbIDIAndWPRO.setChecked(true);
					cbWHO.setChecked(false);
					break;
				case R.id.cbWHO:
					cbWHO.setChecked(true);
					cbIDIAndWPRO.setChecked(false);
					break;
				}
			}
		}
	};
}
