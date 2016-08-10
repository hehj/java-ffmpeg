# java-ffmpeg
@(Hehangjie)[互联网中尉 www.itlieutenant.com]

    A maven project with a bitrate bug corrected ,which is refering to jave-1.0.2-src

    jave源码包的好处是，它封装了ProcessBuilder对ffmpeg的调用，并完美支持了windows和linux环境。
    不需要额外安装ffmpeg，就可以进行音视频文件的转码。
    关于linux的支持情况在centos 6.5亲测可用。
    可能会存在平台差异性问题，还有许多细节被验证和完善，所以选择了让它开源。
    
    V0.0.4 
    在此前jave-1.0.2-src里封装的ffmpeg不支持h264，h265格式的vcodec。这对于原本是用于web项目的转码服务是致命的。
    在这个版本中对此做了修复，将ffmpeg分离出来（意味着你必须独立安装ffmpeg，以后这个项目只是java使用ffmpeg的sdk，
    不再提供ffmpeg的免安装服务）
    
    以下是java调用Encoder的示例代码

```` java
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
		e.printStackTrace();
	} catch (EncoderException e) {
		e.printStackTrace();
	}
}
````
