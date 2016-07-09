# java-ffmpeg
    A maven project with a bitrate bug corrected ,which is refering to jave-1.0.2-src

    jave源码包的好处是，它封装了ProcessBuilder对ffmpeg的调用，并完美支持了windows和linux环境，不需要额外安装ffmpeg，就可以进行音视频文件的转码。
    关于linux的支持情况在centos 6.5亲测可用。
    可能会存在平台差异性问题，还有许多细节被验证和完善，所以选择了让它开源。

```` java
public static void main(String[] args) {
		Encoder encoder = new  Encoder();
		//替换成你的文件
		File source = new File("D://trailer_002winnie.mp4");
		File target = new File("D://trailer_002winnie_ld.mp4");
		
		if(!source.exists()){
			System.out.println("Source file is not exists!");
			return;
		}
		
		try {
			//取视频信息
			MultimediaInfo mmInfo = encoder.getInfo(source);
			VideoInfo vInfo = mmInfo.getVideo();
			AudioInfo aInfo = mmInfo.getAudio();
			System.out.println("真实的bitRete="+mmInfo.getBitRate());
			System.out.println("VideoInfo的bitRete(有bug)="+vInfo.getBitRate());
			System.out.println("AudioInfo的bitRete(有bug)="+aInfo.getBitRate());
			System.out.println("分辨率="+vInfo.getSize());
			
			System.out.println("audio channels="+aInfo.getChannels());
			System.out.println("audio decoder="+aInfo.getDecoder());
			System.out.println("audio samplingRate="+aInfo.getSamplingRate());
			
			EncodingAttributes enAttr = new EncodingAttributes(); 
			VideoAttributes vAttr = new VideoAttributes();
			AudioAttributes aAttr = new AudioAttributes();
			
			VideoSize vSize = new VideoSize(960, 640);
			
			enAttr.setFormat("mp4");
			
			//ffmpeg 默认的 bitRate 是 128kb/s, 防止视频本身低于这个值报错
			vAttr.setBitRate(mmInfo.getBitRate()*1000);
			if(vSize.getWidth()>vInfo.getSize().getWidth()||vSize.getHeight()>vInfo.getSize().getHeight()){
				vSize = new VideoSize(vInfo.getSize().getWidth(),vInfo.getSize().getHeight());
			}
			
			vAttr.setSize(vSize);
			aAttr.setChannels(aInfo.getChannels());
			aAttr.setSamplingRate(aInfo.getSamplingRate());
			enAttr.setVideoAttributes(vAttr);
			enAttr.setAudioAttributes(aAttr);
			encoder.encode(source, target, enAttr);
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
````
