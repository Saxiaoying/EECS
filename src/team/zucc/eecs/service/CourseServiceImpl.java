//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sun.org.apache.regexp.internal.recompile;

import team.zucc.eecs.dao.CourseDao;
import team.zucc.eecs.model.Course;

@Component("CourseServiceImpl")
public class CourseServiceImpl implements CourseService {
	@Autowired
	private CourseDao courseDao;
	
	@Override
	public Course getCourseByCoz_id(String coz_id) {
		try {
			Course course = courseDao.getCourseByCoz_id(coz_id);
			return course;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Course> getCourseListFromAtoB(int a, int b){
		List<Course> courseList = new ArrayList<Course>();
		try {
			courseList = courseDao.getCourseListFromAtoB(a, b);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
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
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return courseList;
	}

	@Override
	public int addCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature, double coz_credit,
			String coz_hrs_wk, double coz_hours) {
		try {
			Course course = courseDao.getCourseByCoz_id(coz_id);
			if(course != null) {
				return 1; //已经存在
			}
			
			courseDao.addCourse(coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;//成功
	}

	@Override
	public int deleteCourseByCoz_id(String coz_id) {
		try {
			courseDao.deleteCourseByCoz_id(coz_id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return 0;
	}

	@Override
	public int updateCourse(String coz_id, String coz_name_ch, String coz_name_eng, String coz_nature,
			double coz_credit, String coz_hrs_wk, double coz_hours) {
		try {
			courseDao.updateCourse(coz_id, coz_name_ch, coz_name_eng, coz_nature, coz_credit, coz_hrs_wk, coz_hours);
		} catch (Exception e) {
			e.printStackTrace();
			return 1; //失败
		}
		return 0;//成功
	}
}
