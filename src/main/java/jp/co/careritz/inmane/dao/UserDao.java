package jp.co.careritz.inmane.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import jp.co.careritz.inmane.common.dao.BaseDao;
import jp.co.careritz.inmane.dto.UserDto;

@Repository
public class UserDao extends BaseDao {
	
	/**
	 * データベースからユーザを取得する
	 * 
	 * @param userid
	 *
	 * @return ユーザを格納したDTO
	 */
	public UserDto selectOne(String userid) {
		// DBの接続情報をプロパティから取得
		String DRIVER_NAME = this.getDriverName();
		String JDBC_URL    = this.getJdbcUrl();
		String USER_ID     = this.getDsUsername();
		String USER_PASS   = this.getDsPassword();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		// 取得結果を格納するDto
		UserDto dto = null;
		
		try {
			System.out.println("### driverName:" + DRIVER_NAME);
			System.out.println("### jdbcUrl:" + JDBC_URL);
			System.out.println("### userId:" + USER_ID);
			System.out.println("### userPass:" + USER_PASS);

			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT * ");
			sqlBuilder.append("FROM USER_INFO ");
			sqlBuilder.append("WHERE USERID LIKE ? ");
			
			System.out.println("### SQL:" + sqlBuilder.toString());
			
			// JDBCドライバのロード
			Class.forName(DRIVER_NAME);
			// DBとの接続を行う
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			
			ps = con.prepareStatement(sqlBuilder.toString());
			
			// バインド
			ps.setString(1, userid);

			// SQLを実行して取得結果をリザルトセットに格納
			rs = ps.executeQuery();

			// リザルトセットから1レコードずつデータを取り出す
			if (rs.next()) {
				// 取得結果を格納するDtoをインスタンス化
				dto = new UserDto();
				// Dtoに取得結果を格納
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setUsername(rs.getString("username"));
				dto.setRoleName(rs.getString("role_name"));
				dto.setLoginFailureCount(rs.getInt("login_failure_count"));
				dto.setLoginDenyTime(rs.getDate("login_deny_time"));
				dto.setDeleted(rs.getInt("deleted"));
				dto.setUpdaterId(rs.getString("updater_id"));
				dto.setUpdateTime(rs.getDate("update_time"));
				dto.setCreaterId(rs.getString("creater_id"));
				dto.setCreateTime(rs.getDate("create_time"));
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 呼び出し元に取得結果を返却
		return dto;
	}
	
	
	/**
	 * データベースから全ユーザを取得する
	 * 
	 * @return ユーザを格納したリスト
	 */
	public List<UserDto> selectAll(){
		// オーバーロードメソッドの呼び出し
		return select(null, null, null, null);
	}
	
	/**
	 * データベースからユーザを取得する
	 * 
	 * @param userid ユーザID
	 * @param username ユーザ名
	 * @param roleName 権限名
	 * @param nonDeleted 削除済を除外フラグ
	 * 
	 * @return ユーザを格納したリスト
	 */
	public List<UserDto> select(String userid, String username, String roleName, String nonDeleted) {
		// DBの接続情報をプロパティから取得
		String DRIVER_NAME = this.getDriverName();
		String JDBC_URL    = this.getJdbcUrl();
		String USER_ID     = this.getDsUsername();
		String USER_PASS   = this.getDsPassword();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		
		// 取得結果を格納するDto
		
		List<UserDto> users = new ArrayList<>();
		try {
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("SELECT * ");
			sqlBuilder.append("FROM USER_INFO ");
			sqlBuilder.append("WHERE USERID    LIKE ? ");
			sqlBuilder.append("AND   USERNAME  LIKE ? ");
			sqlBuilder.append("AND   ROLE_NAME LIKE ? ");
			// 削除済を除外フラグがある場合の条件を設定
			if (nonDeleted  != null && nonDeleted.equals("true")) {
				sqlBuilder.append("AND   DELETED = 0 ");
			}
			sqlBuilder.append("ORDER BY USERID ");
			
			System.out.println("### SQL:" + sqlBuilder.toString());
			
			// JDBCドライバのロード
			Class.forName(DRIVER_NAME);
			// DBとの接続を行う
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			
			ps = con.prepareStatement(sqlBuilder.toString());
			
			// nullの場合はLike '%'で検索(ユーザー名は前方一致)
			ps.setString(1, userid   == null    || userid.equals("")          ? "%" : userid);
			ps.setString(2, username == null    || username.equals("")        ? "%" : username + "%");
			ps.setString(3, roleName == null    || roleName.equals("")        ? "%" : roleName);			

			// SQLを実行して取得結果をリザルトセットに格納
			rs = ps.executeQuery();

			// リザルトセットから1レコードずつデータを取り出す
			
			while (rs.next()) {
				// 取得結果を格納するDtoをインスタンス化
				UserDto dto = new UserDto();
				// Dtoに取得結果を格納
				dto.setUserid(rs.getString("userid"));
				dto.setPassword(rs.getString("password"));
				dto.setUsername(rs.getString("username"));
				dto.setRoleName(rs.getString("role_name"));
				dto.setLoginFailureCount(rs.getInt("login_failure_count"));
				dto.setLoginDenyTime(rs.getDate("login_deny_time"));
				dto.setDeleted(rs.getInt("deleted"));
				dto.setUpdaterId(rs.getString("updater_id"));
				dto.setUpdateTime(rs.getDate("update_time"));
				dto.setCreaterId(rs.getString("creater_id"));
				dto.setCreateTime(rs.getDate("create_time"));
				
				users.add(dto);
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		// 呼び出し元に取得結果を返却
		return users;
	}
	
	public int update(UserDto dto) {
		String DRIVER_NAME = this.getDriverName();
		String JDBC_URL    = this.getJdbcUrl();
		String USER_ID     = this.getDsUsername();
		String USER_PASS   = this.getDsPassword();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		// DBとの接続を行う
		try {
			System.out.println("### driverName:" + DRIVER_NAME);
			System.out.println("### jdbcUrl:" + JDBC_URL);
			System.out.println("### userId:" + USER_ID);
			System.out.println("### userPass:" + USER_PASS);
			
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("UPDATE USER_INFO ");
			sqlBuilder.append("SET ");
			if (dto.getPassword() == null && !dto.getPassword().equals("")) {
				sqlBuilder.append(" PASSWORD = ? ");
			}
			sqlBuilder.append(",USERNAME = ? ");
			sqlBuilder.append(",ROLE_NAME = ? ");
			sqlBuilder.append(",LOGIN_FAILURE_COUNT = ? ");
			sqlBuilder.append(",LOGIN_DENY_TIME = ? ");
			sqlBuilder.append(",DELETED = ? ");
			sqlBuilder.append(",UPDATER_ID = ? ");
			sqlBuilder.append(",UPDATE_TIME = ? ");
			sqlBuilder.append("WHERE USERID = ? ");
			
			System.out.println("### SQL:" + sqlBuilder.toString());
			
			// JDBCドライバのロード
			Class.forName(DRIVER_NAME);
			// DBとの接続を行う
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			
			ps = con.prepareStatement(sqlBuilder.toString());
			
			// 各項目値をバインド
			// 各項目値をバインド
			ps.setString(1, dto.getUserid());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getUsername());
			ps.setString(4, dto.getRoleName());
			ps.setInt   (5, 0);
			ps.setDate  (6, null);
			ps.setInt   (7, 0);
			ps.setString(8, null);
			ps.setDate  (9, null);
			ps.setString(10, dto.getUserid());
			ps.setDate  (11, new Date(System.currentTimeMillis()));

			// SQLを実行
			ps.executeUpdate();
		} catch (SQLException e) {
			int errCode = e.getErrorCode();
			e.printStackTrace();
			System.out.println("SQLException#getErrorCode = " + errCode);
			if (errCode == 1) {
				System.out.println("一意制約エラー");
				return 1;
			} else {
				return 9;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 9;
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


	public int create(UserDto dto) {
		String DRIVER_NAME = this.getDriverName();
		String JDBC_URL    = this.getJdbcUrl();
		String USER_ID     = this.getDsUsername();
		String USER_PASS   = this.getDsPassword();
		
		Connection con = null;
		PreparedStatement ps = null;
		
		// DBとの接続を行う
		try {
			System.out.println("### driverName:" + DRIVER_NAME);
			System.out.println("### jdbcUrl:" + JDBC_URL);
			System.out.println("### userId:" + USER_ID);
			System.out.println("### userPass:" + USER_PASS);
			
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append("INSERT INTO USER_INFO (");
			sqlBuilder.append(" USERID ");
			sqlBuilder.append(",PASSWORD ");
			sqlBuilder.append(",USERNAME ");
			sqlBuilder.append(",ROLE_NAME ");
			sqlBuilder.append(",LOGIN_FAILURE_COUNT ");
			sqlBuilder.append(",LOGIN_DENY_TIME ");
			sqlBuilder.append(",DELETED ");
			sqlBuilder.append(",UPDATER_ID ");
			sqlBuilder.append(",UPDATE_TIME ");
			sqlBuilder.append(",CREATER_ID ");
			sqlBuilder.append(",CREATE_TIME ");
			sqlBuilder.append(") VALUES (");
			sqlBuilder.append(" ? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(",? ");
			sqlBuilder.append(") ");

			
			System.out.println("### SQL:" + sqlBuilder.toString());
			
			// JDBCドライバのロード
			Class.forName(DRIVER_NAME);
			// DBとの接続を行う
			con = DriverManager.getConnection(JDBC_URL, USER_ID, USER_PASS);
			
			ps = con.prepareStatement(sqlBuilder.toString());
			
			// 各項目値をバインド
			ps.setString(1, dto.getUserid());
			ps.setString(2, dto.getPassword());
			ps.setString(3, dto.getUsername());
			ps.setString(4, dto.getRoleName());
			ps.setInt   (5, 0);
			ps.setDate  (6, null);
			ps.setInt   (7, 0);
			ps.setString(8, null);
			ps.setDate  (9, null);
			ps.setString(10, dto.getUserid());
			ps.setDate  (11, new Date(System.currentTimeMillis()));

			// SQLを実行
			ps.executeUpdate();
			
		} catch (SQLException e) {
			int errCode = e.getErrorCode();
			e.printStackTrace();
			System.out.println("SQLException#getErrorCode = " + errCode);
			if (errCode == 1) {
				System.out.println("一意制約エラー");
				return 1;
			} else {
				return 9;
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return 9;
		} finally {
			try {
				if (ps != null) {
					ps.close();
					ps = null;
				}
				if (con != null) {
					con.close();
					con = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}



}
