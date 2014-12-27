package app.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.TimeTableTakeDTO;

public class TimeTableTakeDAO extends Database {

	public static final String TIMETABLETAKE_TABLE = "TIMETABLETAKE";
	public static final String COLUMN_TIMETABLETAKE_ID = "TimeTableTakeId";
	public static final String COLUMN_TIMETABLETAKE_USER_ID = "UserId";
	public static final String COLUMN_TIMETABLETAKE_SICK = "Sick";
	public static final String COLUMN_TIMETABLETAKE_TIME = "Time";
	public static final String COLUMN_TIMETABLETAKE_STATUS = "Status";

	public TimeTableTakeDAO(Context context, String dbName,
			CursorFactory factory, int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertTimeTableTake(TimeTableTakeDTO timeTableTakeDTO) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_TIMETABLETAKE_ID, this.getNewTimeTableTakeId());
			contentValues.put(COLUMN_TIMETABLETAKE_USER_ID,
					timeTableTakeDTO.getUserId());
			contentValues.put(COLUMN_TIMETABLETAKE_SICK,
					timeTableTakeDTO.getSick());
			contentValues.put(COLUMN_TIMETABLETAKE_TIME,
					timeTableTakeDTO.getTime());
			contentValues.put(COLUMN_TIMETABLETAKE_STATUS,
					timeTableTakeDTO.getStatus());
			db.insert(TIMETABLETAKE_TABLE, null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Integer deleteTimeTableTake(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(TIMETABLETAKE_TABLE,
				COLUMN_TIMETABLETAKE_ID + " = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<TimeTableTakeDTO> getListTimeTableTake(Integer userId) {
		ArrayList<TimeTableTakeDTO> arrayListTimeTableTake = new ArrayList<TimeTableTakeDTO>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + TIMETABLETAKE_TABLE
				+ " where " + COLUMN_TIMETABLETAKE_USER_ID + " = ?",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			TimeTableTakeDTO item = new TimeTableTakeDTO();
			item.setTimeTableTakeId(res.getInt(res
					.getColumnIndex(COLUMN_TIMETABLETAKE_ID)));
			item.setUserId(userId);
			item.setSick(res.getString(res
					.getColumnIndex(COLUMN_TIMETABLETAKE_SICK)));
			item.setTime(res.getString(res
					.getColumnIndex(COLUMN_TIMETABLETAKE_TIME)));
			item.setStatus(res.getString(res
					.getColumnIndex(COLUMN_TIMETABLETAKE_STATUS)));
			arrayListTimeTableTake.add(item);
			res.moveToNext();
		}
		return arrayListTimeTableTake;
	}

	public Integer getNewTimeTableTakeId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select " + COLUMN_TIMETABLETAKE_ID + " from "
				+ TIMETABLETAKE_TABLE, null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}
}
