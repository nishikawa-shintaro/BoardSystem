package boardsystem.dao;

import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import boardsystem.beans.UserPost;
import boardsystem.exception.SQLRuntimeException;

public class UserPostDao {
	//絞込み条件による一致したものを取得する
	public static List<UserPost> getUserPost(Connection connection, String startdate, String enddate, String category) {
		PreparedStatement ps = null;

		try{
			//条件に当てはまるものを取得する
			String sql= "SELECT * FROM users_posrs  WHERE insert_date BETWEEN ? AND ?  ";
			//カテゴリーがnullでなければ条件を追加
			if (!(category == null || category.isEmpty())) {
				sql+="and  category = ? ";
			}

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1,startdate+ " 00:00:00");
			ps.setString(2,enddate+" 23:59;59");


			if  (!(category == null || category.isEmpty())) {
				ps.setString(3,category);
			}

			ResultSet rs = ps.executeQuery();
			System.out.println(ps);

			List<UserPost> userPost = toPost(rs);
			return userPost;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	private static List<UserPost> toPost(ResultSet rs) throws SQLException {

		List<UserPost> ret = new ArrayList<UserPost>();
		try{
			while(rs.next()){
				int id = rs.getInt("id");
				int userId = rs.getInt("user_id");
				String title = rs.getString("title");
				String text = rs.getString("text");
				String category = rs.getString("category");
				Timestamp insertdate = rs.getTimestamp("insert_date");
				String name = rs.getString("name");
				int branchId = rs.getInt("branch_id");
				int possitionId = rs.getInt("possition_id");

				UserPost userPost = new UserPost();
				userPost.setId(id);
				userPost.setUserId(userId);
				userPost.setTitle(title);
				userPost.setText(text);
				userPost.setCategory(category);
				userPost.setInsertdate(insertdate);
				userPost.setName(name);
				userPost.setBranchId(branchId);
				userPost.setPossitionId(possitionId);

				ret.add(userPost);
			}
			return ret;
		} finally {
			close(rs);
		}
	}

}
