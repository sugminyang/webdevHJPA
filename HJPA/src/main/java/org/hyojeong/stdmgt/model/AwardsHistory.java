package org.hyojeong.stdmgt.model;

public class AwardsHistory {
//	pid	year	content	organization	memo	modifiedBy
//	1	2020	교내 마라톤	대학교	3등	1
	int pid;
	int year;
	String content="";
	String organization="";
	String memo="";
	int modifiedBy;
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
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
				+ organization + ", memo=" + memo + ", modifiedBy=" + modifiedBy + "]";
	}
	
	
}
