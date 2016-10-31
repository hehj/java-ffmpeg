package com.itlieutenant.example;

import java.io.File;

import com.itlieutenant.core.Encoder;
import com.itlieutenant.exception.EncoderException;
import com.itlieutenant.exception.InputFormatException;

public class M3u8MacDemo {
	public static void main(String[] args) {
		Encoder encoder = new Encoder();
		// your file

		String[] perSource = new String[] { "-y", "-i" };
		File source = new File("/Users/hangjiehe/encode-source/ed650e4f-28a5-4fea-94c9-3aa2c2b4f643.flv");
		String[] perTarget = new String[] { "-vcodec", "libx264", "-hls_time", String.valueOf(6), "-hls_list_size",
				"0" };
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
