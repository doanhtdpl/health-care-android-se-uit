package app.healthcare;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import app.database.TimeTableTakeDAO;
import app.database.UserDAO;
import app.dto.TimeTableTakeDTO;
import app.dto.UserDTO;

public class TimeTableTakeFragment extends Fragment {
	public TableLayout tableData;
	public CheckBox cbFinish;
	public CheckBox cbNotFinish;
	public EditText tbxSickName;
	public EditText tbxDateSick;
	public EditText tbxCountTime;
	public EditText tbxTimeSpacing;
	public EditText tbxStatus;
	public UserDAO userDao;
	public Button btnSave;
	public TimeTableTakeDAO timeTableTakeDao;
	SharedPreferences prefs;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_time_table_take,
				container, false);
		initView(rootView);
		return rootView;
	}

	@Override
	public void onDestroy() {
		getActivity().stopService(
				new Intent(getActivity(), TimeTableTakeService.class));
		super.onDestroy();
	}

	private void initView(View rootView) {
		prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
		tableData = (TableLayout) rootView
				.findViewById(R.id.tableDataTimeTable);
		tbxCountTime = (EditText) rootView.findViewById(R.id.tbxCountTime);
		tbxDateSick = (EditText) rootView.findViewById(R.id.tbxDateSick);
		tbxSickName = (EditText) rootView.findViewById(R.id.tbxSick);
		tbxTimeSpacing = (EditText) rootView.findViewById(R.id.tbxTimeSpacing);
		userDao = new UserDAO(getActivity());
		timeTableTakeDao = new TimeTableTakeDAO(getActivity());
		tbxStatus = (EditText) rootView.findViewById(R.id.tbxStatus);
		btnSave = (Button) rootView.findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {
			@SuppressLint("ShowToast")
			@Override
			public void onClick(View v) {
				if (tbxCountTime.getText().length() == 0) {
					Toast.makeText(getActivity(), "Bạn phải nhập đủ thông số",
							Toast.LENGTH_SHORT).show();
				} else if (tbxDateSick.getText().length() == 0) {
					Toast.makeText(getActivity(), "Bạn phải nhập đủ thông số",
							Toast.LENGTH_SHORT).show();
				} else if (tbxSickName.getText().length() == 0) {
					Toast.makeText(getActivity(), "Bạn phải nhập đủ thông số",
							Toast.LENGTH_SHORT).show();
				} else if (tbxStatus.getText().length() == 0) {
					Toast.makeText(getActivity(), "Bạn phải nhập đủ thông số",
							Toast.LENGTH_SHORT).show();
				} else if (tbxTimeSpacing.getText().length() == 0) {
					Toast.makeText(getActivity(), "Bạn phải nhập đủ thông số",
							Toast.LENGTH_SHORT).show();
				}
				save();
				getActivity().startService(
						new Intent(getActivity(), TimeTableTakeService.class));
			}
		});
		buildTableData();
	}

	public void buildTableData() {
		UserDTO userdto = userDao.getUser();
		List<TimeTableTakeDTO> listdata = timeTableTakeDao
				.getListTimeTableTake(userdto.getUserId());
		int rows = listdata.size();
		int cols = 3;
		for (int i = 0; i < rows; i++) {
			TableRow row = new TableRow(getActivity());
			row.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			for (int j = 1; j < cols; j++) {
				TextView tv = new TextView(getActivity());
				tv.setTextColor(Color.YELLOW);
				if (j == 0) {
					tv.setGravity(Gravity.LEFT);
					tv.setText(listdata.get(i).getTimeTableTakeId());
				} else if (j == 1) {
					tv.setGravity(Gravity.LEFT);
					tv.setText(listdata.get(i).getTime());
				} else if (j == 2) {
					tv.setGravity(Gravity.CENTER);
					tv.setText(listdata.get(i).getSick());
				} else if (j == 3) {
					tv.setGravity(Gravity.RIGHT);
					tv.setText(listdata.get(i).getStatus());
				}
				final int id = listdata.get(i).getTimeTableTakeId();
				row.addView(tv);
				row.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						getDetail(id);
					}
				});
			}
			tableData.addView(row);
		}
	}

	public void getDetail(int id) {
		TimeTableTakeDTO dto = timeTableTakeDao.getTimeTableTake(id);
		tbxSickName.setText(dto.getSick());
		tbxDateSick.setText(dto.getTime());
		tbxCountTime.setText(dto.getCountTime() + "");
		tbxTimeSpacing.setText(dto.getTimeSpacing());
		tbxStatus.setText(dto.getStatus());
	}

	public void save() {
		TimeTableTakeDTO dto = new TimeTableTakeDTO();
		int countTime = Integer.parseInt(tbxCountTime.getText().toString());
		dto.setCountTime(countTime);
		dto.setSick(tbxSickName.getText().toString());
		dto.setStatus(tbxStatus.getText().toString());
		dto.setTime(tbxDateSick.getText().toString());
		dto.setTimeSpacing(tbxTimeSpacing.getText().toString());
		int id = userDao.getUser().getUserId();
		dto.setUserId(id);
		timeTableTakeDao.insertTimeTableTake(dto);
		Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT)
				.show();
		int hour = Constants.getInstance().getTime().hour;
		setTime(countTime, Constants.TIME_COUNT);
		setTime(hour + Integer.parseInt(tbxTimeSpacing.getText().toString()), Constants.CHECK_TIME);
	}

	public void setTime(int score, String s) {

		SharedPreferences.Editor settingsEditor = prefs.edit();
		settingsEditor.putInt(s, score);
		settingsEditor.commit();
	}

	public int getTime(String s) {
		return prefs.getInt(s, 0);
	}
}