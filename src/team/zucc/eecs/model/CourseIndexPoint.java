package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("CourseIndexPoint")
public class CourseIndexPoint {
	@JSONField(ordinal = 1)
	private int cs_id;
	@JSONField(ordinal = 2)
	private int ip_id;
	@JSONField(ordinal = 3)
	private float scale;
	public int getCs_id() {
		return cs_id;
	}
	public void setCs_id(int cs_id) {
		this.cs_id = cs_id;
	}
	public int getIp_id() {
		return ip_id;
	}
	public void setIp_id(int ip_id) {
		this.ip_id = ip_id;
	}
	public float getScale() {
		return scale;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	
}
