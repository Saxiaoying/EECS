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

import team.zucc.eecs.model.CultivationTargerDetail;
import team.zucc.eecs.model.CultivationTarget;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.graduationReaquirement;
import team.zucc.eecs.service.CultivateTargetService;
import team.zucc.eecs.service.SpecialtyService;

@Controller("CultivateTargetController")
public class CultivateTargetController {
	@Autowired
	private CultivateTargetService cultivatetargetservice;
	
	@RequestMapping(value= {"/loadCulTarget"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadCulTarget(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadCulTarget");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <CultivationTarget> cts = new ArrayList<CultivationTarget>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		
		String spec_id = request.getParameter("spec_id");
		String ct_year = request.getParameter("ct_year");
		
		if((spec_id!=null||ct_year!=null)&&(spec_id!=""||ct_year!="")){
			System.out.println("in");
			cts = cultivatetargetservice.loadSubCulTarget(spec_id,ct_year);
		}
		else {
			//显示最新的
			cts = cultivatetargetservice.loadCulTarget();
		}
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", cts.size());
		obj.put("data", cts.subList(page*limit-limit,Math.min(page*limit, cts.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/lookcontent"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject lookcontent(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in lookcontent");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			String content = cultivatetargetservice.lookcontent(ct_id);
			System.out.println(content);
			obj.put("content", content);
		}catch (Exception e) {
			System.out.println("1111");
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editCultarget"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editCultarget(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editCultarget");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		String content = in.getString("content");
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		int year = Integer.parseInt(in.getString("year"));
		int spec_id = Integer.parseInt(in.getString("spec_id"));
		//System.out.println(spec_name);
		//System.out.println(spec_year);
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			cultivatetargetservice.editCultarget(ct_id, content,year,spec_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/addCulTarget"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addCulTarget(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addCulTarget");
		JSONObject obj = new JSONObject();
		String ct_cul_target = in.getString("ct_cul_target");
		int ct_year = Integer.parseInt(in.getString("ct_year"));
		int spec_id = in.getIntValue("spec_id");
		obj.put("state", "");
		try {
			cultivatetargetservice.addCulTarget(spec_id, ct_year,ct_cul_target);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/deleteCulTarget"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCulTarget(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteCulTarget");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		obj.put("state", "");
		try {
			cultivatetargetservice.deleteCulTarget(ct_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	@RequestMapping(value= {"/loadCulTargetHis"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadCulTargetHis(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in loadCulTargetHis");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		int spec_id = Integer.parseInt(request.getParameter("spec_id"));
		List <CultivationTarget> cts = new ArrayList<CultivationTarget>();
		obj.put("state", "");
		try {
			cts = cultivatetargetservice.loadCulTargetHis(spec_id);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		
		obj.put("count", cts.size());
		obj.put("data", cts.subList(page*limit-limit,Math.min(page*limit, cts.size())));
		return obj;
	}
	
	//cul target detail
	
	@RequestMapping(value= {"/loadCulTargetDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadCulTargetDetail(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadCulTargetDetail");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <CultivationTargerDetail> ctds = new ArrayList<CultivationTargerDetail>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		int ct_id = Integer.parseInt(request.getParameter("ct_id"));
		String spec_id = request.getParameter("spec_id");
		String ct_year = request.getParameter("ct_year");
		
		if((spec_id!=null||ct_year!=null)&&(spec_id!=""||ct_year!="")){
			System.out.println("in");
			//cts = cultivatetargetservice.loadSubCulTarget(spec_id,ct_year);
		}
		else {
			//显示最新的
			ctds = cultivatetargetservice.loadCulTargetDetail(ct_id);
		}
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", ctds.size());
		obj.put("data", ctds.subList(page*limit-limit,Math.min(page*limit, ctds.size())));
		return obj;
	}
	
	
	@RequestMapping(value= {"/addCulTargetDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addCulTargetDetail(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addCulTargetDetail");
		JSONObject obj = new JSONObject();
		String content = in.getString("content");
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		obj.put("state", "");
		try {
			cultivatetargetservice.addCulTargetDetail(ct_id, content);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/deleteCulTargetDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteCulTargetDetail(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteCulTargetDetail");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int ctd_id = Integer.parseInt(in.getString("ctd_id"));
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		int ctd_code = Integer.parseInt(in.getString("ctd_code"));
		obj.put("state", "");
		try {
			cultivatetargetservice.deleteCulTargetDetail(ctd_id,ct_id,ctd_code);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editCulTargetDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editCulTargetDetail(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editCulTargetDetail");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		int ctd_id = Integer.parseInt(in.getString("ctd_id"));
		String ctd_content = in.getString("ctd_content");
		//System.out.println(spec_name);
		//System.out.println(spec_year);
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			cultivatetargetservice.editCulTargetDetail(ct_id, ctd_id,ctd_content);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	
	
	
	
	//grad req
	@RequestMapping(value= {"/loadGradReq"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadGradRed(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadGradRed");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <graduationReaquirement> grs = new ArrayList<graduationReaquirement>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		String gr_title = request.getParameter("gr_title");
		String gr_content = request.getParameter("gr_content");
		String ct_id = request.getParameter("ct_id");
		
		if((gr_title!=null||gr_content!=null)&&(gr_title!=""||gr_content!="")){
			System.out.println("in");
			grs = cultivatetargetservice.loadSubGradReq(ct_id,gr_title,gr_content);
		}
		else {
			//显示最新的
			grs = cultivatetargetservice.loadGradReq();
		}
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", grs.size());
		obj.put("data", grs.subList(page*limit-limit,Math.min(page*limit, grs.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/addGradReq"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addGradReq(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addGradReq");
		JSONObject obj = new JSONObject();
		String gr_title = in.getString("gr_title");
		String gr_content = in.getString("gr_content");
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		obj.put("state", "");
		try {
			cultivatetargetservice.addGradReq(ct_id, gr_title,gr_content);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	@RequestMapping(value= {"/deleteGradReq"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteGradReq(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteGradReq");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int gr_id = Integer.parseInt(in.getString("gr_id"));
		int ct_id = Integer.parseInt(in.getString("ct_id"));
		int gr_code = Integer.parseInt(in.getString("gr_code"));
		obj.put("state", "");
		try {
			cultivatetargetservice.deleteGradReq(gr_id,ct_id,gr_code);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editGradReq"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editGradReq(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editGradReq");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		int gr_id = Integer.parseInt(in.getString("gr_id"));
		String gr_title = in.getString("gr_title");
		String gr_content = in.getString("gr_content");
		//System.out.println(spec_name);
		//System.out.println(spec_year);
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			cultivatetargetservice.editGradReq(gr_id, gr_title,gr_content);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	
	
	
	
	
	//index point 
	@RequestMapping(value= {"/loadIdxPt"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadIdxPt(HttpServletRequest request, HttpServletResponse response) {
		
		HttpServletRequest req = (HttpServletRequest) request;
		String url = req.getRequestURI();
		System.out.println("in loadIdxPt");
		//System.out.println("URl:"+url);
		JSONObject obj = new JSONObject();
		List <IndexPoint> ips = new ArrayList<IndexPoint>();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		//System.out.println("tmp "+in.getString("spec_name"));
		String gr_id = request.getParameter("gr_id");
		String ip_code = request.getParameter("ip_code");
		String ip_content = request.getParameter("ip_content");
		
		if((gr_id!=null||ip_code!=null)&&(gr_id!=""||ip_code!="")){
			System.out.println("in");
			ips = cultivatetargetservice.loadSubIdxPt(gr_id,ip_code,ip_content);
		}
		else {
			//显示最新的
			ips = cultivatetargetservice.loadIdxPt();
		}
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", ips.size());
		obj.put("data", ips.subList(page*limit-limit,Math.min(page*limit, ips.size())));
		return obj;
	}
	
	@RequestMapping(value= {"/addIdxPt"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject addIdxPt(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in addIdxPt");
		JSONObject obj = new JSONObject();
		String ip_content = in.getString("ip_content");
		int gr_id = Integer.parseInt(in.getString("gr_id"));
		obj.put("state", "");
		try {
			cultivatetargetservice.addIdxPt(gr_id, ip_content);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/deleteIdxPt"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteIdxPt(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in deleteIdxPt");
		JSONObject obj = new JSONObject();
		//System.out.print(in.getString("spec_id"));
		int gr_id = Integer.parseInt(in.getString("gr_id"));
		int ip_id = Integer.parseInt(in.getString("ip_id"));
		int ip_code = Integer.parseInt(in.getString("ip_code"));
		obj.put("state", "");
		try {
			cultivatetargetservice.deleteIdxPt(ip_id,gr_id,ip_code);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
	
	@RequestMapping(value= {"/editIdxPt"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject editIdxPt(@RequestBody JSONObject in,HttpServletRequest request, HttpServletResponse response) {
		System.out.println("in editIdxPt");
		JSONObject obj = new JSONObject();
		//System.out.println(in.getString("spec_year"));
		int ip_id = Integer.parseInt(in.getString("ip_id"));
		String ip_content = in.getString("ip_content");
		//System.out.println(spec_name);
		//System.out.println(spec_year);
		//System.out.println(spec_id);
		obj.put("state", "");
		try {
			cultivatetargetservice.editIdxPt(ip_id, ip_content);
		}catch (Exception e) {
			obj.put("state", e.getMessage());
		}
		return obj;
	}
}
