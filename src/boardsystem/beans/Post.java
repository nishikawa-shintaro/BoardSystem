package boardsystem.beans;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {
	private static final long serialVersionUID = 1L;


		private int id;
		private String title;
		private String text;
		private String category;
		private int userId;
		private int branchId;
		private int possitionId;
		private Date insertdate;
		private Date updatedate;


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

