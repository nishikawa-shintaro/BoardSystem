CREATE TABLE possitions(
	id		INT				AUTO_INCREMENT PRIMARY KEY
  , name	VARCHAR(20)		UNIQUE NOT NULL
);

INSERT INTO possitions
( name )
VALUES
(  "人事総務部" ),
( "情報管理部" ),
( "店長" ),
("社員");

SELECT * FROM boardtable.branches;