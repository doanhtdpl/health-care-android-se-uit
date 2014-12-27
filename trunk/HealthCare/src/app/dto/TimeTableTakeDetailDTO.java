package app.dto;

public class TimeTableTakeDetailDTO {
	Integer timeTableTakeDetailId;
	Integer timeTableTakeId;
	String timeStart;
	Integer countTime;
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

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}

	public Integer getCountTime() {
		return countTime;
	}

	public void setCountTime(Integer countTime) {
		this.countTime = countTime;
	}

	public String getTimeSpacing() {
		return timeSpacing;
	}

	public void setTimeSpacing(String timeSpacing) {
		this.timeSpacing = timeSpacing;
	}
}