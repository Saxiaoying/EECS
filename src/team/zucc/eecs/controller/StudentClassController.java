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

import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.StudentClass;
import team.zucc.eecs.service.SpecialtyService;
import team.zucc.eecs.service.StudentClassService;


@Controller("StudentClassController")
public class StudentClassController {
	@Autowired
	private StudentClassService studentclassservice;
	
	@RequestMapping(value= {"/loadclass"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadclass(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		//String url = req.getRequestURI();
		System.out.println("in loadclass");
		//System.out.println("URl:"+url);
		
		JSONObject obj = new JSONObject();
		List <StudentClass> classes = new ArrayList<StudentClass>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String class_name = request.getParameter("class_name");
		String spec_id = request.getParameter("spec_id");
		
		//System.out.println(class_name);
		System.out.println(spec_id);
		if((class_name!=null||spec_id!=null)&&(class_name!=""||spec_id!="")) {
			classes = studentclassservice.loadSubClass(class_name,spec_id);
		}else {
			classes = studentclassservice.loadClass();
		}
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", classes.size());
		obj.put("data", classes.subList(page*limit-limit,Math.min(page*limit, classes.size())));
		return obj;
	}
	
	
	@RequestMapping(value= {"/addclass"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addclassc(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addclass");
		JSONObject obj = new JSONObject();
		System.out.println(in.getString("class_name"));
		System.out.println(in.getString("spec_id"));
		String class_name = in.getString("class_name");
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		obj.put("state", "");
		try {
			studentclassservice.addclass(class_name, spec_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/deleteclass"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deletespec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteclass");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int class_id = Integer.parseInt(in.getString("class_id"));
		System.out.println(class_id);
		obj.put("state", "");
		try {
			studentclassservice.deleteclass(class_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	

	@RequestMapping(value= {"/editclass"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editspec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editclass");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		String class_name = in.getString("class_name");
		int class_id = Integer.parseInt(in.getString("class_id"));
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		System.out.println(class_name);
		System.out.println(class_id);
		System.out.println(spec_id);
		obj.put("state", "");
		try {
			studentclassservice.editclass(class_id, class_name,spec_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
}
