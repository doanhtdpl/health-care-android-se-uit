package app.dto;

public class RatioBMIDTO {
	Integer ratioBMIId;
	String time;
	Integer ratio;
	String status;
	Integer userId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getRatioBMIId() {
		return ratioBMIId;
	}

	public void setRatioBMIId(Integer ratioBMIId) {
		this.ratioBMIId = ratioBMIId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getRatio() {
		return ratio;
	}

	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
