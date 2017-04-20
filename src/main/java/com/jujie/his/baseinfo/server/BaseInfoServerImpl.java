package com.jujie.his.baseinfo.server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jujie.his.baseinfo.Company;
import com.jujie.his.baseinfo.Dept;
import com.jujie.his.baseinfo.Doctor;
import com.jujie.his.baseinfo.Feetype;
import com.jujie.his.baseinfo.House;
import com.jujie.his.baseinfo.Medicinal;
import com.jujie.his.baseinfo.Standard;
import com.jujie.his.baseinfo.Unit;
import com.jujie.his.baseinfo.dao.CompanyDaoImpl;
import com.jujie.his.baseinfo.dao.DeptDaoImpl;
import com.jujie.his.baseinfo.dao.DoctorDaoImpl;
import com.jujie.his.baseinfo.dao.FeetypeDaoImpl;
import com.jujie.his.baseinfo.dao.HouseDaoImpl;
import com.jujie.his.baseinfo.dao.MedicinalDaoImpl;
import com.jujie.his.baseinfo.dao.StandardDaoImpl;
import com.jujie.his.baseinfo.dao.UnitDaoImpl;
import com.jujie.util.page.Page;

public class BaseInfoServerImpl {

	// 科室
	private DeptDaoImpl deptDAO;

	public void setDeptDAO(DeptDaoImpl deptDAO) {
		this.deptDAO = deptDAO;
	}

	// 医生
	private DoctorDaoImpl doctorDAO;

	public void setDoctorDAO(DoctorDaoImpl doctorDAO) {
		this.doctorDAO = doctorDAO;
	}

	// 单位
	private UnitDaoImpl unitDAO;

	public void setUnitDAO(UnitDaoImpl unitDAO) {
		this.unitDAO = unitDAO;
	}

	// 规格
	private StandardDaoImpl standardDAO;

	public void setStandardDAO(StandardDaoImpl standardDAO) {
		this.standardDAO = standardDAO;
	}

	// 药房
	private HouseDaoImpl houseDAO;

	public void setHouseDAO(HouseDaoImpl houseDAO) {
		this.houseDAO = houseDAO;
	}

	// 药品供应公司
	private CompanyDaoImpl companyDAO;

	public void setCompanyDAO(CompanyDaoImpl companyDAO) {
		this.companyDAO = companyDAO;
	}

	// 药品
	private MedicinalDaoImpl medicinalDAO;

	public void setMedicinalDAO(MedicinalDaoImpl medicinalDAO) {
		this.medicinalDAO = medicinalDAO;
	}
	//费用类别
    private FeetypeDaoImpl feetypeDaoImpl;
	
    
	// 医生List

	public void setFeetypeDaoImpl(FeetypeDaoImpl feetypeDaoImpl) {
		this.feetypeDaoImpl = feetypeDaoImpl;
	}


	public List<Doctor> queryDoctorList()throws Exception{
		return doctorDAO.queryDoctorList();
	}
	

	// 科室List
	public List<Dept> queryDeptList(Page page) throws Exception {
		return deptDAO.queryDeptList(page);
	}

	// 新增科室
	public Integer addDept(Dept dept) throws Exception {
		return deptDAO.addDept(dept);

	}

	// 查询ByID
	public Dept queryDept(String deptId) throws Exception {
		return deptDAO.queryDept(deptId);
	}

	// 修改科室
	public void updateDept(Dept dept, String statue) throws Exception {
		deptDAO.updateDept(dept, statue);
	}

	// 删除科室
	public void deleteDept(String[] deptId) throws Exception {
		deptDAO.deleteDept(deptId);
	}
	
	
	
	
	

	// 医生List

	public Map<String, Object> showDoctor(Page page) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptList", deptDAO.queryDeptList(new Page()));
		map.put("ddList", doctorDAO.queryDoctorDeptList(page));

		return map;
	}

	// 新增医生
	public void addDoctor(Doctor doctor, String[] deptIds)
			throws Exception {
		
		int doctorId = doctorDAO.addDoctor(doctor);
		
		for (String deptId : deptIds) {
			doctorDAO.addDoctorDept(doctorId , Integer.valueOf(deptId));
		}
	}
	
	// 查询ByID
		public Doctor queryDoctor(String doctorId) throws Exception {
			return doctorDAO.queryDoctor(doctorId);
		}
