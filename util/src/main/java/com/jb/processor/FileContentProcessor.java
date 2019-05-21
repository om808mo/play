package com.jb.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.jb.enu.FileExtendNameEnum;
/**
 * 文本文件内容处理器
 * @author ljc
 * @date 2019-05-21
 *
 */
public class FileContentProcessor {
	/**
	 * 处理文件内容
	 * @param filePath
	 * 			文件绝对路径(可以是目录，或文件)
	 * @param filteredFileNames
	 *			过滤的文件，不需要处理的文件名或目录名
	 * @param processor
	 * 			字符串处理器
	 * @throws IOException
	 * @author ljc
	 * @date 2019-05-17
	 */
	public void process(String filePath,List<String> filteredFileNames,StringProcessor processor) 
			throws Exception{
		process(new File(filePath),filteredFileNames,processor);
	}
	/**
	 * 处理文件内容
	 * @param root
	 * 		文件或目录，如果目录则处理目录中的所有文件
	 * @param filteredFileNames
	 * 		过滤的文件，不需要处理的文件名或目录名
	 * @param processor
	 * 		字符串处理器
	 * @throws IOException
	 * @author ljc
	 * @date 2019-05-17
	 */
	public void process(File root,List<String> filteredFileNames,StringProcessor processor) 
			throws Exception{
		if(root.isDirectory()){
			processDirectory(root,filteredFileNames,processor);
		}else{
			processOneRealFile(root,processor);
		}
	}
	/**
	 * 处理文件内容
	 * @param filePath
	 * 			文件绝对路径(可以是目录，或文件)
	 * @param processor
	 * 			字符串处理器
	 * @throws IOException
	 * @author ljc
	 * @date 2019-05-17
	 */
	public void process(String filePath,StringProcessor processor) 
			throws Exception{
		process(new File(filePath),processor);
	}
	/**
	 * 处理文件内容
	 * @param root
	 * 		文件或目录，如果目录则处理目录中的所有文件
	 * @param processor
	 * 		字符串处理器
	 * @throws IOException
	 * @author ljc
	 * @date 2019-05-17
	 */
	public void process(File root,StringProcessor processor) 
			throws Exception{
		if(root.isDirectory()){
			processDirectory(root,processor);
		}else{
			processOneRealFile(root,processor);
		}
	}
	/**
	 * 处理一个目录
	 * @param directory
	 * @param filteredFileNames
	 * 		被过滤的目录或文件
	 * @param processor
	 * 		字符串处理器
	 * @author ljc
	 * @date 2019-05-21
	 */
	private void processDirectory(File directory,List<String> filteredFileNames,StringProcessor processor){
		List<File> fileList=Arrays.asList(directory.listFiles());
		fileList.stream()
			.filter(f->{
				boolean isFilterFile=false;
				for(int i=0;i<filteredFileNames.size();i++){
					if(f.getName().equals(filteredFileNames.get(i))){
						isFilterFile=true;
						break;
					}
				}
				return !isFilterFile;
			})
			.forEach(f->{
				try {
					process(f,filteredFileNames,processor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	/**
	 * 处理一个目录
	 * @param directory
	 * @param processor
	 * 		字符串处理器
	 * @author ljc
	 * @date 2019-05-21
	 */
	private void processDirectory(File directory,StringProcessor processor){
		List<File> fileList=Arrays.asList(directory.listFiles());
		fileList.stream()
			.forEach(f->{
				try {
					process(f,processor);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	/**
	 * 处理一个真正的文件（不是目录）
	 * @param file
	 * @param processor
	 * 		字符串处理器
	 * @author ljc
	 * @throws Exception 
	 * @date 2019-05-21
	 */
	private void processOneRealFile(File file,StringProcessor processor) throws Exception{
		BufferedReader reader=null;
		BufferedWriter writer=null;
		try{
			String fileName=file.getName();
			String fileExtendName=fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
			if(FileExtendNameEnum.MF.toString().equals(fileExtendName)
				||FileExtendNameEnum.HTML.toString().equals(fileExtendName)
				||FileExtendNameEnum.JAVA.toString().equals(fileExtendName)
				||FileExtendNameEnum.JS.toString().equals(fileExtendName)
				||FileExtendNameEnum.JSP.toString().equals(fileExtendName)){
				reader=new BufferedReader(new FileReader(file));
				String line=null;
				synchronized (processor) {
					List<String> lines=processor.getLines();
					lines.clear();
					// 按行读取字符串
					while((line=reader.readLine())!=null){
						lines.add(line);
					}
					processor.process();
					writer=new BufferedWriter(new FileWriter(file));
					for(String l:processor.getLines()){
						writer.write(l+"\r\n");
					}
					writer.flush();
				}
			}
		}catch(Exception e){
			throw e;
		}finally{
			if(reader!=null){
				reader.close();
			}
			if(writer!=null){
				writer.close();
			}
		}
	}
}
