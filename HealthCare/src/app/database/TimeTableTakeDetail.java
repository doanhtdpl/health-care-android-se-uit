package app.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import app.dto.TimeTableTakeDetailDTO;

public class TimeTableTakeDetail extends DbConnectionService {
	public static final String TIMETABLETAKEDETAIL_TABLE = "TIMETABLETAKEDETAIL";
	public static final String COLUMN_TIMETABLETAKEDETAIL_ID = "TimeTableTakeDetailId";
	public static final String COLUMN_TIMETABLETAKEDETAIL_TIMETABLETAKE_ID = "TimeTableTakeId";
	public static final String COLUMN_TIMETABLETAKEDETAIL_TIMESTART = "TimeStart";
	public static final String COLUMN_TIMETABLETAKEDETAIL_COUNTTIME = "CountTime";
	public static final String COLUMN_TIMETABLETAKEDETAIL_TIMESPACING = "TimeSpacing";

	public TimeTableTakeDetail(Context context) {
		super(context);
	}

	public boolean insertTimeTableTake(
			TimeTableTakeDetailDTO timeTableTakeDetailDTO) {
		try {
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_TIMETABLETAKEDETAIL_ID,
					this.getNewTimeTableTakeDetailId());
			contentValues.put(COLUMN_TIMETABLETAKEDETAIL_TIMETABLETAKE_ID,
					timeTableTakeDetailDTO.getTimeTableTakeId());
			contentValues.put(COLUMN_TIMETABLETAKEDETAIL_TIMESTART,
					timeTableTakeDetailDTO.getTimeStart());
			contentValues.put(COLUMN_TIMETABLETAKEDETAIL_COUNTTIME,
					timeTableTakeDetailDTO.getCountTime());
			contentValues.put(COLUMN_TIMETABLETAKEDETAIL_TIMESPACING,
					timeTableTakeDetailDTO.getTimeSpacing());
			myDb.insert(TIMETABLETAKEDETAIL_TABLE, null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	private Integer getNewTimeTableTakeDetailId() {
		Cursor res = myDb.rawQuery("select " + COLUMN_TIMETABLETAKEDETAIL_ID
				+ " from " + TIMETABLETAKEDETAIL_TABLE, null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}

	public List<TimeTableTakeDetailDTO> getListTimeTableTakeDetail(
			Integer timeTableTakeId) {
		ArrayList<TimeTableTakeDetailDTO> arrayListtimeTableTakeDetail = new ArrayList<TimeTableTakeDetailDTO>();
		Cursor res = myDb.rawQuery("select * from " + TIMETABLETAKEDETAIL_TABLE
				+ " where " + COLUMN_TIMETABLETAKEDETAIL_TIMETABLETAKE_ID
				+ " = ?", new String[] { Integer.toString(timeTableTakeId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			TimeTableTakeDetailDTO item = new TimeTableTakeDetailDTO();
			item.setTimeTableTakeDetailId(res.getInt(res
					.getColumnIndex(COLUMN_TIMETABLETAKEDETAIL_ID)));
			item.setTimeTableTakeId(timeTableTakeId);
			item.setCountTime(res.getInt(res
					.getColumnIndex(COLUMN_TIMETABLETAKEDETAIL_COUNTTIME)));
			item.setTimeStart(res.getString(res
					.getColumnIndex(COLUMN_TIMETABLETAKEDETAIL_TIMESTART)));
			item.setTimeSpacing(res.getString(res
					.getColumnIndex(COLUMN_TIMETABLETAKEDETAIL_TIMESPACING)));
			arrayListtimeTableTakeDetail.add(item);
			res.moveToNext();
		}
		return arrayListtimeTableTakeDetail;
	}

	public Integer deleteTimeTableTake(Integer id) {
		return myDb.delete(TIMETABLETAKEDETAIL_TABLE,
				COLUMN_TIMETABLETAKEDETAIL_TIMETABLETAKE_ID + " = ? ",
				new String[] { Integer.toString(id) });
	}
}
