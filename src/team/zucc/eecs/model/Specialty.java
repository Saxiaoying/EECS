package team.zucc.eecs.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Specialty {
	@JSONField(ordinal = 1)
	private int spec_id;
	@JSONField(ordinal = 2)
	private String spec_name;
	@JSONField(ordinal = 3)
	private int spec_year;
	public int getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(int spec_id) {
		this.spec_id = spec_id;
	}
	public String getSpec_name() {
		return spec_name;
	}
	public void setSpec_name(String spec_name) {
		this.spec_name = spec_name;
	}
	public int getSpec_year() {
		return spec_year;
	}
	public void setSpec_year(int spec_year) {
		this.spec_year = spec_year;
	}
}
