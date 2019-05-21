package com.jb.util;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.jb.exception.IpNotRightException;
import com.jb.exception.PortNotRightException;
import com.jb.exception.PortStringNotRightException;

public class NetUtil {
	/*
	 * 验证端口号是否正确
	 * @param port
	 * @return boolean
	 * @author ljc
	 * @date 2019-02-21
	 */
	public static boolean verifyPort(int port){
		return port>=0&&port<=65535;
	}
	public static void main(String[] args) {
		String s="a";
		try{
			System.out.println(Integer.parseInt(s));
			
		}catch(Exception e){
		}
	}
	/*
	 * 验证IP是否正确
	 * @param ip
	 * @return boolean
	 * @author ljc
	 * @date 2019-02-21
	 */
	public static boolean verifyIp(String ip){
		String pattern="((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))).){3}(25[0-5]"
				+ "|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
		return Pattern.matches(pattern,ip);
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
	 * @throws PortNotRightException 
	 * @throws IOException 
	 * @throws PortStringNotRightException 
	 * @date 2019-01-29
	 */
	public static Map<Integer,Boolean> scan(String ip,String portString) 
			throws IOException, PortNotRightException, PortStringNotRightException{
		Map<Integer,Boolean> resultMap=new HashMap<Integer,Boolean>();
		if(portString.contains(",")){
			String[] ports=portString.split(",");
			for(int i=0;i<ports.length;i++){
				scan(ip,ports[i]);
			}
		}else if(portString.contains("-")){
			String[] portSection=portString.split("-");
			int startPort;
			int endPort;
			try{
				startPort=Integer.parseInt(portSection[0]);
				endPort=Integer.parseInt(portSection[1]);
			}catch(Exception e){
				throw new PortStringNotRightException("要求字符串为：单个端口，用-分割的端口区间，或前面两种的复合（用逗号分割）");
			}
			for(int port=startPort;port<=endPort;port++){
				resultMap.put(port,isConnect(ip,port));
			}
		}else{
			int port;
			try{
				port=Integer.parseInt(portString);
			}catch(Exception e){
				throw new PortStringNotRightException("要求字符串为：单个端口，用-分割的端口区间，或前面两种的复合（用逗号分割）");
			}
			resultMap.put(port,isConnect(ip,port));
		}
		return resultMap;
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
	public static boolean ping(String ip) 
			throws IpNotRightException, UnknownHostException, IOException{
		if(!verifyIp(ip)){
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
	public static boolean isConnect(String ip,int port) 
			throws IOException,PortNotRightException{
		boolean flag=false;
		if(verifyPort(port)){
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
}