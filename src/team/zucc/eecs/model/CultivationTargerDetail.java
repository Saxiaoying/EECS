package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("CultivationTargerDetail")
public class CultivationTargerDetail {
	@JSONField(ordinal = 1)
	private int ctd_id;
	@JSONField(ordinal = 2)
	private int ct_id;
	@JSONField(ordinal = 3)
	private int ctd_code;
	public int getCtd_id() {
		return ctd_id;
	}
	public void setCtd_id(int ctd_id) {
		this.ctd_id = ctd_id;
	}
	public int getCt_id() {
		return ct_id;
	}
	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}
	public int getCtd_code() {
		return ctd_code;
	}
	public void setCtd_code(int ctd_code) {
		this.ctd_code = ctd_code;
	}
	public String getCtd_content() {
		return ctd_content;
	}
	public void setCtd_content(String ctd_content) {
		this.ctd_content = ctd_content;
	}
	private String ctd_content;
	
}
