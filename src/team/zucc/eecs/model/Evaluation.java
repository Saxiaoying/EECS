//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("EvaluationBean")
public class Evaluation {
	@JSONField(ordinal = 1)
	private int eval_id;
	
	@JSONField(ordinal = 2)
	private String eval_cont;

	public int getEval_id() {
		return eval_id;
	}

	public void setEval_id(int eval_id) {
		this.eval_id = eval_id;
	}

	public String getEval_cont() {
		return eval_cont;
	}

	public void setEval_cont(String eval_cont) {
		this.eval_cont = eval_cont;
	}
	
	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("eval_id", eval_id);
		j.put("eval_cont", eval_cont);
		return j;
	}
}
