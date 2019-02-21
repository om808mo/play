package com.joinbright.util;

import java.util.regex.Pattern;

import com.joinbright.exception.IpNotRightException;
import com.joinbright.exception.PortNotRightException;

public class NetUtil {
	/*
	 * ��֤�˿ں��Ƿ���ȷ
	 * @param port
	 * @return void
	 * @throw PortNotRightException
	 * @author ljc
	 * @date 2019-02-21
	 */
	public static void verifyPort(int port) throws PortNotRightException{
		if(port<-1&&port>65535){
			throw new PortNotRightException();
		}
	}
	/*
	 * ��֤IP�Ƿ���ȷ
	 * @param ip
	 * @return void
	 * @throw PortNotRightException
	 * @author ljc
	 * @date 2019-02-21
	 */
	public static void verifyIp(String ip) throws IpNotRightException{
		String pattern="((25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))).){3}(25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))";
		if(!Pattern.matches(pattern,ip)){
			throw new IpNotRightException();
		}	
	}
}
