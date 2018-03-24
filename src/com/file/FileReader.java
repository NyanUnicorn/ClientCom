package com.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import com.Client;

public class FileReader {
	String FILE_NAME;
	int PARAM_NUM;
	File file;
	public Formatter frmtr;
	public Scanner scnr;
	
/*
 * Calling target storing location
 */

public FileReader(String fileName, int paramNumber) {
		super();
		this.FILE_NAME = fileName;
		this.PARAM_NUM = paramNumber;
		
		
	}

	public void openFile() {
		
		try {
			file = new File(FILE_NAME);
			scnr = new Scanner(file);
		}catch(Exception e) {
			try {
				frmtr = new Formatter(FILE_NAME);
				file = new File(FILE_NAME);
				
			} catch (FileNotFoundException e1) {
				
			}
		}
	}
	
	public void readFile() {
		try {
			Client.connectionInfoList.removeAll(null);
		}catch(Exception e) {}
		while(scnr.hasNextLine()) {
			String data = scnr.nextLine();
			String[] values = data.split(";");
			/**
			 * Storing values in target location
			 */
			Client.inputToConnecetionList(values[0], Integer.parseInt(values[1]));
			/**
			 * end
			 */
		}
		//scnr = null;
		
		
	}
	public void addLine(String txtLine) {
		while(scnr.hasNextLine()) {
			scnr.nextLine();
		}
		frmtr.format(txtLine);
	}
	
	public void closeFile() {
		scnr.close();
	}

}