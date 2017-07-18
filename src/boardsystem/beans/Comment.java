package boardsystem.beans;

import java.io.Serializable;
import java.util.Date;

public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String text;
	private Date insertdate;
	private Date updatedate;
	private int userId;
	private int postId;
	private int branchId;
	private int possitionId;


	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getText() {
	    return text;
	}

	public void setText(String text) {
	    this.text = text;
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

	public int getUserId() {
	    return userId;
	}

	public void setUserId(int userId) {
	    this.userId = userId;
	}

	public int getPostId() {
	    return postId;
	}

	public void setPostId(int postId) {
	    this.postId = postId;
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
}