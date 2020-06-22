package org.hyojeong.stdmgt.model;

public class ActiveHistory {
//	pid	year	content	memo	modifiedBy
//	1	2019	교내 영어캠프	장학금 지원받음	1
	int pid;
	int tid;
	
	int year;
	String content="";
	String memo="";
	int modifiedBy;
	
	
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
		return "ActiveHistory [pid=" + pid + ", year=" + year + ", content=" + content + ", memo=" + memo
				+ ", modifiedBy=" + modifiedBy + "]";
	}
	public ActiveHistory() {
		super();
	}
	public ActiveHistory(int pid, int tid, int year, String content, String memo) {
		super();
		this.pid = pid;
		this.tid = tid;
		this.year = year;
		this.content = content;
		this.memo = memo;
	}
	
	
	
}
