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
			//��֤�˿ں��Ƿ�Ϊ�Ϸ�
			NetUtil.verifyPort(port);
			//����һ��������
			LOG.info("�ȴ��ͻ�������...");
			PrintWriter pwtoclien=null;
			Scanner keybordscanner=null;
			Scanner inScanner=null;
			ServerSocket ss=null;
			try {
				ss=new ServerSocket(port);
				//����һ���������ӿͻ��˵Ķ���
				Socket socket=ss.accept();
				LOG.info(socket.getInetAddress()+"�ѳɹ����ӵ���̨�������ϡ�");
				//�ַ������
				pwtoclien=new PrintWriter(socket.getOutputStream());
				pwtoclien.println("�ѳɹ����ӵ�Զ�̷�������\t�����ȷ��ԡ�");
				pwtoclien.flush();
				keybordscanner=new Scanner(System.in);
				inScanner=new Scanner(socket.getInputStream());
				//�����ȴ��ͻ��˷�����Ϣ����
				while(inScanner.hasNextLine()){
					String indata=inScanner.nextLine();
					if("Byebye".equals(indata)){
						break;
					}
					LOG.info("�ͻ��ˣ�"+indata);
					LOG.info("��(�����)��");
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