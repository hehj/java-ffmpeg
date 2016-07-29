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
import it.sauronsoftware.jave.VideoSize;

public class EncoderDemo {
	public static void main(String[] args) {
		Encoder encoder = new  Encoder();
		//your file 
		File source = new File("E:\\encodeStudio\\output.mp4");
		File target = new File("E:\\encodeStudio\\output.mp3");
		
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
