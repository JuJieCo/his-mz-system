package com.jujie.his.mz;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.jujie.global.BaseBean;

public class MzCharge extends BaseBean {
	
	private int id; //编号
	private int uuid; //就诊编号
	private String fph; //发票号
	/*0 没有划价  1 划价 2 收费 3 退费 */
	public static int FLAG_NOHJ=0;
	public static int FLAG_HJ=1;
	public static int FLAG_SHOUFEE=2;
	public static int FLAG_TUIFEI=3;
	
 	private Double hjinfo_totalM;//应收金额
	private Double hjinfo_getM;//收取金额
	private Double hjinfo_backM;//找回金额
	private Double hjinfo_xytm;//西药合计
	private Double hjinfo_zcytm;//中成药合计
	private Double hjinfo_zcytm2;//中草药合计
	private Double hjinfo_zltm;//诊疗项合计
    private Integer sys_user_id;//操作员
	private Date    hjinfo_dotime;//划价时间
	private Integer flag;//划价状态
    private Integer void_userid;//作废操作员
	private Date    void_dotime;//退费时间
	private Integer sf_userid;//收费操作员
    private Date    sf_dotime;//收费时间
	
	
    
    public Integer getSys_user_id() {
		return sys_user_id;
	}
    public void setSys_user_id(Integer sys_user_id) {
		this.sys_user_id = sys_user_id;
	}
    public Date getHjinfo_dotime() {
		return hjinfo_dotime;
	}
    public void setHjinfo_dotime(Date hjinfo_dotime) {
		this.hjinfo_dotime = hjinfo_dotime;
	}
  
	public Double getHjinfo_totalM() {
		return hjinfo_totalM;
	}
	public void setHjinfo_totalM(Double hjinfo_totalM) {
		this.hjinfo_totalM = hjinfo_totalM;
	}
	public Double getHjinfo_getM() {
		return hjinfo_getM;
	}
	public void setHjinfo_getM(Double hjinfo_getM) {
		this.hjinfo_getM = hjinfo_getM;
	}
	public Double getHjinfo_backM() {
		return hjinfo_backM;
	}
	public void setHjinfo_backM(Double hjinfo_backM) {
		this.hjinfo_backM = hjinfo_backM;
	}
	public Double getHjinfo_xytm() {
		return hjinfo_xytm;
	}
	public void setHjinfo_xytm(Double hjinfo_xytm) {
		this.hjinfo_xytm = hjinfo_xytm;
	}
	public Double getHjinfo_zcytm() {
		return hjinfo_zcytm;
	}
	public void setHjinfo_zcytm(Double hjinfo_zcytm) {
		this.hjinfo_zcytm = hjinfo_zcytm;
	}
	public Double getHjinfo_zcytm2() {
		return hjinfo_zcytm2;
	}
	public void setHjinfo_zcytm2(Double hjinfo_zcytm2) {
		this.hjinfo_zcytm2 = hjinfo_zcytm2;
	}
	public Double getHjinfo_zltm() {
		return hjinfo_zltm;
	}
	public void setHjinfo_zltm(Double hjinfo_zltm) {
		this.hjinfo_zltm = hjinfo_zltm;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	public Integer getVoid_userid() {
		return void_userid;
	}
	public void setVoid_userid(Integer void_userid) {
		this.void_userid = void_userid;
	}
	public Date getVoid_dotime() {
		return void_dotime;
	}
	public void setVoid_dotime(Date void_dotime) {
		this.void_dotime = void_dotime;
	}
 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getFph() {
		return fph;
	}
	public void setFph(String fph) {
		this.fph = fph;
	}
	
	public Integer getSf_userid() {
		return sf_userid;
	}
	public void setSf_userid(Integer sf_userid) {
		this.sf_userid = sf_userid;
	}
	public Date getSf_dotime() {
		return sf_dotime;
	}
	public void setSf_dotime(Date sf_dotime) {
		this.sf_dotime = sf_dotime;
	}
	@Override
	public Object mapRow(ResultSet rs, int rownum) throws SQLException {
		MzCharge mzCharge = new MzCharge();
		try {
			mzCharge.setId(rs.getInt("id"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			mzCharge.setUuid(rs.getInt("uuid"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			mzCharge.setHjinfo_totalM(rs.getDouble("hjinfo_totalM"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_getM(rs.getDouble("hjinfo_getM"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_backM(rs.getDouble("hjinfo_backM"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_xytm(rs.getDouble("hjinfo_xytm"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_zcytm(rs.getDouble("hjinfo_zcytm"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_zcytm2(rs.getDouble("hjinfo_zcytm2"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_zltm(rs.getDouble("hjinfo_zltm"));
		} catch (Exception e1) {
		
		}
		try {
			mzCharge.setSys_user_id(rs.getInt("sys_user_id"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setHjinfo_dotime(rs.getDate("hjinfo_dotime"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setFlag(rs.getInt("flag"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setVoid_userid(rs.getInt("void_userid"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setVoid_dotime(rs.getDate("void_dotime"));
		} catch (Exception e1) {
			
		}
		try {
			mzCharge.setFph(rs.getString("fph"));
		} catch (Exception e) {
		}
		
		return mzCharge;
	}
	
	
}
