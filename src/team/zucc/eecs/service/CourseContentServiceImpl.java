//This is maintained by jyl. 
package team.zucc.eecs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseContentDao;

@Component("CourseContentServiceImpl")
public class CourseContentServiceImpl implements CourseContentService {
	@Autowired
	private  CourseContentDao courseContentDao;
	
	@Override
	public int addCourseContent(int cs_id, int cont_typ, String cont_name, int cont_num, String cont_cont,
			String cont_method, String cont_key, String cont_diff, double cont_hrs_tch, double cont_hrs_pr,
			String cont_cla_exe, String cont_hw) {
		try {
			courseContentDao.addCourseContent(cs_id, cont_typ, cont_name, cont_num, cont_cont, cont_method, cont_key, 
					cont_diff, cont_hrs_tch, cont_hrs_pr, cont_cla_exe, cont_hw);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;//成功
	}

}
