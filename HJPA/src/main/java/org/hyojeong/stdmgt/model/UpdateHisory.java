package org.hyojeong.stdmgt.model;

public class UpdateHisory {
	int pid;
	String memo;
	String updateDate;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "UpdateHisory [pid=" + pid + ", memo=" + memo + ", updateDate=" + updateDate + "]";
	}
	public UpdateHisory(int pid, String memo, String updateDate) {
		super();
		this.pid = pid;
		this.memo = memo;
		this.updateDate = updateDate;
	}
	
}
