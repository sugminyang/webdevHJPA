package org.hyojeong.stdmgt.model;

public class StudentDomestic {
	String 접수번호;
	String 장학구분;
	String 지원분야;
	String 이름;
	String 나이;
	public StudentDomestic(String 접수번호, String 장학구분, String 지원분야, String 이름, String 나이) {
		super();
		this.접수번호 = 접수번호;
		this.장학구분 = 장학구분;
		this.지원분야 = 지원분야;
		this.이름 = 이름;
		this.나이 = 나이;
	}
	public String get접수번호() {
		return 접수번호;
	}
	public void set접수번호(String 접수번호) {
		this.접수번호 = 접수번호;
	}
	public String get장학구분() {
		return 장학구분;
	}
	public void set장학구분(String 장학구분) {
		this.장학구분 = 장학구분;
	}
	public String get지원분야() {
		return 지원분야;
	}
	public void set지원분야(String 지원분야) {
		this.지원분야 = 지원분야;
	}
	public String get이름() {
		return 이름;
	}
	public void set이름(String 이름) {
		this.이름 = 이름;
	}
	public String get나이() {
		return 나이;
	}
	public void set나이(String 나이) {
		this.나이 = 나이;
	}

}
