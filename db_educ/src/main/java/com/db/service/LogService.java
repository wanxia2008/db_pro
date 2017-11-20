package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Log;

public interface LogService {

	List<Log> getLogList(@Param("log") Log log, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("statime")String statime,@Param("endtime") String endtime);

    int getCount(@Param("log")Log log, @Param("statime")String statime, @Param("endtime")String endtime);

   public void saveLog(Log log);

   public void deleteLogById(@Param("logId")Integer logId);

   List<Log> findLog(@Param("startTime")String startTime, @Param("endTime")String endTime);

   void batchDelete(@Param("ids")List myList);

}
