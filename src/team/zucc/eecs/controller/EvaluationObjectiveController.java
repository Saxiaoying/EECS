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

import com.alibaba.fastjson.JSONObject;

import team.zucc.eecs.model.Course;
import team.zucc.eecs.model.CourseContent;
import team.zucc.eecs.model.CourseObjective;
import team.zucc.eecs.model.CoursePractice;
import team.zucc.eecs.model.CourseSet;
import team.zucc.eecs.model.Evaluation;
import team.zucc.eecs.model.EvaluationType;
import team.zucc.eecs.service.CourseContentService;
import team.zucc.eecs.service.CourseObjectiveService;
import team.zucc.eecs.service.CoursePracticeService;
import team.zucc.eecs.service.CourseService;
import team.zucc.eecs.service.CourseSetService;
import team.zucc.eecs.service.EvaluationService;
import team.zucc.eecs.service.EvaluationTypeService;

@Controller("EvaluationObjectiveController")
public class EvaluationObjectiveController {
	@Autowired
	private CourseObjectiveService courseObjectiveService;
	
	@Autowired
	private CourseSetService courseSetService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private EvaluationTypeService evaluationTypeService;
	
	@Autowired
	private EvaluationService evaluationService;
	
	
	@Autowired
	private CourseContentService courseContentService;
	
	@Autowired
	private CoursePracticeService  coursePracticeService;
	
	@RequestMapping(value = { "/getEvaluationObjective" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getEvaluationObjective(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入EvaluationObjectiveController-getEvaluationObjective");
		
		JSONObject obj = new JSONObject();
		try {
			String coz_id = in.getString("coz_id");
			String cs_acad_yr = in.getString("cs_acad_yr");
			String cs_sem = in.getString("cs_sem");
			
			CourseSet courseSet = courseSetService.getCourseSetByCoz_idAndTime(coz_id, cs_acad_yr, cs_sem);
			if(courseSet == null) {
				obj.put("state", "暂无该开课信息！");
				return obj;
			}
			
			Course course = courseService.getCourseByCoz_id(courseSet.getCoz_id());
			List<CourseObjective> courseObjectiveList = courseObjectiveService.getCourseObjectiveListByCs_id(courseSet.getCs_id());
			if(courseObjectiveList == null) {
				courseObjectiveList = new ArrayList<CourseObjective>();
			}
			if (courseObjectiveList.size() == 0) {
				obj.put("state", "当前课程暂无课程目标，请前往开课记录设置！");
				return obj;
			}
			List<EvaluationType> evaluationTypeList = new ArrayList<EvaluationType>();
			evaluationTypeList = evaluationTypeService.getEvaluationTypeList();
			
			List<Evaluation> evaluationList = new ArrayList<Evaluation>();
			for (CourseObjective co: courseObjectiveList) {
				for (EvaluationType et: evaluationTypeList) {
					Evaluation e = evaluationService.getEvaluationByCs_idAndCo_idAndEt_id(courseSet.getCs_id(), co.getCo_id(), et.getEt_id());
					if(e == null) {
						int f = evaluationService.addEvaluation(co.getCo_id(), courseSet.getCs_id(), et.getEt_id(), 0, 0, 0, 0, 0);
						if(f == 0) e = evaluationService.getEvaluationByCs_idAndCo_idAndEt_id(courseSet.getCs_id(), co.getCo_id(), et.getEt_id());
					}
					evaluationList.add(e);
				}
			}
			
			List<CourseContent> courseContentList = courseContentService.getCourseContentListByCs_id(courseSet.getCs_id());
			if(courseContentList == null) {
				courseContentList = new ArrayList<CourseContent>();
			}
			
			List<CoursePractice> coursePracticeList = coursePracticeService.getCoursePracticeListByCs_id(courseSet.getCs_id());
			if(coursePracticeList == null) {
				coursePracticeList = new ArrayList<CoursePractice>();
			}
			obj.put("courseSet", courseSet);
			obj.put("course", course);
			obj.put("courseObjectiveList", courseObjectiveList);
			obj.put("courseObjectiveList_num", courseObjectiveList.size());
			obj.put("evaluationList", evaluationList);
			obj.put("evaluationList_num", evaluationList.size());
			obj.put("courseContentList", courseContentList);
			obj.put("courseContentList_num", courseContentList.size());
			obj.put("coursePracticeList", coursePracticeList);
			obj.put("coursePracticeList_num", coursePracticeList.size());
			obj.put("state", "OK");
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}
	
	
	@RequestMapping(value = { "/updateEvaluationObjective" }, method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updateEvaluationObjective(@RequestBody JSONObject in, HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("进入getEvaluationObjective-updateEvaluationObjective");
		
		JSONObject obj = new JSONObject();
		try {
			int co_id = in.getIntValue("co_id");
			int cs_id = in.getIntValue("cs_id");
			int et_id = in.getIntValue("et_id");
			double eval_prop = in.getDoubleValue("eval_prop");
			double eval_points = in.getDoubleValue("eval_points");
			double eval_score = in.getDoubleValue("eval_score");
			double eval_sc_rt = in.getDoubleValue("eval_sc_rt");
			double eval_achv = eval_sc_rt*eval_prop;
			
			Evaluation e = evaluationService.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
			int eval_id = e.getEval_id();
			
			int f = evaluationService.updateEvaluationByEval_id(eval_id, co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			
			if(f== 0) {
				obj.put("state", "OK");
				e = evaluationService.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
				obj.put("evaluation", e);
			}
		} catch (Exception e) {
			e.printStackTrace();
			obj.put("state", "数据库错误！");
			return obj;
		}
		return obj;
	}

}
