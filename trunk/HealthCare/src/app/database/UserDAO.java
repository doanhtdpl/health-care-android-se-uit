package app.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import app.dto.UserDTO;

public class UserDAO extends Database {

	public static final String USER_TABLE = "USER";
	public static final String COLUMN_USER_ID = "UserId";
	public static final String COLUMN_USER_USERNAME = "Username";
	public static final String COLUMN_USER_TIMESTART = "TimeStart";

	public UserDAO(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	public boolean insertUSER(UserDTO userDTO) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();
			ContentValues contentValues = new ContentValues();
			contentValues.put(COLUMN_USER_ID, this.getNewUserId());
			contentValues.put(COLUMN_USER_USERNAME, userDTO.getUserName());
			contentValues.put(COLUMN_USER_TIMESTART, userDTO.getTimeStrat());
			db.insert(USER_TABLE, null, contentValues);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}

	public Integer deleteUSER(Integer id) {
		SQLiteDatabase db = this.getWritableDatabase();
		return db.delete(USER_TABLE, COLUMN_USER_ID + " = ? ",
				new String[] { Integer.toString(id) });
	}

	public UserDTO getUser(Integer userId) {
		UserDTO user = new UserDTO();
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select * from " + USER_TABLE + " where "
				+ COLUMN_USER_ID + " = ?",
				new String[] { Integer.toString(userId) });
		res.moveToFirst();
		user.setUserId(userId);
		user.setUserName(res.getString(res.getColumnIndex(COLUMN_USER_USERNAME)));
		user.setTimeStrat(res.getString(res
				.getColumnIndex(COLUMN_USER_TIMESTART)));
		return user;
	}

	public Integer getNewUserId() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor res = db.rawQuery("select " + COLUMN_USER_ID + " from "
				+ USER_TABLE, null);
		if (res != null) {
			return res.getCount() + 1;
		} else
			return 1;
	}
}
