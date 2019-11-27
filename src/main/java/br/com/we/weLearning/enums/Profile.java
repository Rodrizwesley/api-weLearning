package br.com.we.weLearning.enums;

public enum Profile {
	
	ADMINISTRATOR	("Administrator"),
	STUDENT			("Student"),
	TEACHER			("Teacher");
	
	private final String profile;
	
	Profile(String profile){
		this.profile = profile;
	}
	
	public String getProfile() {
		return this.profile;
	}

}
