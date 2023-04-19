# Board
게시판 만들기 프로젝트


### MYSQL 테이블 생성 코드

create DATABASE board;
show databases;
use board;
show tables;

drop table comment;
drop table post;


-- 테이블 생성 SQL - post
CREATE TABLE post
(
`id`          BIGINT          NOT NULL    AUTO_INCREMENT,
`title`       VARCHAR(100)    NOT NULL,
`content`     TEXT            NOT NULL,
`username`    VARCHAR(30)     NOT NULL,
`created_at`  DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ,
`updated_at`  DATETIME        NULL,
`hits`        BIGINT          NOT NULL,
`password`    VARCHAR(100)    NOT NULL,
PRIMARY KEY (id)
);

-- 테이블 생성 SQL - comment
CREATE TABLE comment
(
`id`          BIGINT          NOT NULL    AUTO_INCREMENT,
`content`     VARCHAR(300)    NOT NULL,
`username`    VARCHAR(30)     NOT NULL,
`post_id`     BIGINT          NOT NULL,
`created_at`  DATETIME        NOT NULL,
`password`    VARCHAR(100)    NOT NULL,
PRIMARY KEY (id)
);

-- Foreign Key 설정 SQL - comment(post_id) -> post(id)
ALTER TABLE comment
ADD CONSTRAINT FK_comment_post_id_post_id FOREIGN KEY (post_id)
REFERENCES post (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

-- Foreign Key 삭제 SQL - comment(post_id)
-- ALTER TABLE comment
-- DROP FOREIGN KEY FK_comment_post_id_post_id;

### 데이터 한번에 집어 넣는 sql문


DELIMITER //
CREATE PROCEDURE insert_posts2()
BEGIN
DECLARE i INT DEFAULT 1;
WHILE i <= 900 DO
INSERT INTO post
(title, content, username, created_at, updated_at, hits, password)
VALUES
(CONCAT('title ', i), CONCAT('content ', i), CONCAT('username ', i), NOW(), NOW(), i, CONCAT('password ', i));
SET i = i + 1;
END WHILE;
END //
DELIMITER ;

CALL insert_posts2();
