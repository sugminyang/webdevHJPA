package org.hyojeong.stdmgt.model;

public class Notice {
	private String notice_id;
    private String notice_title;
    private String notice_coments;
    private String use_yn;
    private String mod_date;
    private String writer;
    
    
    
	public Notice(String notice_title, String notice_coments, String writer) {
		super();
		this.notice_title = notice_title;
		this.notice_coments = notice_coments;
		this.writer = writer;
	}
	public Notice() {
		super();
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getNotice_id() {
		return notice_id;
	}
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	public String getNotice_title() {
		return notice_title;
	}
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	public String getNotice_coments() {
		return notice_coments;
	}
	public void setNotice_coments(String notice_coments) {
		this.notice_coments = notice_coments;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getMod_date() {
		return mod_date;
	}
	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}
	@Override
	public String toString() {
		return "Notice [notice_id=" + notice_id + ", notice_title=" + notice_title + ", notice_coments="
				+ notice_coments + ", use_yn=" + use_yn + ", mod_date=" + mod_date + ", writer=" + writer + "]";
	}

	
    
}
