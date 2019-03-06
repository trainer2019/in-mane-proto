package jp.co.careritz.inmane.dto;

import java.io.Serializable;
import java.sql.Date;

public class UsersDto implements Serializable {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String userid;
	/** パスワード */
	private String password;
	/** ユーザ名 */
	private String username;
	/** ロール名 */
	private String roleName;
	/** ログイン失敗回数 */
	private int loginFailureCount;
	/** ログイン拒否時間 */
	private Date loginDenyTime;
	/** 削除済フラグ */
	private int deleted;
	/** 更新者ID */
	private String updaterId;
	/** 更新日時 */
	private Date updateTime;
	/** 作成者ID */
	private String createrId;
	/** 作成日時 */
	private Date createTime;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userId) {
		this.userid = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getLoginFailureCount() {
		return loginFailureCount;
	}

	public void setLoginFailureCount(int loginFailureCount) {
		this.loginFailureCount = loginFailureCount;
	}

	public Date getLoginDenyTime() {
		return loginDenyTime;
	}

	public void setLoginDenyTime(Date loginDenyTime) {
		this.loginDenyTime = loginDenyTime;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getUpdaterId() {
		return updaterId;
	}

	public void setUpdaterId(String updaterId) {
		this.updaterId = updaterId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreaterId() {
		return createrId;
	}

	public void setCreaterId(String createrId) {
		this.createrId = createrId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}