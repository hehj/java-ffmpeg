package it.sauronsoftware.example;

import java.io.File;

import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.AudioInfo;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;
import it.sauronsoftware.jave.MultimediaInfo;
import it.sauronsoftware.jave.VideoAttributes;
import it.sauronsoftware.jave.VideoInfo;

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
			System.out.println("begin!");
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
