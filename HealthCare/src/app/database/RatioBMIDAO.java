package app.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.HeartRateDTO;
import app.dto.RatioBMIDTO;

public class RatioBMIDAO extends Database {

	public RatioBMIDAO(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertRatioBMI(String time, String ratio, String status) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put("RatioBMIId", this.getNewRatioBMIId());
			contentValues.put("Time", time);
			contentValues.put("Ratio", ratio);
			contentValues.put("Status", status);
			db.insert("RATIOBMI", null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Integer deleteRatioBMI(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete("RATIOBMI", "id = ? ",
				new String[] { Integer.toString(id) });
	}

	public ArrayList<RatioBMIDTO> getListRatioBMI(Integer userId) {
		ArrayList<RatioBMIDTO> arrayListRatioBMI = new ArrayList();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from RATIOBMI where UserId = ",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		while (res.isAfterLast() == false) {
			RatioBMIDTO item = new RatioBMIDTO();
			item.setRatioBMIId(res.getInt(res.getColumnIndex("RatioBMIId")));
			item.setTime(res.getString(res.getColumnIndex("Time")));
			item.setRatio(res.getInt(res.getColumnIndex("Ratio")));
			item.setStatus(res.getString(res.getColumnIndex("Status")));
			arrayListRatioBMI.add(item);
			res.moveToNext();
		}
		return arrayListRatioBMI;
	}

	public Integer getNewRatioBMIId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select HeartRateId from HEARTRATE", null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}
}
