package com.match.systemconfig.utils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

@Service
public class LoginUtil {

	public long getCUserID(HttpSession session) {
		return 0;
	}

	public List<Long> getCDepartID(HttpSession session) {
		return null;
	}

	public long getCRoleID(HttpSession session) {
		return 0;
	}

	public long getCPositionID(HttpSession session, long departID) {
		return 0;
	}

	public long getCWorkerID(HttpSession session) {
		return 0;
	}
}
