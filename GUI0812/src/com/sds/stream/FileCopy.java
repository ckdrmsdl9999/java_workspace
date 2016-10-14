package com.sds.stream;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileCopy {
		FileReader reader;
		BufferedReader reader1;
		FileWriter writer;
		BufferedWriter writer1;
		String name;
		//String dest;

		public FileCopy(String a){
			//아래의 코드는 실행시 에러의 가능성을 안고 있는 코드이다.
			//따라서 올바르지 않은 경로를 실수로 넣을 경우 ,프로그램은
			//비정상 종료가 되어 버린다.
			//예외 처리의 목적은 비정상 종료의 방지!!
			name=a;
			try {
				writer.write(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
