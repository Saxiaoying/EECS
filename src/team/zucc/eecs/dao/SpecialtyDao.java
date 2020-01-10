package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.Specialty;

//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;

import team.zucc.eecs.model.User;

public interface SpecialtyDao {
	List<Specialty> loadSpecialty();
}
