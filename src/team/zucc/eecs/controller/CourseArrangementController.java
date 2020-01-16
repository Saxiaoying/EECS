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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseArrangement;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.service.CourseArrangementService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;

@Controller("CourseArrangementController")
public class CourseArrangementController {
	@Autowired
	private CourseArrangementService courseArrangementService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@RequestMapping(value = { "/getCourseArrangementList" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject searchCourse(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseArrangementController-getCourseArrangementList");

		JSONObject obj = new JSONObject();
		List<CourseArrangement> courseArrangemrntList = new ArrayList<CourseArrangement>();
		List<CourseSet> courseSetList = new ArrayList<CourseSet>();
		List<Course> courseList = new ArrayList<Course>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
	
			String cs_acad_yr = "", cs_sem = "", coz_nature = ""; 
			if (in.getString("cs_acad_yr").compareTo("学年（所有）") != 0) cs_acad_yr = in.getString("cs_acad_yr");
			if (in.getString("cs_sem").compareTo("学期（所有）") != 0) cs_sem = in.getString("cs_sem");

			// 处理字符串中的不可见字符
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if (coz_id.isEmpty()) coz_id = "";

			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if (coz_name_ch.isEmpty()) coz_name_ch = "";

			String tch_name = in.getString("tch_name");
			tch_name = tch_name.replaceAll("\\s", "");
			if (tch_name.isEmpty()) tch_name = "";
			
			courseArrangemrntList = courseArrangementService.getCourseArrangementListByInfFromAtoB(a, b, cs_acad_yr, cs_sem, coz_id, coz_name_ch, tch_name);
            int total = courseArrangementService.getCourseArrangementNumberByInf(cs_acad_yr, cs_sem, coz_id, coz_name_ch, tch_name);
			
            for(CourseArrangement ca: courseArrangemrntList) {
            	CourseSet cs = courseSetService.getCourseSetByCs_id(ca.getCs_id());
            	Course c = courseService.getCourseByCoz_id(cs.getCoz_id());
            	courseSetList.add(cs);
            	courseList.add(c);
            }
			JSONArray arr = new JSONArray();
			arr.addAll(courseArrangemrntList);

			JSONArray arr1 = new JSONArray();
			arr.addAll(courseSetList);
			
			JSONArray arr2 = new JSONArray();
			arr2.addAll(courseList);
			
			obj.put("total", total);
			obj.put("courseArrangemrntList", arr);
			obj.put("courseSetList", arr1);
			obj.put("courseList", arr2);
			
			if (courseArrangemrntList.size() == 0) obj.put("state", "暂无符合条件的记录！");
			else obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
