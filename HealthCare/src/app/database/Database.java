package app.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

	public Database(Context context, String dbName, CursorFactory factory,
			int version) {
		super(context, dbName, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table USER "
				+ "(UserId integer primary key, UserId integer, Username text,TimeStart text)");
		db.execSQL("create table RATIOBMI "
				+ "(RatioBMIId integer primary key, UserId integer, Time text, Ratio text, Status text)");
		db.execSQL("create table RATIOWHR "
				+ "(RatioWHRId integer primary key, UserId integer, Time text, Ratio text, Status text)");
		db.execSQL("create table STEPRUN "
				+ "(StepRunId integer primary key, UserId integer, TotalTime text, Tagets integer, TotalRun integer)");
		db.execSQL("create table HEARTRATE "
				+ "(HeartRateId integer primary key, UserId integer, Time text, HeartRate integer)");
		db.execSQL("create table TIMETABLETAKE "
				+ "(TimeTableTakeId integer primary key, UserId integer, Sick text, Time text, Time text, Status text)");
		db.execSQL("create table TIMETABLETAKEDETAIL "
				+ "(TimeTableTakeDetailId integer primary key, TimeTableTakeId integer, Username text,TimeStart text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS USER");
		db.execSQL("DROP TABLE IF EXISTS RATIOBMI");
		db.execSQL("DROP TABLE IF EXISTS RATIOWHR");
		db.execSQL("DROP TABLE IF EXISTS STEPRUN");
		db.execSQL("DROP TABLE IF EXISTS HEARTRATE");
		db.execSQL("DROP TABLE IF EXISTS TIMETABLETAKE");
		db.execSQL("DROP TABLE IF EXISTS TIMETABLETAKEDETAIL");
		onCreate(db);
	}
}
