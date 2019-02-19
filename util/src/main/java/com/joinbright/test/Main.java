package com.joinbright.test;
import java.io.IOException;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import com.joinbright.exception.IpNotRightException;
import com.joinbright.util.OSUtil;
/**
 * 测试功能
 * @author ljc
 *
 */
public class Main {
	private static final Logger LOG=Logger.getLogger(Main.class);
	public static void main(String[] args) throws UnknownHostException,IpNotRightException,IOException {
		if(args.length==0){
			LOG.info("--help query usage!");
		}else{
			if(args.length==1){
				if("--help".equals(args[0])){
					LOG.info("Usage:[option] ...");
					LOG.info("-s		exec system command!");
					LOG.info("-p		scan port");
					LOG.info("e.g:java -jar util.jar 'pwd'");
					LOG.info("e.g:java -jar util.jar 192.168.10.121 10-24");
				}else if("-s".equals(args[0])){
					LOG.info("Input command!");
				}else if("-p".equals(args[0])){
					LOG.info("Input ip and port!");
				}else{
					LOG.info("invalid option");
					LOG.info("--help query usage!");
				}
			}else{
				if("-s".equals(args[0])){
					LOG.info(OSUtil.exec(args[1]));
				}else if("-p".equals(args[0])){
					if(args.length==3){
						String ip=args[1];
						if(OSUtil.ping(ip)){
							OSUtil.scan(ip,args[2]);
						}
					}else{
						LOG.info("invalid option");
						LOG.info("--help query usage!");
					}
				}
			}
		}
	}
}
