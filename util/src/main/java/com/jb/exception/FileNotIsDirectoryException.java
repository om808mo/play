package com.jb.exception;
/**
 * 文件不是目录异常
 * @author ljc
 * @date 2019-05-21
 */
public class FileNotIsDirectoryException extends Exception{
	
	private static final long serialVersionUID = -3930636771012493388L;
	
	public FileNotIsDirectoryException(){
		super();
	}
	public FileNotIsDirectoryException(String mess){
		super(mess);
	}
}