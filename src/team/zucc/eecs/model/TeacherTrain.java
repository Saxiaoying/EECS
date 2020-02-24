package team.zucc.eecs.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("TeacherTrain")
public class TeacherTrain {
	@JSONField(ordinal = 1)
	private int tr_id;
	@JSONField(ordinal = 2)
	private int tch_id;
	@JSONField(ordinal = 3)
	private Timestamp tr_start_time;
	@JSONField(ordinal = 4)
	private Timestamp tr_end_time;
	@JSONField(ordinal = 5)
	private String tr_area;
	@JSONField(ordinal = 6)
	private String tr_place;
	@JSONField(ordinal = 7)
	private String tr_content;
	@JSONField(ordinal = 8)
	private String tr_remark;
	public int getTr_id() {
		return tr_id;
	}
	public void setTr_id(int tr_id) {
		this.tr_id = tr_id;
	}
	public int getTch_id() {
		return tch_id;
	}
	public void setTch_id(int tch_id) {
		this.tch_id = tch_id;
	}
	public Timestamp getTr_start_time() {
		return tr_start_time;
	}
	public void setTr_start_time(Timestamp tr_start_time) {
		this.tr_start_time = tr_start_time;
	}
	public Timestamp getTr_end_time() {
		return tr_end_time;
	}
	public void setTr_end_time(Timestamp tr_end_time) {
		this.tr_end_time = tr_end_time;
	}
	public String getTr_area() {
		return tr_area;
	}
	public void setTr_area(String tr_area) {
		this.tr_area = tr_area;
	}
	public String getTr_place() {
		return tr_place;
	}
	public void setTr_place(String tr_place) {
		this.tr_place = tr_place;
	}
	public String getTr_content() {
		return tr_content;
	}
	public void setTr_content(String tr_content) {
		this.tr_content = tr_content;
	}
	public String getTr_remark() {
		return tr_remark;
	}
	public void setTr_remark(String tr_remark) {
		this.tr_remark = tr_remark;
	}
}
