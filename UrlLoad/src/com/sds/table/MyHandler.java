package com.sds.table;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler{

	MyModel model;
	String[] data;
	
	//실행부가 어느 태그를 지나가고 있는지를 체크할 수 있는 변수
	boolean name;
	boolean age;
	boolean gender;
	
	public MyHandler(MyModel model) {
		this.model=model;
	}
	//시작 태그가 발견되면 호출되는 메서드
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		
		if(qName.equalsIgnoreCase("member")){
			System.out.println(qName);
			//일차원 배열을 만든다!!!
			data=new String[3];
		}else if(qName.equalsIgnoreCase("name")){
			name= true; //name을 지나가고 있음 을 알려주자
		}else if(qName.equalsIgnoreCase("age")){
			age=true;
		}else if(qName.equalsIgnoreCase("gender")){
			gender=true;
		}
	}
	//태그의 값이 발견되면 호출되는 메서드
	public void characters(char[] ch, int start, int length) throws SAXException {
		String str= new String(ch, start, length);
		System.out.println(str);
		if(name){//실행부가 방금 name에 도달한 경우
			data[0]=str;
			name=false;
		}else if(age){//실행부가 방금 age에 도달한 경우
			data[1]=str;
			age=false;
		}else if(gender){//실행부가 방금 gender에 도달한 경우
			data[2]=str;
			gender=false;
		}
	}
	
	//닫는 태그가 발견되면 호출되는 메서드
	public void endElement(String uri, String localName, String qName) throws SAXException {
		//한 사람에 대한 정보가 완료된 시점이므로...list에 탑재시키자!!
		if(qName.equalsIgnoreCase("member")){
			System.out.println("한사람 정보 완성");
			model.list.add(data);
		}
	}
}
