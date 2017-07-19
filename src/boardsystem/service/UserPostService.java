package boardsystem.service;

import static boardsystem.utils.CloseableUtil.*;
import static boardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import boardsystem.beans.UserPost;
import boardsystem.dao.UserPostDao;

public class UserPostService {

	//絞込み
	public List<UserPost> getPosts(String startdate, String enddate, String category) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<UserPost> userPost = UserPostDao.getUserPost(connection, startdate, enddate, category);

			commit(connection);

			return userPost;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
