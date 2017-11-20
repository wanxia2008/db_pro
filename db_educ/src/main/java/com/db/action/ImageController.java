package com.db.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.util.BaseUtil;
import com.db.util.ImageCodeUtil;

@Controller
@RequestMapping("/imageCode")
public class ImageController extends BaseUtil{
	
	@RequestMapping("/createCode")
	 public void captcha(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException 
	    {
	        ImageCodeUtil.outputCaptcha(request, response);
	    }
}
