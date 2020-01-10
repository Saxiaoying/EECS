//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import team.zucc.eecs.dao.CourseDao;
import team.zucc.eecs.model.Course;

@Component("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public List<Course> getCourseListFromAtoB(int a, int b){
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = courseDao.getCourseListFromAtoB(a, b);
			return courseList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Course> getCourseListByInf(String coz_id, String coz_name_ch, String coz_nature) {
		Set<Course> courses = new HashSet<Course>();
		List<Course> courseList = new ArrayList<Course>();
		try {
			if(coz_id != null) {
				List<Course> tmp = courseDao.getCourseListByCoz_id(coz_id);
				for (Course c: tmp) {
					courses.add(c);
				}
			}
			
			if (coz_name_ch != null) {
				List<Course> tmp = courseDao.getCourseListByCoz_name_ch(coz_name_ch);
				for (Course c: tmp) {
					courses.add(c);
				}
			}
			
			if (coz_name_ch != null) {
				List<Course> tmp = courseDao.getCourseListByCoz_nature(coz_nature);
				for (Course c: tmp) {
					courses.add(c);
				}
			}
			
			for (Course c: courses) {
				courseList.add(c);
			}
			
			courses.clear();
			
			return courseList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
