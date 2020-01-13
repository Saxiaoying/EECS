//This is maintained by jyl. 
package team.zucc.eecs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseObjectiveDao;

@Component("CourseObjectiveServiceImpl")
public class CourseObjectiveServiceImpl implements CourseObjectiveService {
	@Autowired
	private  CourseObjectiveDao courseObjectiveDao;

	@Override
	public int addCourseObjective(int cs_id, int co_num, String co_cont) {
		try {
			courseObjectiveDao.addCourseObjective(cs_id, co_num, co_cont);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
