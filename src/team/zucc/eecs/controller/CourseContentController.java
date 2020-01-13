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

import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.service.CourseObjectiveService;

@Controller("CourseContentController")
public class CourseContentController {
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@RequestMapping(value = { "/addCourseContent" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addCourseContent(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseContentController-addCourseContent");
		
		List<CourseObjective> courseObjectiveList = new ArrayList<CourseObjective>();
		//List<CourseContent> courseContentList = new ArrayList<CourseContent>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
			
		    JSONArray arr_obj = in.getJSONArray("arr_obj");
		    for (Object o : arr_obj) {
		    	String s = (String) o;
		    	String[] tmp = s.split(";");
		    	int co_num = Integer.valueOf(tmp[0]);
		    	String co_cont = tmp[1];
		    	co_cont = co_cont.replaceAll("\\s", "");
				if(co_cont.isEmpty()) {
					obj.put("state", "存在空白项！");
					return obj;
				}
		    	
		    	
		    	CourseObjective co = new CourseObjective();
		    	co.setCs_id(cs_id);
		    	co.setCo_num(co_num);
		    	co.setCo_cont(co_cont);
		    	courseObjectiveList.add(co);
		    }
			for (CourseObjective co: courseObjectiveList) {
				courseObjectiveService.addCourseObjective(co.getCs_id(), co.getCo_num(), co.getCo_cont());
			}
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
