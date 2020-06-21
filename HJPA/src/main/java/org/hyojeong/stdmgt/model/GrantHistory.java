package org.hyojeong.stdmgt.model;

public class GrantHistory {
//	pid	year	content	memo	modifiedBy
//	1	2019	교내 영어캠프	장학금 지원받음	1
	int pid;
	String semester = "";
	String grant_hyojung="";
	String grant_sunmoon="";
	String grant_other="";
	String tuitionfee="";
	int modifiedBy;
	
	public GrantHistory() {
		super();
	}
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getGrant_hyojung() {
		return grant_hyojung;
	}
	public void setGrant_hyojung(String grant_hyojung) {
		this.grant_hyojung = grant_hyojung;
	}
	public String getGrant_sunmoon() {
		return grant_sunmoon;
	}
	public void setGrant_sunmoon(String grant_sunmoon) {
		this.grant_sunmoon = grant_sunmoon;
	}
	public String getGrant_other() {
		return grant_other;
	}
	public void setGrant_other(String grant_other) {
		this.grant_other = grant_other;
	}
	public String getTuitionfee() {
		return tuitionfee;
	}
	public void setTuitionfee(String tuitionfee) {
		this.tuitionfee = tuitionfee;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "GrantHistory [pid=" + pid + ", semester=" + semester + ", grant_hyojung=" + grant_hyojung
				+ ", grant_sunmoon=" + grant_sunmoon + ", grant_other=" + grant_other + ", tuitionfee=" + tuitionfee
				+ ", modifiedBy=" + modifiedBy + "]";
	}
	

	
	
	
}
