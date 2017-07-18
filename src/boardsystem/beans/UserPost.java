package boardsystem.beans;

import java.io.Serializable;
import java.util.Date;

public class UserPost implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private int userId;
	private String title;
	private String text;
	private String category;
	private Date insertdate;
	private String name;
	private int possitionId;
	private int branchId;


	public int getId() {
	    return id;
	}

	public void setId(int id) {
	    this.id = id;
	}

	public String getTitle() {
	    return title;
	}

	public void setTitle(String title) {
	    this.title = title;
	}

	public String getText() {
	    return text;
	}

	public void setText(String text) {
	    this.text = text;
	}

	public String getCategory() {
	    return category;
	}

	public void setCategory(String category) {
	    this.category = category;
	}

	public Date getInsertdate() {
	    return insertdate;
	}

	public void setInsertdate(Date insertdate) {
	    this.insertdate = insertdate;
	}


	public int getUserId() {
	    return userId;
	}

	public void setUserId(int userId) {
	    this.userId = userId;
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
}
