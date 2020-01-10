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
import team.zucc.eecs.service.CourseService;

@Controller("CourseController")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@RequestMapping(value= {"/getCourseList"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getCourseList(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-getCourseList");

		JSONObject obj = new JSONObject();
		List<Course> courseList = new ArrayList<Course>();
		try {
			int a = in.getIntValue("a");
			int b = in.getIntValue("b");
			
			courseList = courseService.getCourseListFromAtoB(a, b);
			
			JSONArray arr = new JSONArray();
			arr.addAll(courseList);
			
			obj.put("total", courseList.size());
			obj.put("courseList", arr);
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@RequestMapping(value= {"/searchCourse"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject searchCourse(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入CourseController-searchCourse");
		
		JSONObject obj = new JSONObject();
		List<Course> courseList = new ArrayList<Course>();
		try {
			//处理字符串中的不可见字符
			String coz_id = in.getString("coz_id");
			coz_id = coz_id.replaceAll("\\s", "");
			if(coz_id.isEmpty()) coz_id = null;
			
			String coz_name_ch = in.getString("coz_name_ch");
			coz_name_ch = coz_name_ch.replaceAll("\\s", "");
			if(coz_name_ch.isEmpty()) coz_name_ch = null;
			
			String coz_nature = in.getString("coz_nature");
			coz_nature = coz_nature.replaceAll("\\s", "");
			if(coz_nature.isEmpty()) coz_nature = null;
			
			courseList = courseService.getCourseListByInf(coz_id, coz_name_ch, coz_nature);
			
			JSONArray arr = new JSONArray();
			arr.addAll(courseList);
			
			obj.put("total", courseList.size());
			obj.put("courseList", arr);
			
			return obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
