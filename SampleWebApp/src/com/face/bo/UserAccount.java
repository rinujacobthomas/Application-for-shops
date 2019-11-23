package com.face.bo;

public class UserAccount {
	  private String userName;							//private variables for stroing username annd  password
	    private String gender;
	    private String password;

	public String getUserName() {						// Setters and Getters for accesing these variables
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static String getGenderMale() {
		return GENDER_MALE;
	}
	public static String getGenderFemale() {
		return GENDER_FEMALE;
	}
	public static final String GENDER_MALE ="M";
    public static final String GENDER_FEMALE = "F";
     
  }