//		//查询医生的同时还需要查询该医生对应的所有科室。
//		public List<DoctorDept> queryDoctorDept(String doctorId) throws Exception{
//			return doctorDAO.queryDoctorDept(doctorId);
//		}

		// 修改医生
		public void updateDoctor(Doctor doctor, String statue) throws Exception {
			doctorDAO.updateDoctor(doctor, statue);
		}

		// 删除医生
		public void deleteDoctor(String doctorId, String deptId) throws Exception {
			doctorDAO.DoctorDept(doctorId , deptId);
			doctorDAO.deleteDoctor(doctorId);
		}
		//Json
		public Doctor queryOneDoctorIntro(String doctorId) throws Exception{
			return doctorDAO.queryOneDoctorIntro(doctorId);
				
		}
	
	
	
	
	

	// 单位List
	public List<Unit> queryUnitList(Page page) throws Exception {
		return unitDAO.queryUnitList(page);
	}

	// 新增单位
	public Integer addUnit(Unit unit) throws Exception {
		return unitDAO.addUnit(unit);

	}

	// 查询ByID
	public Unit queryUnit(String unitId) throws Exception {
		return unitDAO.queryUnit(unitId);
	}

	// 修改单位
	public void updateUnit(Unit unit, String statue) throws Exception {
		unitDAO.updateUnit(unit, statue);
	}

	// 删除单位
	public void deleteUnit(String[] unitId) throws Exception {
		unitDAO.deleteUnit(unitId);
	}
	
	
	
	
	

	// 规格List
	public List<Standard> queryStandardList(Page page) throws Exception {
		return standardDAO.queryStandardList(page);
	}

	// 新增规格
	public Integer addStandard(Standard standard) throws Exception {
		return standardDAO.addStandard(standard);

	}

	// 查询ByID
	public Standard queryStandard(String standardId) throws Exception {
		return standardDAO.queryStandard(standardId);
	}

	// 修改规格
	public void updateStandard(Standard standard, String statue)
			throws Exception {
		standardDAO.updateStandard(standard, statue);
	}

	// 删除规格
	public void deleteStandard(String[] standardId) throws Exception {
		standardDAO.deleteStandard(standardId);
	}
	
	
	
	

	// 药房List
	public List<House> queryHouseList(Page page) throws Exception {
		return houseDAO.queryHouseList(page);
	}

	// 新增药房
	public Integer addHouse(House house) throws Exception {
		return houseDAO.addHouse(house);

	}

	// 查询ByID
	public House queryHouse(String houseId) throws Exception {
		return houseDAO.queryHouse(houseId);
	}

	// 修改药房
	public void updateHouse(House house, String statue) throws Exception {
		houseDAO.updateHouse(house, statue);
	}

	// 删除药房
	public void deleteHouse(String[] houseId) throws Exception {
		houseDAO.deleteHouse(houseId);
	}
	
	
	
	

	// 药品供应公司List
	public List<Company> queryCompanyList(Page page) throws Exception {
		return companyDAO.queryCompanyList(page);
	}

	// 新增药品供应公司
	public Integer addCompany(Company company) throws Exception {
		return companyDAO.addCompany(company);

	}

	// 查询ByID
	public Company queryCompany(String companyId) throws Exception {
		return companyDAO.queryCompany(companyId);
	}

	// 修改药品供应公司
	public void updateCompany(Company company, String statue) throws Exception {
		companyDAO.updateCompany(company, statue);
	}

	// 删除药品供应公司
	public void deleteCompany(String[] companyId) throws Exception {
		companyDAO.deleteCompany(companyId);
	}
	
	
	public List<Medicinal> queryAllMedicinals() throws Exception {
		return medicinalDAO.queryAllMedicinals();
	}
	
	// 药品List
	public Map<String ,Object> queryMedicinalList(Object[] objs, Page page) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("medicinalList", medicinalDAO.queryMedicinalList(objs, page));
		Page ppage = new Page();
		ppage.setPageSize(500);
		map.put("unitList", unitDAO.queryUnitList(ppage));
		map.put("standardList",  standardDAO.queryStandardList(ppage));
		
		return map;
	}

	// 新增药品
	public Integer addMedicinal(Medicinal medicinal) throws Exception {
		return medicinalDAO.addMedicinal(medicinal);

	}

	// 查询ByID
	public Medicinal queryMedicinal(String medicinalId) throws Exception {
		return medicinalDAO.queryMedicinal(medicinalId);
	}
	// 修改药品
	public void updateMedicinal(Medicinal medicinal, String statue) throws Exception {
		medicinalDAO.updateMedicinal(medicinal, statue);
	}

	// 删除药品
	public void deleteMedicinal(String[] medicinalId) throws Exception {
		medicinalDAO.deleteMedicinal(medicinalId);
	}
	
	// 费用类别List
	public List<Feetype> queryFeetypeList(Page page) throws Exception {
		return feetypeDaoImpl.queryFeetypeList(page);
	}

	// 新增费用类别
	public Integer addFeetype(Feetype feetype) throws Exception {
		return feetypeDaoImpl.addFeetype(feetype);

	}

	// 查询费用类别ByID
	public Feetype queryFeetype(String feetype_id) throws Exception {
		return feetypeDaoImpl.queryFeetype(feetype_id);
	}

	// 修改费用类别
	public void updateFeetype(Feetype feetype) throws Exception {
		feetypeDaoImpl.updateFeetype(feetype);
	}

	// 删除费用类别
	public void deleteFeetype(String[] feetype_id) throws Exception {
		feetypeDaoImpl.deleteFeetype(feetype_id);
	}
}
