package com.planittesting.jupitertoys.model.data;

public class LoginData {

	private String username;
	private String password;
	
	public LoginData withUsername(String username) {
		this.username = username;
		return this;
	}
	
	
	public LoginData withPassword(String password) {
		this.password = password;
		return this;
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
	
	
	
}
