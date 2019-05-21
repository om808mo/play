package com.jb.exception;
/**
 * Ip不正确异常
 * @author ljc
 * @date 2019-02
 */
public class IpNotRightException extends Exception{
	private static final long serialVersionUID = 3438251711217168585L;
	
	public IpNotRightException(){
		super();
	}
	public IpNotRightException(String mess){
		super(mess);
	}
}