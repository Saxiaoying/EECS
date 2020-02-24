package team.zucc.eecs.controller;

import java.sql.Timestamp;
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

import team.zucc.eecs.model.StudentEmployOrient;
import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.TeacherTrain;
import team.zucc.eecs.service.StudentService;
import team.zucc.eecs.service.TeacherService;

@Controller("TeacherController")
public class TeacherController {
	@Autowired
	private TeacherService teacherservice;
	
	@RequestMapping(value= {"/loadTeacherInfo"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadTeacherInfo(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadTeacherInfo");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <Teacher> teas = new ArrayList<Teacher>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String tch_id = request.getParameter("tch_id");
		String tch_name = request.getParameter("tch_name");
		String tch_title = request.getParameter("tch_title");
		String tch_fin_degree = request.getParameter("tch_fin_degree");
		String tch_qualification = request.getParameter("tch_qualification");
		String tch_prof = request.getParameter("tch_prof");
		String tch_intro = request.getParameter("tch_intro");
//		
//		System.out.println(ad_num);
//		System.out.println(ad_province);
		if((tch_id!=null||tch_name!=null)&&(tch_id!=""||tch_name!="")){
			System.out.println("in");
			teas = teacherservice.loadSubTeacherInfo(tch_id, tch_name,tch_title,tch_fin_degree,tch_qualification,tch_prof,tch_intro);
		}
		else {
			teas = teacherservice.loadTeacherInfo();
		}
		
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", teas.size());
		obj.put("data", teas.subList(page*limit-limit,Math.min(page*limit, teas.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/addTeacherInfo"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addTeacherInfo(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addTeacherInfo");
		JSONObject obj = new JSONObject();
		int tch_id = Integer.parseInt(in.getString("tch_id"));
		String tch_name = in.getString("tch_name");
		String tch_title = in.getString("tch_title");
		String tch_fin_degree = in.getString("tch_fin_degree");
		String tch_qualification = in.getString("tch_qualification");
		String tch_prof = in.getString("tch_prof");
		String tch_intro = in.getString("tch_intro");
		obj.put("state", "");
		try {
			teacherservice.addTeacherInfo(tch_id, tch_name,tch_title,tch_fin_degree,tch_qualification,tch_prof,tch_intro);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	@RequestMapping(value= {"/deleteTeacherInfo"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteTeacherInfo(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteTeacherInfo");
		JSONObject obj = new JSONObject();
		System.out.print(in.getString("spec_id"));
		int tch_id = Integer.parseInt(in.getString("tch_id"));
		System.out.println(tch_id);
		obj.put("state", "");
		try {
			teacherservice.deleteTeacherInfo(tch_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editTeacherInfo"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editTeacherInfo(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editTeacherInfo");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		int tch_id = Integer.parseInt(in.getString("tch_id"));
		String tch_name = in.getString("tch_name");
		String tch_title = in.getString("tch_title");
		String tch_fin_degree = in.getString("tch_fin_degree");
		String tch_qualification = in.getString("tch_qualification");
		String tch_prof = in.getString("tch_prof");
		String tch_intro = in.getString("tch_intro");
		//System.out.println(spec_id);
//		System.out.println(ad_year);
//		System.out.println(ad_num);
		obj.put("state", "");
		try {
			teacherservice.editTeacherInfo(tch_id, tch_name,tch_title,tch_fin_degree,tch_qualification,tch_prof,tch_intro);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	//teacher train
	@RequestMapping(value= {"/loadTeacherTrain"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadTeacherTrain(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadTeacherTrain");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <TeacherTrain> teas = new ArrayList<TeacherTrain>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String tr_id = request.getParameter("tr_id");
		String tch_id = request.getParameter("tch_id");
		String tr_area = request.getParameter("tr_area");
		String tr_place = request.getParameter("tr_place");
		String tr_content = request.getParameter("tr_content");
		String tr_remark = request.getParameter("tr_remark");
//		
//		System.out.println(ad_num);
//		System.out.println(ad_province);
		if((tr_id!=null||tch_id!=null)&&(tr_id!=""||tch_id!="")){
			System.out.println("in");
			teas = teacherservice.loadSubTeacherTrain(tr_id, tch_id,tr_area,tr_place,tr_content,tr_remark);
		}
		else {
			teas = teacherservice.loadTeacherTrain();
		}
		
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", teas.size());
		obj.put("data", teas.subList(page*limit-limit,Math.min(page*limit, teas.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/addTeacherTran"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addTeacherTran(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addTeacherTran");
		JSONObject obj = new JSONObject();
		int tch_id = Integer.parseInt(in.getString("tch_id"));
		Timestamp tr_start_time = in.getTimestamp("tr_start_time");
		Timestamp tr_end_time = in.getTimestamp("tr_end_time");
		String tr_area = in.getString("tr_area");
		String tr_place = in.getString("tr_place");
		String tr_content = in.getString("tr_content");
		String tr_remark = in.getString("tr_remark");
		obj.put("state", "");
		try {
			teacherservice.addTeacherTran(tch_id, tr_start_time,tr_end_time,tr_area,tr_place,tr_content,tr_remark);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/deleteTeacherTran"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteTeacherTran(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteTeacherTran");
		JSONObject obj = new JSONObject();
		System.out.print(in.getString("spec_id"));
		int tr_id = Integer.parseInt(in.getString("tr_id"));
		System.out.println(tr_id);
		obj.put("state", "");
		try {
			teacherservice.deleteTeacherTran(tr_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editTeacherTran"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editTeacherTran(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editTeacherTran");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		int tr_id = Integer.parseInt(in.getString("tr_id"));
		int tch_id = Integer.parseInt(in.getString("tch_id"));
		Timestamp tr_start_time = in.getTimestamp("tr_start_time");
		Timestamp tr_end_time = in.getTimestamp("tr_end_time");
		String tr_area = in.getString("tr_area");
		String tr_place = in.getString("tr_place");
		String tr_content = in.getString("tr_content");
		String tr_remark = in.getString("tr_remark");
		//System.out.println(spec_id);
//		System.out.println(ad_year);
//		System.out.println(ad_num);
		obj.put("state", "");
		try {
			teacherservice.editTeacherTran(tr_id,tch_id, tr_start_time,tr_end_time,tr_area,tr_place,tr_content,tr_remark);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
}
