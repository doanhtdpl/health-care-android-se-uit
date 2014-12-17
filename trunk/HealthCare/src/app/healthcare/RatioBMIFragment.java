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

public class RatioBMI extends Activity {
	CheckBox cbWHO;
	CheckBox cbIDIAndWPRO;
	EditText tbxHeight;
	EditText tbxWeight;
	TextView tbxImpact;
	TextView tbxResult;
	
	Button btnReinphut;
	Button btnCalculateBMI;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_ratio_bmi);
		cbIDIAndWPRO = (CheckBox) findViewById(R.id.cbIDIAndWPRO);
		cbIDIAndWPRO.setOnCheckedChangeListener(listener);
		cbWHO = (CheckBox) findViewById(R.id.cbWHO);
		cbWHO.setChecked(true);
		cbWHO.setOnCheckedChangeListener(listener);
		tbxHeight = (EditText) findViewById(R.id.tbxHeight);
		tbxWeight = (EditText) findViewById(R.id.tbxWeight);
		btnCalculateBMI = (Button) findViewById(R.id.btnCalculateBMI);
		btnCalculateBMI.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	calculateBMI();
            }
        });
		btnReinphut = (Button) findViewById(R.id.btnReinput);
		btnReinphut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            }
        });
		tbxResult = (TextView)findViewById(R.id.tbxResultBMI);
		tbxImpact = (TextView)findViewById(R.id.tbxImpact);
	}

	public void reInput(){
		tbxHeight.setText("");
		tbxWeight.setText("");
		tbxImpact.setText("Mức độ ảnh hưởng"); 
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
				/*result = "Cân nặng thấp (Thể trạng gầy, thiếu năng lượng trường diễn):\n"
						+ "Bạn ốm quá.\n Chương trình khuyên bạn cố gắng ăn uống 5 - 8 bữa "
						+ "(3 bữa chính + 3 - 4 bữa phụ)/ ngày.\n"
						+ "Uống sữa cao năng lượng + nước 2 lít/ ngày. Nhớ tập thể hình hợp lý để tăng cân nhé!\n"
						+ "Quan trọng là đừng thức khuya nha.\n"
						+ "Chúc bạn sớm tăng cân nhé.";*/
			} else if (ratioBMI == 25) {
				result = "Bạn đang thừa cân";
			} else if (ratioBMI >= 18.5 && ratioBMI < 25) {
				result = "Thân hình bình thường";
				/*result = "Bình thường:\nBạn có chỉ số cơ thể bình thường. Chúc mừng bạn nhé.\n"
						+ "Chương trình khuyên bạn hãy tiếp tục duy trì chế độ tập luyện và ăn uống hiện tại."
						+ " Bạn hãy tập thêm các môn mang tính vận động cơ bắp nếu bạn muốn thể hình đẹp.\n"
						+ "Chúc bạn có nhiều sức khỏe nhé.";*/
			} else if (ratioBMI > 25 && ratioBMI < 30) {
				result = "Thừa cân - Tiền béo phì";
				/*result = "Thừa cân - Tiền béo phì:\n"
						+ "Bạn đang trong giai đoạn tiền béo phì rồi. Đừng lo nhé."
						+ " Chương trình khuyên bạn nên kết hợp tập thể hình với việc chạy bộ, nhảy dây."
						+ " Mục đích của việc này giúp giảm mỡ "
						+ "và tăng khối lượng cơ bắp, giúp săn chắc cơ bắp. "
						+ "Bạn cũng nên ăn nhiều rau, hạn chế dầu mỡ và uống nhiều nước nhé."
						+ "Nếu bạn tập thể hình thì bình thường "
						+ "(vì lúc này khối lượng cơ luôn nặng hơn mỡ nên BMI luôn >=25).";*/
			} else if (ratioBMI >= 30 && ratioBMI < 35) {
				result = "Béo phì độ I";/*:\n"
						+ "Đừng buồn bạn à. Bạn nên tham mưu BS chuyên khoa để có thể giúp bạn tốt hơn."
						+ " Chương trình chỉ khuyên bạn nên tập chạy bộ, nhảy dây thôi ..."
						+ " Ngoài ra, bạn nên uống nhiều nước, ăn đồ hấp luộc để giảm bớt thèm ăn, ăn nhiều rau xanh,"
						+ " hoa quả có tính chua ..."
						+ "\nNếu bạn tập thể hình thì bình thường"
						+ " (vì lúc này khối lượng cơ luôn nặng hơn mỡ nên BMI luôn >=25).";*/
			} else if (ratioBMI >= 35 && ratioBMI < 40) {
				result = "Béo phì độ II";/*:\n"
						+ "Đừng chán nản bạn à. "
						+ "Bạn hãy tích cực vận động thể lực như đi bộ, bơi lội, nếu được, bạn có thể chạy bộ ..."
						+ " Nếu có điều kiện hãy nhờ BS và HLV Thể thao tư vấn thêm nhé. "
						+ "Chế độ ăn nên cắt giảm các món ăn chiên xào, ngọt ... "
						+ "Nếu dùng thuốc giảm béo cũng nên tham khảo ý kiến BS nhé ..."
						+ "\nNếu bạn tập thể hình thì bình thường (vì lúc này khối lượng cơ luôn nặng hơn mỡ nên"
						+ " BMI luôn >=25).";*/
			} else {
				result = "Béo phì độ III";/*:\n"
						+ "Cố lên bạn ơi! Có lẽ, BS sẽ giúp bạn tốt hơn."
						+ " Chương trình khuyên bạn nên tập luyện đi bộ, bơi lội ..."
						+ " Bạn nên tham mưu HLV Thể thao thêm nhé. Bạn cố gắng ăn nhiều rau xanh, "
						+ "hoa quả chua, uống nhiều nước, sử dụng đồ ăn hấp luộc để no lâu và giúp giảm giác "
						+ "thèm ăn nữa đó ... "
						+ "\nNếu bạn tập thể hình thì bình thường (vì lúc này khối lượng cơ luôn nặng hơn "
						+ "mỡ nên BMI luôn >=25).";*/
			}
		} else {
			if (ratioBMI < 18.5) {
				result = "Thể trạng gầy, thiếu năng lượng";
				/*result = "Cân nặng thấp (Thể trạng gầy, thiếu năng lượng trường diễn):\n"
						+ "Bạn ốm quá.\n Chương trình khuyên bạn cố gắng ăn uống 5 - 8 bữa "
						+ "(3 bữa chính + 3 - 4 bữa phụ)/ ngày.\n"
						+ "Uống sữa cao năng lượng + nước 2 lít/ ngày. Nhớ tập thể hình hợp lý để tăng cân nhé!\n"
						+ "Quan trọng là đừng thức khuya nha.\n"
						+ "Chúc bạn sớm tăng cân nhé.";*/
			} else if (ratioBMI == 23) {
				result = "Bạn đang thừa cân";
			} else if (ratioBMI >= 18.5 && ratioBMI < 23) {
				result = "Thân hình bình thường";
				/*result = "Bình thường:\nBạn có chỉ số cơ thể bình thường. Chúc mừng bạn nhé.\n"
						+ "Chương trình khuyên bạn hãy tiếp tục duy trì chế độ tập luyện và ăn uống hiện tại."
						+ " Bạn hãy tập thêm các môn mang tính vận động cơ bắp nếu bạn muốn thể hình đẹp.\n"
						+ "Chúc bạn có nhiều sức khỏe nhé.";*/
			} else if (ratioBMI > 23 && ratioBMI < 25) {
				result = "Thừa cân - Tiền béo phì";/*:\n"
						+ "Bạn đang trong giai đoạn tiền béo phì rồi. Đừng lo nhé."
						+ " Chương trình khuyên bạn nên kết hợp tập thể hình với việc chạy bộ, nhảy dây."
						+ " Mục đích của việc này giúp giảm mỡ "
						+ "và tăng khối lượng cơ bắp, giúp săn chắc cơ bắp. "
						+ "Bạn cũng nên ăn nhiều rau, hạn chế dầu mỡ và uống nhiều nước nhé."
						+ "Nếu bạn tập thể hình thì bình thường "
						+ "(vì lúc này khối lượng cơ luôn nặng hơn mỡ nên BMI luôn >=25).";*/
			} else if (ratioBMI >= 25 && ratioBMI < 30) {
				result = "Béo phì độ I";/*:\n"
						+ "Đừng buồn bạn à. Bạn nên tham mưu BS chuyên khoa để có thể giúp bạn tốt hơn."
						+ " Chương trình chỉ khuyên bạn nên tập chạy bộ, nhảy dây thôi ..."
						+ " Ngoài ra, bạn nên uống nhiều nước, ăn đồ hấp luộc để giảm bớt thèm ăn, ăn nhiều rau xanh,"
						+ " hoa quả có tính chua ..."
						+ "\nNếu bạn tập thể hình thì bình thường"
						+ " (vì lúc này khối lượng cơ luôn nặng hơn mỡ nên BMI luôn >=25).";*/
			} else if (ratioBMI == 30) {
				result = "Béo phì độ II";/*:\n"
						+ "Đừng chán nản bạn à. "
						+ "Bạn hãy tích cực vận động thể lực như đi bộ, bơi lội, nếu được, bạn có thể chạy bộ ..."
						+ " Nếu có điều kiện hãy nhờ BS và HLV Thể thao tư vấn thêm nhé. "
						+ "Chế độ ăn nên cắt giảm các món ăn chiên xào, ngọt ... "
						+ "Nếu dùng thuốc giảm béo cũng nên tham khảo ý kiến BS nhé ..."
						+ "\nNếu bạn tập thể hình thì bình thường (vì lúc này khối lượng cơ luôn nặng hơn mỡ nên"
						+ " BMI luôn >=25).";*/
			} else {
				result = "Béo phì độ III";/*:\n"
						+ "Cố lên bạn ơi! Có lẽ, BS sẽ giúp bạn tốt hơn."
						+ " Chương trình khuyên bạn nên tập luyện đi bộ, bơi lội ..."
						+ " Bạn nên tham mưu HLV Thể thao thêm nhé. Bạn cố gắng ăn nhiều rau xanh, "
						+ "hoa quả chua, uống nhiều nước, sử dụng đồ ăn hấp luộc để no lâu và giúp giảm giác "
						+ "thèm ăn nữa đó ... "
						+ "\nNếu bạn tập thể hình thì bình thường (vì lúc này khối lượng cơ luôn nặng hơn "
						+ "mỡ nên BMI luôn >=25).";*/
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
