package team.zucc.eecs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import team.zucc.eecs.dao.CourseDao;
import team.zucc.eecs.dao.CultivateTargetDao;
import team.zucc.eecs.model.CultivationTargerDetail;
import team.zucc.eecs.model.CultivationTarget;
import team.zucc.eecs.model.IndexPoint;
import team.zucc.eecs.model.graduationReaquirement;

@Component("CultivateTargetServiceImpl")
public class CultivateTargetServiceImpl implements CultivateTargetService{
	@Autowired
	private CultivateTargetDao cultivatetargetdao;
	
	@Override
	public List<CultivationTarget> loadCulTarget(){
		return cultivatetargetdao.loadCulTarget();
	}
	
	@Override
	public String lookcontent(int ct_id) {
		return cultivatetargetdao.lookcontent(ct_id);
	}
	
	@Override
	public void editCultarget(int ct_id,String content,int year,int spec_id) throws Exception{
		cultivatetargetdao.editCultarget(ct_id, content, year, spec_id);
	}
	
	@Override
	public void addCulTarget(int spec_id,int ct_year,String ct_cul_target) throws Exception{
		cultivatetargetdao.addCulTarget(spec_id, ct_year, ct_cul_target);
	}
	@Override
	public void deleteCulTarget(int ct_id)throws Exception{
		cultivatetargetdao.deleteCulTarget(ct_id);
	}
	
	@Override
	public List<CultivationTarget> loadCulTargetHis(int spec_id){
		return cultivatetargetdao.loadCulTargetHis(spec_id);
	}
	@Override
	public List<CultivationTarget> loadSubCulTarget(String spec_id,String ct_year) {
		return cultivatetargetdao.loadSubCulTarget(spec_id, ct_year);
	}
	
	@Override
	public List<CultivationTargerDetail> loadCulTargetDetail(int ct_id) {
		return cultivatetargetdao.loadCulTargetDetail(ct_id);
	}
	
	@Override
	public void addCulTargetDetail(int ct_id,String ctd_content) throws Exception{
		cultivatetargetdao.addCulTargetDetail(ct_id, ctd_content);
	}
	
	@Override
	public void deleteCulTargetDetail(int ctd_id,int ct_id,int ctd_code)throws Exception{
		cultivatetargetdao.deleteCulTargetDetail(ctd_id, ct_id,ctd_code);
	}
	@Override
	public void editCulTargetDetail(int ct_id,int ctd_id,String ctd_content ) throws Exception{
		cultivatetargetdao.editCulTargetDetail(ct_id, ctd_id, ctd_content);
	}
	
	
	
	//grad req

	
	@Override
	public List<graduationReaquirement> loadGradReq() {
		return cultivatetargetdao.loadGradReq();
	}
	
	@Override
	public void addGradReq(int ct_id,String gr_title,String gr_content) throws Exception{
		cultivatetargetdao.addGradReq(ct_id, gr_title, gr_content);
	}
	
	@Override
	public void deleteGradReq(int gr_id,int ct_id,int gr_code)throws Exception{
		cultivatetargetdao.deleteGradReq(gr_id, ct_id, gr_code);
	}
	
	@Override
	public void editGradReq(int gr_id,String gr_title,String gr_content ) throws Exception{
		cultivatetargetdao.editGradReq(gr_id, gr_title, gr_content);
	}
	@Override
	public List<graduationReaquirement> loadSubGradReq(String ct_id,String gr_title,String gr_content){
		return cultivatetargetdao.loadSubGradReq(ct_id, gr_title, gr_content);
	}
	
	
	
	
	//index point
	@Override
	public List<IndexPoint> loadIdxPt() {
		return cultivatetargetdao.loadIdxPt();
	}
	
	@Override
	public void addIdxPt(int gr_id,String ip_content) throws Exception{
		cultivatetargetdao.addIdxPt(gr_id, ip_content);
	}
	
	@Override
	public void deleteIdxPt(int ip_id,int gr_id,int ip_code)throws Exception{
		cultivatetargetdao.deleteIdxPt(ip_id, gr_id, ip_code);
	}
	
	@Override
	public void editIdxPt(int ip_id,String ip_content) throws Exception{
		cultivatetargetdao.editIdxPt(ip_id, ip_content);
	}
	
	@Override
	public List<IndexPoint> loadSubIdxPt(String gr_id,String ip_code,String ip_content){
		return cultivatetargetdao.loadSubIdxPt(gr_id, ip_code, ip_content);
	}
}
