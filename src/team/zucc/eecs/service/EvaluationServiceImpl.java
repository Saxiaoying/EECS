//This is maintained by jyl. 
package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.EvaluationDao;
import team.zucc.eecs.model.Evaluation;

@Component("EvaluationServiceImpl")
public class EvaluationServiceImpl implements EvaluationService {
	@Autowired
	private  EvaluationDao evaluationDao;
	
	@Override
	public int existEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id) {
		try {
			Evaluation e = evaluationDao.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
			if(e == null) return 0;
			else return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return -1; //出错
		}
	}

	@Override
	public Evaluation getEvaluationByCs_idAndCo_idAndEt_id(int cs_id, int co_id, int et_id) {
		try {
			Evaluation e = evaluationDao.getEvaluationByCs_idAndCo_idAndEt_id(cs_id, co_id, et_id);
			return e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addEvaluation(int co_id, int cs_id, int et_id, double eval_prop, double eval_points, double eval_score,
			double eval_sc_rt, double eval_achv) {
		try {
			evaluationDao.addEvaluation(co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int deleteEvaluationByEval_id(int eval_id) {
		try {
			evaluationDao.deleteEvaluationByEval_id(eval_id);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	@Override
	public int updateEvaluationByEval_id(int eval_id, int co_id, int cs_id, int et_id, double eval_prop, double eval_points,
			double eval_score, double eval_sc_rt, double eval_achv) {
		try {
			evaluationDao.updateEvaluationByEval_id(eval_id, co_id, cs_id, et_id, eval_prop, eval_points, eval_score, eval_sc_rt, eval_achv);
			return 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

}
