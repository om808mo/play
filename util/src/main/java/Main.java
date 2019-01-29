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
			System.out.println("--help query usage!");
		}else{
			if(args.length==1){
				if("--help".equals(args[0])){
					System.out.println("Usage:[option] ...");
					System.out.println("-s		exec system command!");
					System.out.println("-p		scan port");
					System.out.println("e.g:java -jar util.jar 'pwd'");
					System.out.println("e.g:java -jar util.jar 192.168.10.121 10-24");
				}else if("-s".equals(args[0])){
					System.out.println("Input command!");
				}else if("-p".equals(args[0])){
					System.out.println("Input ip and port!");
				}else{
					System.out.println("invalid option");
					System.out.println("--help query usage!");
				}
			}else{
				if("-s".equals(args[0])){
					System.out.println(OSUtil.exec(args[1]));
				}else if("-p".equals(args[0])){
					if(args.length==3){
						String ip=args[1];
						if(OSUtil.ping(ip)){
							OSUtil.scan(ip,args[2]);
						}
					}else{
						System.out.println("invalid option");
						System.out.println("--help query usage!");
					}
				}
			}
		}
	}
}
