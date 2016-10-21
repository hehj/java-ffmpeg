package com.itlieutenant.example;

import java.io.File;

import com.itlieutenant.core.Encoder;
import com.itlieutenant.entity.AudioAttributes;
import com.itlieutenant.entity.AudioInfo;
import com.itlieutenant.entity.EncodingAttributes;
import com.itlieutenant.entity.MultimediaInfo;
import com.itlieutenant.entity.VideoAttributes;
import com.itlieutenant.entity.VideoInfo;
import com.itlieutenant.entity.VideoSize;
import com.itlieutenant.exception.EncoderException;
import com.itlieutenant.exception.InputFormatException;

public class EncoderDemo {
	public static void main(String[] args) {
		Encoder encoder = new  Encoder();
		//your file 
		File source = new File("E:/00d05abe-ae8b-451b-bbc1-bcd37cb866bd.mp4");
		File target = new File("E:/output.mp3");
		
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
			
			if(vInfo==null){//not video
				vInfo = new VideoInfo();
			}else{
				System.out.println("bitRete="+mmInfo.getBitRate());
				System.out.println("VideoInfo的bitRete(bug)="+vInfo.getBitRate());
				System.out.println("AudioInfo的bitRete(bug)="+aInfo.getBitRate());
				System.out.println("video size="+vInfo.getSize());
				VideoSize vSize = new VideoSize(960, 640);
				if(vSize.getWidth()>vInfo.getSize().getWidth()||vSize.getHeight()>vInfo.getSize().getHeight()){
					vSize = new VideoSize(vInfo.getSize().getWidth(),vInfo.getSize().getHeight());
				}
				vAttr.setSize(vSize);
			}
			
			if(aInfo==null){//no audio
				aInfo = new AudioInfo();
			}else{
				System.out.println("audio channels="+aInfo.getChannels());
				System.out.println("audio samplingRate="+aInfo.getSamplingRate());
			}
			
			enAttr.setFormat("flv");
			
			//ffmpeg defualt bitRate is 128kb/s, lower then this value will run error
			vAttr.setBitRate(mmInfo.getBitRate()*1000);
			
			aAttr.setChannels(aInfo.getChannels());
			aAttr.setSamplingRate(aInfo.getSamplingRate());
			enAttr.setVideoAttributes(vAttr);
			enAttr.setAudioAttributes(aAttr);
			encoder.encode(source, target, enAttr);
			System.out.println("success!");
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
