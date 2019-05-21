package com.jb.processor;

import java.util.ArrayList;
import java.util.List;
/**
 * 字符串替换处理器
 * 		对于放于字符串容器中的所有字符串进行替换
 * @author ljc
 * @date 2019-05-21
 *
 */
public class StringReplaceProcessor implements StringProcessor{
	private String oldContent;
	private String newContent;
	private List<String> lines;
	private final List<String> tempList=new ArrayList<String>();

	public StringReplaceProcessor(){};
	
	public StringReplaceProcessor(List<String> lines,String oldContent,String newContent){
		this.lines=lines;
		this.oldContent=oldContent;
		this.newContent=newContent;
	}
	/**
	 * 清空存放字符串的容器
	 * @return void
	 * @author ljc
	 * @date 2019-05-21
	 */
	public void clear(){
		this.lines.clear();
	}
	/**
	 * 字符串替换
	 * 		用newContent替换oldContent
	 * @author ljc
	 * @date 2019-05-17
	 */
	public void process() throws Exception{
		this.tempList.clear();
		this.lines.stream().map(line->line.replaceAll(this.oldContent,this.newContent))
			.forEach(line->tempList.add(line));
		this.lines.clear();
		this.lines.addAll(tempList);
	}
	/**
	 * 获取存放字符串的容器
	 * @return List<String>
	 * @author ljc
	 * @date 2019-05-21
	 */
	public List<String> getLines() {
		return lines;
	}
	
	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	String getOldContent() {
		return oldContent;
	}
	
	void setOldContent(String oldContent) {
		this.oldContent = oldContent;
	}
	
	String getNewContent() {
		return newContent;
	}
	
	void setNewContent(String newContent) {
		this.newContent = newContent;
	}
}
