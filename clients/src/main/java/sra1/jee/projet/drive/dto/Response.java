package sra1.jee.projet.drive.dto;

public class Response {
	
	public String message;
	public Status status = Status.OK;
	
	public enum Status {
		OK, ERROR
	};
	

}
