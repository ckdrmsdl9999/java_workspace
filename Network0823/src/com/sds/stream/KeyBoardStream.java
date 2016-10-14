/*
  Stream 이란?
  데이터의 흐름
  
  분류
  1) 방향성에 따른 분류(실행중인 프로그램을 기준 = 프로세스)
  	  -입력 (Input)
      -출력 (Output)	
  
  2)데이터 처리 방법에 따른 분류
     -바이트 기반 스트림 : 기본 스트림이며 1byte씩 이해하는 스트림 
     	단점 )미국에서는 문제 없지만 타 언어에서는 1byte로 처리 가 안됨
     
     -문자 기반 스트림
       2byte씩 처리 하도록 업그레이드 한 스트림
       비영어권 문자(2byte씩 처리)도 표현할 수 있음 
  
   -버퍼기반 스트림
   		데이터 단위의 끝을 발견할때만, 버퍼에 모아둔 데이터를
   		읽어들이도록 처리된 스트림
   		
   		
 * */

package com.sds.stream;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class KeyBoardStream {
	InputStream is; 
	InputStreamReader reader;
	BufferedReader buffr;
	int count;
	String data1=null;

	public KeyBoardStream() {
		//Keyboard, 바코드 스태너 등 시스템이 제어하는 스트림은
		//개발자가 원하는 시점에 마음대로 생성할 수 없다!!
		//따라서 시스템으로 부터 얻어와야 한다!!
		
		is= System.in;//시스템에 연결된 키보드 스트림을 얻어옴
		reader= new InputStreamReader(is);
		buffr = new BufferedReader(reader);
		int data;
		
		try {
			while(true){
				data1=buffr.readLine();
				System.out.println(data1);
				count++;
				System.out.print("읽혀진 횟수"+count);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new KeyBoardStream();
	}

}
