package com.joinbright.socket;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.joinbright.exception.PortNotRightException;
import com.joinbright.util.NetUtil;
 
public class ChatServer {
	private static final Logger LOG=Logger.getLogger(NetUtil.class);
	public static void main(String[] args) throws IOException,NumberFormatException,PortNotRightException{
		if(args.length==0){
			LOG.info("Usage:ChatServer port");
		}else{
			int port=Integer.parseInt(args[0]);
			//验证端口号是否为合法
			NetUtil.verifyPort(port);
			//创建一个服务器
			LOG.info("等待客户端连接...");
			PrintWriter pwtoclien=null;
			Scanner keybordscanner=null;
			Scanner inScanner=null;
			ServerSocket ss=null;
			try {
				ss=new ServerSocket(port);
				//创建一个接收连接客户端的对象
				Socket socket=ss.accept();
				LOG.info(socket.getInetAddress()+"已成功连接到此台服务器上。");
				//字符输出流
				pwtoclien=new PrintWriter(socket.getOutputStream());
				pwtoclien.println("已成功连接到远程服务器！\t请您先发言。");
				pwtoclien.flush();
				keybordscanner=new Scanner(System.in);
				inScanner=new Scanner(socket.getInputStream());
				//阻塞等待客户端发送消息过来
				while(inScanner.hasNextLine()){
					String indata=inScanner.nextLine();
					if("Byebye".equals(indata)){
						break;
					}
					LOG.info("客户端："+indata);
					LOG.info("我(服务端)：");
					String keyborddata=keybordscanner.nextLine();
					pwtoclien.println(keyborddata);
					pwtoclien.flush();
				}
			}catch (IOException e){
				throw e;
			}finally{
				pwtoclien.close();
				keybordscanner.close();
				inScanner.close();
				try {
					ss.close();
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}
}