package com.example.demo.service;

public class Email extends Thread {

	private String email;
	
	public Email(String email) {
		this.email = email;
	}
	
	@Override
	public void run() {
		sendEmail(this.email);
	}
	
	public void sendEmail(String email) {
		System.out.println("Sending email to "+email+" from Thread "+Thread.currentThread().getName());
	}
	
}
