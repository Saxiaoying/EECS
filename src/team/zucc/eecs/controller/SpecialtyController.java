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

import team.zucc.eecs.dao.SpecialtyDao;
import team.zucc.eecs.dao.UserDao;
import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.User;
import team.zucc.eecs.service.SpecialtyService;


@Controller("SpecialtyController")
public class SpecialtyController {
	@Autowired
	private SpecialtyService specialtyservice;
	
	
	@RequestMapping(value= {"/loadspec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadspec(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadspec");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <Specialty> specs = new ArrayList<Specialty>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String spec_name = request.getParameter("spec_name");
		String spec_year = request.getParameter("spec_year");
		
		System.out.println(spec_name);
		System.out.println(spec_year);
		if((spec_name!=null||spec_year!=null)&&(spec_name!=""||spec_year!="")){
			System.out.println("in");
				specs = specialtyservice.loadSubSpecialty(spec_name,spec_year);
		}
		else {
			specs = specialtyservice.loadSpecialty();
		}
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", specs.size());
		obj.put("data", specs.subList(page*limit-limit,Math.min(page*limit, specs.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/deletespec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deletespec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deletespec");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			specialtyservice.deletespec(spec_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/addspec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addspec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addspec");
		JSONObject obj = new JSONObject();
		System.out.println(in.getString("spec_name"));
		//System.out.println(in.getString("spec_year"));
		String spec_name = in.getString("spec_name");
		int spec_year = Integer.parseInt(in.getString("spec_year"));
		obj.put("state", "");
		try {
			specialtyservice.addspec(spec_name, spec_year);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editspec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editspec(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editspec");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		String spec_name = in.getString("spec_name");
		int spec_year = Integer.parseInt(in.getString("spec_year"));
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		//System.out.println(spec_name);
		//System.out.println(spec_year);
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			specialtyservice.editspec(spec_id, spec_name,spec_year);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
}
