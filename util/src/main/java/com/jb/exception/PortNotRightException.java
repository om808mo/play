package com.jb.exception;
/**
 * port不正确异常
 * @author ljc
 * @date 2019-02
 *
 */
public class PortNotRightException extends Exception{
	
	private static final long serialVersionUID = 966443687919875660L;
	
	public PortNotRightException(){
		super();
	}
	public PortNotRightException(String mess){
		super(mess);
	}
}