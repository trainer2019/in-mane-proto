package jp.co.careritz.inmane.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

/**
 * ログインフォーム
 */
public class UserSearchForm implements Serializable  {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** ユーザID */
	public String userid;
	/** ユーザ名 */
	public String username;
	@NotEmpty
	public String roleName;
	/** 削除済を含む */
	@NotEmpty
	public String deleted;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getDeleted() {
		return deleted;
	}
	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

}
