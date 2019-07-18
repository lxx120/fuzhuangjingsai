package com.match.common.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Ma extends Authenticator{
	public static final String userName = "mlyong108@126.com";
	public static final String passWord = "menglingyong";
	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(userName, passWord);
	}
}
