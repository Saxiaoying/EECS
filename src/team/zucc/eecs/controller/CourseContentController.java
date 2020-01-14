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

import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.service.CourseContentService;
import team.zucc.eecs.service.CourseObjectiveService;

@Controller("CourseContentController")
public class CourseContentController {
	@Autowired
	private CourseContentService courseContentService;
	
	@RequestMapping(value = { "/addCourseContent" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addCourseContent(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入CourseContentController-addCourseContent");
		
		List<CourseContent> courseContentList = new ArrayList<CourseContent>();
		JSONObject obj = new JSONObject();
		try {
			int cs_id = in.getIntValue("cs_id");
		    JSONArray arr_cont = in.getJSONArray("arr_cont");
		    for (Object o : arr_cont) {
		    	String s = (String) o;
		    	String[] tmp = s.split(";");
		    	
		    	int cont_typ = Integer.valueOf(tmp[0]);
		    	String cont_name = tmp[1];
		    	int cont_num = Integer.valueOf(tmp[2]);
		    	String cont_cont = tmp[3];
		    	String cont_method = tmp[4];
		    	String cont_key = tmp[5];
		    	String cont_diff = tmp[6];
		    	Double cont_hrs_tch = Double.valueOf(tmp[7]);
		    	Double cont_hrs_pr = Double.valueOf(tmp[8]);
		    	String cont_cla_exe = tmp[9];
		    	String cont_hw = tmp[10];
		    	
		    	
		    	CourseContent cc = new CourseContent();
		    	cc.setCs_id(cs_id);//开课流水号（外码）
		    	cc.setCont_typ(cont_typ); //类别（0：实验；1：授课）
		    	cc.setCont_name(cont_name);//教学内容的名称
		    	cc.setCont_num(cont_num);//教学内容序号
		    	cc.setCont_cont(cont_cont);//教学主要内容
		    	cc.setCont_method(cont_method);//教学方法与要求
		    	cc.setCont_key(cont_key);//重点
		    	cc.setCont_diff(cont_diff);//难点
		    	cc.setCont_hrs_tch(cont_hrs_tch);//讲课时数
		    	cc.setCont_hrs_pr(cont_hrs_pr);//实验时数
		    	cc.setCont_cla_exe(cont_cla_exe);//课堂练习
		    	cc.setCont_hw(cont_hw);//课后作业
		    	
		    	courseContentList.add(cc);
		    }
		    for (CourseContent cc: courseContentList) {
		    	courseContentService.addCourseContent(cc.getCs_id(), cc.getCont_typ(), cc.getCont_name(), cc.getCont_num(), 
		    			cc.getCont_cont(), cc.getCont_method(), cc.getCont_key(), cc.getCont_diff(), cc.getCont_hrs_tch(), 
		    			cc.getCont_hrs_pr(), cc.getCont_cla_exe(), cc.getCont_hw());
		    }
			
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
		}
		return obj;
	}
}
