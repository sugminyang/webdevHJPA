package org.hyojeong.stdmgt.model;

public class Student {
	int pid;
	String birth = "";
	String nationality= "";
	String name_kor= "";
	String name_eng= "";
	String email= "";
	String phone= "";
	String sns_id= "";
	String address= "";
	String sex= "";
	String college= "";
	String dept= "";
	String sno_univ= "";
	String sno_acad= "";
	String snsType= "";
	String continent= "";
	int grade;
	String status = "재학";
	String awardStatus = "유지";
	String profile = "/resources/img/image.jpeg";
	
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAwardStatus() {
		return awardStatus;
	}
	public void setAwardStatus(String awardStatus) {
		this.awardStatus = awardStatus;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
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
		return "Student [pid=" + pid + ", birth=" + birth + ", nationality=" + nationality + ", name_kor=" + name_kor
				+ ", name_eng=" + name_eng + ", email=" + email + ", phone=" + phone + ", sns_id=" + sns_id
				+ ", address=" + address + ", sex=" + sex + ", college=" + college + ", dept=" + dept + ", sno_univ="
				+ sno_univ + ", sno_acad=" + sno_acad + ", snsType=" + snsType + ", continent=" + continent + ", grade="
				+ grade + ", status=" + status + ", awardStatus=" + awardStatus + ", profile=" + profile + "]";
	}
	public Student() {
		super();
	}
	
	public Student(String sno_univ, String sno_acad, int grade, String status, 
			String awardStatus, String email, String phone, String college, 
			String dept, String snsType, String sns_id, String address,
			String profile) {
		super();
		this.email = email;
		this.phone = phone;
		this.sns_id = sns_id;
		this.address = address;
		this.college = college;
		this.dept = dept;
		this.sno_univ = sno_univ;
		this.sno_acad = sno_acad;
		this.snsType = snsType;
		this.grade = grade;
		this.status = status;
		this.awardStatus = awardStatus;
		this.profile = profile;
	}
	
	
	

	
}
