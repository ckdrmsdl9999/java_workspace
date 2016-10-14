/*
 * 쇼핑몰에 사용할 모든 상품 목록의 기본 디자인을
 * 정의하자!!!
 * */

package com.sds.shopping.client.main;

import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Product extends JPanel{
	ImageIcon icon;
	JLabel la_img;
	JLabel la_product_name;
	JLabel la_price;
	String path="C:/product_img/";
	
	
	public Product(String filename, String product_name, int price) {
		icon= new ImageIcon(path+filename);
		la_img= new JLabel(icon);
		la_img.setPreferredSize(new Dimension(120, 120));
		la_product_name = new JLabel(product_name);
		la_price = new JLabel(Integer.toString(price));
		
		setPreferredSize(new Dimension(140, 178));
		la_product_name.setPreferredSize(new Dimension(128, 15));
		la_price.setPreferredSize(new Dimension(128, 15));
		add(la_img);
		add(la_product_name);
		add(la_price);
	}

}
