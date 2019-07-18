package com.match.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CertifyMethod {
	private int padding=2;
	private String randstr;
	private BufferedImage image1;
	public Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255)
			fc = 255;
		if (bc > 255)
			bc = 255;
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
	public  void generateImg(int x,int y,int count,boolean onlynum)
	{
		int fontsize=y-padding-padding;
		char[] chars;
		if (onlynum == true) {
			chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9' };
		} else
			chars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'k', 'l',
					'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
					'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
					'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
					'W', 'X', 'Y', 'Z' };
		int length = chars.length;
		BufferedImage image = new BufferedImage(x, y,
				BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random = new Random();
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, x, y);
		Font f=new Font("Times New Roman", Font.PLAIN, fontsize);
		g.setFont(f);
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x_1 = random.nextInt(x);
			int y_1 = random.nextInt(y);
			int x_2 = random.nextInt(12);
			int y_2 = random.nextInt(12);
			g.drawLine(x_1, y_1, x_1 + x_2, y_1 + y_2);
		}
		int wordpad=(x-6-6)/count;
		String srand="";
		for(int j=0;j<count;j++){
			String trand=String.valueOf(chars[random.nextInt(length)]);
			 g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			 g.drawString(trand,wordpad*j+6,fontsize);
			 srand=srand+trand;
		}
		g.dispose();
		this.randstr=srand.toLowerCase();
		this.image1=image;
			
	}
	public  void generateImg(int x,int y)
	{
		 generateImg(x,y,5,true);
	}
	public String getRandstr() {
		return randstr;
	}
	public void setRandstr(String randstr) {
		this.randstr = randstr;
	}
	public BufferedImage getImage1() {
		return image1;
	}
	public void setImage1(BufferedImage image1) {
		this.image1 = image1;
	}
	
}
