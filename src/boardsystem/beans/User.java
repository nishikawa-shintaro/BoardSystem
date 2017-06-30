package boardsystem.beans;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private int branchId;
	private int possitionId;
	private int isStopped;
	private Date insertdate;
	private Date updatedate;

	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getLoginId() {
	    return loginId;
	}

	public void setLoginId(String loginId) {
	    this.loginId = loginId;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getBranchId() {
	    return branchId;
	}

	public void setBranchId(int branchId) {
	    this.branchId = branchId;
	}

	public int getPossitionId() {
	    return possitionId;
	}

	public void setPossitionId(int possitionId) {
	    this.possitionId = possitionId;
	}

	public int getIsStopped() {
	    return isStopped;
	}

	public void setIsStopped(int isStopped) {
	    this.isStopped = isStopped;
	}

	public Date getInsertdate() {
	    return insertdate;
	}

	public void setInsertdate(Date insertdate) {
	    this.insertdate = insertdate;
	}

	public Date getUpdatedate() {
	    return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
	    this.updatedate = updatedate;
	}
}
