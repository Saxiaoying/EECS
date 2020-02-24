package team.zucc.eecs.model;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.annotation.JSONField;

@Component("StudentEmployOrient")
public class StudentEmployOrient {
	@JSONField(ordinal = 1)
	private int eo_id;
	@JSONField(ordinal = 2)
	private int eoc_id;
	@JSONField(ordinal = 3)
	private int stu_id;
	@JSONField(ordinal = 4)
	private int eo_year;
	@JSONField(ordinal = 5)
	private String eo_department;
	@JSONField(ordinal = 6)
	private String eo_city;
	@JSONField(ordinal = 7)
	private float eo_annual_salary;
	public int getEo_id() {
		return eo_id;
	}
	public void setEo_id(int eo_id) {
		this.eo_id = eo_id;
	}
	public int getEoc_id() {
		return eoc_id;
	}
	public void setEoc_id(int eoc_id) {
		this.eoc_id = eoc_id;
	}
	public int getStu_id() {
		return stu_id;
	}
	public void setStu_id(int stu_id) {
		this.stu_id = stu_id;
	}
	public int getEo_year() {
		return eo_year;
	}
	public void setEo_year(int eo_year) {
		this.eo_year = eo_year;
	}
	public String getEo_department() {
		return eo_department;
	}
	public void setEo_department(String eo_department) {
		this.eo_department = eo_department;
	}
	public String getEo_city() {
		return eo_city;
	}
	public void setEo_city(String eo_city) {
		this.eo_city = eo_city;
	}
	public float getEo_annual_salary() {
		return eo_annual_salary;
	}
	public void setEo_annual_salary(float eo_annual_salary) {
		this.eo_annual_salary = eo_annual_salary;
	}
	
}
