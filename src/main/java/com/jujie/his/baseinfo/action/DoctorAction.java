package com.jujie.his.baseinfo.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.DateJsonValue;
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.Doctor;
import com.jujie.his.baseinfo.DoctorDept;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.util.page.Page;

@SuppressWarnings("unchecked")
public class DoctorAction  extends BaseActionSupper{

private static final long serialVersionUID = 1L;
	
	private Page page;
	private String s_token;
	private  List<Doctor> doctorList;
	private  List<Dept> deptList;
	private  List<DoctorDept> ddList;
	private  Doctor doctor;
	private DoctorDept doctorDept;
	private static Log log = LogFactory.getLog(DoctorAction.class);
	
	
	
	
	public DoctorDept getDoctorDept() {
		return doctorDept;
	}
	public void setDoctorDept(DoctorDept doctorDept) {
		this.doctorDept = doctorDept;
	}
	public List<DoctorDept> getDdList() {
		return ddList;
	}
	public void setDdList(List<DoctorDept> ddList) {
		this.ddList = ddList;
	}
	public List<Dept> getDeptList() {
		return deptList;
	}
	public void setDeptList(List<Dept> deptList) {
		this.deptList = deptList;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getS_token() {
		return s_token;
	}
	public void setS_token(String s_token) {
		this.s_token = s_token;
	}
	public List<Doctor> getDoctorList() {
		return doctorList;
	}
	public void setDoctorList(List<Doctor> doctorList) {
		this.doctorList = doctorList;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	
	
			// 得到医生列表
			
			public String showDoctor(){
				if(page==null){
					page = new Page(1);
				}
				
			BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
			try {
	
				
				Map<String ,Object> map = baseInfoServer.showDoctor(page);
				deptList = (List<Dept>)map.get("deptList");
				ddList = (List<DoctorDept>)map.get("ddList");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "doctor";
		}
			
		// 创建/修改医生
		public String editDoctor(){
				BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
				String tmp = request.getParameter("isUpdate");
				
				if(null!=tmp&&!"".equals(tmp)&&"isUpdate".equals(tmp)){
					try {
						baseInfoServer.updateDoctor(doctor,"noStatue");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					try {
						
						String[]  deptIds = request.getParameterValues("deptId");
						
						doctor.setDoctorStatue(1);
						baseInfoServer.addDoctor(doctor,deptIds);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return this.showDoctor();
			}
		
		
				//修改状态
				public String updateDoctorStatue(){
					BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
					
					String doctorId = request.getParameter("doctorId");
					try {
						doctor = baseInfoServer.queryDoctor(doctorId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
						if(doctor.getDoctorStatue()==0){
							doctor.setDoctorStatue(1);
						}else{
							doctor.setDoctorStatue(0);
						}	
						try {
							
							baseInfoServer.updateDoctor(doctor,"isStatue");
						} catch (Exception e) {
							e.printStackTrace();
						}

					
						doctor.setDoctorId(null);
					return this.showDoctor();
				}
				
				
				//显示要修改的
				public String showUpdateDoctor(){
					BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
					String doctorId = request.getParameter("doctorId");
					try {
						doctor = baseInfoServer.queryDoctor(doctorId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					request.setAttribute("isUpdate", "isUpdate");
					
					return this.showDoctor();
				}
				
				
				//删除医生
				public String deleteDoctor(){
		
					String doctorId = request.getParameter("doctorId");
					String deptId = request.getParameter("deptId");
					BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");

					try {
						baseInfoServer.deleteDoctor(doctorId,deptId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return this.showDoctor();
				}
				
				//Jason
				public String jsonBillDetails(){
					String doctorId=request.getParameter("doctorId");
					BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
					try {
						doctor=baseInfoServer.queryOneDoctorIntro(doctorId);
						JsonConfig jsonConfig = new JsonConfig();
						jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValue("yyy-MM-dd HH:mm:ss"));
						JSONArray jSONArray = JSONArray.fromObject(doctor);
						String str = jSONArray.toString();
						log.info(str);
						try {
							response.setCharacterEncoding("UTF-8");
							response.getWriter().write(str);
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return null;
				}
			
			
}
