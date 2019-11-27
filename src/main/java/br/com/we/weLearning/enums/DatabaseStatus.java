package br.com.we.weLearning.enums;

public enum DatabaseStatus {
	
	ATIVE 		(1),
	INACTIVE	(2),
	EXCLUDED	(3);
	
	private final int status;
	
	DatabaseStatus(int status) {
		this.status = status;
	}
	
	public int getDatabaseStatus() {
		return this.status;
	}
	
}
