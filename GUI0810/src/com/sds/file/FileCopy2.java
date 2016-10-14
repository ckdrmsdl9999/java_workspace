/*
사진을 실행중인 자바 프로그램으로 읽어들여,
원하는 디렉토리에 복원시키자 = 복사

자바에서는 입출력과 관련된 기능을 패키지 java.io패키지에서
지원한다.

이 예제에서는 여러 입출력 관련 기능 중 파일을 대상으로 한 io처리를
실습해 보자!!


*/


package com.sds.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

class FileCopy2{
	FileInputStream fis;//파일을 대상으로 한 입력스트림
	FileOutputStream fos;//파일을 대상으로 한 출력 스트림
	String name;
	String dest;

	public FileCopy2(String a,String b){
		//아래의 코드는 실행시 에러의 가능성을 안고 있는 코드이다.
		//따라서 올바르지 않은 경로를 실수로 넣을 경우 ,프로그램은
		//비정상 종료가 되어 버린다.
		//예외 처리의 목적은 비정상 종료의 방지!!
		this.name=a;
		this.dest=b;
		try{
			fis=new FileInputStream(name);
			fos=new FileOutputStream(dest);

			int data;//읽어들인 알갱이 1알을 받기위한 변수
			
			while ((data=fis.read()) !=-1){
				
				//System.out.println(data);
				//방금 읽어들인 데이터를 빈 파일에 출력하자!!
				fos.write(data); //1byte 출력

			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();//printStackTrace 개발자가 에러를 확인하기 위해 서용
										// stack구조로 에러 발생한 정보를 보여줌.
			System.out.println("존재하지 않는 경로네요!");
		}catch(IOException e){
			System.out.println("파일을 읽기 에러");
			
		}finally{
			/*
			예외 처리시 try 문이건 catch문이 수행된 이후 무조건 처리해야할 로직이 있다면
			finally에서 작업해준다!!
			finally란?
			try 문이나 catch문을 진입한 실행부는 반드시 finally에 도달하게 되어있다.
			따라서 반드시 처리해야할 작업에 사용될 수 있다.
			사실상 거의 대부분 db 닫는 작업 , 스트림 닫는 작업에 압도적으로 사용 된다.
			*/
			if(fos!=null){
				try{
					fos.close();//스트림 닫기
					
				}catch(IOException e){
				}
			}
			if(fis!=null){
				try{
					fis.close();//스트림 닫기
				}catch(IOException e){
				}
			}
		}
		
	}


	public static void main(String[] args) {
		new FileCopy2();
	}
}
