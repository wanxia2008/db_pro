package com.db.service;

import java.util.List;

import com.db.entity.Grade;
import com.db.entity.SchoolYear;

/**
 * 学年
 *
 */
public interface SchoolYearService {

	public void addYear(SchoolYear schoolYear);
	
	public List<SchoolYear> getYear();
}
