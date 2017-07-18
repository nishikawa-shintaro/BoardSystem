package boardsystem.service;

import static boardsystem.utils.CloseableUtil.*;
import static boardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import boardsystem.beans.Comment;
import boardsystem.dao.NewCommentDao;

public class CommentService {

	//コメントの登録メソッド
	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();

			NewCommentDao.insert(connection, comment);

			commit(connection);

		} catch (RuntimeException e){
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//すべてのコメントを取得する
	public List<Comment> getCommentAll() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Comment> commentList = NewCommentDao.getCommentAll(connection);

			commit(connection);

			return commentList;
		} catch (RuntimeException e){
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	//コメントの削除
	public void delete(int commentId) {

			Connection connection = null;
			try{
				connection = getConnection();

				NewCommentDao.delete(connection, commentId);

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

}
