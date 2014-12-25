package app.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.StepRunDTO;

public class StepRunDAO extends Database {
	public StepRunDAO(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertStepRun(String totalTime, String targets, String totalRun) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("StepRunId", this.getNewStepRunId());
			contentValues.put("TotalTime", totalTime);
			contentValues.put("Targets", targets);
			contentValues.put("TotalRun", totalRun);
			db.insert("STEPRUN", null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Integer deleteStepRun(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("STEPRUN", "id = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<StepRunDTO> getListStepRun(Integer userId) {
		ArrayList<StepRunDTO> arrayListStepRun = new ArrayList<StepRunDTO>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from STEPRUN where UserId = ",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			StepRunDTO item = new StepRunDTO();
			item.setStepRunId(res.getInt(res.getColumnIndex("StepRunId")));
			item.setTagets(res.getInt(res.getColumnIndex("Tagets")));
			item.setTotalTime(res.getString(res.getColumnIndex("TotalTime")));
			item.setTotalRun(res.getInt(res.getColumnIndex("TotalRun")));
			arrayListStepRun.add(item);
			res.moveToNext();
		}
		return arrayListStepRun;
	}

	public Integer getNewStepRunId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select StepRunId from STEPRUN", null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}
}