/*
	�ڹٵ� �ٸ� ���� ���α׷�ó�� GUI�� �����Ѵ�.
	��) �ڹٵ� ������,��ư,üũ�ڽ�,TextArea ����� ����
	
	�ڹٿ��� GUI ������Ʈ�� awt ��Ű������ �����Ѵ�.

*/

package gui;
import java.awt.Frame;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;

class GuiTest{
	public static void main(String[] args){
		//��� GUI������Ʈ�� �÷��� ������ â
		//�ڹٿ��� �������� ����Ʈ�Ӽ��� �����ϴ� �� �⺻��
		//������ �ʴ� �Ӽ��� ������ �ִ�.
		//���� ���̰� ����.

		Frame win= new Frame();
		win.setVisible(true);//���̰� �ϱ�
		win.setSize(300,400);
		win.setTitle("�� ������");

		Button bt=new Button("�� ��ư");
		TextArea texta = new TextArea(10,20);//���� ����
		

		//�ڹ� �� html ó��, �� ������Ʈ�� �����ϱ⿡ �ռ�
		//��� ��� �������� ���� ������ ����� �����ȴ�.
		//�� ������ �����ϰ� ����.
		FlowLayout flow =new FlowLayout();
		win.setLayout(flow);//��ġ ����� �����ϴ� �޼���
		
		//��ư�� �����ӿ� ����!!
		win.add(bt);
		win.add(texta);
		
		//�ڹٿ����� ��ü���� �ڷ����̴�.
		//���� �迭�� �󸶵��� �����ϴ�.
		Checkbox[] chk = new Checkbox[20];

		for(int i=0;i<20;i++){
			Checkbox ch =new Checkbox(i+"��° �ڽ�");
			chk[i]=ch;
			win.add(chk[i]);

		}

		TextField tf= new TextField(20);
		win.add(tf);

		Choice cho = new Choice();
		for(int i=1;i<21;i++){
			cho.add(Integer.toString(i));// i�� String�̱� �ٶ���!!
		}
		win.add(cho);

	}
}
