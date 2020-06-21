package org.hyojeong.stdmgt.model;

public class AbsenceHistory {
//	pid	date	consultant	topic	memo	modifiedBy
//	1	20190701	김경진	교우관계 상담	-	4
	int pid;
	String date="";
	String consultant="";
	String content="";
	String term="";
	int modifiedBy;
	
	public AbsenceHistory() {
		super();
	}
	
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getConsultant() {
		return consultant;
	}
	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public int getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(int modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	@Override
	public String toString() {
		return "AbsenceHistory [pid=" + pid + ", date=" + date + ", consultant=" + consultant + ", content=" + content
				+ ", term=" + term + ", modifiedBy=" + modifiedBy + "]";
	}

	
	
	
}
