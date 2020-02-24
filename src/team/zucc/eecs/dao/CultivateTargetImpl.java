package team.zucc.eecs.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Component;

import team.zucc.eecs.model.CultivationTargerDetail;
import team.zucc.eecs.model.CultivationTarget;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.Specialty;
import team.zucc.eecs.model.Teacher;
import team.zucc.eecs.model.TeacherTrain;
import team.zucc.eecs.model.graduationReaquirement;


@Component("CultivateTargetImpl")
public class CultivateTargetImpl implements CultivateTargetDao {
	
	@Autowired
	JdbcTemplate template;
	
	//查询按spec_id 分类的最新记录
	@Override
	public List<CultivationTarget> loadCulTarget() {
		//select t.* from (select * from tb_cul_target order by ct_update_time) t GROUP BY t.spec_id
		String sql = "select t.* from (select * from tb_cul_target order by ct_update_time desc limit 10000000000) t group by t.spec_id";
		List <CultivationTarget> cts = new ArrayList<CultivationTarget>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CultivationTarget ct= new CultivationTarget();
				ct.setCt_cul_target(rs.getString("ct_cul_target"));
				ct.setCt_id(rs.getInt("ct_id"));
				ct.setCt_update_time(rs.getTimestamp("ct_update_time"));
				ct.setCt_year(rs.getInt("ct_year"));
				ct.setSpec_id(rs.getInt("spec_id"));
				cts.add(ct);
			}
		});
		return cts;
	}

	@Override
	public String lookcontent(int ct_id) {
		String sql = "select ct_cul_target from tb_cul_target where ct_id =?";
		String content = template.queryForObject(sql, new Object[] {ct_id},String.class);
		return content;
	}
	
	@Override
	public void editCultarget(int ct_id,String content,int year,int spec_id) throws Exception{
	
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "update tb_cul_target set ct_cul_target = ?, ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {content,d,ct_id});
		
		return  ;
	}
	
	@Override
	public void addCulTarget(int spec_id,int ct_year,String ct_cul_target) throws Exception{
		
	
		String sql = "select count(*) from tb_cul_target where spec_id = ? and ct_year = ?";
		int count = template.queryForObject(sql, new Object[] {spec_id,ct_year},Integer.class);
		if(count!=0)
			throw new Exception("该年份的培养目标已经存在");
		sql  = "select count(*) from tb_spec where spec_id = ?";
		count = template.queryForObject(sql, new Object[] {spec_id,ct_year},Integer.class);
		if(count==0)
			throw new Exception("该专业不存在");
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		sql = "insert into tb_cul_target(spec_id,ct_year,ct_cul_target,ct_update_time) values(?,?,?,?)";
		template.update(sql,new Object[] {spec_id,ct_year,ct_cul_target,d});
		
		return  ;
	}
	
	@Override
	public void deleteCulTarget(int ct_id)throws Exception{
		 String sql = "select count(*) from tb_cul_target_detail where ct_id = ?";
		 int count = template.queryForObject(sql, new Object[] {ct_id},Integer.class);
		 if(count!=0)
			 throw new Exception("该培养目标总则还存在培养目标细则,无法删除");
		 
		 sql = "select count(*) from tb_grad_req where ct_id = ?";
		 count = template.queryForObject(sql, new Object[] {ct_id},Integer.class);
		 if(count!=0)
			 throw new Exception("该培养目标总则还存在毕业要求,无法删除");
		sql = "delete from tb_cul_target where ct_id = ?";
		template.update(sql,new Object[] {ct_id});
	}
	
	@Override
	public List<CultivationTarget> loadCulTargetHis(int spec_id) {
		//select t.* from (select * from tb_cul_target order by ct_update_time) t GROUP BY t.spec_id
		String sql = "select * from tb_cul_target where spec_id = ? order by ct_update_time desc limit 10000000000";
		List <CultivationTarget> cts = new ArrayList<CultivationTarget>();
		template.query(sql, new Object[] {spec_id},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CultivationTarget ct= new CultivationTarget();
				ct.setCt_cul_target(rs.getString("ct_cul_target"));
				ct.setCt_id(rs.getInt("ct_id"));
				ct.setCt_update_time(rs.getTimestamp("ct_update_time"));
				ct.setCt_year(rs.getInt("ct_year"));
				ct.setSpec_id(rs.getInt("spec_id"));
				cts.add(ct);
			}
		});
		return cts;
	}
	
	@Override
	public List<CultivationTarget> loadSubCulTarget(String spec_id,String ct_year) {
		//select t.* from (select * from tb_cul_target order by ct_update_time) t GROUP BY t.spec_id
		String sql = "select t.* from (select * from tb_cul_target where spec_id like ? and ct_year like ? order by ct_update_time desc limit 10000000000) t group by t.spec_id";
		List <CultivationTarget> cts = new ArrayList<CultivationTarget>();
		template.query(sql, new Object[] {'%'+spec_id+'%','%'+ct_year+'%'},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CultivationTarget ct= new CultivationTarget();
				ct.setCt_cul_target(rs.getString("ct_cul_target"));
				ct.setCt_id(rs.getInt("ct_id"));
				ct.setCt_update_time(rs.getTimestamp("ct_update_time"));
				ct.setCt_year(rs.getInt("ct_year"));
				ct.setSpec_id(rs.getInt("spec_id"));
				cts.add(ct);
			}
		});
		return cts;
	}
	
	//cul target detail
	@Override
	public List<CultivationTargerDetail> loadCulTargetDetail(int ct_id) {
		String sql = "select * from tb_cul_target_detail where ct_id = ? order by ctd_code asc";
		List <CultivationTargerDetail> ctds = new ArrayList<CultivationTargerDetail>();
		template.query(sql, new Object[] {ct_id},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				CultivationTargerDetail ct= new CultivationTargerDetail();
				ct.setCt_id(rs.getInt("ct_id"));
				ct.setCtd_code(rs.getInt("ctd_code"));
				ct.setCtd_content(rs.getString("ctd_content"));
				ct.setCtd_id(rs.getInt("ctd_id"));
				ctds.add(ct);
			}
		});
		return ctds;
	}
	
	@Override
	public void addCulTargetDetail(int ct_id,String ctd_content) throws Exception{
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "select ifnull(max(ctd_code),0) from tb_cul_target_detail where ct_id = ?";
		int ctd_code =  template.queryForObject(sql, new Object[] {ct_id},Integer.class)+1;
		sql = "insert into tb_cul_target_detail(ct_id,ctd_code,ctd_content) values(?,?,?)";
		template.update(sql,new Object[] {ct_id,ctd_code,ctd_content});
		
		sql  = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
		return  ;
	}
	
	@Override
	public void deleteCulTargetDetail(int ctd_id,int ct_id,int ctd_code)throws Exception{
		String sql = "delete from tb_cul_target_detail where ctd_id = ?";
		template.update(sql,new Object[] {ctd_id});
		sql  ="update tb_cul_target_detail set ctd_code = ctd_code -1 where ctd_code >?";
		template.update(sql,new Object[] {ctd_code});
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		sql  = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
	}
	
	@Override
	public void editCulTargetDetail(int ct_id,int ctd_id,String ctd_content ) throws Exception{
	
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "update tb_cul_target_detail set ctd_content = ? where ctd_id = ?";
		template.update(sql,new Object[] {ctd_content,ctd_id});
		
		sql = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
		return  ;
	}
	
	
	
	
	
	
	//grad req
	@Override
	public List<graduationReaquirement> loadGradReq() {
		String sql = "select * from tb_grad_req order by gr_id desc";
		List <graduationReaquirement> grs = new ArrayList<graduationReaquirement>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				graduationReaquirement gr= new graduationReaquirement();
				gr.setCt_id(rs.getInt("ct_id"));
				gr.setGr_code(rs.getInt("gr_code"));
				gr.setGr_content(rs.getString("gr_content"));
				gr.setGr_id(rs.getInt("gr_id"));
				gr.setGr_title(rs.getString("gr_title"));
				grs.add(gr);
			}
		});
		return grs;
	}
	
	@Override
	public void addGradReq(int ct_id,String gr_title,String gr_content) throws Exception{
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "select ifnull(max(gr_code),0) from tb_grad_req where ct_id = ?";
		int gr_code =  template.queryForObject(sql, new Object[] {ct_id},Integer.class)+1;
		
		sql = "select count(*) from tb_cul_target where ct_id = ?";
		int count = template.queryForObject(sql, new Object[] {ct_id},Integer.class);
		if(count==0)
			 throw new Exception("该培养目标不存在");
		sql = "insert into tb_grad_req(ct_id,gr_title,gr_content,gr_code) values(?,?,?,?)";
		template.update(sql,new Object[] {ct_id,gr_title,gr_content,gr_code});
		
		sql  = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
		return  ;
	}
	
	@Override
	public void deleteGradReq(int gr_id,int ct_id,int gr_code)throws Exception{
		String sql = "delete from tb_grad_req where gr_id = ?";
		template.update(sql,new Object[] {gr_id});
		sql  ="update tb_grad_req set gr_code = gr_code -1 where gr_code >? and ct_id =?";
		template.update(sql,new Object[] {gr_code,ct_id});
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		sql  = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
	}
	
	@Override
	public void editGradReq(int gr_id,String gr_title,String gr_content ) throws Exception{
	
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "update tb_grad_req set gr_title = ? , gr_content = ? where gr_id = ?";
		template.update(sql,new Object[] {gr_title,gr_content,gr_id});
		
		sql = "select ct_id from tb_grad_req where gr_id = ?";
		int ct_id =  template.queryForObject(sql, new Object[] {gr_id},Integer.class);
		
		sql = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
		return  ;
	}
	
	@Override
	public List<graduationReaquirement> loadSubGradReq(String ct_id,String gr_title,String gr_content){
		String sql = "select * from tb_grad_req where ct_id like ? and gr_title like ? and gr_content like ?";
		List <graduationReaquirement> grs = new ArrayList<graduationReaquirement>();
		template.query(sql, new Object[] {"%"+ct_id+"%","%"+gr_title+"%","%"+gr_content+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				graduationReaquirement gr= new graduationReaquirement();
				gr.setCt_id(rs.getInt("ct_id"));
				gr.setGr_code(rs.getInt("gr_code"));
				gr.setGr_content(rs.getString("gr_content"));
				gr.setGr_id(rs.getInt("gr_id"));
				gr.setGr_title(rs.getString("gr_title"));
				grs.add(gr);
			}
		});
		return grs;
	}
	
	
	
	
	
	//index point 
	@Override
	public List<IndexPoint> loadIdxPt() {
		String sql = "select * from tb_idx_pt order by ip_id desc";
		List <IndexPoint> ipx = new ArrayList<IndexPoint>();
		template.query(sql, new Object[] {},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				IndexPoint ip= new IndexPoint();
				ip.setGr_id(rs.getInt("gr_id"));
				ip.setIp_code(rs.getInt("ip_code"));
				ip.setIp_content(rs.getString("ip_content"));
				ip.setIp_id(rs.getInt("ip_id"));
				ipx.add(ip);
			}
		});
		return ipx;
	}
	
	
	@Override
	public void addIdxPt(int gr_id,String ip_content) throws Exception{
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "select ifnull(max(ip_code),0) from tb_idx_pt where gr_id = ?";
		int ip_code =  template.queryForObject(sql, new Object[] {gr_id},Integer.class)+1;
		
		sql = "select count(*) from tb_grad_req where gr_id = ?";
		int count = template.queryForObject(sql, new Object[] {gr_id},Integer.class);
		if(count==0)
			 throw new Exception("该毕业要求不存在");
		sql = "insert into tb_idx_pt(gr_id,ip_content,ip_code) values(?,?,?)";
		template.update(sql,new Object[] {gr_id,ip_content,ip_code});
		
		sql = "select ct_id from tb_grad_req where gr_id = ?";
		int ct_id = template.queryForObject(sql, new Object[] {gr_id},Integer.class);
		
		sql  = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
		return  ;
	}
	
	@Override
	public void deleteIdxPt(int ip_id,int gr_id,int ip_code)throws Exception{
		String sql = "delete from tb_idx_pt where ip_id = ?";
		template.update(sql,new Object[] {ip_id});
		
		sql  ="update tb_idx_pt set ip_code = ip_code -1 where ip_code >? and gr_id =?";
		template.update(sql,new Object[] {ip_code,gr_id});
		
		sql = "select ct_id from tb_grad_req where gr_id = ?";
		int ct_id = template.queryForObject(sql, new Object[] {gr_id},Integer.class);
		
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		sql  = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
	}
	
	
	@Override
	public void editIdxPt(int ip_id,String ip_content) throws Exception{
	
		Timestamp d = new Timestamp(System.currentTimeMillis()); 
		String sql = "update tb_idx_pt set ip_content = ? where ip_id = ?";
		template.update(sql,new Object[] {ip_content,ip_id});
		
		sql = "select gr_id from tb_idx_pt where ip_id = ?";
		int gr_id =  template.queryForObject(sql, new Object[] {ip_id},Integer.class);
		
		sql = "select ct_id from tb_grad_req where gr_id = ?";
		int ct_id =  template.queryForObject(sql, new Object[] {gr_id},Integer.class);
		
		sql = "update tb_cul_target set ct_update_time = ? where ct_id = ?";
		template.update(sql,new Object[] {d,ct_id});
		
		return  ;
	}
	
	@Override
	public List<IndexPoint> loadSubIdxPt(String gr_id,String ip_code,String ip_content){
		
		String sql = "select * from tb_idx_pt where gr_id like ? and ip_code like ? and ip_content like ?";
		List <IndexPoint> ipx = new ArrayList<IndexPoint>();
		template.query(sql, new Object[] {"%"+gr_id+"%","%"+ip_code+"%","%"+ip_content+"%"},new RowCallbackHandler() {
			@Override
			public void processRow(ResultSet rs) throws SQLException {
				IndexPoint ip= new IndexPoint();
				ip.setGr_id(rs.getInt("gr_id"));
				ip.setIp_code(rs.getInt("ip_code"));
				ip.setIp_content(rs.getString("ip_content"));
				ip.setIp_id(rs.getInt("ip_id"));
				ipx.add(ip);
			}
		});
		return ipx;
	}
}
