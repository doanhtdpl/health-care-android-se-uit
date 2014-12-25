package app.dto;

public class RatioWHRDTO {
	Integer ratioWHRid;
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

	public Integer getRatioWHRid() {
		return ratioWHRid;
	}

	public void setRatioWHRid(Integer ratioWHRid) {
		this.ratioWHRid = ratioWHRid;
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
