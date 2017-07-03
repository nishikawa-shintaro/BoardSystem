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

CREATE TABLE positions(
	id		INT				AUTO_INCREMENT PRIMARY KEY
  , name	VARCHAR(20)		UNIQUE NOT NULL
);
