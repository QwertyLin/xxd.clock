package cn.xxd.clock.bean;

import java.util.ArrayList;
import java.util.List;

import cn.xxd.clock.bean.Clock.Week;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import q.util.SqliteBase;

public class ClockDb extends SqliteBase<Clock> {

	public ClockDb(Context context, String dbName, String tableName) {
		super(context, "clock", "clock");
	}

	@Override
	protected String getSqlCreateTable() {
		StringBuffer sb = new StringBuffer("CREATE TABLE clock (");
		sb.append("id LONG PRIMARY KEY,");
		sb.append("cycle INTEGER,");
		sb.append("year INTEGER,");
		sb.append("month INTEGER,");
		sb.append("day INTEGER,");
		sb.append("hour INTEGER,");
		sb.append("min INTEGER,");
		sb.append("week TEXT)");
		return sb.toString();
	}

	@Override
	protected ContentValues buildContentValues(Clock e) {
		ContentValues cv = new ContentValues();
		cv.put("cycle", e.getCycle().getId());
		cv.put("year", e.getYear());
		cv.put("month", e.getMonth());
		cv.put("day", e.getDay());
		cv.put("hour", e.getHour());
		cv.put("min", e.getMin());
		StringBuffer week = new StringBuffer();
		for(Week w : e.getWeek()){
			week.append(w.getId() + ",");
		}
		cv.put("week", week.toString());
		return cv;
	}

	@Override
	protected Clock buildEntity(Cursor cs) {
		Clock c = new Clock();
		c.setId(cs.getLong(0));
		c.setCycle(Clock.Cycle.SINGLE.getById(cs.getInt(1)));
		c.setYear(cs.getInt(2));
		c.setMonth(cs.getInt(3));
		c.setDay(cs.getInt(4));
		c.setHour(cs.getInt(5));
		c.setMin(cs.getInt(6));
		String weekStr = cs.getString(7);
		List<Week> weeks = new ArrayList<Clock.Week>();
		for(String week:weekStr.split(",")){
			if(week.length() == 1){
				weeks.add(Week.MON.getById(Integer.parseInt(week)));
			}
		}
		c.setWeek(weeks);
		return c;
	}

}
