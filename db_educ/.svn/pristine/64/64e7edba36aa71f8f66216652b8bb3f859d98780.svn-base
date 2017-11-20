package com.db.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public final class ImageCodeUtil{
	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K',
			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S','T', 'U', 'V', 'W', 'X', 'Y',
			'Z', 'a', 'b', 'c','d','e','f', 'g', 'h','i','j','k', 'l', 'm','n','o',
			'p', 'q', 'r', 's','t','u','v', 'w', 'x','y','z'};
	 private static Random random = new Random();
	    
	    /*
	     * 获取6位随机数
	     */
	    private static String getRandomString()
	    {
	        StringBuffer buffer = new StringBuffer();
	        for(int i = 0; i < 5; i++)
	        {
	            buffer.append(chars[random.nextInt(chars.length)]);
	        }
	        return buffer.toString();
	    }
	    
	    /*
	     * 获取随机数颜色
	     */
	    private static Color getRandomColor(int fc, int bc)
	    {
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
	    
	    /*
	     * 返回某颜色的反色
	     */
	    private static Color getReverseColor(Color c)
	    {
	        return new Color(255 - c.getRed(), 255 - c.getGreen(),
	                255 - c.getBlue());
	    }
	    
	    public static void outputCaptcha(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException 
	    {

	        response.setContentType("image/jpeg");

	        String randomString = getRandomString();
	        request.getSession(true).setAttribute("randomString", randomString);

	        int width = 100;
	        int height = 30;

	        Color color = getRandomColor(200, 250);
	        Color reverse = getReverseColor(color);

	        BufferedImage bi = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_RGB);
	        Graphics2D g = bi.createGraphics();
	        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
	        g.setColor(color);
	        g.fillRect(0, 0, width, height);
	        g.setColor(reverse);
	        g.drawString(randomString, 18, 20);
	        //添加噪点
//	        for (int i = 0, n = random.nextInt(40); i < n; i++) 
//	        {
//	            g.drawRect(random.nextInt(width), random.nextInt(height), 1, 1);
//	        }

	        // 转成JPEG格式
	        ServletOutputStream out = response.getOutputStream();
	        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
	        encoder.encode(bi);
	        out.flush();
	    }
}
