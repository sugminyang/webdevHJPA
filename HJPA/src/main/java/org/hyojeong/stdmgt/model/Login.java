package org.hyojeong.stdmgt.model;

public class Login {

	private String id;
	private String password;

	public String getUsername() {
		return id;
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

	@Override
	public String toString() {
		return "Login [id=" + id + ", password=" + password + "]";
	}

	public Login(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public Login() {
		super();
	}
	

}
