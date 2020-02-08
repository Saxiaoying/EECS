//This is maintained by jyl.
package team.zucc.eecs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;

@Controller("CourseEvaluationController")
public class CourseEvaluationController {
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value = { "/getCourseDataByInf" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseDataByInf(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseEvaluationController-getCourseDataByInf");
		
		JSONObject obj = new JSONObject();
		try {
			String coz_id = in.getString("coz_id");
			String cs_acad_yr = in.getString("cs_acad_yr");
			String cs_sem = in.getString("cs_sem");
			
			CourseSet courseSet = courseSetService.getCourseSetByCoz_idAndTime(coz_id, cs_acad_yr, cs_sem);
			if(courseSet == null) {
				obj.put("state", "暂无该开课信息！");
				return obj;
			}
			
			Course course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			List<CourseObjective> courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(courseSet.getCs_id());
			if(courseObjectiveList == null) courseObjectiveList = new ArrayList<CourseObjective>();
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("courseObjectiveList", courseObjectiveList);
			obj.put("num", courseObjectiveList.size());
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
	

}
