package cn.xxd.clock.bean;

import java.io.Serializable;
import java.util.List;

import q.util.SqliteBase.ISqlite;

public class Clock implements Serializable, ISqlite {
	
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Cycle cycle;
	private int year; //2012
	private int month; //1-12
	private int day; //1-31
	private int hour; // 0~23
	private int min; // 0~59
	private List<Week> week; //1-7
	
	public String formatYearMonthDay(){
		return 
			this.year + "-"
			+ (this.month >= 10 ? this.month : "0" + this.month) + "-"
			+ (this.day >= 10 ? this.day : "0" + this.day);
	}
	
	public String formatDay(){
		return (this.day >= 10 ? this.day : "0" + this.day) + "号";
	}
	
	public String formatHourMin(){
		return 
			(this.hour >= 10 ? this.hour : "0" + this.hour) 
			+ ":" 
			+ (this.min >= 10 ? this.min : "0" + this.min);
	}
	
	public String formatWeek(){
		if(this.week.size() == 5 && this.week.get(0) == Week.MON && this.week.get(4) == Week.FRI){
			return "周一至周五";
		}
		StringBuffer sb = new StringBuffer("周");
		for(int i = 0, size = this.week.size(); i < size; i++){
			sb.append(this.week.get(i).getName());
			if(i != size -1){
				sb.append(", ");
			}
		}
		return sb.toString();
	}
	
	
	public static enum Cycle {
		
		SINGLE(1, "单次"),
		DAY(2, "每天"),
		WEEK(3, "每周"),
		MONTH(4, "每月");
		
		private int id;
		private String name;  
		
		private Cycle(int id, String name) {  
			this.id = id;
	        this.name = name;  
	    }
		
		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Cycle getById(int id){
			for(Cycle t : Cycle.values()){
				if(id == t.id){
					return t;
				}
			}
			return null;
		}
		
	}
	
	public static enum Week {
		
		MON(1, "一"),
		TUE(2, "二"),
		WED(3, "三"),
		THU(4, "四"),
		FRI(5, "五"),
		SAT(6, "六"),
		SUN(7, "日");
		
		private int id;
		private String name;  
		
		private Week(int id, String name) {  
			this.id = id;
	        this.name = name;  
	    }
		
		public String getName() {
			return name;
		}
		
		public int getId() {
			return id;
		}

		public Week getById(int id){
			for(Week t : Week.values()){
				if(id == t.id){
					return t;
				}
			}
			return null;
		}
		
	}
	
	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMin() {
		return min;
	}
	public void setMin(int min) {
		this.min = min;
	}

	public List<Week> getWeek() {
		return week;
	}

	public void setWeek(List<Week> week) {
		this.week = week;
	}

	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}
	

}
