package com.itlieutenant.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.itlieutenant.core.Encoder;
import com.itlieutenant.exception.EncoderException;
import com.itlieutenant.exception.InputFormatException;

public class M3u8MacDemo {
	public static void main(String[] args) {
		Encoder encoder = new Encoder();
		// your file
		List<String> perSource = new ArrayList<>();
		perSource.add("-y");
		perSource.add("-i");
		File source = new File("/Users/hangjiehe/encode-source/ed650e4f-28a5-4fea-94c9-3aa2c2b4f643.flv");
		List<String> perTarget = new ArrayList<>();
		perTarget.add("-vcodec");
		perTarget.add("libx264");
		perTarget.add("-hls_time");
		perTarget.add(String.valueOf(6));
		perTarget.add("-hls_list_size");
		perTarget.add("0");
		File target = new File("/Users/hangjiehe/encode-source/ed650e4f-28a5-4fea-94c9-3aa2c2b4f643.m3u8");

		if (!source.exists()) {
			System.out.println("Source file is not exists!");
			return;
		}

		try {
			encoder.encode(perSource, source, perTarget, target);
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}
	}
}
