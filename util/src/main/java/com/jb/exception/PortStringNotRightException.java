package com.jb.exception;
/**
 * �˿��ַ�������ȷ�쳣
 * @author ljc
 * @date 2019-05-21
 */
public class PortStringNotRightException extends Exception{
	
	private static final long serialVersionUID = 7362365518344580806L;
	
	public PortStringNotRightException(){
		super();
	}
	public PortStringNotRightException(String mess){
		super(mess);
	}
}
