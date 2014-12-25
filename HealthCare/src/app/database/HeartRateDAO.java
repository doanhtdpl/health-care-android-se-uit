package app.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.HeartRateDTO;

public class HeartRateDAO extends Database {

	public HeartRateDAO(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertHeartRate(String time,
			String heartRate) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();

			contentValues.put("HeartRateId", this.getNewHeartRateaId());
			contentValues.put("Time", time);
			contentValues.put("HeartRate", heartRate);
			db.insert("HeartRate", null, contentValues);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Integer deleteHeartRate(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("HeartRate", "id = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<HeartRateDTO> getListHeartRate(Integer userId) {
		ArrayList<HeartRateDTO> arrayListHeartRate = new ArrayList<HeartRateDTO>();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from HEARTRATE where UserId = ",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			HeartRateDTO item = new HeartRateDTO();
			item.setHeartRateId(res.getInt(res.getColumnIndex("HeartRateId")));
			item.setTime(res.getString(res.getColumnIndex("Time")));
			item.setHeartRate(res.getInt(res.getColumnIndex("HeartRate")));
			arrayListHeartRate.add(item);
			res.moveToNext();
		}
		return arrayListHeartRate;
	}

	public Integer getNewHeartRateaId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select HeartRateId from HEARTRATE", null);
		if (res!=null){
			return res.getCount() + 1;
		}else 
			return 1;
	}
}