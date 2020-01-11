//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CourseSet;

public interface CourseSetDao {
		//查询
		List<CourseSet> getCourseSetList();
		CourseSet getCourseSetByCs_id(int cs_id);
		List<CourseSet> getCourseSetListByCoz_id(String coz_id); 
		List<CourseSet> getCourseSetListByCs_acad_yr(int cs_acad_yr); 
		List<CourseSet> getCourseSetListByCs_sem(int cs_sem); 
		List<CourseSet> getCourseSetListByInf(String coz_id, int cs_acad_yr, int cs_sem);
		
		//添加
		@Transactional(propagation = Propagation.REQUIRED)
		void addCourseSet(String coz_id, int cs_acad_yr, int cs_sem);
		
		//删除
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteCourseSetByCoz_id(int cs_id);
		
		//修改
		@Transactional(propagation = Propagation.REQUIRED)
		void updateCourseSet(int cs_id, String coz_id, int cs_acad_yr, int cs_sem);
}
