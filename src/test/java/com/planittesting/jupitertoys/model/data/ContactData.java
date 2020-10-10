package com.planittesting.jupitertoys.model.data;


public class ContactData {
	private String forename = null;
	private String surname = null;
	private String email = null;
	private String telephone = null;
	private String message = null;
	
	public ContactData withForename(String forename) { 
		setForename(forename);
		return this;
	}
	
	public ContactData withSurname(String surname) {
		setSurname(surname);
		return this;
	}
	
	public ContactData withEmail(String email) {
		setEmail(email);
		return this;
	}
	
	public ContactData withTelephone(String telephone) {
		setTelephone(telephone);
		return this;
	}
	
	public ContactData withMessage(String message) {
		setMessage(message);
		return this;
	}
	
	public String getForename() {
		return forename;
	}
	public void setForename(String forename) {
		this.forename = forename;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
