package team.zucc.eecs.dao;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.Specialty;

//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.User;

public interface SpecialtyDao {
	List<Specialty> loadSpecialty();
	List<Specialty> loadSubSpecialty(String spec_name,String spec_year);
	
	@Transactional(propagation = Propagation.REQUIRED)
	void deletespec(int spec_id) throws Exception;
	
	@Transactional(propagation = Propagation.REQUIRED)
	void addspec(String spec_name,int spec_year) throws Exception;
	@Transactional(propagation = Propagation.REQUIRED)
	void editspec(int spec_id, String spec_name, int spec_year) throws Exception;
	
}
