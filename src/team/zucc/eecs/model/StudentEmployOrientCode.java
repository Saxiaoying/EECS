package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("StudentEmployOrientCode")
public class StudentEmployOrientCode {
	@JSONField(ordinal = 1)
	private int eoc_id;
	@JSONField(ordinal = 2)
	private String eoc_name;
	public int getEoc_id() {
		return eoc_id;
	}
	public void setEoc_id(int eoc_id) {
		this.eoc_id = eoc_id;
	}
	public String getEoc_name() {
		return eoc_name;
	}
	public void setEoc_name(String eoc_name) {
		this.eoc_name = eoc_name;
	}
}
