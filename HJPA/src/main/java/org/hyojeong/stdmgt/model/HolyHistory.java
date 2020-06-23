package org.hyojeong.stdmgt.model;

public class HolyHistory {
//	pid	semester	reading_session	Worship	warnings	modifiedBy
//	1	2019-2	20	40	2	1
	
	int pid;
	String semester="";
	String reading_session="";
	String Worship="";
	int warnings;
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	public String getReading_session() {
		return reading_session;
	}
	public void setReading_session(String reading_session) {
		this.reading_session = reading_session;
	}
	public String getWorship() {
		return Worship;
	}
	public void setWorship(String worship) {
		Worship = worship;
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
		return "HolyHistory [pid=" + pid + ", semester=" + semester + ", reading_session=" + reading_session
				+ ", Worship=" + Worship + ", warnings=" + warnings + ", modifiedBy=" + modifiedBy + "]";
	}
	public HolyHistory() {
		super();
	}
	public HolyHistory(int pid, int tid) {
		super();
		this.pid = pid;
		this.tid = tid;
	}
	public HolyHistory(int pid, String semester, String reading_session, String Worship, int warnings, int tid) {
		super();
		this.pid = pid;
		this.semester = semester;
		this.reading_session = reading_session;
		this.Worship = Worship;
		this.warnings = warnings;
		this.tid = tid;
	}
	
	
	
}
