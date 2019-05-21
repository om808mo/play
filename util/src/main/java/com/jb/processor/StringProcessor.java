package com.jb.processor;

import java.util.List;
/**
 * 字符串处理器
 * @author ljc
 * @date 2019-05-21
 */
public interface StringProcessor{
	/**
	 * 字符串处理
	 * @return void
	 * @throws Exception
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void process() throws Exception;
	/**
	 * 获取存放字符串的容器
	 * @return List<String>
	 * @author ljc
	 * @date 2019-05-21
	 */
	public List<String> getLines();
	/**
	 * 清空存放字符串的容器
	 * @return void
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void clear();
}
