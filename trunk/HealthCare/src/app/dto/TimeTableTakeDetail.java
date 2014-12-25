package app.dto;

public class TimeTableTakeDetail {
	Integer timeTableTakeDetailId;
	Integer timeTableTakeId;
	Integer userId;
	String timeStart;
	String countTime;
	String timeSpacing;

	public Integer getTimeTableTakeDetailId() {
		return timeTableTakeDetailId;
	}

	public void setTimeTableTakeDetailId(Integer timeTableTakeDetailId) {
		this.timeTableTakeDetailId = timeTableTakeDetailId;
	}

	public Integer getTimeTableTakeId() {
		return timeTableTakeId;
	}

	public void setTimeTableTakeId(Integer timeTableTakeId) {
		this.timeTableTakeId = timeTableTakeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public String getCountTime() {
		return countTime;
	}

	public void setCountTime(String countTime) {
		this.countTime = countTime;
	}

	public String getTimeSpacing() {
		return timeSpacing;
	}

	public void setTimeSpacing(String timeSpacing) {
		this.timeSpacing = timeSpacing;
	}
}