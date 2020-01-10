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
	
//	public List<Student> loadAllStu() throws Exception{
//		String sql = "select * from student";
//		List<Student> students = new ArrayList<>();	
//		jdbcTemplate.query(sql, new Object[] {},new RowCallbackHandler() {
//			@Override
//			public void processRow(ResultSet rs) throws SQLException {
//				Student student = new Student();
//				student.setUser_id(rs.getInt(1));
//				student.setUser_name(rs.getString(2));
//				student.setUser_pwd(rs.getString(3));
//				student.setUser_finished_credit(rs.getInt(4));
//				student.setUser_credit(rs.getInt(5));
//				students.add(student);
//			}
//		});
//		
//		return students;
//	}
	
//	@Override
//	public Specialty (int user_id) {
//		return template.query("select * from `tb_user` where `user_id`=" + user_id, new ResultSetExtractor<User>() {
//			@Override
//			public User extractData(ResultSet rs) throws SQLException, DataAccessException {
//				if (rs.next()) {
//					User u = new User();
//					u.setUser_id(rs.getInt("user_id"));
//					u.setUser_name(rs.getString("user_name"));
//					u.setUser_pwd(rs.getString("user_pwd"));
//					u.setUser_log_t(rs.getTimestamp("user_log_t"));
//					u.setUser_typ(rs.getInt("user_typ"));
//					return u;
//				} else {
//					return null;
//				}
//			}
//			
//		});
//	}


}
