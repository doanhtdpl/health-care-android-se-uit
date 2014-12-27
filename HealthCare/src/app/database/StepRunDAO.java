package app.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.StepRunDTO;

public class StepRunDAO extends Database {
	public static final String STEPRUN_TABLE = "STEPRUN";
	public static final String COLUMN_STEPRUN_ID = "StepRunId";
	public static final String COLUMN_STEPRUN_USER_ID = "UserId";
	public static final String COLUMN_STEPRUN_TOTAL_TIME = "TotalTime";
	public static final String COLUMN_STEPRUN_TAGETS = "Tagets";
	public static final String COLUMN_STEPRUN_TOTAL_RUN = "TotalRun";

	public StepRunDAO(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertStepRun(StepRunDTO stepRunDTO) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_STEPRUN_ID, this.getNewStepRunId());
			contentValues.put(COLUMN_STEPRUN_USER_ID, stepRunDTO.getUserId());
			contentValues.put(COLUMN_STEPRUN_TOTAL_TIME,
					stepRunDTO.getTotalTime());
			contentValues.put(COLUMN_STEPRUN_TAGETS, stepRunDTO.getTagets());
			contentValues.put(COLUMN_STEPRUN_TOTAL_RUN,
					stepRunDTO.getTotalRun());
			db.insert(STEPRUN_TABLE, null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Integer deleteStepRun(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(STEPRUN_TABLE, COLUMN_STEPRUN_ID + " = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<StepRunDTO> getListStepRun(Integer userId) {
		ArrayList<StepRunDTO> arrayListStepRun = new ArrayList<StepRunDTO>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + STEPRUN_TABLE + " where "
				+ COLUMN_STEPRUN_USER_ID + " = ?",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			StepRunDTO item = new StepRunDTO();
			item.setStepRunId(res.getInt(res.getColumnIndex(COLUMN_STEPRUN_ID)));
			item.setUserId(userId);
			item.setTagets(res.getInt(res.getColumnIndex(COLUMN_STEPRUN_TAGETS)));
			item.setTotalTime(res.getString(res
					.getColumnIndex(COLUMN_STEPRUN_TOTAL_TIME)));
			item.setTotalRun(res.getInt(res
					.getColumnIndex(COLUMN_STEPRUN_TOTAL_RUN)));
			arrayListStepRun.add(item);
			res.moveToNext();
		}
		return arrayListStepRun;
	}

	public Integer getNewStepRunId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select " + COLUMN_STEPRUN_ID + " from "
				+ STEPRUN_TABLE, null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}
}