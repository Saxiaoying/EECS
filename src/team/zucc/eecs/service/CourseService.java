//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.Course;

public interface CourseService {
	List<Course> getCourseListFromAtoB(int a, int b);
	List<Course> getCourseListByInf(String coz_id, String coz_name_ch, String coz_nature);
	
}
