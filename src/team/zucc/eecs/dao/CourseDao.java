//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.Course;

public interface CourseDao {
	//查询
	List<Course> getCourseList();
	Course getCourseByCoz_id(int coz_id);
	List<Course> getCourseListByCoz_name_ch(String coz_name_ch); //模糊查询
	List<Course> getCourseListByCoz_nature(String coz_nature); //模糊查询
	
	//添加
	void addCourse(int coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit, double coz_hrs_wk, double coz_hours);
	
	//删除
	void deleteCourseByCoz_id(int coz_id);
	
	//修改
	void updateCourse(int coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit, double coz_hrs_wk, double coz_hours);
}
