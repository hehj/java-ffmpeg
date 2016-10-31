package com.itlieutenant.example;

import java.io.File;

import com.itlieutenant.core.Encoder;
import com.itlieutenant.entity.MultimediaInfo;
import com.itlieutenant.exception.EncoderException;
import com.itlieutenant.exception.InputFormatException;

public class VedioInfoMacDemo {
	public static void main(String[] args) {
		Encoder encoder = new Encoder();
		// your file
		File source = new File("/Users/hangjiehe/encode-source/ed650e4f-28a5-4fea-94c9-3aa2c2b4f643.flv");
		//File source = new File("/Users/hangjiehe/encode-source/08b3e4c0-abf8-40b5-9197-13c44c67b79e.mp3");

		if (!source.exists()) {
			System.out.println("Source file is not exists!");
			return;
		}

		try {
			// video info
			MultimediaInfo mmInfo = encoder.getInfo(source);
			System.out.print("duration=" + mmInfo.getDuration());
			System.out.print("bitrate=" + mmInfo.getBitRate());

		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}
	}
}
