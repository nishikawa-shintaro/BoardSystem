CREATE TABLE comments(
	id				INT			AUTO_INCREMENT PRIMARY KEY
  , text			TEXT		NOT NULL
  , user_id			INT			NOT NULL
  , post_id			INT			NOT NULL
  , branch_id		INT			NOT NULL
  , possition_id	INT			NOT NULL
  , insert_date		TIMESTAMP	NOT NULL
  , update_date		TIMESTAMP	NOT NULL
);