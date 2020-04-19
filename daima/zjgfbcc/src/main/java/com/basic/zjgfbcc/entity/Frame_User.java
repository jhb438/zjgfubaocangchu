package com.basic.zjgfbcc.entity;

import java.util.Date;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * <p>Title: Frame_User</p>
 * <p>Description: 用户实体</p>
 * @author my
 */
public class Frame_User {

        //行标识
        private int rowId;
        //记录唯一标识
        private String rowGuid;
        //删除标识
        private int delFlag;
        //创建时间
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date createTime;
        //姓名
        private String userName;
        //登录名
        private String loginId;
        //性别
        private String sex;
        //工号
        private String gongHao;
        //密码
        private String password;
        //部门唯一标识
        private String deptGuid;
        //部门名称
        private String deptName;
        //职务
        private String duty;
        //联系电话
        private String tel;
        //手机号码
        private String mobile;
        //是否禁用
        private int isForbid;
        //上次登录时间
        @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
        private Date lastloginTime;
        //排序号
        private int sortSq;
        
        private String[] roleGuid;
        
        private List<Frame_Role> roles;
        //uid
	    
	    private String deptCode;
	    
		public List<Frame_Role> getRoles() {
			return roles;
		}

		public void setRoles(List<Frame_Role> roles) {
			this.roles = roles;
		}

		public String getDeptName() {
			return deptName;
		}

		public void setDeptName(String deptName) {
			this.deptName = deptName;
		}

		public String[] getRoleGuid() {
			return roleGuid;
		}

		public void setRoleGuid(String[] roleGuid) {
			this.roleGuid = roleGuid;
		}

		public String getDeptCode() {
			return deptCode;
		}

		public void setDeptCode(String deptCode) {
			this.deptCode = deptCode;
		}

		/**
         * Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
		 * withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
         */
        public String getToken(Frame_User user) {
            String token="";
            token= JWT.create().withAudience(user.getRowGuid())
                    .sign(Algorithm.HMAC256(user.getPassword()));
            return token;
        }
        
        public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public int getRowId() {
			return rowId;
		}

		public void setRowId(int rowId) {
			this.rowId = rowId;
		}

		public String getRowGuid() {
			return rowGuid;
		}

		public void setRowGuid(String rowGuid) {
			this.rowGuid = rowGuid;
		}

		public int getDelFlag() {
			return delFlag;
		}

		public void setDelFlag(int delFlag) {
			this.delFlag = delFlag;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getLoginId() {
			return loginId;
		}

		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		public String getSex() {
			return sex;
		}

		public void setSex(String sex) {
			this.sex = sex;
		}

		public String getGongHao() {
			return gongHao;
		}

		public void setGongHao(String gongHao) {
			this.gongHao = gongHao;
		}


		public String getDeptGuid() {
			return deptGuid;
		}

		public void setDeptGuid(String deptGuid) {
			this.deptGuid = deptGuid;
		}

		public String getDuty() {
			return duty;
		}

		public void setDuty(String duty) {
			this.duty = duty;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getMobile() {
			return mobile;
		}

		public void setMobile(String mobile) {
			this.mobile = mobile;
		}

		public int getIsForbid() {
			return isForbid;
		}

		public void setIsForbid(int isForbid) {
			this.isForbid = isForbid;
		}

		public Date getLastloginTime() {
			return lastloginTime;
		}

		public void setLastloginTime(Date lastloginTime) {
			this.lastloginTime = lastloginTime;
		}

		public int getSortSq() {
			return sortSq;
		}

		public void setSortSq(int sortSq) {
			this.sortSq = sortSq;
		}

	@Override
		public String toString() {
			return "Frame_User [rowId=" + rowId + ", rowGuid=" + rowGuid + "]";
		}
		
		

    }


