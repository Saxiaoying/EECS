//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.CourseArrangement;

public interface CourseArrangementDao {
	    //查询
		List<CourseArrangement> getCourseArrangementList();
		CourseArrangement getCourseArrangementByCag_id(int cag_id);
		List<CourseArrangement> getCourseArrangementByCs_id(int cs_id); 
		List<CourseArrangement> getCourseArrangementByTch_id(int tch_id); 
		List<CourseArrangement> getCourseArrangementByCs_idAndTch_id(int cs_id, int tch_id); 
		
		//添加
		@Transactional(propagation = Propagation.REQUIRED)
		void addCourseArrangement(int cag_id, int cs_id, int tch_id, int cag_num, String cag_name);
		
		//删除
		@Transactional(propagation = Propagation.REQUIRED)
		void deleteCourseArrangementByCag_id(int cag_id);
		
		//修改
		@Transactional(propagation = Propagation.REQUIRED)
		void updateCourseArrangement(int cag_id, int cs_id, int tch_id, int cag_num, String cag_name);

}
