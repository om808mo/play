package com.joinbright.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.joinbright.exception.IpNotRightException;
import com.joinbright.exception.PortNotRightException;

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
	/**
	 * 扫描ip的端口
	 * @param ip
	 * @param portString
	 * 			可以是单个端口
	 * 				22
	 * 			可以是端口段
	 * 				22-1024
	 * 			可以是多个端口,用逗号分隔
	 * 				22,43,45
	 * 			可以是上述的组合
	 * 				22,45-100,1024,7001
	 * @return void
	 * @author ljc
	 * @date 2019-01-29
	 */
	public static void scan(String ip,String portString){
		if(portString.contains("-")){
			String[] portSection=portString.split("-");
			int startPort=Integer.parseInt(portSection[0]);
			int endPort=Integer.parseInt(portSection[1]);
			for(int port=startPort;port<=endPort;port++){
				scanOnePort(ip,port);
			}
		}else if(portString.contains(",")){
			String[] ports=portString.split(",");
			for(int i=0;i<ports.length;i++){
				scan(ip,ports[i]);
			}
		}else{
			int port=Integer.parseInt(portString);
			scanOnePort(ip,port);
		}
	}
	/**
	 * 测试ip是否连通
	 * @param ipAddress
	 * @return boolean
	 * @throws Exception
	 * @return boolean
	 * 		当返回值是true时,说明host是可用的,false则不可
	 * @author ljc
	 * @throws IpNotRightException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @date 2019-01-29
	 */
	public static boolean ping(String ip) throws IpNotRightException, UnknownHostException, IOException{
		//校验ip
		String pattern="((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))).){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
		if(!Pattern.matches(pattern,ip)){
			throw new IpNotRightException();
		}
		return InetAddress.getByName(ip).isReachable(3000);
	}
	/**
	 * 扫描ip的端口
	 * @param ip
	 * @param port
	 * 		单个端口
	 * @author ljc
	 * @throws IOException 
	 * @throws PortNotRightException
	 * 			端口为0-65536，当端口不正确时抛出异常
	 * @date 2019-01-29
	 */
	public static boolean isConnect(String ip,int port) throws IOException,PortNotRightException{
		boolean flag=false;
		if(port>=0&&port<=65535){
			Socket socket=null;
			try{
				socket=new Socket(ip,port);
				flag=true;
			}catch(Exception e){
				flag=false;
			}finally{
				if(socket!=null){
					socket.close();
				}
			}
		}else{
			throw new PortNotRightException();
		}
		return flag;
	}
	/**
	 * 扫描单个port
	 * @param ip
	 * @param port
	 * @author ljc
	 * @date 2019-01-29
	 */
	private static void scanOnePort(String ip,int port){
		try {
			if(isConnect(ip,port)){
				LOG.info(ip+"'s port "+port+" is connected!");
			}else{
				LOG.info(ip+"'s port "+port+" is not connected!");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (PortNotRightException e) {
			e.printStackTrace();
		}
	}
}
