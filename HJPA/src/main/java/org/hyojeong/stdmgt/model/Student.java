package org.hyojeong.stdmgt.model;

public class Student {
	int pid;
	String birth;
	String nationality;
	String name_kor;
	String name_eng;
	String email;
	String phone;
	String sns_id;
	String sex;
	String college;
	String dept;
	String sno_univ;
	String sno_acad;
	String snsType;
	String continent;
	int grade;

	
	
	public String getSnsType() {
		return snsType;
	}
	public void setSnsType(String snsType) {
		this.snsType = snsType;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getName_kor() {
		return name_kor;
	}
	public void setName_kor(String name_kor) {
		this.name_kor = name_kor;
	}
	public String getName_eng() {
		return name_eng;
	}
	public void setName_eng(String name_eng) {
		this.name_eng = name_eng;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSns_id() {
		return sns_id;
	}
	public void setSns_id(String sns_id) {
		this.sns_id = sns_id;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSno_univ() {
		return sno_univ;
	}
	public void setSno_univ(String sno_univ) {
		this.sno_univ = sno_univ;
	}
	public String getSno_acad() {
		return sno_acad;
	}
	public void setSno_acad(String sno_acad) {
		this.sno_acad = sno_acad;
	}
	@Override
	public String toString() {
		return "Student pid=" + pid + ", birth=" + birth + ", nationality=" + nationality + ", name_kor=" + name_kor
				+ ", name_eng=" + name_eng + ", email=" + email + ", phone=" + phone + ", sns_id=" + sns_id + ", sex="
				+ sex + ", college=" + college + ", dept=" + dept + ", sno_univ=" + sno_univ + ", sno_acad=" + sno_acad
				+ ", snsType=" + snsType + ", continent=" + continent + ", grade=" + grade + "";
		
//		return pid+"\t"+ birth + "\t" + nationality + "\t" + name_kor +"\t" + name_eng + "\t"
//					+ email + "\t" + phone + "\t" + sns_id + "\t" + sex + "\t" + college + "\t" + dept +"\t"
//					+ sno_univ + "\t" + sno_acad + "\t" + snsType + "\t" + continent + "\t" + grade;
		
				
	}
	

	
}
