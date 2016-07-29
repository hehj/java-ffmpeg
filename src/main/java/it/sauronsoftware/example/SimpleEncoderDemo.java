package it.sauronsoftware.example;

import java.io.File;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;

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
