package team.zucc.eecs.dao;

//import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.support.GeneratedKeyHolder;
//import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.User;


@Component("SpecialtyDaoImpl")
public class SpecialtyDaoImpl implements SpecialtyDao {
	@Autowired
	JdbcTemplate template;
	
	
	@Override
	public List<Specialty> loadSpecialty() {
		String sql = "select * from tb_spec";
		List<Specialty> specs = new ArrayList<>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Specialty spec= new Specialty();
				spec.setSpec_id(rs.getInt("spec_id"));
				spec.setSpec_name(rs.getString("spec_name"));
				spec.setSpec_year(rs.getInt("spec_year"));
				specs.add(spec);
			}
		});
		return specs;
	}
	
	@Override
	public void deletespec(int spec_id)throws Exception{
		 String sql = "select count(*) from tb_class where spec_id = ?";
		 int count = template.queryForObject(sql, new Object[] {spec_id},Integer.class);
		 if(count!=0)
			 throw new Exception("该专业还存在班级无法删除");
		 
		 
		sql = "delete from tb_spec where spec_id = ?";
		template.update(sql,new Object[] {spec_id});
	}
	
	@Override
	public void addspec(String spec_name,int spec_year) throws Exception{
		

		if("".equals(spec_name)||"".equals(spec_year))
			throw new Exception("专业名或录入年份不能为空");
		
		String sql = "select count(*) from tb_spec where spec_name = ? and spec_year = ?";
		int count = template.queryForObject(sql, new Object[] {spec_name,spec_year},Integer.class);
		if(count!=0)
			throw new Exception("该课程已经存在");
		
		sql = "insert into tb_spec(spec_name,spec_year) values(?,?)";
		template.update(sql,new Object[] {spec_name,spec_year});
		
		return  ;
	}
	
	@Override
	public List<Specialty> loadSubSpecialty(String spec_name,String spec_year){
		String sql = "select * from tb_spec where spec_name like ? and spec_year like ?";
		List<Specialty> specs = new ArrayList<>();
		template.query(sql, new Object[] {"%"+spec_name+"%","%"+spec_year+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				Specialty spec= new Specialty();
				spec.setSpec_id(rs.getInt("spec_id"));
				spec.setSpec_name(rs.getString("spec_name"));
				spec.setSpec_year(rs.getInt("spec_year"));
				specs.add(spec);
			}
		});
		return specs;
	}
	
	@Override
	public void editspec(int spec_id,String spec_name,int spec_year) throws Exception{
	
		String sql = "select count(*) from tb_spec where spec_name = ? and spec_year = ?";
		int count = template.queryForObject(sql, new Object[] {spec_name,spec_year},Integer.class);
		if(count!=0)
			throw new Exception("该课程已经存在");
		
		sql = "update tb_spec set spec_name = ? , spec_year =? where spec_id = ?";
		template.update(sql,new Object[] {spec_name,spec_year,spec_id});
		
		return  ;
	}
}
