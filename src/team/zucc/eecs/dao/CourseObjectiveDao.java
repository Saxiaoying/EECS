//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.CourseObjective;

public interface CourseObjectiveDao {
	//查询
		List<CourseObjective> getCourseObjectiveList();
		CourseObjective getCourseObjectiveByCo_id(int co_id);
		CourseObjective getCourseObjectiveByCs_id(int cs_id);
		CourseObjective getCourseObjectiveByCs_idAndCo_num(int cs_id, int co_num);
		
		//添加
		void addCourseObjective(int co_id, int cs_id,  int co_num,  String co_cont);
		
		//删除
		void deleteCourseObjectiveByCo_id(int co_id);
		//修改
		void updateCourseObjective(int co_id, int cs_id,  int co_num,  String co_cont);
}
