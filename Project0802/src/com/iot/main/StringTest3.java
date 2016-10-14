package com.iot.main;

class StringTest3{
	public static void main(String[] args){
		String s="photo.jpg";
		
		String[] arr=s.split("\\.");
		System.out.println(arr.length);
		System.out.println(arr[0]);
		System.out.println(arr[1]);


	}
}
