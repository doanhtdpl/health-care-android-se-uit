package app.healthcare;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class RatioWHRFragment extends Fragment {

	CheckBox cbMale;
	CheckBox cbFeMale;
	EditText tbxCe;
	EditText tbxCm;
	TextView tbxImpact;
	TextView tbxResult;
	Button btnReinphut;
	Button btnCalculateBMI;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_ratio_whr);
		cbMale = (CheckBox) findViewById(R.id.cbMale);
		cbMale.setOnCheckedChangeListener(listener);
		cbFeMale = (CheckBox) findViewById(R.id.cbFeMale);
		cbFeMale.setChecked(true);
		cbMale.setOnCheckedChangeListener(listener);
		tbxCe = (EditText) findViewById(R.id.tbxCe);
		tbxCm = (EditText) findViewById(R.id.tbxCm);
		btnCalculateBMI = (Button) findViewById(R.id.btnCalculateBMI);
		btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				calculateWHR();
			}
		});
		btnReinphut = (Button) findViewById(R.id.btnReinput);
		btnReinphut.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				reInput();
			}
		});
		tbxResult = (TextView) findViewById(R.id.tbxResultBMI);
		tbxImpact = (TextView) findViewById(R.id.tbxImpact);
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
				result = "Sá»©c khá»�e tá»‘t";
			}
			else if (ratioWHR>0.9&&ratioWHR<=0.95){
				result = "Ă�t nguy hiá»ƒm";
			}
			else if (ratioWHR>0.95&&ratioWHR<1){
				result = "Má»©c Ä‘á»™ nguy hiá»ƒm trung bĂ¬nh";
			}else {
				result = "Má»©c Ä‘á»™ nguy hiá»ƒm cao";
			}
		}else {
			if (ratioWHR <= 0.7)
			{
				result = "Sá»©c khá»�e tá»‘t";
			}
			else if (ratioWHR>0.7&&ratioWHR<=0.8){
				result = "Ă�t nguy hiá»ƒm";
			}
			else if (ratioWHR>0.8&&ratioWHR<0.85){
				result = "Má»©c Ä‘á»™ nguy hiá»ƒm trung bĂ¬nh";
			}else {
				result = "Má»©c Ä‘á»™ nguy hiá»ƒm cao";
			}
		}
		tbxResult.setText(String.valueOf(ratioWHR));
		tbxImpact.setText(result);
	}

	public void reInput() {
		tbxCe.setText("");
		tbxCm.setText("");
		tbxImpact.setText("Má»©c Ä‘á»™ áº£nh hÆ°á»Ÿng");
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
