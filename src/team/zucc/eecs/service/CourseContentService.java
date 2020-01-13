//This is maintained by jyl. 
package team.zucc.eecs.service;

public interface CourseContentService {
	int addCourseContent(int cs_id, int cont_typ, String cont_name,int cont_num, 
			String cont_cont, String cont_method, String cont_key, String cont_diff, 
			double cont_hrs_tch, double cont_hrs_pr, String cont_cla_exe, String cont_hw);
}
