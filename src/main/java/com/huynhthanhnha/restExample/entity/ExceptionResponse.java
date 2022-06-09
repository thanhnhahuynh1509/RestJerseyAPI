package com.huynhthanhnha.restExample.entity;

public class ExceptionResponse {

	private int status;
	private String content;
	
	public ExceptionResponse(int status, String content) {
		super();
		this.status = status;
		this.content = content;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
