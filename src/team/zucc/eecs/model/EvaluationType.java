//This is maintained by jyl. 
package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

@Component("EvaluationTypeBean")
public class EvaluationType {
	@JSONField(ordinal = 1)
	private int et_id; //类型表ID （外码）
	
	@JSONField(ordinal = 2)
	private int et_name; //名字
	
	public JSONObject toJson() {
		JSONObject j = new JSONObject();
		
		j.put("et_id", et_id);
		j.put("et_name", et_name);
		return j;
	}
}
