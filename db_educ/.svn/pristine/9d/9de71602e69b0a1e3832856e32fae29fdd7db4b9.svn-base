package com.db.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.db.entity.TeachingMaterial;

public interface TeachingMaterialService {

	List<TeachingMaterial> teachingMaterial();

	List<TeachingMaterial> materialList(@Param("meterialName")String meterialName, @Param("pageNo")int pageNo,@Param("pageSize") Integer pageSize, @Param("type")String string,@Param("subject") Integer subject);

	int getCount(@Param("meterialName")String meterialName, @Param("type")String string,@Param("subject") Integer subject);

	TeachingMaterial getMeterialName(@Param("meterialName")String tachMeterialName,@Param("subject") Integer subject);

	void addMaterial(TeachingMaterial tmMaterial);

	void daleteMaterial(@Param("materialId")Integer materialId);

	TeachingMaterial getMaterialById(@Param("materialId")Integer materialId);

	void updateMateerial(TeachingMaterial tm);

	List<TeachingMaterial> getTeacherIsMate(@Param("subject")Integer subject);

}
