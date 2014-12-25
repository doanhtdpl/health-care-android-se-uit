package app.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.RatioWHRDTO;

public class RatioWHRDAO extends Database {
	public RatioWHRDAO(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertRatioWHR(String time, String ratio, String status) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("RatioWHRId", this.getNewRatioWHRId());
			contentValues.put("Time", time);
			contentValues.put("Ratio", ratio);
			contentValues.put("Status", status);
			db.insert("RATIOWHR", null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Integer deleteRatioWHR(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("RATIOWHR", "id = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<RatioWHRDTO> getListRatioWHR(Integer userId) {
		ArrayList<RatioWHRDTO> arrayListRatioWHR = new ArrayList<RatioWHRDTO>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from RATIOWHR where UserId = ",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			RatioWHRDTO item = new RatioWHRDTO();
			item.setRatioWHRId(res.getInt(res.getColumnIndex("RatioWHRId")));
			item.setTime(res.getString(res.getColumnIndex("Time")));
			item.setRatio(res.getInt(res.getColumnIndex("Ratio")));
			item.setStatus(res.getString(res.getColumnIndex("Status")));
			arrayListRatioWHR.add(item);
			res.moveToNext();
		}
		return arrayListRatioWHR;
	}

	public Integer getNewRatioWHRId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select RatioWHRId from RATIOWHR", null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}
}
