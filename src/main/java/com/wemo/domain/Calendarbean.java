package com.wemo.domain;

public class Calendarbean {
	private String USER_EMAIL;
	private int MEMO_NUM;		
	private String MEMO_SUB;	
	private String MEMO_TEX;	
	private String title;
	private String start;
	private String end;	
	private String MEMO_COLOR;
	private boolean allDay;
	
	private String CALENDAR_USERNAME;
	private String CALENDAR_TEXTCOLOR;
	private String CALENDAR_ALLDAY;
	
	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}
	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}
	public int getMEMO_NUM() {
		return MEMO_NUM;
	}
	public void setMEMO_NUM(int mEMO_NUM) {
		MEMO_NUM = mEMO_NUM;
	}
	public String getMEMO_SUB() {
		return MEMO_SUB;
	}
	public void setMEMO_SUB(String mEMO_SUB) {
		MEMO_SUB = mEMO_SUB;
	}
	public String getMEMO_TEX() {
		return MEMO_TEX;
	}
	public void setMEMO_TEX(String mEMO_TEX) {
		MEMO_TEX = mEMO_TEX;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getMEMO_COLOR() {
		return MEMO_COLOR;
	}
	public void setMEMO_COLOR(String mEMO_COLOR) {
		MEMO_COLOR = mEMO_COLOR;
	}
	public boolean isAllDay() {
	      return allDay;
	}
	public void setAllDay(boolean allDay) {
	      this.allDay = allDay;
	}
	public String getCALENDAR_USERNAME() {
		return CALENDAR_USERNAME;
	}
	public void setCALENDAR_USERNAME(String cALENDAR_USERNAME) {
		CALENDAR_USERNAME = cALENDAR_USERNAME;
	}
	public String getCALENDAR_TEXTCOLOR() {
		return CALENDAR_TEXTCOLOR;
	}
	public void setCALENDAR_TEXTCOLOR(String cALENDAR_TEXTCOLOR) {
		CALENDAR_TEXTCOLOR = cALENDAR_TEXTCOLOR;
	}
	public String getCALENDAR_ALLDAY() {
		return CALENDAR_ALLDAY;
	}
	public void setCALENDAR_ALLDAY(String cALENDAR_ALLDAY) {
		CALENDAR_ALLDAY = cALENDAR_ALLDAY;
	}
	
}
	