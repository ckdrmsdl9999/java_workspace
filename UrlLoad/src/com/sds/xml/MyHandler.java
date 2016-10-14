/*
 * SAX 파싱 방법은 각 태그 요소를 모두 메모리에 올리는 방식이 아니라
 * 각 태그 요소가 발견될때마다 개발자에게 이벤트를 통해 전달한다.
 * 따라서 메모리에 올라오는 객체는 따로 없다.
 * 따라서 개발자가 이 이벤트 타이밍을 놓치지 않고 적절한 처리를 해줘야 한다.
 * */

package com.sds.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.sds.table.MyModel;

public class MyHandler extends DefaultHandler{
	MyModel model;
	public MyHandler(MyModel model) {
		this.model=model;
	}
	
	public void startDocument() throws SAXException {
		//System.out.println("문서가 시작됬어요");
	}
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.print("<"+qName+">");
	}
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("</"+qName+">");
	}
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String data=new String(ch,start,length);
		System.out.println(data);
	}
	
}
