package com.iot.main;

class UseKorea{
	public static void main(String[] args){
		String str="korea java";  
		char ab= str.charAt(3);
		System.out.println(ab);//문자열에서 4번째 위치한 'e' 문자만 추출하여 출력하세요. 
		System.out.println(str.indexOf('a'));// 문장 중 첫번째 a의 index (순번)를 출력하세요.
		System.out.println("라스트는"+str.lastIndexOf('a'));//문장 중 마지막 a의 index 를 출력하세요
		System.out.println(str.codePointCount(0, str.length()));//문자열의 총 길이를 출력하세요.
		//System.out.println(str.replace("korea", "코리아"));//) korea 를 "코리아"로 대체하여 "코리아 java"를 출력하세요
		
		System.out.println(str.substring(3 , 8));
		System.out.println(str.replaceAll("korea java","KOREA JAVA"));
	}
}
