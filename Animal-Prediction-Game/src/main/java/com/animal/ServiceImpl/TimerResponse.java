package com.animal.ServiceImpl;

public class TimerResponse {
	 private String spinId;
	    private long serverStartTime;
	    private long remainingTime;
		public String getSpinId() {
			return spinId;
		}
		public void setSpinId(String spinId) {
			this.spinId = spinId;
		}
		public long getServerStartTime() {
			return serverStartTime;
		}
		public void setServerStartTime(long serverStartTime) {
			this.serverStartTime = serverStartTime;
		}
		public long getRemainingTime() {
			return remainingTime;
		}
		public void setRemainingTime(long remainingTime) {
			this.remainingTime = remainingTime;
		}
		public TimerResponse(String spinId, long serverStartTime, long remainingTime) {
			super();
			this.spinId = spinId;
			this.serverStartTime = serverStartTime;
			this.remainingTime = remainingTime;
		}
		public TimerResponse() {
			super();
			// TODO Auto-generated constructor stub
		}

	    
	    
   
}

