package boardsystem.service;

import static boardsystem.utils.CloseableUtil.*;
import static boardsystem.utils.DBUtil.*;

import java.sql.Connection;

import boardsystem.beans.User;
import boardsystem.dao.UserDao;
import boardsystem.utils.CipherUtil;

public class LoginService {

	public User login(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			//パスワードの暗号化
			String encPassword = CipherUtil.encrypt(password);
			//ユーザー情報の取得
			User user = userDao.getLoginUser(connection, loginId, encPassword);

			commit(connection);
			return user;
		}catch(RuntimeException e) {
			rollback(connection);
			throw e;
		}catch(Error e) {
			rollback(connection);
			throw e;
		}finally {
			close(connection);
		}
	}
}