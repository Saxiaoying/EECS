package team.zucc.eecs.service;

import java.util.List;

import team.zucc.eecs.model.Specialty;

public interface SpecialtyService {
	List<Specialty> loadSpecialty() ;
	
	void deletespec(int spec_id) throws Exception;
	
	void addspec(String spec_name,int spec_year) throws Exception;
	List<Specialty> loadSubSpecialty(String spec_name,String spec_year);
	void editspec(int spec_id, String spec_name, int spec_year) throws Exception;
}
