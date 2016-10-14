/*
  Stream �̶�?
  �������� �帧
  
  �з�
  1) ���⼺�� ���� �з�(�������� ���α׷��� ���� = ���μ���)
  	  -�Է� (Input)
      -��� (Output)	
  
  2)������ ó�� ����� ���� �з�
     -����Ʈ ��� ��Ʈ�� : �⺻ ��Ʈ���̸� 1byte�� �����ϴ� ��Ʈ�� 
     	���� )�̱������� ���� ������ Ÿ ������ 1byte�� ó�� �� �ȵ�
     
     -���� ��� ��Ʈ��
       2byte�� ó�� �ϵ��� ���׷��̵� �� ��Ʈ��
       �񿵾�� ����(2byte�� ó��)�� ǥ���� �� ���� 
  
   -���۱�� ��Ʈ��
   		������ ������ ���� �߰��Ҷ���, ���ۿ� ��Ƶ� �����͸�
   		�о���̵��� ó���� ��Ʈ��
   		
   		
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
		//Keyboard, ���ڵ� ���³� �� �ý����� �����ϴ� ��Ʈ����
		//�����ڰ� ���ϴ� ������ ������� ������ �� ����!!
		//���� �ý������� ���� ���;� �Ѵ�!!
		
		is= System.in;//�ý��ۿ� ����� Ű���� ��Ʈ���� ����
		reader= new InputStreamReader(is);
		buffr = new BufferedReader(reader);
		int data;
		
		try {
			while(true){
				data1=buffr.readLine();
				System.out.println(data1);
				count++;
				System.out.print("������ Ƚ��"+count);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new KeyBoardStream();
	}

}
