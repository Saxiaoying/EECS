package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("StudentAdmission")
public class StudentAdmission {
	@JSONField(ordinal = 1)
	private int ad_id;
	@JSONField(ordinal = 2)
	private int spec_id;
	@JSONField(ordinal = 3)
	private int ad_year;
	@JSONField(ordinal = 4)
	private String ad_province;
	@JSONField(ordinal = 5)
	private int ad_min_point;
	@JSONField(ordinal = 6)
	private int ad_num;
	public int getAd_id() {
		return ad_id;
	}
	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}
	public int getSpec_id() {
		return spec_id;
	}
	public void setSpec_id(int spec_id) {
		this.spec_id = spec_id;
	}
	public int getAd_year() {
		return ad_year;
	}
	public void setAd_year(int ad_year) {
		this.ad_year = ad_year;
	}
	public String getAd_province() {
		return ad_province;
	}
	public void setAd_province(String ad_province) {
		this.ad_province = ad_province;
	}
	public int getAd_min_point() {
		return ad_min_point;
	}
	public void setAd_min_point(int ad_min_point) {
		this.ad_min_point = ad_min_point;
	}
	public int getAd_num() {
		return ad_num;
	}
	public void setAd_num(int ad_num) {
		this.ad_num = ad_num;
	}
}
