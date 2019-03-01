package jp.co.careritz.inmane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.careritz.inmane.dao.UserDao;
import jp.co.careritz.inmane.dto.UserDto;

@Service
public class UserService {

	@Autowired
	private UserDao dao;
	
	/**
	 * ユーザ情報を取得するサービス
	 *
	 * @return メッセージを格納したDTO
	 */
	public UserDto findByPk(String userid) {

		return dao.selectOne(userid);
	}

	/**
	 * 全ユーザ情報を取得するサービス
	 *
	 * @return メッセージを格納したリスト
	 */
	public List<UserDto> findAll() {

		return dao.selectAll();
	}
	
	/**
	 * ユーザ情報を取得するサービス
	 * @param deleted 
	 *
	 * @return メッセージを格納したリスト
	 */
	public List<UserDto> find(String userid, String username, String roleName, String deleted) {

		return dao.select(userid, username, roleName, deleted);
	}
	
	/**
	 * ユーザ情報を更新するサービス
	 *
	 * @param userDto メッセージを格納したリスト
	 */
	public void updateByPk(UserDto userDto) {

		dao.update(userDto);
	}
}
