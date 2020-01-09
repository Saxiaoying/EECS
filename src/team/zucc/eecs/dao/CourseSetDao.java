//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.CourseSet;

public interface CourseSetDao {
		//查询
		List<CourseSet> getCourseSetList();
		CourseSet getCourseSetByCs_id(int cs_id);
		List<CourseSet> getCourseSetListByCoz_id(int coz_id); 
		List<CourseSet> getCourseSetListByCs_acad_yr(int cs_acad_yr); 
		List<CourseSet> getCourseSetListByCs_sem(int cs_sem); 
		
		//添加
		void addCourseSet(int coz_id, int cs_acad_yr, int cs_sem);
		
		//删除
		void deleteCourseSetByCoz_id(int cs_id);
		
		//修改
		void updateCourseSet(int cs_id, int coz_id, int cs_acad_yr, int cs_sem);
}
