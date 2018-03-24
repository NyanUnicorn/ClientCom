package com;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class SocketCom implements Runnable{
	public String IP_ADDRESS;
	public int SOCKET_NUM;
	public int i, count, sliderValue;
	public boolean handled;
	
	public static String SRETURN;
	
	Socket s ;
	Scanner sc1;
	PrintStream p;
	

	public SocketCom(String ip_address, int socket_num) {
		super();
		this.IP_ADDRESS = ip_address;
		this.SOCKET_NUM = socket_num;
		count = 0;
		handled = true;
		i = 1;
	}
	
	public SocketCom() {
		super();
		IP_ADDRESS = "127.0.0.1";
		SOCKET_NUM = 3330;
		count = 0;
		handled = true;
		i = 1;
	}

	@Override
	public void run() {
		socketAllocation();
		
		
		portAllocation();
		
		
		do{		
			sliderValue = Client.getSliderValue();

			p.println(sliderValue);
			SRETURN =sc1.next();
			Client.SetSReturnValue(Integer.parseInt(SRETURN));

			System.out.println(SRETURN);
			try {
				Thread.sleep(20);
			}catch(Exception e) {
				
			}
			count++;
		}while(SRETURN != "closed") ;
		System.out.println("serverStopped");
	}
	
	
	
	private void socketAllocation(String socketNumString) {
		int socketNum = Integer.parseInt(socketNumString);
		s = null;
		sc1 = null;
		p = null;
		try {
			try {
				s = new Socket(IP_ADDRESS, socketNum);
			} catch (UnknownHostException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			sc1 = new Scanner(s.getInputStream());
			p = new PrintStream(s.getOutputStream());
			
		} catch (IOException e) {
			System.out.println("not going");
			e.printStackTrace();
		}
		
	}

	private void socketAllocation() {
		s = null;
		sc1 = null;
		p = null;
		try {
			try {
				s = new Socket(IP_ADDRESS, SOCKET_NUM);
			} catch (UnknownHostException e) {
				
				e.printStackTrace();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			sc1 = new Scanner(s.getInputStream());
			p = new PrintStream(s.getOutputStream());
			
		} catch (IOException e) {
			System.out.println("not going");
			e.printStackTrace();
		}
		
	}

	private void portAllocation() {
		p.println("127.0.0.1"+";"+"open");
//		System.out.println(sc1.next());
		SRETURN =sc1.next();
		socketAllocation(SRETURN);
		
	}

	public void setNumber(int input){
		this.i = input;
	}

}
