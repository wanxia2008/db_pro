package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.LectureNotes;

public interface LectureNotesService {

	List<LectureNotes> lectureNotesList(@Param("teacherId")Integer teacherId, @Param("ln")LectureNotes ln,@Param("pageNo") int pageNo,@Param("pageSize") Integer pageSize);

	LectureNotes getLectureNotes(@Param("handoutId")Integer handoutId);

    public void addlectureNotes(LectureNotes ln);

	public void deleteLectureNote(Integer handoutId);

	public void updateNotes(LectureNotes lNotes);

	LectureNotes flip(int page);

	int getCount(LectureNotes ln);

	void updateIsPublic(LectureNotes lNotes);
	
	void updateIsStart(LectureNotes lNotes);

	LectureNotes getLectureNotesTeacherId(@Param("handoutId")Integer handoutId);

	LectureNotes getNotesSubject(Integer handoutId);
	List<LectureNotes> lectureNotesByIdList(@Param("gradeList")List<String> gradeList ,@Param("teacherId")Integer teacherId, @Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("handoutType") Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject,@Param("isPublic") Integer isPublic, @Param("grade")Integer grade);

	int getCountMunber(@Param("gradeList")List<String> gradeList , @Param("handoutType") Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject,@Param("isPublic") Integer isPublic, @Param("teacherId")Integer teacherId, @Param("grade")Integer grade);

	public List<LectureNotes> getPublicNote(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("gradeId")Integer gradeId,@Param("subjectId")Integer subjectId, @Param("createUser")Integer createUser);
	
	public long getPublicNoteCount(@Param("gradeId")Integer gradeId,@Param("subjectId")Integer subjectId, @Param("createUser")Integer createUser);

	LectureNotes getLectuseTitle(@Param("handoutTitle")String handoutTitle);

	int getCountIspublicMunber(@Param("gradeList")List<String> gradeList, @Param("handoutType") Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject,@Param("isPublic") Integer isPublic, @Param("teacherId")Integer teacherId);

	int getCountIspublicMunberbyids(@Param("handoutType") Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject,@Param("isPublic") Integer isPublic, 
			@Param("teacherId")Integer teacherId, @Param("grade")Integer grade);

	
	List<LectureNotes> lectureNotesIsPublicByIdList(@Param("gradeList")List<String> gradeList ,@Param("teacherId")Integer teacherId, @Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize,@Param("handoutType") Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject,@Param("isPublic") Integer isPublic, @Param("grade")Integer grade);
	int seeCountIspublicMunber1(@Param("handoutType")Integer handoutType,@Param("handoutTitle") String handoutTitle,@Param("subject") Integer subject, @Param("isPublic")Integer isPublic, @Param("grade")Integer grade);
	
	List<LectureNotes> seelectureNotesIsPublicByIdList1(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize, @Param("handoutType")Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject, @Param("isPublic")Integer isPublic,@Param("grade") Integer grade);
	
	int seeCountIspublicMunber(@Param("handoutType")Integer handoutType,@Param("handoutTitle") String handoutTitle,@Param("subject") Integer subject, @Param("isPublic")Integer isPublic, @Param("grade")Integer grade);

	List<LectureNotes> seelectureNotesIsPublicByIdList(@Param("pageNo")int pageNo, @Param("pageSize")Integer pageSize, @Param("handoutType")Integer handoutType,
			@Param("handoutTitle")String handoutTitle, @Param("subject")Integer subject, @Param("isPublic")Integer isPublic,@Param("grade") Integer grade);

	void updateUsedCount(LectureNotes lectureNotes);

	List<LectureNotes> getSubjectById(Integer subjectId);
}
