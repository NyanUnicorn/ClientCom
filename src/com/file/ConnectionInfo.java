package com.file;

public class ConnectionInfo {
	public static String IP_ADDRESS;
	public static int SOCKET_NUMBER;

	public ConnectionInfo(String IP_ADDRESS, int SOCKET_NUMBER) {
		super();
		this.IP_ADDRESS = IP_ADDRESS;
		this.SOCKET_NUMBER = SOCKET_NUMBER;
		// TODO Auto-generated constructor stub
	}
	
	public static String getIp() {
		return IP_ADDRESS;
	}
	
	public static int getSocket() {
		return SOCKET_NUMBER;
	}

}
