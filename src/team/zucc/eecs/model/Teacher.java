package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("Teacher")
public class Teacher {
	@JSONField(ordinal = 1)
	private int tch_id;
	@JSONField(ordinal = 2)
	private String tch_name;
	@JSONField(ordinal = 3)
	private String tch_title;
	@JSONField(ordinal = 4)
	private String tch_fin_degree;
	@JSONField(ordinal = 5)
	private String tch_qualification;
	@JSONField(ordinal = 6)
	private String tch_prof;
	@JSONField(ordinal = 7)
	private String tch_intro;
	public int getTch_id() {
		return tch_id;
	}
	public void setTch_id(int tch_id) {
		this.tch_id = tch_id;
	}
	public String getTch_name() {
		return tch_name;
	}
	public void setTch_name(String tch_name) {
		this.tch_name = tch_name;
	}
	public String getTch_title() {
		return tch_title;
	}
	public void setTch_title(String tch_title) {
		this.tch_title = tch_title;
	}
	public String getTch_fin_degree() {
		return tch_fin_degree;
	}
	public void setTch_fin_degree(String tch_fin_degree) {
		this.tch_fin_degree = tch_fin_degree;
	}
	public String getTch_qualification() {
		return tch_qualification;
	}
	public void setTch_qualification(String tch_qualification) {
		this.tch_qualification = tch_qualification;
	}
	public String getTch_prof() {
		return tch_prof;
	}
	public void setTch_prof(String tch_prof) {
		this.tch_prof = tch_prof;
	}
	public String getTch_intro() {
		return tch_intro;
	}
	public void setTch_intro(String tch_intro) {
		this.tch_intro = tch_intro;
	}
	
}
