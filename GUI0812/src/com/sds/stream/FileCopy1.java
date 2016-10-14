package com.sds.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy1 {
	StringBuffer sb;
	public FileCopy1(String a,String b) {
		
		FileReader fr =null;
		FileWriter fw=null;
		
		BufferedReader br = null;
		BufferedWriter bw = null;
		
		try{
			fr=new FileReader(a);
			br=new BufferedReader(fr);

			fw= new FileWriter(b);
			bw=new BufferedWriter(fw);
			
			
			sb=new StringBuffer();
			String s=null;
			
			while((s=br.readLine())!=null){
				sb.append(s);
				String k=sb.toString();
				bw.write(k);
				bw.newLine();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(br !=null) try{br.close();}catch(IOException e){};
			if(fr !=null) try{fr.close();}catch(IOException e){};

			if(bw !=null) try{bw.close();}catch(IOException e){};
			if(fw !=null) try{fw.close();}catch(IOException e){};
		}
		
	}
}
