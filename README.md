# java-ffmpeg
@(Hehangjie)[互联网中尉 www.itlieutenant.com]

    Dependencies
    1、Install ffmpeg, Guide address - http://trac.ffmpeg.org/wiki/CompilationGuide/Centos
    2、Use H264 decoder, args like "-vcodec libx264", libx264 is opensource
    3、At last, Run cmd like "ffmpeg -i inputFile -vcodec libx264 outputFile"
    4、About default args, For example, Ffmpeg default bitrate arg is 200k, if you inputFile bitrate 
    lower then 200k, it will get a exception. So u must pay attention to this case.
    
    V0.0.1
    A maven project with a bitrate bug corrected ,which is refering to jave-1.0.2-src
    
    V0.0.4 
    在此前jave-1.0.2-src里封装的ffmpeg不支持h264，h265格式的vcodec。这对于原本是用于web项目的转码服务是致命的。
    在这个版本中对此做了修复，将ffmpeg分离出来（意味着你必须独立安装ffmpeg，以后这个项目只是java使用ffmpeg的sdk，
    不再提供ffmpeg的免安装服务）
    
    V0.0.5
    弃用了jave，改名java-ffmpeg
    
    v0.1.3
    Supported the u3m8 encode
    支持u3m8转码。
    新增了encode方法，可以直接传送ffmpeg参数。
    
    
``` java
public void encode(List<String> perCmds, File source, List<String> cmds, File tartget);
```
    
    调用方法见M3u7MacDemo.java
    
    
