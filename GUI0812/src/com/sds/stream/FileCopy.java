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
			//�Ʒ��� �ڵ�� ����� ������ ���ɼ��� �Ȱ� �ִ� �ڵ��̴�.
			//���� �ùٸ��� ���� ��θ� �Ǽ��� ���� ��� ,���α׷���
			//������ ���ᰡ �Ǿ� ������.
			//���� ó���� ������ ������ ������ ����!!
			name=a;
			try {
				writer.write(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
