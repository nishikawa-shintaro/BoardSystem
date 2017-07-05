CREATE TABLE users(
    id				INT 			AUTO_INCREMENT PRIMARY KEY
  , login_id 		VARCHAR(20)		UNIQUE NOT NULL
  , password		VARCHAR(20)		NOT NULL
  , name			VARCHAR(10)		NOT NULL
  , branch_id		INT				NOT NULL
  , possition_id	INT				NOT NULL
  , is_stopped		INT				NOT NULL
  , insert_date		TIMESTAMP		NOT NULL	
  , update_date		TIMESTAMP		NOT NULL
);

CREATE TABLE branches(
	id		INT				AUTO_INCREMENT PRIMARY KEY
  , name	VARCHAR(20)		UNIQUE NOT NULL
);

CREATE TABLE posts(
	id				INT			AUTO_INCREMENT PRIMARY KEY
  , title			VARCHAR(50)	NOT NULL
  , text			TEXT		NOT NULL
  , category		VARCHAR(10)	NOT NULL
  , user_id			INT			NOT NULL
  , branch_id		INT			NOT NULL
  , possition_id	INT			NOT NULL
  , insert_date		TIMESTAMP	NOT NULL
  , update_date		TIMESTAMP	NOT NULL
);

CREATE TABLE messages(
	id				INT			AUTO_INCREMENT PRIMARY KEY
  , text			TEXT		NOT NULL
  , user_id			INT			NOT NULL
  , post_id			INT			NOT NULL
  , branch_id		INT			NOT NULL
  , possition_id	INT			NOT NULL
  , insert_date		TIMESTAMP	NOT NULL
  , update_date		TIMESTAMP	NOT NULL
);
 
CREATE TABLE possitions(
	id		INT				AUTO_INCREMENT PRIMARY KEY
  , name	VARCHAR(20)		UNIQUE NOT NULL
);
