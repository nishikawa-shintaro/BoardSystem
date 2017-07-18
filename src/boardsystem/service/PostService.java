package boardsystem.service;

import static boardsystem.utils.CloseableUtil.*;
import static boardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import boardsystem.beans.Post;
import boardsystem.beans.UserPost;
import boardsystem.dao.NewPostDao;

public class PostService {

	//新規投稿
	public void register(Post post) {

		Connection connection = null;
		try {
			connection = getConnection();

			NewPostDao.insert(connection, post);

			commit(connection);

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
	//
	public List<Post> getPost() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Post> posts =NewPostDao.getPost(connection);

			commit(connection);

			return posts;

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
	//投稿の削除
	public void delete(int postId) {

		Connection connection = null;
		try{
			connection = getConnection();

			NewPostDao.delete(connection, postId);

			commit(connection);
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

	//全投稿を取得する

	public List<String> getCategoryAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<String> categories = NewPostDao.getCategoryAll(connection);

			commit(connection);

			return categories;
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

	//絞込み
	public List<UserPost> getPosts(String startdate, String enddate, String category) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<UserPost> userPost = NewPostDao.getUserPost(connection, startdate, enddate, category);

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
