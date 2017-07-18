package boardsystem.dao;

import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import boardsystem.beans.Comment;
import boardsystem.exception.SQLRuntimeException;

public class NewCommentDao {

	  // コメントの追加

	public static void insert(Connection connection, Comment comment) {

		//コメントをDBに登録する
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO comments (");
			sql.append("  id");
			sql.append(", text");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(", user_id");
			sql.append(", post_id");
			sql.append(", branch_id");
			sql.append(", possition_id");
			sql.append(") VALUES (");
			sql.append("  NULL");	// id(auto_increment)
			sql.append(", ?");		// text
			sql.append(", CURRENT_TIMESTAMP");	// created_at
			sql.append(", CURRENT_TIMESTAMP");	// update_at
			sql.append(", ?");		// user_id
			sql.append(", ?");		// post_id
			sql.append(", ?");		// branch_id
			sql.append(", ?");		// possition_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUserId());
			ps.setInt(3, comment.getPostId());
			ps.setInt(4, comment.getBranchId());
			ps.setInt(5, comment.getPossitionId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//コメントリスト取得
	public static List<Comment> getCommentAll(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM comments order by id");

			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<Comment> ret = toCommentList(rs);

			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	private static List<Comment> toCommentList(ResultSet rs) throws SQLException {{

			List<Comment> ret = new ArrayList<Comment>();
			try {
				while (rs.next()) {
					int id = rs.getInt("id");
					String text = rs.getString("text");
					Timestamp insertdate = rs.getTimestamp("insert_date");
					Timestamp updatedate = rs.getTimestamp("update_date");
					int userId = rs.getInt("user_id");
					int postId = rs.getInt("post_id");
					int branchId = rs.getInt("branch_id");
					int possitionId = rs.getInt("possition_id");

					Comment comment = new Comment();
					comment.setId(id);
					comment.setText(text);
					comment.setInsertdate(insertdate);
					comment.setUpdatedate(updatedate);
					comment.setUserId(userId);
					comment.setPostId(postId);
					comment.setBranchId(branchId);
					comment.setPossitionId(possitionId);

					ret.add(comment);
				}

				return ret;
			} finally {
				close(rs);
			}
		}
	}

	public static void delete(Connection connection, int commentId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, commentId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}