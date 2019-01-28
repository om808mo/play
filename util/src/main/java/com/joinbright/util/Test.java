package com.joinbright.util;

import java.io.BufferedReader;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;
import java.util.Vector;

public class Test {
	public static void main(String[] args) {
		 Runtime run = Runtime.getRuntime();
		 try {
			 if(args.length!=0){
				 Process process=run.exec(args[0]);
				 InputStream in=process.getInputStream();
				 InputStream error=process.getErrorStream();
				 BufferedReader bs=new BufferedReader(new InputStreamReader(in));
				 StringBuffer out=new StringBuffer();
				 byte[] b=new byte[8192];
				 int n=0;
				 while((n=in.read(b))!=-1){
					 out.append(new String(b,0,n));
				 }
				 n=0;
				 while((n=error.read(b))!=-1){
					 out.append(new String(b,0,n));
				 }
				 System.out.println(out.toString());
				 in.close();
				 process.destroy();
			 }else{
				 System.out.println("���������");
			 }
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
}
