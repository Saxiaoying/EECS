package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;
@Component("StudentClass")
public class StudentClass {
	@JSONField(ordinal = 1)
	private int class_id;
	@JSONField(ordinal = 2)
	private int spec_id;
	@JSONField(ordinal = 3)
	private String class_name;
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public int getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(int spec_id) {
		this.spec_id = spec_id;
	}
	public String getClass_name() {
		return class_name;
	}
	public void setClass_name(String class_name) {
		this.class_name = class_name;
	}
	
}
