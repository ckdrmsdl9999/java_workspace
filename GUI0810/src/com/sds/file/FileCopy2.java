/*
������ �������� �ڹ� ���α׷����� �о�鿩,
���ϴ� ���丮�� ������Ű�� = ����

�ڹٿ����� ����°� ���õ� ����� ��Ű�� java.io��Ű������
�����Ѵ�.

�� ���������� ���� ����� ���� ��� �� ������ ������� �� ioó����
�ǽ��� ����!!


*/


package com.sds.file;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;

class FileCopy2{
	FileInputStream fis;//������ ������� �� �Է½�Ʈ��
	FileOutputStream fos;//������ ������� �� ��� ��Ʈ��
	String name;
	String dest;

	public FileCopy2(String a,String b){
		//�Ʒ��� �ڵ�� ����� ������ ���ɼ��� �Ȱ� �ִ� �ڵ��̴�.
		//���� �ùٸ��� ���� ��θ� �Ǽ��� ���� ��� ,���α׷���
		//������ ���ᰡ �Ǿ� ������.
		//���� ó���� ������ ������ ������ ����!!
		this.name=a;
		this.dest=b;
		try{
			fis=new FileInputStream(name);
			fos=new FileOutputStream(dest);

			int data;//�о���� �˰��� 1���� �ޱ����� ����
			
			while ((data=fis.read()) !=-1){
				
				//System.out.println(data);
				//��� �о���� �����͸� �� ���Ͽ� �������!!
				fos.write(data); //1byte ���

			}
			
		}catch(FileNotFoundException e){
			e.printStackTrace();//printStackTrace �����ڰ� ������ Ȯ���ϱ� ���� ����
										// stack������ ���� �߻��� ������ ������.
			System.out.println("�������� �ʴ� ��γ׿�!");
		}catch(IOException e){
			System.out.println("������ �б� ����");
			
		}finally{
			/*
			���� ó���� try ���̰� catch���� ����� ���� ������ ó���ؾ��� ������ �ִٸ�
			finally���� �۾����ش�!!
			finally��?
			try ���̳� catch���� ������ ����δ� �ݵ�� finally�� �����ϰ� �Ǿ��ִ�.
			���� �ݵ�� ó���ؾ��� �۾��� ���� �� �ִ�.
			��ǻ� ���� ��κ� db �ݴ� �۾� , ��Ʈ�� �ݴ� �۾��� �е������� ��� �ȴ�.
			*/
			if(fos!=null){
				try{
					fos.close();//��Ʈ�� �ݱ�
					
				}catch(IOException e){
				}
			}
			if(fis!=null){
				try{
					fis.close();//��Ʈ�� �ݱ�
				}catch(IOException e){
				}
			}
		}
		
	}


	public static void main(String[] args) {
		new FileCopy2();
	}
}
