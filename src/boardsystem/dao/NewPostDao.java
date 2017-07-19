package boardsystem.dao;

import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import boardsystem.beans.Post;
import boardsystem.exception.SQLRuntimeException;

public class NewPostDao {

	public static void insert(Connection connection, Post post) {

		PreparedStatement ps = null;
		try{
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO posts (");
			sql.append("  id");
			sql.append(", title");
			sql.append(", text");
			sql.append(", category");
			sql.append(", insert_date");
			sql.append(", update_date");
			sql.append(", user_id");
			sql.append(", branch_id");
			sql.append(", possition_id");
			sql.append(") VALUES (");
			sql.append("  NULL");	// id
			sql.append(", ?");		// title
			sql.append(", ?");		// text
			sql.append(", ?");		// category
			sql.append(", CURRENT_TIMESTAMP");	// insert_dt
			sql.append(", CURRENT_TIMESTAMP");	// update_dt
			sql.append(", ?");		// user_id
			sql.append(", ?");		// branch_id
			sql.append(", ?");		// possition_id
			sql.append(")");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, post.getTitle());
			ps.setString(2, post.getText());
			ps.setString(3, post.getCategory());
			ps.setInt(4, post.getUserId());
			ps.setInt(5, post.getBranchId());
			ps.setInt(6, post.getPossitionId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//投稿の一覧を取得する
	public static List<Post> getPost(Connection connection){

		PreparedStatement ps = null;
		try{
			StringBuilder sql =new StringBuilder();
			sql.append("SELECT * FROM posts ORDER BY id DESC");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Post> ret = toPostList(rs);

			return ret;
		}catch (SQLException e){
			throw new SQLRuntimeException(e);
		}finally{
			close(ps);
		}

	}

	private static List<Post> toPostList(ResultSet rs) throws SQLException {

		List<Post> ret =new ArrayList<Post>();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertdate = rs.getTimestamp("insert_date");
				Timestamp updatedate = rs.getTimestamp("update_date");
				int userId = rs.getInt("user_id");
				int branchId = rs.getInt("branch_id");
				int possitionId = rs.getInt("possition_id");

				Post post = new Post();
				post.setId(id);
				post.setTitle(title);
				post.setText(text);
				post.setCategory(category);
				post.setInsertdate(insertdate);
				post.setUpdatedate(updatedate);
				post.setUserId(userId);
				post.setBranchId(branchId);
				post.setPossitionId(possitionId);

				ret.add(post);
			}

			return ret;
		} finally {
			close(rs);
		}
	}
	//投稿の削除
	public static void delete(Connection connection, int postId) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM posts WHERE id = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setInt(1, postId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//カテゴリーの取得
	public static List<String> getCategoryAll(Connection connection) {

		PreparedStatement ps = null;
		List<String> categoryList = new ArrayList<String>();
		try {
			String sql =("SELECT * FROM posts ORDER BY id DESC");

			ps = connection.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String category = rs.getString("category");
				categoryList.add(category);
			}
			return categoryList;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
