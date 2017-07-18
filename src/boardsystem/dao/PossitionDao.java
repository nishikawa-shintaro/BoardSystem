package boardsystem.dao;


import static boardsystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import boardsystem.beans.Possition;
import boardsystem.exception.SQLRuntimeException;

//役職情報を取得する
public class PossitionDao {

	public static List<Possition> getPossition(Connection connection) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM possitions order by id");

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Possition> ret = toPossitionList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}finally {
			close(ps);
		}
	}

	//役職名を取得する
	public static Possition setPossition(Connection connection, String PossitionName) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM possitions where name = ?");

			ps = connection.prepareStatement(sql.toString());
			ps.setString(1, PossitionName);

			ResultSet rs = ps.executeQuery();
			Possition ret = toPossition(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	//役職コードを取得する
	private static  Possition toPossition(ResultSet rs)
			throws SQLException {

		Possition ret = new Possition();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");

				Possition possition = new Possition();
				possition.setId(id);
				possition.setName(name);

				ret = possition;
			}
			return ret;
		} finally {
			close(rs);
		}
	}


	private static List<Possition> toPossitionList(ResultSet rs)
			throws SQLException {

			List<Possition> ret = new ArrayList<Possition>();
			try {
				while(rs.next()) {
					int	id = rs.getInt("id");
					String name = rs.getString("name");


					Possition possitions = new Possition();
					possitions.setId(id);
					possitions.setName(name);

					ret.add(possitions);

				}
				return ret;
			}finally {
				close(rs);
			}
		}
}