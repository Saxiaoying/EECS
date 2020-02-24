package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("graduationReaquirement")
public class graduationReaquirement {
	@JSONField(ordinal = 1)
	private int gr_id;
	@JSONField(ordinal = 2)
	private int ct_id;
	@JSONField(ordinal = 3)
	private int gr_code;
	@JSONField(ordinal = 4)
	private String gr_title;
	@JSONField(ordinal = 5)
	private String gr_content;
	public int getGr_id() {
		return gr_id;
	}
	public void setGr_id(int gr_id) {
		this.gr_id = gr_id;
	}
	public int getCt_id() {
		return ct_id;
	}
	public void setCt_id(int ct_id) {
		this.ct_id = ct_id;
	}
	public int getGr_code() {
		return gr_code;
	}
	public void setGr_code(int gr_code) {
		this.gr_code = gr_code;
	}
	public String getGr_title() {
		return gr_title;
	}
	public void setGr_title(String gr_title) {
		this.gr_title = gr_title;
	}
	public String getGr_content() {
		return gr_content;
	}
	public void setGr_content(String gr_content) {
		this.gr_content = gr_content;
	}
	
}
