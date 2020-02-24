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
import team.zucc.eecs.model.Student;
import team.zucc.eecs.model.StudentAdmission;
import team.zucc.eecs.model.StudentChangeSpec;
import team.zucc.eecs.model.StudentEmployOrient;
import team.zucc.eecs.service.SpecialtyService;
import team.zucc.eecs.service.StudentService;


@Controller("StudentController")
public class StudentController {
	@Autowired
	private StudentService studentservice;

	
	@RequestMapping(value= {"/loadad"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadad(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadad");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <StudentAdmission> ads = new ArrayList<StudentAdmission>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String spec_id = request.getParameter("spec_id");
		String ad_year = request.getParameter("ad_year");
		String ad_province = request.getParameter("ad_province");
		String ad_min_point = request.getParameter("ad_min_point");
		String ad_num = request.getParameter("ad_num");
		
		System.out.println(spec_id);
		System.out.println(ad_num);
		System.out.println(ad_province);
		if((spec_id!=null||ad_year!=null)&&(spec_id!=""||ad_year!="")){
			System.out.println("in");
			ads = studentservice.loadSubady(spec_id,ad_year,ad_province,ad_min_point,ad_num);
		}
		else {
			ads = studentservice.loadAd();
		}
		
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", ads.size());
		obj.put("data", ads.subList(page*limit-limit,Math.min(page*limit, ads.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/addad"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addad(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addad");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_id"));
		//System.out.println(in.getString("ad_year"));
		//System.out.println(in.getString("ad_province"));
		//System.out.println(in.getString("ad_min_point"));
		//System.out.println(in.getString("ad_num"));
		String ad_province = in.getString("ad_province");
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		int ad_year = Integer.parseInt(in.getString("ad_year"));
		int ad_min_point = Integer.parseInt(in.getString("ad_min_point"));
		int ad_num = Integer.parseInt(in.getString("ad_num"));
		obj.put("state", "");
		try {
			studentservice.addad(spec_id, ad_year,ad_province,ad_min_point,ad_num);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/deletead"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deletead(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deletead");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int ad_id = Integer.parseInt(in.getString("ad_id"));
		System.out.println(ad_id);
		obj.put("state", "");
		try {
			studentservice.deletead(ad_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editad"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editad(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editad");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		String ad_province = in.getString("ad_province");
		int ad_id = Integer.parseInt(in.getString("ad_id"));
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		int ad_year = Integer.parseInt(in.getString("ad_year"));
		int ad_min_point = Integer.parseInt(in.getString("ad_min_point"));
		int ad_num = Integer.parseInt(in.getString("ad_num"));
		System.out.println(ad_id);
		//System.out.println(spec_id);
		System.out.println(ad_year);
		System.out.println(ad_num);
		obj.put("state", "");
		try {
			studentservice.editad(ad_id, spec_id,ad_year,ad_province,ad_min_point,ad_num);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/loadstu"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadstu(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadstu");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <Student> stus = new ArrayList<Student>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String stu_id = request.getParameter("stu_id");
		String class_id = request.getParameter("class_id");
		String stu_name = request.getParameter("stu_name");
//		
		System.out.println(stu_id);
//		System.out.println(ad_num);
//		System.out.println(ad_province);
		if((stu_id!=null||class_id!=null)&&(stu_id!=""||class_id!="")){
			System.out.println("in");
			stus = studentservice.loadSubStu(stu_id,class_id,stu_name);
		}
		else {
			stus = studentservice.loadstu();
		}
		
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", stus.size());
		obj.put("data", stus.subList(page*limit-limit,Math.min(page*limit, stus.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/addstu"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addstu(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addstu");
		JSONObject obj = new JSONObject();
		System.out.println(in.getString("spec_id"));
		//System.out.println(in.getString("ad_year"));
		//System.out.println(in.getString("ad_province"));
		//System.out.println(in.getString("ad_min_point"));
		//System.out.println(in.getString("ad_num"));
		int stu_id = Integer.parseInt(in.getString("stu_id"));
		int class_id = Integer.parseInt(in.getString("class_id"));
		String stu_name = in.getString("stu_name");
		obj.put("state", "");
		try {
			studentservice.addstu(stu_id, class_id,stu_name);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/deletestu"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deletestu(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deletestu");
		JSONObject obj = new JSONObject();
		System.out.print(in.getString("spec_id"));
		int stu_id = Integer.parseInt(in.getString("stu_id"));
		System.out.println(stu_id);
		obj.put("state", "");
		try {
			studentservice.deletestu(stu_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editstu"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editstu(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editstu");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		String stu_name = in.getString("stu_name");
		int stu_id = Integer.parseInt(in.getString("stu_id"));
		int class_id = Integer.parseInt(in.getString("class_id"));
		System.out.println(stu_id);
		//System.out.println(spec_id);
//		System.out.println(ad_year);
//		System.out.println(ad_num);
		obj.put("state", "");
		try {
			studentservice.editstu(stu_id, class_id,stu_name);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	
	//change_spec
	@RequestMapping(value= {"/loadChangeSpec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadChangeSpec(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadChangeSpec");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <StudentChangeSpec> stus = new ArrayList<StudentChangeSpec>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String cs_id = request.getParameter("cs_id");
		String in_class_id = request.getParameter("in_class_id");
		String out_class_id = request.getParameter("out_class_id");
		String stu_id = request.getParameter("stu_id");
//		
//		System.out.println(ad_num);
//		System.out.println(ad_province);
		if((cs_id!=null||in_class_id!=null)&&(cs_id!=""||in_class_id!="")){
			System.out.println("in");
			stus = studentservice.loadSubChangeSpec(cs_id,in_class_id,out_class_id,stu_id);
		}
		else {
			stus = studentservice.loadChangeSpec();
		}
		
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", stus.size());
		obj.put("data", stus.subList(page*limit-limit,Math.min(page*limit, stus.size())));
		return obj;
	}
	@RequestMapping(value= {"/addChangeSpec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addChangeSpec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addstu");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_id"));
		//System.out.println(in.getString("ad_year"));
		//System.out.println(in.getString("ad_province"));
		//System.out.println(in.getString("ad_min_point"));
		//System.out.println(in.getString("ad_num"));
		int in_class_id = Integer.parseInt(in.getString("in_class_id"));
		int out_class_id = Integer.parseInt(in.getString("out_class_id"));
		int stu_id = Integer.parseInt(in.getString("stu_id"));
		obj.put("state", "");
		try {
			studentservice.addChangeSpec(in_class_id, out_class_id,stu_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/deleteChangeSpec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteChangeSpec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteChangeSpec");
		JSONObject obj = new JSONObject();
		System.out.print(in.getString("spec_id"));
		int cs_id = Integer.parseInt(in.getString("cs_id"));
		System.out.println(cs_id);
		obj.put("state", "");
		try {
			studentservice.deleteChangeSpec(cs_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editChangeSpec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editChangeSpec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editChangeSpec");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		int cs_id = Integer.parseInt(in.getString("cs_id"));
		int in_class_id = Integer.parseInt(in.getString("in_class_id"));
		int out_class_id = Integer.parseInt(in.getString("out_class_id"));
		//System.out.println(spec_id);
//		System.out.println(ad_year);
//		System.out.println(ad_num);
		obj.put("state", "");
		try {
			studentservice.editChangeSpec(cs_id, in_class_id,out_class_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	//emploty orient
	//change_spec
		@RequestMapping(value= {"/loadEmployOrient"}, method=RequestMethod.POST)
		@ResponseBody
		public JSONObject loadEmployOrient(HttpServletRequest request, HttpServletResponse response) {
			
			HttpServletRequest req = (HttpServletRequest) request;
			String url = req.getRequestURI();
			System.out.println("in loadEmployOrient");
			//System.out.println("URl:"+url);
			JSONObject obj = new JSONObject();
			List <StudentEmployOrient> stus = new ArrayList<StudentEmployOrient>();
			int page = Integer.parseInt(request.getParameter("page"));
			int limit = Integer.parseInt(request.getParameter("limit"));
			
			//System.out.println("tmp "+in.getString("spec_name"));
			
			String eoc_id = request.getParameter("eoc_id");
			String stu_id = request.getParameter("stu_id");
			String eo_department = request.getParameter("eo_department");
			String eo_city = request.getParameter("eo_city");
			String eo_annual_salary = request.getParameter("eo_annual_salary");
//			
//			System.out.println(ad_num);
//			System.out.println(ad_province);
			if((eoc_id!=null||stu_id!=null)&&(eoc_id!=""||stu_id!="")){
				System.out.println("in");
				stus = studentservice.loadSubEmployOrient(eoc_id,stu_id,eo_department,eo_city,eo_annual_salary);
			}
			else {
				stus = studentservice.loadEmployOrient();
			}
			
			obj.put("code", "0");
			obj.put("msg", "");
			obj.put("count", stus.size());
			obj.put("data", stus.subList(page*limit-limit,Math.min(page*limit, stus.size())));
			return obj;
		}
		
		@RequestMapping(value= {"/addEmployOrient"}, method=RequestMethod.POST)
		@ResponseBody
		public JSONObject addEmployOrient(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
			System.out.println("in addEmployOrient");
			JSONObject obj = new JSONObject();
			//System.out.println(in.getString("spec_id"));
			//System.out.println(in.getString("ad_year"));
			//System.out.println(in.getString("ad_province"));
			//System.out.println(in.getString("ad_min_point"));
			//System.out.println(in.getString("ad_num"));
			int eoc_id = Integer.parseInt(in.getString("eoc_id"));
			int stu_id = Integer.parseInt(in.getString("stu_id"));
			int eo_year = Integer.parseInt(in.getString("eo_year"));
			String eo_department = in.getString("eo_department");
			String eo_city = in.getString("eo_city");
			float eo_annual_salary = Float.parseFloat(in.getString("eo_annual_salary"));
			obj.put("state", "");
			try {
				studentservice.addEmployOrient(eoc_id, stu_id,eo_year,eo_department,eo_city,eo_annual_salary);
			}catch (Exception e) {
				obj.put("state", e.getMessage());
			}
			return obj;
		}
		
		@RequestMapping(value= {"/deleteEmployOrient"}, method=RequestMethod.POST)
		@ResponseBody
		public JSONObject deleteEmployOrient(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
			System.out.println("in deleteEmployOrient");
			JSONObject obj = new JSONObject();
			System.out.print(in.getString("spec_id"));
			int eo_id = Integer.parseInt(in.getString("eo_id"));
			System.out.println(eo_id);
			obj.put("state", "");
			try {
				studentservice.deleteEmployOrient(eo_id);
			}catch (Exception e) {
				obj.put("state", e.getMessage());
			}
			return obj;
		}
		
		@RequestMapping(value= {"/editEmployOrient"}, method=RequestMethod.POST)
		@ResponseBody
		public JSONObject editEmployOrient(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
			System.out.println("in editEmployOrient");
			JSONObject obj = new JSONObject();
			//System.out.println(in.getString("spec_year"));
			int eo_id = Integer.parseInt(in.getString("eo_id"));
			int eoc_id = Integer.parseInt(in.getString("eoc_id"));
			int stu_id = Integer.parseInt(in.getString("stu_id"));
			int eo_year = Integer.parseInt(in.getString("eo_year"));
			String eo_department = in.getString("eo_department");
			String eo_city = in.getString("eo_city");
			float eo_annual_salary = Float.parseFloat(in.getString("eo_annual_salary"));
			//System.out.println(spec_id);
//			System.out.println(ad_year);
//			System.out.println(ad_num);
			obj.put("state", "");
			try {
				studentservice.editEmployOrient(eo_id, eoc_id,stu_id,eo_year,eo_department,eo_city,eo_annual_salary);
			}catch (Exception e) {
				obj.put("state", e.getMessage());
			}
			return obj;
		}
}
