package boardsystem.service;

import static boardsystem.utils.CloseableUtil.*;
import static boardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import boardsystem.beans.Possition;
import boardsystem.dao.PossitionDao;

public class PossitionService {

	public List<Possition> getPossition() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Possition> ret = PossitionDao.getPossition(connection);

			commit(connection);

			return ret;
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

	public Possition setPossition(String possitionName) {

		Connection connection = null;
		try {
			connection = getConnection();

			Possition ret = PossitionDao.setPossition(connection, possitionName);

			commit(connection);

			return ret;
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
