/*
 * ������Ʈ �� Canvas�� ��ӹ޾� �����ڰ� �ֵ��Ͽ�
 * �׸��� �׷������� �Ѵ�.
 * paint �޼��带 ������ �غ���!!
 * */


package com.sds.gallery;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GalleryCanvas extends Canvas{
	Toolkit kit= Toolkit.getDefaultToolkit();// �߻�Ŭ������!!
	Image[] imgs;//�߻� Ŭ������!!
	String[] path={
			"C:/java_workspace/GUI0808/res/cat1.jpg",
			"C:/java_workspace/GUI0808/res/cat2.jpg",
			"C:/java_workspace/GUI0808/res/cat3.jpg",
			"C:/java_workspace/GUI0808/res/cat4.jpg",
			"C:/java_workspace/GUI0808/res/cat5.jpg",
			"C:/java_workspace/GUI0808/res/cat6.jpg",
			"C:/java_workspace/GUI0808/res/cat7.jpg",
			"C:/java_workspace/GUI0808/res/cat8.jpg",
			"C:/java_workspace/GUI0808/res/cat9.jpg",
			"C:/java_workspace/GUI0808/res/cat10.jpg"
	};
	int count=0;
	
	public GalleryCanvas() {
		imgs= new Image[path.length];
		
		for(int i=0;i<imgs.length;i++){
			imgs[i]=kit.getImage(path[i]);
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(imgs[count], 0, 0, 700, 600, this);
	}
}
