package app.dto;

public class TimeTableTakeDTO {
	Integer timeTableTakeId;
	String sick;
	String time;
	String status;
	Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTimeTableTakeId() {
		return timeTableTakeId;
	}
	public void setTimeTableTakeId(Integer timeTableTakeId) {
		this.timeTableTakeId = timeTableTakeId;
	}
	public String getSick() {
		return sick;
	}
	public void setSick(String sick) {
		this.sick = sick;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}