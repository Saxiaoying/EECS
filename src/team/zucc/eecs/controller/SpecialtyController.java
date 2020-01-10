package team.zucc.eecs.controller;

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


@Controller("SpecialtyController")
public class SpecialtyController {
	@Autowired
	private SpecialtyDao specdao;
	
	
	@RequestMapping(value= {"/loadspec"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject loadspec(HttpServletRequest request, HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		List <Specialty> specs = specdao.loadSpecialty();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		obj.put("code", "0");
		obj.put("msg", "");
		obj.put("count", specs.size());
		obj.put("data", specs.subList(page*limit-limit,Math.min(page*limit, specs.size())));
		return obj;
	}
/*	
	@RequestMapping(value= {"/userLogin"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject userLogin(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入UserLoginController-userLogin");
		JSONObject obj = new JSONObject();
		int user_id = in.getIntValue("user_id");
		String pwd = in.getString("user_pwd");
		try {
			User user = userDao.getUserById(user_id);
			System.out.println(user);
			if(user == null) {
				obj.put("state", "NO_USER");
				return obj;
			}
			
			if(user.getUser_pwd().compareTo(pwd) == 0) {
				request.getSession().setAttribute("USER_ID", user.getUser_id());
				request.getSession().setAttribute("USER_NAME", user.getUser_name());
				request.getSession().setAttribute("USER_PWD", user.getUser_pwd());
				request.getSession().setAttribute("USER_LOG_T", user.getUser_log_t());
				request.getSession().setAttribute("USER_TYP", user.getUser_typ());
			} else {
				obj.put("state", "WRONG_PWD");
				return obj;
			}
		} catch (Exception e) {
			obj.put("state", "ERROR");
			e.printStackTrace();
			return obj;
		}
		obj.put("state", "OK");
		return obj;
	}

	*/
	
}
