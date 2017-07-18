package boardsystem.service;

import static boardsystem.utils.CloseableUtil.*;
import static boardsystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import boardsystem.beans.Branch;
import boardsystem.dao.BranchDao;

public class BranchService {

		public List<Branch> getBranch() {

			Connection connection = null;
			try {
				connection = getConnection();

				List<Branch> ret = BranchDao.getBranch(connection);

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

		public Branch setBranch(String branchName) {

			Connection connection = null;
			try {
				connection = getConnection();


				Branch ret = BranchDao.setBranch(connection, branchName);

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