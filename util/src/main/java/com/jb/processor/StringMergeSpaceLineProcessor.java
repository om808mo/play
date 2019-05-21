package com.jb.processor;

import java.util.ArrayList;
import java.util.List;

public class StringMergeSpaceLineProcessor implements StringProcessor,Cloneable{
	private List<String> lines;
	private final List<String> tempList=new ArrayList<String>();
	
	public StringMergeSpaceLineProcessor(){}
	public StringMergeSpaceLineProcessor(List<String> lines){
		this.lines=lines;
	}
	
	/**
	 * 参数parms[0]为List<String>，表示多行文字,每个元素代表一行
	 * 参数parms[1]为String,表示当前的一行文字
	 * List<String>中的最后一个元素为上一行文字
	 * 		1、当前行如果是空行且无上一行，则当前行不加入List<String>容器中；
	 * 		2、当前行如果为空行且上一行也是空行，则当前行不加入List<String>容器中；
	 * 		3、其它情况，则当前行加入到List<String>容器中；
	 * @param parms
	 * @return Object
	 * @throws Exception
	 */
	public void process() throws Exception{
		tempList.clear();
		for(int i=0;i<this.lines.size();i++){
			String line=this.lines.get(i);
			String l=new String(line.getBytes(),"utf-8");
			int currentLineLength=line.trim().length();
			if(tempList.isEmpty()){
				if(currentLineLength==0){
				}else{
					tempList.add(l);
				}
			}else{
				String lastLine=tempList.get(tempList.size()-1);
				if(currentLineLength==0&&((String)lastLine).trim().length()==0){
				}else{
					tempList.add(l);
				}
			}
		}
		this.lines.clear();
		this.lines.addAll(tempList);
	}
	public List<String> getLines() {
		return this.lines;
	}
	public void setLines(List<String> lines) {
		this.lines = lines;
	}
	
	public void clear() {
		this.lines.clear();
	}
}
