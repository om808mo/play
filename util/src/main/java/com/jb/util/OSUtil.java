package com.jb.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;

/**
 * 操作系统命令操作工具类
 * 		window与linux系统都可
 * @author ljc
 * @date 2019-01-29
 */
public class OSUtil {
	private static final Logger LOG=Logger.getLogger(OSUtil.class);
	/**
	 * 执行单个命令
	 * 		in用来返回控制台上的信息
	 * 		out用来返回控制台上的错误信息
	 * @param command
	 * @return String
	 * @throws IOException
	 * @author ljc
	 * @date 2019-01-29
	 */
	public static String exec(String command) throws IOException{
		Runtime run = Runtime.getRuntime();
		InputStream in=null;
		InputStream error=null;
		StringBuffer out=new StringBuffer();
		try {
			 Process process=run.exec(command);
			 in=process.getInputStream();
			 error=process.getErrorStream();
			 byte[] b=new byte[8192];
			 int n=0;
			 while((n=in.read(b))!=-1){
				 out.append(new String(b,0,n));
			 }
			 n=0;
			 while((n=error.read(b))!=-1){
				 out.append(new String(b,0,n));
			 }
			 in.close();
			 error.close();
			 process.destroy();
		}catch(IOException e){
			throw e;
		}finally{
			if(in!=null){
				in.close();
			}
			if(error!=null){
				error.close();
			}
		}
		return out.toString();
	}	
}