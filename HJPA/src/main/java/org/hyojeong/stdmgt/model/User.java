package org.hyojeong.stdmgt.model;

public class User {
	int pid;
	String username;
	String password;
	int authority; // 0 : student, 1 : teacher, 2 : amdin
	
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
	@Override
	public String toString() {
		return "User [pid=" + pid + ", username=" + username + ", password=" + password + ", authority=" + authority
				+ "]";
	}
	
	
}
