package kr.or.ddit.vo;

import java.util.Date;

//자바빈 클래스
public class ItemAttachVO {
	private int itemId;
	private int seq;
	private String fullname;
	private Date regdate;
	
	public ItemAttachVO() {}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		return "ItemAttachVO [itemId=" + itemId + ", seq=" + seq + ", fullname=" + fullname + ", regdate=" + regdate
				+ "]";
	}
	
	
}
