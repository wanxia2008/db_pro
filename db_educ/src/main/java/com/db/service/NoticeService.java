package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.Notice;

/**
 * 
 * @author Jerry
 *	公告接口
 */
public interface NoticeService {
	//添加公告
	public void addNotice(Notice notice);
	
	public Notice getNoticeById(Integer noticeId);
	
	public List<Notice> getAllNotice(@Param("pageNo")Integer pageNo,@Param("pageSize")Integer pageSize,@Param("deleteId") Integer deleteId);
	
	public int updateNotice(Notice notice);
	
	public int deleteNotice(Integer id);

	public int updateIsRemove(Notice notice);

	public List<Notice> findUserById(@Param("userId")Integer teacherId);

	public void updateIsRemoveDelete(@Param("noticeId")Integer id);
}
