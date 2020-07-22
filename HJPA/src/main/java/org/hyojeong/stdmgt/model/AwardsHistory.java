package org.hyojeong.stdmgt.model;

public class AwardsHistory {
//	pid	year	content	organization	memo	modifiedBy
//	1	2020	교내 마라톤	대학교	3등	1
	int pid;
	String year;
	String content="";
	String organization="";
	String memo="";
	int modifiedBy;
	int tid;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@Override
	public String toString() {
		return "AwardsHistory [pid=" + pid + ", year=" + year + ", content=" + content + ", organization="
				+ organization + ", memo=" + memo + ", modifiedBy=" + modifiedBy + ", tid=" + tid + "]";
	}
	public AwardsHistory(int pid, int tid, String year, String content, String organization, String memo) {
		super();
		this.pid = pid;
		this.year = year;
		this.content = content;
		this.organization = organization;
		this.memo = memo;
		this.tid = tid;
	}
	public AwardsHistory() {
		super();
	}
	public AwardsHistory(int pid, int tid) {
		super();
		this.pid = pid;
		this.tid = tid;
	}
	
	
}
