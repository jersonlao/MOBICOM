package com.example.object;

public class Event {

	private int eventID;
	private int diaryID;
	private String eventName;
	private String eventDesc;

	public int getEventID() {
		return eventID;
	}
	public int getDiaryID() {
		return diaryID;
	}
	public String getEventName() {
		return eventName;
	}
	public String getEventDesc() {
		return eventDesc;
	}
	public void setEventID(int eventID) {
		this.eventID = eventID;
	}
	public void setDiaryID(int diaryID) {
		this.diaryID = diaryID;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}
}
