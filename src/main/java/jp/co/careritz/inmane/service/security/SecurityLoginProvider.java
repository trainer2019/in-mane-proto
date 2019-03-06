package jp.co.careritz.inmane.service.security;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;

import jp.co.careritz.inmane.config.PropertyConfig;
import jp.co.careritz.inmane.dto.UsersDto;
import jp.co.careritz.inmane.model.security.SecurityUserModel;
import jp.co.careritz.inmane.service.UsersService;

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
	private UsersService usersService;

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
			UsersDto result = usersService.findByPk(user.getUserid());
			
			UsersDto dto = new UsersDto();
			dto.setUserid(result.getUserid());
			dto.setLoginFailureCount(0);
			dto.setLoginDenyTime(null);
			dto.setUpdaterId(result.getUpdaterId());
			// USERデータの更新
			usersService.updateByPk(dto);

			return auth;
		}
		// 認証失敗
		catch (BadCredentialsException e) {

			// 認証失敗情報の登録
			UsersDto result = usersService.findByPk(authentication.getName());
			
			if (result != null) {
				Date now = new Date(System.currentTimeMillis());
				UsersDto dto = new UsersDto();
				dto.setUserid(result.getUserid());
				dto.setLoginFailureCount(result.getLoginFailureCount() + 1);
				dto.setLoginDenyTime(now);
				dto.setUpdaterId(result.getUpdaterId());

				// USERデータの更新
				usersService.updateByPk(dto);
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
