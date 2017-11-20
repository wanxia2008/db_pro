//package com.db.util;
//
//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.image.BufferedImage;
//import java.io.ByteArrayInputStream;
//import java.io.ByteArrayOutputStream;
//import java.util.Random;
//import javax.imageio.ImageIO;
//import javax.imageio.stream.ImageOutputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//public class VerificationCodeUtil {
//	
//	private static final char[] chars = { '0', '1', '2', '3', '4', '5', '6',
//			'7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I','J', 'K',
//			'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S','T', 'U', 'V', 'W', 'X', 'Y',
//			'Z', 'a', 'b', 'c','d','e','f', 'g', 'h','i','j','k', 'l', 'm','n','o',
//			'p', 'q', 'r', 's','t','u','v', 'w', 'x','y','z'};
//	 private static Random random = new Random();
//	    
//	    /*
//	     * 获取6位随机数
//	     */
//	    private static String getRandomString()
//	    {
//	        StringBuffer buffer = new StringBuffer();
//	        for(int i = 0; i < 5; i++)
//	        {
//	            buffer.append(chars[random.nextInt(chars.length)]);
//	        }
//	        return buffer.toString();
//	    }
//	
////	private ByteArrayInputStream image;// 图像  
////    private String str;// 验证码  
////    private static final int WIDTH = 80;  
////    private static final int HEIGHT = 20;  
//    
//    
//    /** 
//     * 功能：获取一个验证码类的实例 
//     * @param response 
//     * @param request 
//     *  
//     * @return 
//     */  
//    public static VerificationCodeUtil Instance(HttpServletRequest request, HttpServletResponse response) {  
//        return new VerificationCodeUtil(request,response);  
//    }  
//    private VerificationCodeUtil(HttpServletRequest request, HttpServletResponse response) {  
//
//        response.setContentType("image/jpeg");
//        String randomString = getRandomString();
//        request.getSession(true).setAttribute("randomString", randomString);
//        int width = 100;
//        int height = 30;
////        int randomNum = new Random().nextInt(3);  
////        if (randomNum == 1) {  
////            initLetterAndNumVerificationCode(image);  
////        }  
//    }  
//    public void initLetterAndNumVerificationCode(BufferedImage image) {  
//    	  
//        Random random = new Random(); // 生成随机类  
//        Graphics g = initImage(image, random);  
//        String[] letter = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J",  
//                "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",  
//                "W", "X", "Y", "Z" };  
//        String sRand = "";  
//        for (int i = 0; i < 6; i++) {  
//            String tempRand = "";  
//            if (random.nextBoolean()) {  
//                tempRand = String.valueOf(random.nextInt(10));  
//            } else {  
//                tempRand = letter[random.nextInt(25)];  
//                if (random.nextBoolean()) {// 随机将该字母变成小写  
//                    tempRand = tempRand.toLowerCase();  
//                }  
//            }  
//            sRand += tempRand;  
//            g.setColor(new Color(20 + random.nextInt(10), 20 + random  
//                    .nextInt(110), 20 + random.nextInt(110)));  
//            g.drawString(tempRand, 13 * i + 6, 16);  
//        }  
//        this.setStr(sRand);/* 赋值验证码 */  
//        g.dispose(); // 图象生效  
//        this.setImage(drawImage(image));  
//    }  
//    public Graphics initImage(BufferedImage image, Random random) {  
//        Graphics g = image.getGraphics(); // 获取图形上下文  
//        g.setColor(getRandColor(200, 250));// 设定背景色  
//        g.fillRect(0, 0, WIDTH, HEIGHT);  
//        g.setFont(new Font("Times New Roman", Font.PLAIN, 14));// 设定字体  
//        g.setColor(getRandColor(160, 200)); // 随机产生165条干扰线，使图象中的认证码不易被其它程序探测到  
//        for (int i = 0; i < 165; i++) {  
//            int x = random.nextInt(WIDTH);  
//            int y = random.nextInt(HEIGHT);  
//            int xl = random.nextInt(12);  
//            int yl = random.nextInt(12);  
//            g.drawLine(x, y, x + xl, y + yl);  
//        }  
//        return g;  
//    }  
//    public ByteArrayInputStream drawImage(BufferedImage image) {  
//        ByteArrayInputStream input = null;  
//        ByteArrayOutputStream output = new ByteArrayOutputStream();  
//        try {  
//            ImageOutputStream imageOut = ImageIO  
//                    .createImageOutputStream(output);  
//            ImageIO.write(image, "JPEG", imageOut);  
//            imageOut.close();  
//            input = new ByteArrayInputStream(output.toByteArray());  
//        } catch (Exception e) {  
//            System.out.println("验证码图片产生出现错误：" + e.toString());  
//        }  
//        return input;  
//    }  
//    /* 
//     * 功能：给定范围获得随机颜色 
//     */  
//    private Color getRandColor(int fc, int bc) {  
//        Random random = new Random();  
//        if (fc > 255)  
//            fc = 255;  
//        if (bc > 255)  
//            bc = 255;  
//        int r = fc + random.nextInt(bc - fc);  
//        int g = fc + random.nextInt(bc - fc);  
//        int b = fc + random.nextInt(bc - fc);  
//        return new Color(r, g, b);  
//    }  
//    /** 
//     * 功能：获取验证码的字符串值 
//     *  
//     * @return 
//     */  
//    public String getVerificationCodeValue() {  
//        return this.getStr();  
//    }  
//    /** 
//     * 功能：取得验证码图片 
//     *  
//     * @return 
//     */  
//    public ByteArrayInputStream getImage() {  
//        return this.image;  
//    }  
//    public String getStr() {  
//        return str;  
//    }  
//  
//    public void setStr(String str) {  
//        this.str = str;  
//    }  
//    public void setImage(ByteArrayInputStream image) {  
//        this.image = image;  
//    }  
//}
