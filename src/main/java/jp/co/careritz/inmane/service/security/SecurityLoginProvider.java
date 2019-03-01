package jp.co.careritz.inmane.service.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import jp.co.careritz.inmane.config.PropertyConfig;
import jp.co.careritz.inmane.dto.UserDto;
import jp.co.careritz.inmane.model.security.SecurityUserModel;
import jp.co.careritz.inmane.service.UserService;

/**
 * ログインプロバイダー
 */
public class SecurityLoginProvider extends DaoAuthenticationProvider {

	// ----------------------------------------------------------------------
	// インスタンス変数
	// ----------------------------------------------------------------------
	@Autowired
	private PropertyConfig propertyConfig;
	@Autowired
	private UserService userService;

	// ----------------------------------------------------------------------
	// インスタンスメソッド
	// ----------------------------------------------------------------------
	@Override
	public Authentication authenticate(Authentication authentication) {

		try {
			// 認証処理
			Authentication auth = super.authenticate(authentication);

			// 認証情報の取得
			SecurityUserModel user = (SecurityUserModel) auth.getPrincipal();

			// 認証失敗情報の初期化
			UserDto dto = userService.findByPk(user.getUserid());
			dto.setLoginFailureCount(0);
			dto.setLoginDenyTime(null);
			dto.setUpdaterId(dto.getUserid());
			dto.setUpdateTime(new Date(System.currentTimeMillis()));
			// USERデータの更新
			userService.updateByPk(dto);

			return auth;
		}
		// 認証失敗
		catch (BadCredentialsException e) {

			// 認証失敗情報の登録
			UserDto dto = userService.findByPk(authentication.getName());
			
			if (dto != null) {
				Date now = new Date(System.currentTimeMillis());
				dto.setLoginFailureCount(dto.getLoginFailureCount() + 1);
				dto.setLoginDenyTime(now);
				dto.setUpdaterId(dto.getUpdaterId());
				dto.setUpdateTime(now);

				// USERデータの更新
				userService.updateByPk(dto);
			}

			throw new BadCredentialsException(propertyConfig.get("error.app.login.auth"));
		}
		// 退会
		catch (DisabledException e) {
			throw new BadCredentialsException(propertyConfig.get("error.app.login.auth"));
		}
		// アカウントロック
		catch (LockedException e) {
			throw new BadCredentialsException(propertyConfig.get("error.app.login.lock"));
		}
	}
}
