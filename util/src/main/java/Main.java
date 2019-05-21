import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jb.processor.FileContentProcessor;
import com.jb.processor.FileQueryProcessor;


public class Main {
	
	private static final Logger LOG=Logger.getLogger(Main.class);
	
	public static void main(String[] args) throws Exception {
		List<String> filteredFileNames=new ArrayList<String>();
		filteredFileNames.add("bin");
		filteredFileNames.add(".metadata");
		filteredFileNames.add("bp-osgi");
		filteredFileNames.add(".settings");
		filteredFileNames.add("lib");
		filteredFileNames.add("config");
		filteredFileNames.add("Main.java");
		FileContentProcessor fcProcessor=new FileContentProcessor();
		FileQueryProcessor fqProcessor=new FileQueryProcessor();
		//文件替换
//		fcProcessor.process("E:/f1/xny",filteredFileNames,
//				new StringReplaceProcessor(new ArrayList<String>(),"commonWeb/scripts/sbtz/sbtzwh_cs_fn.js",
//						"commonWeb/scripts/zygl/sbtz/sbtzwh_cs_fn.js"));
		//合并空行
//		fcProcessor.process("d:/com.jb.f1.car",filteredFileNames,
//				new StringMergeSpaceLineProcessor(new ArrayList<String>()));
		List<String> filteredFile=new ArrayList<String>();
		filteredFile.add("bin");
		filteredFile.add(".metadata");
		filteredFile.add("bp-osgi");
		filteredFile.add(".settings");
		filteredFile.add("lib");
		filteredFile.add("config");
		//清空临时目录，当前项目的根目录，与src同一目录
		fqProcessor.clearTempDirectory();
		//文件查找,存放在临时目录
		fqProcessor.query("E:/svn/program/platform_xm","bzjjb.js",filteredFile);
	}
//	public static void main(String[] args) 
//			throws UnknownHostException,IpNotRightException,IOException,
//			PortNotRightException, PortStringNotRightException {
//		if(args.length==0){
//			LOG.info("--help query usage!");
//		}else{
//			if(args.length==1){
//				if("--help".equals(args[0])){
//					LOG.info("Usage:[option] ...");
//					LOG.info("-s		exec system command!");
//					LOG.info("-p		scan port");
//					LOG.info("e.g:java -jar util.jar 'pwd'");
//					LOG.info("e.g:java -jar util.jar 192.168.10.121 10-24");
//				}else if("-s".equals(args[0])){
//					LOG.info("Input command!");
//				}else if("-p".equals(args[0])){
//					LOG.info("Input ip and port!");
//				}else{
//					LOG.info("invalid option");
//					LOG.info("--help query usage!");
//				}
//			}else{
//				if("-s".equals(args[0])){
//					LOG.info(OSUtil.exec(args[1]));
//				}else if("-p".equals(args[0])){
//					if(args.length==3){
//						String ip=args[1];
//						if(NetUtil.ping(ip)){
//							NetUtil.scan(ip,args[2]);
//						}
//					}else{
//						LOG.info("invalid option");
//						LOG.info("--help query usage!");
//					}
//				}
//			}
//		}
//	}
}
