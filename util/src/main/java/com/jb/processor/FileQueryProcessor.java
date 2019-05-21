package com.jb.processor;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.jb.enu.FileExtendNameEnum;
import com.jb.exception.FileNotIsDirectoryException;
/**
 * 文件查找处理器
 * @author ljc
 * @date 2019-05-21
 */
public class FileQueryProcessor {
	//临时文件夹路径
	private static final String TEMPLATE_PATH=FileQueryProcessor.class
			.getResource("/")
			.getPath()
			.replace("bin","template");
	
	/**
	 * 查找文件，并把找到的文件存放到默认临时目录中
	 * @param filePath
	 * 			查找文件的起始文件或目录的绝对路径
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @param filteredFileNames
	 * 			要过滤掉的文件或目录
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(String filePath,String fileName,List<String> filteredFileNames) 
			throws IOException,FileNotIsDirectoryException{
		query(new File(filePath),fileName,filteredFileNames,TEMPLATE_PATH);
	}
	/**
	 * 查找文件，并把找到的文件存放到默认临时目录中
	 * @param filePath
	 * 			查找文件的起始文件或目录的绝对路径
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(String filePath,String fileName) 
			throws IOException,FileNotIsDirectoryException{
		query(new File(filePath),fileName,TEMPLATE_PATH);
	}
	/**
	 * 查找文件，并把找到的文件存放到临时目录中
	 * @param filePath
	 * 			查找文件的起始文件或目录的绝对路径
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @param tempplatePutFilePath
	 * 			临时文件夹
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(String filePath,String fileName,String tempplatePutFilePath) 
			throws IOException,FileNotIsDirectoryException{
		query(new File(filePath),fileName,tempplatePutFilePath);
	}
	/**
	 * 查找文件，并把找到的文件存放到临时目录中
	 * @param filePath
	 * 			查找文件的起始文件或目录的绝对路径
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @param filteredFileNames
	 * 			要过滤掉的文件或目录
	 * @param tempplatePutFilePath
	 * 			临时文件夹
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(String filePath,String fileName,List<String> filteredFileNames,String tempplatePutFilePath) 
			throws IOException,FileNotIsDirectoryException{
		query(new File(filePath),fileName,filteredFileNames,tempplatePutFilePath);
	}
	/**
	 * 查找文件，并把找到的文件存放到默认临时目录中
	 * @param root
	 * 			查找文件的起始文件或目录
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @param filteredFileNames
	 * 			要过滤掉的文件或目录
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(File root,String fileName,List<String> filteredFileNames) 
			throws IOException,FileNotIsDirectoryException{
		query(root,fileName,filteredFileNames,TEMPLATE_PATH);
	}
	/**
	 * 查找文件，并把找到的文件存放到默认临时目录中
	 * @param root
	 * 			查找文件的起始文件或目录
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(File root,String fileName) 
			throws IOException,FileNotIsDirectoryException{
		query(root,fileName,TEMPLATE_PATH);
	}
	/**
	 * 查找文件，并把找到的文件存放到临时目录中
	 * @param root
	 * 			查找文件的起始文件或目录
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @param tempplatePutFilePath
	 * 			临时文件夹
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(File root,String fileName,String tempplatePutFilePath) 
			throws IOException,FileNotIsDirectoryException{
		if(root.isDirectory()){
			queryFromDirectory(root,fileName,tempplatePutFilePath);
		}else{
			queryFromOneFile(root,fileName,tempplatePutFilePath);
		}
	}
	/**
	 * 查找文件，并把找到的文件存放到临时目录中
	 * @param root
	 * 			查找文件的起始文件或目录
	 * @param fileName
	 * 			要查找的文件名（非目录）
	 * @param filteredFileNames
	 * 			要过滤掉的文件或目录
	 * @param tempplatePutFilePath
	 * 			临时文件夹
	 * @throws IOException
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void query(File root,String fileName,List<String> filteredFileNames,String tempplatePutFilePath) 
			throws IOException,FileNotIsDirectoryException{
		if(root.isDirectory()){
			queryFromDirectory(root,fileName,filteredFileNames,tempplatePutFilePath);
		}else{
			queryFromOneFile(root,fileName,tempplatePutFilePath);
		}
	}
	/**
	 * 清空临时文件夹
	 * @param path
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void clearTempDirectory() throws FileNotIsDirectoryException{
		clearTempDirectory(TEMPLATE_PATH);
	}
	/**
	 * 清空临时文件夹
	 * @param path
	 * @throws FileNotIsDirectoryException
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void clearTempDirectory(String path) throws FileNotIsDirectoryException{
		File root=new File(path);
		if(root.exists()){
			if(root.isDirectory()){
				File[] files=root.listFiles();
				for(File f:files){
					f.delete();
				}
			}else{
				throw new FileNotIsDirectoryException("不是目录！");
			}
		}else{
			root.mkdirs();
		}
	}
	/**
	 * 从文件夹中查找文件
	 * @param root
	 * @param fileName
	 * @param filteredFileNames
	 * @param tempplatePutFilePath
	 * @author ljc
	 * @date 2019-05-21
	 */
	private void queryFromDirectory(File root,String fileName,List<String> filteredFileNames,String tempplatePutFilePath){
		List<File> fileList=Arrays.asList(root.listFiles());
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
					query(f,fileName,filteredFileNames,tempplatePutFilePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	/**
	 * 从文件夹中查找文件
	 * @param root
	 * @param fileName
	 * @param tempplatePutFilePath
	 * @author ljc
	 * @date 2019-05-21
	 */
	private void queryFromDirectory(File root,String fileName,String tempplatePutFilePath){
		List<File> fileList=Arrays.asList(root.listFiles());
		fileList.stream()
			.forEach(f->{
				try {
					query(f,fileName,tempplatePutFilePath);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
	}
	/**
	 * 比对当前文件是否为要查找的文件，是则放入临时文件夹中
	 * @param root
	 * @param fileName
	 * @param tempplatePutFilePath
	 * 			临时文件夹
	 * @throws IOException
	 * @author ljc
	 * @date 2019-05-21 
	 */
	private void queryFromOneFile(File root,String fileName,String tempplatePutFilePath) throws IOException{
		String finedFileName=root.getName();
		if(fileName.equals(finedFileName)){
			String fileExtendName=fileName.substring(fileName.lastIndexOf(".")+1).toUpperCase();
			BufferedReader reader=null;
			BufferedWriter writer=null;
			try{
				reader=new BufferedReader(new FileReader(root));
				String line=null;
				List<String> lines=new ArrayList();
				// 按行读取字符串
				while((line=reader.readLine())!=null){
					lines.add(line);
				}
				File outFile=new File(tempplatePutFilePath+finedFileName);
				if(outFile.exists()){
					outFile=new File(tempplatePutFilePath+finedFileName+(int)Math.floor(Math.random()*100+1));
				}
				writer=new BufferedWriter(new FileWriter(outFile));
				if(FileExtendNameEnum.JAVA.toString().equals(fileExtendName)){
					writer.write("//"+root.getAbsolutePath()+"\r\n");
				}else{
					writer.write("<!-- "+root.getAbsolutePath()+" -->\r\n");
				}
				for(String l:lines){
					writer.write(l+"\r\n");
				}
				writer.flush();
				System.out.println(outFile.getAbsolutePath());
				System.out.println(root.getAbsolutePath());
			}catch(Exception e){
				e.printStackTrace();
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
	/**
	 * 比对当前文件是否为要查找的文件，是则放入默认临时文件夹中
	 * @param root
	 * @param fileName
	 * @throws IOException
	 * @author ljc
	 * @date 2019-05-21 
	 */
	private void queryFromOneFile(File root,String fileName) throws IOException{
		queryFromOneFile(root,fileName,TEMPLATE_PATH);
	}
}
