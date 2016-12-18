package de.andhillgames.ahglauncher.classes;

import javafx.beans.property.SimpleStringProperty;

public class ErrorLog {

	private final SimpleStringProperty eCode;
	private final SimpleStringProperty eDate;
	private final SimpleStringProperty eTime;
	private final SimpleStringProperty eMsg;
	
	
	private ErrorLog(String errorCode, String date, String time, String errorMsg) {
		this.eCode = new SimpleStringProperty(errorCode);
		this.eDate = new SimpleStringProperty(date);
		this.eTime = new SimpleStringProperty(time);
		this.eMsg = new SimpleStringProperty(errorMsg);
	}

	public String getErrorCode() {
		return eCode.get();
	}
	
	public void setErrorCode(String errorCode) {
		eCode.set(errorCode);
	}
	
	public String getErrorDate() {
		return eDate.get();
	}
	
	public void setErrorDate(String date) {
		eDate.set(date);
	}
	
	public String getErrorTime() {
		return eTime.get();
	}
	
	public void setErrorTime(String time) {
		eTime.set(time);
	}
	
	public String getErrorMessage() {
		return eMsg.get();
	}
	
	public void setErrorMessage(String errorMsg) {
		eMsg.set(errorMsg);
	}
	
}
