package team.zucc.eecs.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("CultivationTarget")
public class CultivationTarget {
	@JSONField(ordinal = 1)
	private int ct_id;
	@JSONField(ordinal = 2)
	private int spec_id;
	@JSONField(ordinal = 3)
	private int ct_year;
	@JSONField(ordinal = 4)
	private String ct_cul_target;
	@JSONField(ordinal = 5)
	private Timestamp ct_update_time;
	public int getCt_id() {
		return ct_id;
	}
	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}
	public int getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(int spec_id) {
		this.spec_id = spec_id;
	}
	public int getCt_year() {
		return ct_year;
	}
	public void setCt_year(int ct_year) {
		this.ct_year = ct_year;
	}
	public String getCt_cul_target() {
		return ct_cul_target;
	}
	public void setCt_cul_target(String ct_cul_target) {
		this.ct_cul_target = ct_cul_target;
	}
	public Timestamp getCt_update_time() {
		return ct_update_time;
	}
	public void setCt_update_time(Timestamp ct_update_time) {
		this.ct_update_time = ct_update_time;
	}
	
}
