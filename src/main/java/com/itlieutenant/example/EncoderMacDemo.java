package com.itlieutenant.example;

import java.io.File;

import com.itlieutenant.core.Encoder;
import com.itlieutenant.entity.AudioAttributes;
import com.itlieutenant.entity.AudioInfo;
import com.itlieutenant.entity.EncodingAttributes;
import com.itlieutenant.entity.MultimediaInfo;
import com.itlieutenant.entity.VideoAttributes;
import com.itlieutenant.entity.VideoInfo;
import com.itlieutenant.exception.EncoderException;
import com.itlieutenant.exception.InputFormatException;

public class EncoderMacDemo {
	public static void main(String[] args) {
		Encoder encoder = new  Encoder();
		//your file 
		File source = new File("/Volumes/WOKSTATION/encodeStudio/28ce0b00-c0be-4710-a893-ba6e45374c94.wmv");
		File target = new File("/Volumes/WOKSTATION/encodeStudio/28ce0b00-c0be-4710-a893-ba6e45374c94.mp4");
		
		if(!source.exists()){
			System.out.println("Source file is not exists!");
			return;
		}
		
		try {
			//video info
			MultimediaInfo mmInfo = encoder.getInfo(source);
			VideoInfo vInfo = mmInfo.getVideo();
			AudioInfo aInfo = mmInfo.getAudio();
			
			EncodingAttributes enAttr = new EncodingAttributes(); 
			VideoAttributes vAttr = new VideoAttributes();
			AudioAttributes aAttr = new AudioAttributes();
			
			if(vInfo!=null){
				vAttr.setSize(vInfo.getSize());
				vAttr.setBitRate(mmInfo.getBitRate()*1000);
			}
			
			if(aInfo!=null){
				aAttr.setSamplingRate(aInfo.getSamplingRate());
			}
			
			vAttr.setCodec("libx264");
			enAttr.setFormat("mp4");
			enAttr.setVideoAttributes(vAttr);
			enAttr.setAudioAttributes(aAttr);
			encoder.encode(source, target, enAttr);
		} catch (InputFormatException e) {
			e.printStackTrace();
		} catch (EncoderException e) {
			e.printStackTrace();
		}
	}
}
