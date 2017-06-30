package boardsystem.dao;

import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import boardsystem.beans.User;
import boardsystem.exception.SQLRuntimeException;

public class UserDao {

	public User getLoginUser(Connection connection, String login_id, String password) {
		PreparedStatement ps =null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ? AND is_deleted =0";

			ps =connection.prepareStatement(sql);
			ps.setString(1, login_id);
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

}