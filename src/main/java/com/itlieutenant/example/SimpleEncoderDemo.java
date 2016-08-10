package com.itlieutenant.example;

import java.io.File;

import com.itlieutenant.exception.EncoderException;
import com.itlieutenant.exception.InputFormatException;
import com.itlieutenant.service.Encoder;

public class SimpleEncoderDemo {
	public static void main(String[] args) {
		Encoder encoder = new  Encoder();

		File source = new File("E:\\encodeStudio\\output.mp4");
		File target = new File("E:\\encodeStudio\\output.mp3");
		
		if(!source.exists()){
			System.out.println("Source file is not exists!");
			return;
		}
		
		try {
			encoder.encode(source, target);
			System.out.println("SUCCESS!");
			
			System.out.println(target.exists());
			System.out.println(target.length());
			System.out.println(target.getAbsolutePath());
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}