package san.video.subtitle.player;

 public class MyData {
	 private String start, startTime, end, endTime, subTitle;

	 public MyData(String start, String startTime, String end, String endTime,
				   String subTitle) {
		super();
		this.start = start;
		this.startTime = startTime;
		this.end = end;
		this.endTime = endTime;
		this.subTitle = subTitle;
	}
	
	 MyData() {}

	 String getStart() {
		return start;
	}

	 void setStart(String start) {
		this.start = start;
	}

	 String getStartTime() {
		return startTime;
	}

	 void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	 String getEnd() {
		return end;
	}

	 void setEnd(String end) {
		this.end = end;
	}

	 String getEndTime() {
		return endTime;
	}

	 void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	 String getSubTitle() {
		return subTitle;
	}

	 void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}
	
	
}
