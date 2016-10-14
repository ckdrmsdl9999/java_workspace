/*
 * �� ������(�ý��� ����� Ʋ��) ������ ��ȯ�� ���� ���Ǵ�
 * ������ �ؽ�Ʈ ��� �����ͺ��̽��� xml�� �ڹٿ��� ����� ����!!!
 * 
 * 
 * �Ϲ����� ���α׷����� �Ľ��ϴ� ����� ũ�� 2������ �ִ�
 * 
 * 1.DOM - html�� ���� �� �� �ִ� �������̽�
 * 				����: ��üȭ ���� ������ �� �־ ��ü���� ��� �������
 * 						���α׷��� �����ϴ�.
 * 				����: �޸� ��뷮�� ���� ������ Ư�� ����� ��⿡�� �Ҹ��ϴ� 
 * 2.SAX - ����ΰ� �� �±׸� �߰��Ҷ����� ������ �̺�Ʈ�� �߻��ϴ� �������
 * 			   �����ڴ� �� �̺�Ʈ Ÿ�̹��� ��Ƴ��� ���ϴ� ó���� ����� �ϴ� ���
 * 				����- �ӵ��� ������(�޸𸮸� �� ��Ƹ���)
 * 				����- ���α׷��� ��� �Ӵ�.!!
 * */

package com.sds.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParserTest {
	//SAXParser�� �߻� Ŭ�����̹Ƿ� ���� new �� �� ����, �Ʒ��� 
	//SAXParserFactory ��ü�� ���� �ν��Ͻ��� ���;� �Ѵ�....
	SAXParserFactory factory;
	String path="C:/java_workspace/UrlLoad/res/member.xml";
	MyHandler handler= new MyHandler();
	
	public ParserTest() {
		factory= SAXParserFactory.newInstance();
		try {
			SAXParser parser=factory.newSAXParser();
			
			//� ������ �ؼ�����, �� �̺�Ʈ ó���ڴ� �������� ���� ����!!!!!!
			parser.parse(new File(path), handler);
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new ParserTest();
	}

}










