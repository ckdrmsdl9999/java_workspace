/*
 * 이 기종간(시스템 기반이 틀린) 데이터 교환시 자주 사용되는
 * 고전적 텍스트 기반 데이터베이스인 xml을 자바에서 사용해 본다!!!
 * 
 * 
 * 일반적인 프로그램에서 파싱하는 방법은 크게 2가지가 있다
 * 
 * 1.DOM - html을 제어 할 수 있는 인터페이스
 * 				장점: 객체화 시켜 제어할 수 있어서 객체지향 언어 방식으로
 * 						프로그램이 가능하다.
 * 				단점: 메모리 사용량이 많기 때문에 특히 모바일 기기에서 불리하다 
 * 2.SAX - 실행부가 각 태그를 발견할때마다 적절한 이벤트가 발생하는 방식으로
 * 			   개발자는 이 이벤트 타이밍을 잡아내서 원하는 처리를 해줘야 하는 방식
 * 				장점- 속도가 빠르다(메모리를 덜 잡아먹음)
 * 				단점- 프로그램이 까다 롭다.!!
 * */

package com.sds.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ParserTest {
	//SAXParser가 추상 클래스이므로 직접 new 할 수 없고, 아래의 
	//SAXParserFactory 객체로 부터 인스턴스를 얻어와야 한다....
	SAXParserFactory factory;
	String path="C:/java_workspace/UrlLoad/res/member.xml";
	MyHandler handler= new MyHandler();
	
	public ParserTest() {
		factory= SAXParserFactory.newInstance();
		try {
			SAXParser parser=factory.newSAXParser();
			
			//어떤 파일을 해석할지, 또 이벤트 처리자는 누구인지 지정 하자!!!!!!
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










