/*
	Ŭ���� �ۼ���, ������ private�� ������ �����ϰ�
	�� ������ ���� ������ �޼��带 ���� �����ϴ� ��ü��������
	���� ����� ������ ����ȭ(=encapsulation)�� �Ѵ�.
	�� ���¸� �����Ѵ�.
*/
package com.iot.main;

public class Account{
	//���������� Ŭ������ ������ �����ʹ� ��ȣ ����̴�.
	//���� private���� ���� ó�� �ϴ°� ��κ��̴�.
	private String num="110-58789-232";
	private int total=3000;

	//���� ���¸� ���� �� �� �ֵ��� �޼��带 ����...
	public int getTotal(){
		return total;
	}
	// ��� ������ ���� ������ �뵵�� ���ǵǴ� �޼��带 ������ setter�� �Ѵ�.
	//setter �޼��� ���� �� ��Ģ
	//set+��������� , ���� �� ��  set ���� �ܾ�� ù ö�ڸ�
	//�빮�ڷ� �Ѵ�.!!
	public void setTotal(int total){
		this.total=total;
	}
}
