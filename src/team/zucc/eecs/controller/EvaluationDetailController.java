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

import team.zucc.eecs.model.EvaluationDetail;
import team.zucc.eecs.service.EvaluationDetailService;

@Controller("EvaluationDetailController")
public class EvaluationDetailController {
	@Autowired
	private EvaluationDetailService evaluationDetailService;
	
	@RequestMapping(value= {"/getEvaluationDetail"}, method=RequestMethod.POST)
	@ResponseBody
	public JSONObject getEvaluationDetail(@RequestBody JSONObject in, HttpServletRequest request, HttpServletResponse response) {
		System.out.println("进入EvaluationDetailController-getEvaluationDetail");

		JSONObject obj = new JSONObject();
		List<EvaluationDetail> evaluationDetailList = new ArrayList<EvaluationDetail>();
		try {
			int cs_id = in.getIntValue("cs_id");
			int et_id = in.getIntValue("et_id");
			evaluationDetailList = evaluationDetailService.getEvaluationDatailListByCs_idAndEt_id(cs_id, et_id);

			obj.put("evaluationDetailList_num", evaluationDetailList.size());
			obj.put("evaluationDetailList", evaluationDetailList);
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
}
