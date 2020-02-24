package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseDao;
import team.zucc.eecs.dao.SpecialtyDao;
import team.zucc.eecs.model.Specialty;

@Component("SpecialtyServiceImpl")
public class SpecialtyServiceImpl implements SpecialtyService{
	@Autowired
	private SpecialtyDao specialtydao;
	
	@Override
	public List<Specialty> loadSpecialty() {
			return specialtydao.loadSpecialty();
		
  }
	@Override
	public void deletespec(int spec_id) throws Exception{
		try {
			specialtydao.deletespec(spec_id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	@Override
	public void addspec(String spec_name,int spec_year) throws Exception{
		try {
			specialtydao.addspec(spec_name, spec_year);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	} 
	
	@Override
	public List<Specialty> loadSubSpecialty(String spec_name,String spec_year){
		return specialtydao.loadSubSpecialty(spec_name, spec_year);
	}
	
	@Override
	public void editspec(int spec_id, String spec_name, int spec_year) throws Exception{
		try {
			specialtydao.editspec(spec_id, spec_name, spec_year);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return ;
	}
}
