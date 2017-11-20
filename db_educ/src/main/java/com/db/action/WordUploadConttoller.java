package com.db.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.docx4j.fonts.IdentityPlusMapper;
import org.docx4j.fonts.Mapper;
import org.docx4j.fonts.PhysicalFonts;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

/**
 * word文档上传
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/wordUpload")
public class WordUploadConttoller extends BaseUtil {

	private Logger log = Logger.getLogger(ExamController.class);

	@RequestMapping(value = "/upload")
	public void upload(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;
		if (file == null) {
			respJson = JsonUtil.buildFalseJson("1", "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 20000000) { // 10M
			respJson = JsonUtil.buildFalseJson("2", "文件大小限制在20M以内!");
			output(response, respJson);
			return;
		}

		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String oldfilename = file.getOriginalFilename();// 文件名
		String pexfix = format.format(now) + random
				+ oldfilename.substring(oldfilename.lastIndexOf("."), oldfilename.length());// 重新命名文件名
		String pdfname = format.format(now) + random + ".pdf";
		String path = request.getServletContext().getRealPath("/") + "WordDir";// 文件所在盘路径 linux
		//String path = request.getServletContext().getRealPath("/") + "\\WordDir";// 文件所在盘路径 window
		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/WordDir/" + pexfix;
		String pdfurl = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/WordDir/"
				+ pdfname;
		File new_file = new File(path);// 创建文件用的
		File file2 = new File(path + "/" + pexfix);// 写入\
		boolean isSuccess = false;
		// 判断上传文件的保存目录是否存在
		if (!new_file.exists() && !new_file.isDirectory()) {
			System.out.println(path + "  目录不存在，需要创建");
			// 创建目录
			new_file.mkdir();
		}
		try {
			file.transferTo(file2);
			//window 
			//String transPath = path.replaceAll("/", "\\");  //window
			//String sourcePath = transPath + "\\" + pexfix;  //window
			//String dintPath = transPath + "\\" + pdfname;   //window
			
			//linux
			String sourcePath = path + "/" + pexfix;
			String dintPath = path + "/" + pdfname;
			log.info("原路径:" + sourcePath);
			log.info("目标路径:" + dintPath);
			isSuccess = trunPdf(sourcePath, dintPath);
			// trunPdf("D:\\test\\网上业务系统相关说明.docx","D:\\test\\网上业务系统相关说明.pdf");
		} catch (Exception e) {
			log.info("转码失败");
		}

		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("wordPath", url);
		if (isSuccess) {
			returnMap.put("pdfPath", pdfurl);
		}
		String pathName = path+"/"+pexfix;
		//File file11 = new File(pathName);
		String doc1 = null; 
		File filein =null ;
		FileInputStream fis = null;
		try {	
			filein= new File(pathName);  
			fis = new FileInputStream(filein);
			XWPFDocument doc = new XWPFDocument(fis);  
			XWPFWordExtractor xdocx = new XWPFWordExtractor(doc);  
			doc1 = xdocx.getText();
			log.info("doc1："+doc1 );
		} catch (Exception e) {
//			e.printStackTrace();
			filein = new File(pathName);  
			fis = new FileInputStream(filein);
			WordExtractor  xdoc = new WordExtractor (fis);
			doc1 = xdoc.getText();
			log.info("doc2："+doc1 );
	
		}finally{
			fis.close();
		}
		
//		log.info("doc1："+doc1 );
		log.info("wordcontent："+doc1 );
		returnMap.put("wordcontent", doc1);



		respJson = JsonUtil.MapToJson(returnMap);
		//log.info(returnMap);
		output(response, respJson);
	}

public boolean trunPdf(String wordpath, String pdfpath) throws Exception {
		
		try {  
			String wpath = wordpath.substring(0, wordpath.lastIndexOf("/"));
			String command = "/usr/bin/libreoffice5.4  --invisible --convert-to pdf "+ wordpath;
			String[] cmds = new String[] {
				     "/bin/sh",
				     "-c",
				     "cd " + wpath + " && "+ command  };
            Process ps = Runtime.getRuntime().exec(cmds);  
            log.info("输入命令为："+ Arrays.asList(cmds));
            BufferedReader br = new BufferedReader(new InputStreamReader(ps.getInputStream()));  
            StringBuffer sb = new StringBuffer();  
            String line;  
            while ((line = br.readLine()) != null) {  
                sb.append(line).append("\n");  
            }  
            String result = sb.toString();  
            log.info("转换后路径:" + result);
            //返回值如：convert /root/upfile/1234.doc -> /root/upfile/1234.pdf using filter : writer_pdf_Export
            //判断返回值是否含有PDF路径
            if (result.indexOf(pdfpath) != -1) {
            	log.info("trunpdf转码成功");
            	return true;
            } 
        } catch (Exception e) {  
            e.printStackTrace();  
            log.info("trunpdf转码失败");
            return false;
        }		
		return false;

//	try {
//		WordprocessingMLPackage wml = WordprocessingMLPackage.load((new FileInputStream(new File(wordpath))));
//		Mapper fontMapper = new IdentityPlusMapper();
//		wml.setFontMapper(fontMapper);
//		fontMapper.put("隶书", PhysicalFonts.get("LiSu"));
//		fontMapper.put("宋体", PhysicalFonts.get("SimSun"));
//		fontMapper.put("微软雅黑", PhysicalFonts.get("Microsoft Yahei"));
//		fontMapper.put("黑体", PhysicalFonts.get("SimHei"));
//		fontMapper.put("楷体", PhysicalFonts.get("KaiTi"));
//		fontMapper.put("新宋体", PhysicalFonts.get("NSimSun"));
//		fontMapper.put("华文行楷", PhysicalFonts.get("STXingkai"));
//		fontMapper.put("华文仿宋", PhysicalFonts.get("STFangsong"));
//		fontMapper.put("宋体扩展", PhysicalFonts.get("simsun-extB"));
//		fontMapper.put("仿宋", PhysicalFonts.get("FangSong"));
//		fontMapper.put("仿宋_GB2312", PhysicalFonts.get("FangSong_GB2312"));
//		fontMapper.put("幼圆", PhysicalFonts.get("YouYuan"));
//		fontMapper.put("华文宋体", PhysicalFonts.get("STSong"));
//		fontMapper.put("华文中宋", PhysicalFonts.get("STZhongsong"));
//		try {
//			org.docx4j.Docx4J.toPDF(wml, new FileOutputStream(new File(pdfpath)));
//			return true;
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//			return false;
//		}
//	} catch (Docx4JException e) {
//		e.printStackTrace();
//		log.info("trunpdf转码失败");
//		return false;
//	}
}

	
	
	@RequestMapping(value = "/uploadpdf")
	public void uploadpdf(@RequestParam(value = "file") CommonsMultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;
		if (file == null) {
			respJson = JsonUtil.buildFalseJson("1", "上传文件为空!");
			output(response, respJson);
			return;
		}
		if (file.getSize() > 20000000) { // 20M
			respJson = JsonUtil.buildFalseJson("2", "文件大小限制在20M以内!");
			output(response, respJson);
			return;
		}

		Date now = new Date();
		String random = UUID.randomUUID().toString().replace("-", "").substring(0, 5);
		String oldfilename = file.getOriginalFilename();// 文件名
		String pexfix = format.format(now) + random + oldfilename.substring(oldfilename.lastIndexOf("."),oldfilename.length());//重新命名文件名 
		String path = request.getServletContext().getRealPath("/") + "WordDir";// 文件所在盘路径
		String contextPath = request.getContextPath();
		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/WordDir/"
				+ pexfix;
		File new_file = new File(path);//创建文件用的
		File file2 = new File(path+"/"+ pexfix);//写入
		//判断上传文件的保存目录是否存在
		if (!new_file.exists() && !new_file.isDirectory()) {
			  System.out.println(path+"  目录不存在，需要创建");
			  //创建目录
			  new_file.mkdir();
		}
		
		//如果文件名是.ppt，转化成pdf
		boolean  isSuccess = false;
		String newpexfix = pexfix.substring(0, pexfix.indexOf("."))+".pdf" ;
		String sourcePath = path+"/"+ pexfix;
		String dintPath	  = path+"/"+ newpexfix;
		log.info("原路径:" + sourcePath);
		log.info("目标路径:" + dintPath);
		log.info("url:" + url);
		try {
			file.transferTo(file2);
			
			if (oldfilename.toLowerCase().indexOf("ppt")>=0) {
				isSuccess = trunPdf(sourcePath, dintPath);
				file2 = new File(dintPath);//写入
				url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/WordDir/"
						+ newpexfix;
				log.info("url:" + url);

			}	
			
		} catch (Exception e) {
//			e.printStackTrace();
		}
		respJson = JsonUtil.buildFalseJson("0", url);
		
		output(response, respJson);
	}
	
}
