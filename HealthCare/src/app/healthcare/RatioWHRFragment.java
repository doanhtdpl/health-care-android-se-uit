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
import app.database.RatioWHRDAO;
import app.database.UserDAO;

public class RatioWHRFragment extends Fragment {

	CheckBox cbMale;
	CheckBox cbFeMale;
	EditText tbxCe;
	EditText tbxCm;
	TextView tbxImpact;
	TextView tbxResult;
	Button btnReinphut;
	Button btnCalculateWHR;
	UserDAO userdao;
	RatioWHRDAO dao;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_ratio_whr,
				container, false);
		
		
		initView(rootView);
		return rootView;
	}

	public void initView(View rootView)
	{
		cbMale = (CheckBox) rootView.findViewById(R.id.cbMale);
		cbMale.setOnCheckedChangeListener(listener);
		cbFeMale = (CheckBox) rootView.findViewById(R.id.cbFeMale);
		cbFeMale.setChecked(true);
		cbMale.setOnCheckedChangeListener(listener);
		tbxCe = (EditText) rootView.findViewById(R.id.tbxCe);
		tbxCm = (EditText) rootView.findViewById(R.id.tbxCm);
		btnCalculateWHR = (Button) rootView.findViewById(R.id.btnCalculateWHR);
		btnCalculateWHR.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("ShowToast")
			public void onClick(View v) {
				if(tbxCe.getText().length()==0){
					Toast.makeText(getActivity(), "Nhập thông số vòng eo", Toast.LENGTH_SHORT).show();
					return;
				}
				if(tbxCm.getText().length()==0){
					Toast.makeText(getActivity(), "Nhập thông số vòng mông", Toast.LENGTH_SHORT).show();
					return;
				}
				calculateWHR();
			}
		});
		btnReinphut = (Button) rootView.findViewById(R.id.btnReinput);
		btnReinphut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				
				reInput();
			}
		});
		tbxResult = (TextView) rootView.findViewById(R.id.tbxResultWHR);
		tbxImpact = (TextView) rootView.findViewById(R.id.tbxImpact);
	}
	protected void calculateWHR() {
		float ce = Float.parseFloat(tbxCe.getText().toString());
		float cm = Float.parseFloat(tbxCm.getText().toString());
		String result;
		float ratioWHR = ce/cm;
		if (cbMale.isChecked())
		{
			if (ratioWHR <= 0.9)
			{
				result = "Sức khỏe tốt";
			}
			else if (ratioWHR>0.9&&ratioWHR<=0.95){
				result = "Ít nguy hiểm";
			}
			else if (ratioWHR>0.95&&ratioWHR<1){
				result = "Mức độ nguy hiểm trung bình";
			}else {
				result = "Rất nguy hiểm";
			}
		}else {
			if (ratioWHR <= 0.7)
			{
				result = "Sức khỏe tốt";
			}
			else if (ratioWHR>0.7&&ratioWHR<=0.8){
				result = "Ít nguy hiểm";
			}
			else if (ratioWHR>0.8&&ratioWHR<0.85){
				result = "Mức độ nguy hiểm trung bình";
			}else {
				result = "Rất nguy hiểm";
			}
		}
		tbxResult.setText(String.valueOf(ratioWHR));
		tbxImpact.setText(result);
	}

	public void reInput() {
		tbxCe.setText("");
		tbxCm.setText("");
		tbxImpact.setText("Mức độ ảnh hưởng");
		tbxResult.setText("WHR");

	}

	private OnCheckedChangeListener listener = new OnCheckedChangeListener() {
		public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
			if (isChecked) {
				switch (arg0.getId()) {
				case R.id.cbFeMale:
					cbFeMale.setChecked(true);
					cbMale.setChecked(false);
					break;
				case R.id.cbMale:
					cbMale.setChecked(true);
					cbFeMale.setChecked(false);
					break;
				}
			}

		}
	};
}
