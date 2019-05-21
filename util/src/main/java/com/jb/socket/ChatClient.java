package com.jb.socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.jb.exception.IpNotRightException;
import com.jb.exception.PortNotRightException;
import com.jb.util.NetUtil;
 
public class ChatClient {
	private static final Logger LOG=Logger.getLogger(ChatClient.class);
	public static void main(String[] args) throws IpNotRightException, PortNotRightException, IOException {
		if(args.length<2){
			LOG.info("Usage:ChatClient ip port");
		}else{
			String ip=args[0];
			int port=Integer.parseInt(args[1]);
			//验证ip是否合法
			NetUtil.verifyIp(ip);
			//验证端口号是否为合法
			NetUtil.verifyPort(port);
			LOG.info("正在向服务器请求连接...");
			Socket socket=null;
			Scanner keybordscanner=null;
			Scanner inScanner=null;
			PrintWriter pwtoserver=null;
			try {
				socket=new Socket(ip,port);
				inScanner=new Scanner(socket.getInputStream()); 
				LOG.info(inScanner.nextLine());
				pwtoserver=new PrintWriter(socket.getOutputStream());
				LOG.info("我(客户端)：");
				//先读取键盘录入方可向服务端发送消息
				keybordscanner=new Scanner(System.in);
				while(keybordscanner.hasNextLine()){
					String keyborddata=keybordscanner.nextLine();
					//写到服务端的的控制台
					pwtoserver.println(keyborddata);
					pwtoserver.flush();
					if("Byebye".equals(keyborddata)){
						break;
					}else{
						//阻塞等待接收服务端的消息
						String indata=inScanner.nextLine();
						LOG.info("服务端："+indata);
						LOG.info("我(客户端)：");
					}
				}
			} catch (UnknownHostException e) {
				throw e;
			} catch (IOException e) {
				throw e;
			}finally {
				keybordscanner.close();
				pwtoserver.close();
				inScanner.close();
				try {
					socket.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}
}
