//This is maintained by jyl. 
package team.zucc.eecs.dao;

import java.util.List;


import team.zucc.eecs.model.CourseContent;;

public interface CourseContentDao {
	//查询
	List<CourseContent> getCourseContentList();
	CourseContent getCourseContentByCont_id(int cont_id);
	CourseContent getCourseContentByCs_id(int cs_id); 
	
	//添加
	void addCourseContent( int cont_id, int cs_id, int cont_typ, String cont_name,int cont_num, 
			String cont_cont, int cont_method, String cont_key, String cont_diff, 
			double cont_hrs_tch, double cont_hrs_pr, int cont_cla_exe, String cont_hw);
	
	
	//删除
	void deleteCourseContentByCoz_id(int cont_id);
	
	//修改
	void updateCourseContent(int cont_id, int cs_id, int cont_typ, String cont_name,int cont_num, 
			String cont_cont, int cont_method, String cont_key, String cont_diff, 
			double cont_hrs_tch, double cont_hrs_pr, int cont_cla_exe, String cont_hw);
}
