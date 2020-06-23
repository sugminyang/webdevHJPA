package org.hyojeong.stdmgt.model;

public class ConsultHistory {
//	pid	date	consultant	topic	memo	modifiedBy
//	1	20190701	김경진	교우관계 상담	-	4
	int pid;
	String date="";
	String consultant="";
	String topic="";
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
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
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
		return "ConsultHistory [pid=" + pid + ", date=" + date + ", consultant=" + consultant + ", topic=" + topic
				+ ", memo=" + memo + ", modifiedBy=" + modifiedBy + ", tid=" + tid + "]";
	}
	public ConsultHistory(int pid, String date,String topic,  String memo,  String consultant, int tid) {
		super();
		this.pid = pid;
		this.date = date;
		this.consultant = consultant;
		this.topic = topic;
		this.memo = memo;
		this.tid = tid;
	}
	public ConsultHistory(int pid, int tid) {
		super();
		this.pid = pid;
		this.tid = tid;
	}
	public ConsultHistory() {
		super();
	}
	
	
}
