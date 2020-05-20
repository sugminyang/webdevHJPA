package org.hyojeong.stdmgt.model;

public class User {
	int pid;
	String id;
	String password;
	String authority; // 0 : student, 1 : teacher, 2 : amdin
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "User [pid=" + pid + ", id=" + id + ", password=" + password + ", authority=" + authority + "]";
	}
	
	
}
