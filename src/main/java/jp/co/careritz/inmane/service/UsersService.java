package jp.co.careritz.inmane.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.careritz.inmane.dao.UsersDao;
import jp.co.careritz.inmane.dto.UsersDto;

@Service
public class UsersService {

	@Autowired
	private UsersDao dao;
	
	/**
	 * ユーザ情報を取得するサービス
	 *
	 * @return メッセージを格納したDTO
	 */
	public UsersDto findByPk(String userid) {

		return dao.selectOne(userid);
	}

	/**
	 * ユーザ情報を取得するサービス
	 * @param deleted 
	 *
	 * @return メッセージを格納したリスト
	 */
	public List<UsersDto> find(UsersDto dto, boolean nonDeleted) {

		return dao.select(dto, nonDeleted);
	}
	
	/**
	 * ユーザ情報を更新するサービス
	 *
	 * @param userDto メッセージを格納したリスト
	 */
	public int updateByPk(UsersDto dto) {

		return dao.update(dto);
	}

	public int create(UsersDto dto) {

		return dao.create(dto);
	}
}
