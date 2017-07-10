package boardsystem.dao;

import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import boardsystem.beans.User;
import boardsystem.exception.SQLRuntimeException;

public class UserDao {

	//入力されたデータが一致しているか判定する
	public User getLoginUser(Connection connection, String loginId, String password) {
		PreparedStatement ps =null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps =connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			}else {
				return userList.get(0);
			}
		}catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	private static List<User> toUserList(ResultSet rs) throws SQLException {

		//ユーザー情報を取得する処理
		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				int possitionId = rs.getInt("possition_id");
				int branchId = rs.getInt("branch_id");
				int isStopped = rs.getInt("is_stopped");
				Timestamp insertdate = rs.getTimestamp("insert_date");
				Timestamp updatedate = rs.getTimestamp("update_date");

				User user = new User();
				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setName(name);
				user.setPossitionId(possitionId);
				user.setBranchId(branchId);
				user.setIsStopped(isStopped);
				user.setInsertdate(insertdate);
				user.setUpdatedate(updatedate);

				ret.add(user);
			}

			return ret;
		} finally {
			close(rs);
		}
	}

	//ユーザー情報を登録する処理

	public void insert(Connection connection, User user) {

		PreparedStatement ps = null;

		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO users( ");
			sql.append(" login_id");
			sql.append(", password");
			sql.append(", name");
			sql.append(", branch_id");
			sql.append(", possition_id");
			sql.append(", is_stopped");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(") VALUES (");
			sql.append(" ?"); // login_id
			sql.append(", ?"); // passwprd
			sql.append(", ?"); // name
			sql.append(", ?"); // branch_id
			sql.append(", ?"); // possition_id
			sql.append(", ?"); //is_stopped
			sql.append(", CURRENT_TIMESTAMP"); // created_date
			sql.append(", CURRENT_TIMESTAMP"); // update_date
			sql.append(");");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getLoginId());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setInt(4, user.getBranchId());
			ps.setInt(5, user.getPossitionId());
			ps.setInt(6, user.getIsStopped());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//全ユーザーを取得する
	public static List<User> getUserAll(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM users");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<User> ret = toUserList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//ユーザーの編集情報をDBに格納する
	public static void upDate(Connection connection,User user){

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append("name =?");
			sql.append(", login_id =?");
			sql.append(", branch_id =?");
			sql.append(", possition_id =?");
			if(!StringUtils.isBlank(user.getPassword())) {
				sql.append(", password =?");
			}
			sql.append(" WHERE");
			sql.append(" id =?");

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1, user.getName());
			ps.setString(2, user.getLoginId());
			ps.setInt(3, user.getBranchId());
			ps.setInt(4, user.getPossitionId());

			if(!StringUtils.isBlank(user.getPassword())) {
				ps.setString(5, user.getPassword());
				ps.setInt(6, user.getId());
			}else {
				ps.setInt(5, user.getId());
			}
		}catch(SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);

		}
	}

	public List<User> getUserEdit(Connection connection, String userId) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
}

