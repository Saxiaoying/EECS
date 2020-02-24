package team.zucc.eecs.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("StudentChangeSpec")
public class StudentChangeSpec {
	@JSONField(ordinal = 1)
	private int cs_id;
	@JSONField(ordinal = 2)
	private int in_class_id;
	@JSONField(ordinal = 3)
	private int out_class_id;
	@JSONField(ordinal = 4)
	private int stu_id;
	@JSONField(ordinal = 5)
	private Timestamp cs_time;
	public int getCs_id() {
		return cs_id;
	}
	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}
	public int getIn_class_id() {
		return in_class_id;
	}
	public void setIn_class_id(int in_class_id) {
		this.in_class_id = in_class_id;
	}
	public int getOut_class_id() {
		return out_class_id;
	}
	public void setOut_class_id(int out_class_id) {
		this.out_class_id = out_class_id;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public Timestamp getCs_time() {
		return cs_time;
	}
	public void setCs_time(Timestamp cs_time) {
		this.cs_time = cs_time;
	}
	
}
