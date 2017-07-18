package boardsystem.dao;

import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import boardsystem.beans.Branch;
import boardsystem.exception.SQLRuntimeException;

	//支店情報を取得する
	public class BranchDao {

		public static List<Branch> getBranch(Connection connection) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM branches order by id");

				ps = connection.prepareStatement(sql.toString());

				ResultSet rs = ps.executeQuery();
				List<Branch> ret = toBranchList(rs);
				System.out.println(ret.size());

				return ret;
			} catch(SQLException e) {
				throw new SQLRuntimeException(e);
			}finally {
				close(ps);
			}
		}

		//支店名を取得する
		public static  Branch setBranch(Connection connection, String branchName) {

			PreparedStatement ps = null;
			try {
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT * FROM branches where name = ?");

				ps = connection.prepareStatement(sql.toString());
				ps.setString(1, branchName);

				ResultSet rs = ps.executeQuery();
				Branch ret = toBranch(rs);
				return ret;
			} catch (SQLException e) {
				throw new SQLRuntimeException(e);
			} finally {
				close(ps);
			}
		}

		//支店コードを取得する
		private static  Branch toBranch(ResultSet rs)
				throws SQLException {

			Branch ret = new Branch();
			try {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");

					Branch branch = new Branch();
					branch.setId(id);
					branch.setName(name);

					ret = branch;
				}
				return ret;
			} finally {
				close(rs);
			}
		}

		private static List<Branch> toBranchList(ResultSet rs)
				throws SQLException {

				List<Branch> ret = new ArrayList<Branch>();
				try {
					while(rs.next()) {
						int	id = rs.getInt("id");
						String name = rs.getString("name");


						Branch branches = new Branch();
						branches.setId(id);
						branches.setName(name);

						ret.add(branches);

					}
					return ret;
				}finally {
					close(rs);
				}
			}
	}