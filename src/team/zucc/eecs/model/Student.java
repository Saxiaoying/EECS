package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("Student")
public class Student {
	@JSONField(ordinal = 1)
	private int stu_id;
	@JSONField(ordinal = 2)
	private int class_id;
	@JSONField(ordinal = 3)
	private String stu_name;
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public int getClass_id() {
		return class_id;
	}
	public void setClass_id(int class_id) {
		this.class_id = class_id;
	}
	public String getStu_name() {
		return stu_name;
	}
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	
}
