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
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

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
            public void onClick(View v) {
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
				result = "Thá»ƒ tráº¡ng gáº§y, thiáº¿u nÄƒng lÆ°á»£ng";
				/*result = "CĂ¢n náº·ng tháº¥p (Thá»ƒ tráº¡ng gáº§y, thiáº¿u nÄƒng lÆ°á»£ng trÆ°á»�ng diá»…n):\n"
						+ "Báº¡n á»‘m quĂ¡.\n ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n cá»‘ gáº¯ng Äƒn uá»‘ng 5 - 8 bá»¯a "
						+ "(3 bá»¯a chĂ­nh + 3 - 4 bá»¯a phá»¥)/ ngĂ y.\n"
						+ "Uá»‘ng sá»¯a cao nÄƒng lÆ°á»£ng + nÆ°á»›c 2 lĂ­t/ ngĂ y. Nhá»› táº­p thá»ƒ hĂ¬nh há»£p lĂ½ Ä‘á»ƒ tÄƒng cĂ¢n nhĂ©!\n"
						+ "Quan trá»�ng lĂ  Ä‘á»«ng thá»©c khuya nha.\n"
						+ "ChĂºc báº¡n sá»›m tÄƒng cĂ¢n nhĂ©.";*/
			} else if (ratioBMI == 25) {
				result = "Báº¡n Ä‘ang thá»«a cĂ¢n";
			} else if (ratioBMI >= 18.5 && ratioBMI < 25) {
				result = "ThĂ¢n hĂ¬nh bĂ¬nh thÆ°á»�ng";
				/*result = "BĂ¬nh thÆ°á»�ng:\nBáº¡n cĂ³ chá»‰ sá»‘ cÆ¡ thá»ƒ bĂ¬nh thÆ°á»�ng. ChĂºc má»«ng báº¡n nhĂ©.\n"
						+ "ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n hĂ£y tiáº¿p tá»¥c duy trĂ¬ cháº¿ Ä‘á»™ táº­p luyá»‡n vĂ  Äƒn uá»‘ng hiá»‡n táº¡i."
						+ " Báº¡n hĂ£y táº­p thĂªm cĂ¡c mĂ´n mang tĂ­nh váº­n Ä‘á»™ng cÆ¡ báº¯p náº¿u báº¡n muá»‘n thá»ƒ hĂ¬nh Ä‘áº¹p.\n"
						+ "ChĂºc báº¡n cĂ³ nhiá»�u sá»©c khá»�e nhĂ©.";*/
			} else if (ratioBMI > 25 && ratioBMI < 30) {
				result = "Thá»«a cĂ¢n - Tiá»�n bĂ©o phĂ¬";
				/*result = "Thá»«a cĂ¢n - Tiá»�n bĂ©o phĂ¬:\n"
						+ "Báº¡n Ä‘ang trong giai Ä‘oáº¡n tiá»�n bĂ©o phĂ¬ rá»“i. Ä�á»«ng lo nhĂ©."
						+ " ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n nĂªn káº¿t há»£p táº­p thá»ƒ hĂ¬nh vá»›i viá»‡c cháº¡y bá»™, nháº£y dĂ¢y."
						+ " Má»¥c Ä‘Ă­ch cá»§a viá»‡c nĂ y giĂºp giáº£m má»¡ "
						+ "vĂ  tÄƒng khá»‘i lÆ°á»£ng cÆ¡ báº¯p, giĂºp sÄƒn cháº¯c cÆ¡ báº¯p. "
						+ "Báº¡n cÅ©ng nĂªn Äƒn nhiá»�u rau, háº¡n cháº¿ dáº§u má»¡ vĂ  uá»‘ng nhiá»�u nÆ°á»›c nhĂ©."
						+ "Náº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng "
						+ "(vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n má»¡ nĂªn BMI luĂ´n >=25).";*/
			} else if (ratioBMI >= 30 && ratioBMI < 35) {
				result = "BĂ©o phĂ¬ Ä‘á»™ I";/*:\n"
						+ "Ä�á»«ng buá»“n báº¡n Ă . Báº¡n nĂªn tham mÆ°u BS chuyĂªn khoa Ä‘á»ƒ cĂ³ thá»ƒ giĂºp báº¡n tá»‘t hÆ¡n."
						+ " ChÆ°Æ¡ng trĂ¬nh chá»‰ khuyĂªn báº¡n nĂªn táº­p cháº¡y bá»™, nháº£y dĂ¢y thĂ´i ..."
						+ " NgoĂ i ra, báº¡n nĂªn uá»‘ng nhiá»�u nÆ°á»›c, Äƒn Ä‘á»“ háº¥p luá»™c Ä‘á»ƒ giáº£m bá»›t thĂ¨m Äƒn, Äƒn nhiá»�u rau xanh,"
						+ " hoa quáº£ cĂ³ tĂ­nh chua ..."
						+ "\nNáº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng"
						+ " (vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n má»¡ nĂªn BMI luĂ´n >=25).";*/
			} else if (ratioBMI >= 35 && ratioBMI < 40) {
				result = "BĂ©o phĂ¬ Ä‘á»™ II";/*:\n"
						+ "Ä�á»«ng chĂ¡n náº£n báº¡n Ă . "
						+ "Báº¡n hĂ£y tĂ­ch cá»±c váº­n Ä‘á»™ng thá»ƒ lá»±c nhÆ° Ä‘i bá»™, bÆ¡i lá»™i, náº¿u Ä‘Æ°á»£c, báº¡n cĂ³ thá»ƒ cháº¡y bá»™ ..."
						+ " Náº¿u cĂ³ Ä‘iá»�u kiá»‡n hĂ£y nhá»� BS vĂ  HLV Thá»ƒ thao tÆ° váº¥n thĂªm nhĂ©. "
						+ "Cháº¿ Ä‘á»™ Äƒn nĂªn cáº¯t giáº£m cĂ¡c mĂ³n Äƒn chiĂªn xĂ o, ngá»�t ... "
						+ "Náº¿u dĂ¹ng thuá»‘c giáº£m bĂ©o cÅ©ng nĂªn tham kháº£o Ă½ kiáº¿n BS nhĂ© ..."
						+ "\nNáº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng (vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n má»¡ nĂªn"
						+ " BMI luĂ´n >=25).";*/
			} else {
				result = "BĂ©o phĂ¬ Ä‘á»™ III";/*:\n"
						+ "Cá»‘ lĂªn báº¡n Æ¡i! CĂ³ láº½, BS sáº½ giĂºp báº¡n tá»‘t hÆ¡n."
						+ " ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n nĂªn táº­p luyá»‡n Ä‘i bá»™, bÆ¡i lá»™i ..."
						+ " Báº¡n nĂªn tham mÆ°u HLV Thá»ƒ thao thĂªm nhĂ©. Báº¡n cá»‘ gáº¯ng Äƒn nhiá»�u rau xanh, "
						+ "hoa quáº£ chua, uá»‘ng nhiá»�u nÆ°á»›c, sá»­ dá»¥ng Ä‘á»“ Äƒn háº¥p luá»™c Ä‘á»ƒ no lĂ¢u vĂ  giĂºp giáº£m giĂ¡c "
						+ "thĂ¨m Äƒn ná»¯a Ä‘Ă³ ... "
						+ "\nNáº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng (vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n "
						+ "má»¡ nĂªn BMI luĂ´n >=25).";*/
			}
		} else {
			if (ratioBMI < 18.5) {
				result = "Thá»ƒ tráº¡ng gáº§y, thiáº¿u nÄƒng lÆ°á»£ng";
				/*result = "CĂ¢n náº·ng tháº¥p (Thá»ƒ tráº¡ng gáº§y, thiáº¿u nÄƒng lÆ°á»£ng trÆ°á»�ng diá»…n):\n"
						+ "Báº¡n á»‘m quĂ¡.\n ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n cá»‘ gáº¯ng Äƒn uá»‘ng 5 - 8 bá»¯a "
						+ "(3 bá»¯a chĂ­nh + 3 - 4 bá»¯a phá»¥)/ ngĂ y.\n"
						+ "Uá»‘ng sá»¯a cao nÄƒng lÆ°á»£ng + nÆ°á»›c 2 lĂ­t/ ngĂ y. Nhá»› táº­p thá»ƒ hĂ¬nh há»£p lĂ½ Ä‘á»ƒ tÄƒng cĂ¢n nhĂ©!\n"
						+ "Quan trá»�ng lĂ  Ä‘á»«ng thá»©c khuya nha.\n"
						+ "ChĂºc báº¡n sá»›m tÄƒng cĂ¢n nhĂ©.";*/
			} else if (ratioBMI == 23) {
				result = "Báº¡n Ä‘ang thá»«a cĂ¢n";
			} else if (ratioBMI >= 18.5 && ratioBMI < 23) {
				result = "ThĂ¢n hĂ¬nh bĂ¬nh thÆ°á»�ng";
				/*result = "BĂ¬nh thÆ°á»�ng:\nBáº¡n cĂ³ chá»‰ sá»‘ cÆ¡ thá»ƒ bĂ¬nh thÆ°á»�ng. ChĂºc má»«ng báº¡n nhĂ©.\n"
						+ "ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n hĂ£y tiáº¿p tá»¥c duy trĂ¬ cháº¿ Ä‘á»™ táº­p luyá»‡n vĂ  Äƒn uá»‘ng hiá»‡n táº¡i."
						+ " Báº¡n hĂ£y táº­p thĂªm cĂ¡c mĂ´n mang tĂ­nh váº­n Ä‘á»™ng cÆ¡ báº¯p náº¿u báº¡n muá»‘n thá»ƒ hĂ¬nh Ä‘áº¹p.\n"
						+ "ChĂºc báº¡n cĂ³ nhiá»�u sá»©c khá»�e nhĂ©.";*/
			} else if (ratioBMI > 23 && ratioBMI < 25) {
				result = "Thá»«a cĂ¢n - Tiá»�n bĂ©o phĂ¬";/*:\n"
						+ "Báº¡n Ä‘ang trong giai Ä‘oáº¡n tiá»�n bĂ©o phĂ¬ rá»“i. Ä�á»«ng lo nhĂ©."
						+ " ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n nĂªn káº¿t há»£p táº­p thá»ƒ hĂ¬nh vá»›i viá»‡c cháº¡y bá»™, nháº£y dĂ¢y."
						+ " Má»¥c Ä‘Ă­ch cá»§a viá»‡c nĂ y giĂºp giáº£m má»¡ "
						+ "vĂ  tÄƒng khá»‘i lÆ°á»£ng cÆ¡ báº¯p, giĂºp sÄƒn cháº¯c cÆ¡ báº¯p. "
						+ "Báº¡n cÅ©ng nĂªn Äƒn nhiá»�u rau, háº¡n cháº¿ dáº§u má»¡ vĂ  uá»‘ng nhiá»�u nÆ°á»›c nhĂ©."
						+ "Náº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng "
						+ "(vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n má»¡ nĂªn BMI luĂ´n >=25).";*/
			} else if (ratioBMI >= 25 && ratioBMI < 30) {
				result = "BĂ©o phĂ¬ Ä‘á»™ I";/*:\n"
						+ "Ä�á»«ng buá»“n báº¡n Ă . Báº¡n nĂªn tham mÆ°u BS chuyĂªn khoa Ä‘á»ƒ cĂ³ thá»ƒ giĂºp báº¡n tá»‘t hÆ¡n."
						+ " ChÆ°Æ¡ng trĂ¬nh chá»‰ khuyĂªn báº¡n nĂªn táº­p cháº¡y bá»™, nháº£y dĂ¢y thĂ´i ..."
						+ " NgoĂ i ra, báº¡n nĂªn uá»‘ng nhiá»�u nÆ°á»›c, Äƒn Ä‘á»“ háº¥p luá»™c Ä‘á»ƒ giáº£m bá»›t thĂ¨m Äƒn, Äƒn nhiá»�u rau xanh,"
						+ " hoa quáº£ cĂ³ tĂ­nh chua ..."
						+ "\nNáº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng"
						+ " (vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n má»¡ nĂªn BMI luĂ´n >=25).";*/
			} else if (ratioBMI == 30) {
				result = "BĂ©o phĂ¬ Ä‘á»™ II";/*:\n"
						+ "Ä�á»«ng chĂ¡n náº£n báº¡n Ă . "
						+ "Báº¡n hĂ£y tĂ­ch cá»±c váº­n Ä‘á»™ng thá»ƒ lá»±c nhÆ° Ä‘i bá»™, bÆ¡i lá»™i, náº¿u Ä‘Æ°á»£c, báº¡n cĂ³ thá»ƒ cháº¡y bá»™ ..."
						+ " Náº¿u cĂ³ Ä‘iá»�u kiá»‡n hĂ£y nhá»� BS vĂ  HLV Thá»ƒ thao tÆ° váº¥n thĂªm nhĂ©. "
						+ "Cháº¿ Ä‘á»™ Äƒn nĂªn cáº¯t giáº£m cĂ¡c mĂ³n Äƒn chiĂªn xĂ o, ngá»�t ... "
						+ "Náº¿u dĂ¹ng thuá»‘c giáº£m bĂ©o cÅ©ng nĂªn tham kháº£o Ă½ kiáº¿n BS nhĂ© ..."
						+ "\nNáº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng (vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n má»¡ nĂªn"
						+ " BMI luĂ´n >=25).";*/
			} else {
				result = "BĂ©o phĂ¬ Ä‘á»™ III";/*:\n"
						+ "Cá»‘ lĂªn báº¡n Æ¡i! CĂ³ láº½, BS sáº½ giĂºp báº¡n tá»‘t hÆ¡n."
						+ " ChÆ°Æ¡ng trĂ¬nh khuyĂªn báº¡n nĂªn táº­p luyá»‡n Ä‘i bá»™, bÆ¡i lá»™i ..."
						+ " Báº¡n nĂªn tham mÆ°u HLV Thá»ƒ thao thĂªm nhĂ©. Báº¡n cá»‘ gáº¯ng Äƒn nhiá»�u rau xanh, "
						+ "hoa quáº£ chua, uá»‘ng nhiá»�u nÆ°á»›c, sá»­ dá»¥ng Ä‘á»“ Äƒn háº¥p luá»™c Ä‘á»ƒ no lĂ¢u vĂ  giĂºp giáº£m giĂ¡c "
						+ "thĂ¨m Äƒn ná»¯a Ä‘Ă³ ... "
						+ "\nNáº¿u báº¡n táº­p thá»ƒ hĂ¬nh thĂ¬ bĂ¬nh thÆ°á»�ng (vĂ¬ lĂºc nĂ y khá»‘i lÆ°á»£ng cÆ¡ luĂ´n náº·ng hÆ¡n "
						+ "má»¡ nĂªn BMI luĂ´n >=25).";*/
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
