package org.hyojeong.stdmgt.model;

public class GradeHistory {
	int pid;
	String semester="";
	String degree;
	int credit;
	int warnings;
	int modifiedBy;
//	1 2019-1	19	4.1	0	1
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

	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public int getWarnings() {
		return warnings;
	}
	public void setWarnings(int warnings) {
		this.warnings = warnings;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "GradeHistory [pid=" + pid + ", semester=" + semester + ", degree=" + degree + ", credit=" + credit
				+ ", warnings=" + warnings + ", modifiedBy=" + modifiedBy + "]";
	}

	
	
}
