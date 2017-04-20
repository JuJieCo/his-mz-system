package com.jujie.user.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jujie.global.action.BaseActionSupper;
import com.jujie.global.util.CreatUserCode;
import com.jujie.his.baseinfo.server.BaseInfoServerImpl;
import com.jujie.login.Login;
import com.jujie.login.Role;
import com.jujie.login.server.LoginRoleFunServerImpl;
import com.jujie.user.User;
import com.jujie.user.server.UserServerImpl;
import com.jujie.util.DataUtils;
import com.jujie.util.page.Page;

@SuppressWarnings("serial")
public class UserAction extends BaseActionSupper {
	

	private static Log log = LogFactory.getLog(UserAction.class);
	
	private User user;
	private List<User> userList;
	private Login login;
	private List<Login> loginList;
	
	private Page page;
	private String s_token;
	
	
	
	public String getS_token() {
		return s_token;
	}
	public void setS_token(String s_token) {
		this.s_token = s_token;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public Login getLogin() {
		return login;
	}
	public void setLogin(Login login) {
		this.login = login;
	}

	public List<Login> getLoginList() {
		return loginList;
	}
	public void setLoginList(List<Login> loginList) {
		this.loginList = loginList;
	}
	public String showOneUser() {
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");

		try {
			if(user!=null){
				user = server.queryOneUser(user.getSysUserId());
				login  = lrfServer.getOneLoginByUserID(user.getSysUserId());
				log.info("获取用户"+user.getSysUserId()+"-"+user.getSysUserName()+" 成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.getCxt().put("type", DataUtils.getStringK(request.getParameter("type")));
	
		BaseInfoServerImpl baseInfoServer=(BaseInfoServerImpl)this.getService("baseInfoServer");
		try {
			this.getCxt().put("houseList",baseInfoServer.queryHouseList(new Page()));
			this.getCxt().put("doctorList",baseInfoServer.queryDoctorList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "userinfo";
	}
	
	public String queryAllUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			if(page==null){
				page = new Page(1);
			}
			if(user==null){
				userList = server.queryAllUser(null,page);
			}else{
				userList = server.queryAllUser(new Object[]{},page);
			}
			loginList = new ArrayList<Login>();
			for (User user : userList) {
				Login login  = lrfServer.getOneLoginByUserID(user.getSysUserId());
				if(login!=null){
					loginList.add(login);
				}
			}
			log.info("获取全部用户成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userlist";
	}
	
	public String saveOneUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			user.setSysUserCode(CreatUserCode.creatCode(1));
			user.setSysUserId(server.saveOneUser(user));
			login.getUser().setSysUserId(user.getSysUserId());
			lrfServer.saveLogin(login);
			log.info("保持用户"+user.getSysUserId()+" "+user.getSysUserName()+" 成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = null;
		return queryAllUser();
	}
	
	public String editOneUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			User user_t = server.queryOneUser(user.getSysUserId());
//			if(user_t.getSysUserLevel()!=user.getSysUserLevel()){
//				user.setSysUserCode(CreatUserCode.creatCode(user.getSysUserLevel()));
//			}else{
				user.setSysUserCode(user_t.getSysUserCode());
//			}
			server.editOneUser(user);
			login.setUser(user);
			if(login.getUuid()==null){
				if(!"".equals(login.getLoginName())){
					lrfServer.saveLogin(login);
				}
			}else{
				lrfServer.editLogin(login);
			}
			log.info("修改用户"+user.getSysUserId()+" "+user.getSysUserName()+" 成功！");
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = null;
		return queryAllUser();
	}
	
	public String deleteUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		String[] ids = request.getParameterValues("checkbox_uuid");
		try {
			if(ids!=null){
				for (String id : ids) {
					lrfServer.deleteLoginByUserID(DataUtils.getInt(id));
					server.deleteOneUser(DataUtils.getInt(id));
				}
			}else{
				lrfServer.deleteLoginByUserID(user.getSysUserId());
				server.deleteOneUser(user.getSysUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		user = null;
		return queryAllUser();
	}
	
	public String toAuthorUser(){
		UserServerImpl server = (UserServerImpl) this.getService("userServer");
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try {
			user = server.queryOneUser(user.getSysUserId());
			login  = lrfServer.getOneLoginByUserID(user.getSysUserId());
			if(login==null){
				user = null;
				return queryAllUser();
			}
			this.getCxt().put("roleList", lrfServer.queryAllRoles(null,null));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "userauthor";
	}
	
	public String authorUser(){
		LoginRoleFunServerImpl lrfServer = (LoginRoleFunServerImpl)this.getService("loginRoleFunServer");
		try{
			String[] roleUuids = request.getParameterValues("role_uuid");
			List<Role> roleList = new ArrayList<Role>();
			for (String roleUuid : roleUuids) {
				Role role = new Role();
				role.setUuid(DataUtils.getInt(roleUuid));
				roleList.add(role);
			}
			login.setRoleList(roleList);
			lrfServer.authorizeLogin(login);
		}catch(Exception e){
			e.printStackTrace();
		}
		return queryAllUser();
	}
}
