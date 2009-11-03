package com.synaptik.jwow;

public class JWoWException extends Exception {
	private static final long serialVersionUID = 1L;

	public JWoWException (String msg) {
		super("JWoW: " + msg);
	}
}
