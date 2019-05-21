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
			//��֤ip�Ƿ�Ϸ�
			NetUtil.verifyIp(ip);
			//��֤�˿ں��Ƿ�Ϊ�Ϸ�
			NetUtil.verifyPort(port);
			LOG.info("�������������������...");
			Socket socket=null;
			Scanner keybordscanner=null;
			Scanner inScanner=null;
			PrintWriter pwtoserver=null;
			try {
				socket=new Socket(ip,port);
				inScanner=new Scanner(socket.getInputStream()); 
				LOG.info(inScanner.nextLine());
				pwtoserver=new PrintWriter(socket.getOutputStream());
				LOG.info("��(�ͻ���)��");
				//�ȶ�ȡ����¼�뷽�������˷�����Ϣ
				keybordscanner=new Scanner(System.in);
				while(keybordscanner.hasNextLine()){
					String keyborddata=keybordscanner.nextLine();
					//д������˵ĵĿ���̨
					pwtoserver.println(keyborddata);
					pwtoserver.flush();
					if("Byebye".equals(keyborddata)){
						break;
					}else{
						//�����ȴ����շ���˵���Ϣ
						String indata=inScanner.nextLine();
						LOG.info("����ˣ�"+indata);
						LOG.info("��(�ͻ���)��");
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
