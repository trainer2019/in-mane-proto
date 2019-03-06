package jp.co.careritz.inmane.form;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * ログインフォーム
 */
public class UsersSearchForm implements Serializable  {

	/** シリアルバージョンUID */
	private static final long serialVersionUID = 1L;

	/** ユーザID */
	private String userid;
	/** ユーザ名 */
	private String username;
	@NotEmpty(message="必須入力の項目です。")
	@Pattern(regexp = "ALL|USER|ADMIN", message="不正な値が設定されています。")
	private String roleName;
	/** 削除済を含む */
	private String nonDeleted;
	
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
	public String getNonDeleted() {
		return nonDeleted;
	}
	public void setNonDeleted(String nonDeleted) {
		this.nonDeleted = nonDeleted;
	}

}
