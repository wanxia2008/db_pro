package com.db.action;


import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.apache.commons.io.FileUtils;  
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.db.entity.Log;
import com.db.service.LogService;
import com.db.util.BaseUtil;
import com.db.util.JsonUtil;

@Controller
@RequestMapping("/logger")
public class LogController extends BaseUtil {

	@Resource
	private LogService logService;

	/**
	 * 日志列表
	 * 
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping("/logmanage")
	public ModelAndView Logmanage(Integer pageNo, Integer pageSize, HttpServletRequest request, Log log,
			String startCreateTime, String endCreateTime) throws ParseException {
		if (pageNo == null) {
			pageNo = 1;
		}
		if (pageSize == null) {
			pageSize = 20;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat startsdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat endsdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String endtime = null;
		String statime = null;
		if (startCreateTime != null && startCreateTime != "") {
			statime = startsdf.format(sdf.parse(startCreateTime));
		}
		if (endCreateTime != null && endCreateTime != "") {
			endtime = endsdf.format(sdf.parse(endCreateTime));
		}
//		HttpSession session = request.getSession();
//		Teacher teacher = (Teacher) session.getAttribute("currTeacher");
		int rowsCount = logService.getCount(log, statime, endtime);// 获得总条数
		int totalPages = 0;
		if (rowsCount % pageSize == 0) {
			totalPages = rowsCount / pageSize;
		} else {
			totalPages = (rowsCount / pageSize) + 1;
		}
		ModelAndView mv = new ModelAndView();
		List<Log> logList = logService.getLogList(log, (pageNo - 1) * pageSize, pageSize,
				statime, endtime);
		if(!logList.isEmpty() && logList!=null) {
			mv.addObject("logList", logList);
		} else {
			mv.addObject("logList", null);
		}
		mv.addObject("page", pageNo);
		mv.addObject("pageSize", pageSize);
		mv.addObject("totalPages", totalPages);
		mv.addObject("log", log);
		mv.addObject("startCreateTime", startCreateTime);
		mv.addObject("endCreateTime", endCreateTime);
		mv.setViewName("logmanage/logmanage");
		return mv;
	}

	/**
	 * 删除日志
	 * 
	 * @param logId
	 */
	@RequestMapping("/delete")
	public void delete(Integer logId, HttpServletResponse response) {
		try {
			logService.deleteLogById(logId);
			output(response, JsonUtil.buildFalseJson("0", "删除成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "删除失败!"));
		}
	}
	@RequestMapping("/batchDelete")
	public void batchDeleteLog(HttpServletResponse response,String logIds){
		String[] my = logIds.split(",");
		List myList = Arrays.asList(my);
		try {
			logService.batchDelete(myList);
			output(response, JsonUtil.buildFalseJson("0", "批量删除成功!"));
		} catch (Exception e) {
			output(response, JsonUtil.buildFalseJson("1", "批量删除失败!"));
		}
	}
	

	/**
	 * 导出日志
	 * 
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value = "/exportLog")
	public ResponseEntity<byte[]> exportLog(HttpServletResponse response, 
		HttpServletRequest request,String startTime,String endTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat startsdf = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
		SimpleDateFormat endsdf = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
		String statime = "";
		String enttime = "";
		if(startTime != null && startTime != ""){
		    statime = startsdf.format(sdf.parse(startTime));
		}
		if(endTime != null && endTime != ""){
		    enttime = endsdf.format(sdf.parse(endTime));
		}
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Log");
		HSSFRow row = sheet.createRow((int) 0);
		HSSFCellStyle style = wb.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("日志Id");
		cell.setCellStyle(style);

		cell = row.createCell((short) 1);
		cell.setCellValue("操作用户");
		cell.setCellStyle(style);

//		cell = row.createCell((short) 2);
//		cell.setCellValue("操作用户姓名");
//		cell.setCellStyle(style);

		cell = row.createCell((short) 2);
		cell.setCellValue("操作内容");
		cell.setCellStyle(style);

		cell = row.createCell((short) 3);
		cell.setCellValue("操作类型");
		cell.setCellStyle(style);

		cell = row.createCell((short) 4);
		cell.setCellValue("操作结果");
		cell.setCellStyle(style);

		cell = row.createCell((short) 5);
		cell.setCellValue("操作模块");
		cell.setCellStyle(style);

		cell = row.createCell((short) 6);
		cell.setCellValue("操作开始时间");
		cell.setCellStyle(style);

		cell = row.createCell((short) 7);
		cell.setCellValue("操作结束时间");
		cell.setCellStyle(style);

		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		List<Log> logList = logService.findLog(statime,enttime);

		List list = LogController.getLog(logList);
		for (int i = 0; i < list.size(); i++) {
			row = sheet.createRow((int) i + 1);
			Log log = (Log) list.get(i);
			// 第四步，创建单元格，并设置值
			row.createCell((short) 0).setCellValue(log.getLogId());
//			row.createCell((short) 1).setCellValue(log.getOperationId());
			row.createCell((short) 1).setCellValue(log.getOperationName());
			row.createCell((short) 2).setCellValue(log.getOperationContent());
			row.createCell((short) 3).setCellValue(log.getOperationResult());
			row.createCell((short) 4).setCellValue(log.getOperationType());
			row.createCell((short) 5).setCellValue(log.getModular());
			cell = row.createCell((short) 6);
			cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getStartTime()));
			cell = row.createCell((short) 7);
			cell.setCellValue(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(log.getEndTime()));
		}
		// 第六步，将文件存到指定位置
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String respJson = null;
		Date now = new Date();
		// String random = UUID.randomUUID().toString().replace("-",
		// "").substring(0, 5);
		String filename = format.format(now) + ".xls";// 文件名
		String path = request.getServletContext().getRealPath("/") + "Log" + "/" + filename;// 文件所在盘路径
		String savepath = request.getServletContext().getRealPath("/") + "Log";
		File file = new File(savepath);
		if(!file.exists()) {//不存在路径，则创建目录
			file.mkdir();
		}
		File file2 = new File(file, filename);
		if(!file2.exists()) {
			file2.createNewFile();
		}
		try {
			FileOutputStream fout = new FileOutputStream(path);
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		String contextPath = request.getContextPath();
//		String port = request.getServerPort() == 80 ? "" : ":" + request.getServerPort();
//		String url = request.getScheme() + "://" + request.getServerName() + port + contextPath + "/Log/" + filename;
//		File file=new File(path);  
		HttpHeaders headers = new HttpHeaders(); 
//		String fileName=new String(filename.getBytes("UTF-8"),"iso-8859-1");//为了解决中文名称乱码问题  
		headers.setContentDispositionFormData("attachment", filename);   
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);   
        
        return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file2),    
                                          headers, HttpStatus.CREATED); 
	}

	private static List<Log> getLog(List<Log> logList) throws Exception {
		List list = new ArrayList();
		for (Log l : logList) {
			Log log = new Log();
			log.setLogId(l.getLogId());
			log.setOperationId(l.getOperationId());
			log.setOperationName(l.getOperationName());
			log.setOperationContent(l.getOperationContent());
			log.setOperationType(l.getOperationType());
			log.setStartTime(l.getStartTime());
			log.setEndTime(l.getEndTime());
			log.setModular(l.getModular());
			log.setOperationResult(l.getOperationResult());
			list.add(log);
		}
		return list;
	}
}
