package kr.or.ddit.vo;

import java.util.Date;

public class ItextVO {
	public Date regDt;
	public String memTel;
	public String memId;
	public String memName;
	public String memAddr;
	
	public ItextVO() {}

	public Date getRegDt() {
		return regDt;
	}

	public void setRegDt(Date regDt) {
		this.regDt = regDt;
	}

	public String getMemTel() {
		return memTel;
	}

	public void setMemTel(String memTel) {
		this.memTel = memTel;
	}

	public String getMemId() {
		return memId;
	}

	public void setMemId(String memId) {
		this.memId = memId;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public String getMemAddr() {
		return memAddr;
	}

	public void setMemAddr(String memAddr) {
		this.memAddr = memAddr;
	}

	@Override
	public String toString() {
		return "ItextVO [regDt=" + regDt + ", memTel=" + memTel + ", memId=" + memId + ", memName=" + memName
				+ ", memAddr=" + memAddr + "]";
	}
	
	
	
}
