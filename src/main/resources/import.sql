INSERT INTO USERS (first_name, last_name, gender_code, birthday, dtype, role, version) VALUES ('Alice', 'Johnson', 2, DATE '2008-05-14', 'admin', 'Administrator', 0);
INSERT INTO USERS (first_name, last_name, gender_code, birthday, dtype, role, version) VALUES ('Bob', 'Smith', 1, DATE '2002-09-03', 'admin', 'Developer', 0);
     		
INSERT INTO PROFILE (biography, user_id, version) VALUES ('Hi, I’m Alice Johnson.',1, 0);
INSERT INTO PROFILE (biography, user_id, version) VALUES ('Hello, I’m Bob Smith.', 2, 0);

INSERT INTO POST (content, user_id, version) VALUES ('The scent of the wind has changed slightly. With every passing season, the landscapes within my heart subtly shift.',1, 0);
INSERT INTO POST (content, user_id, version) VALUES ('I found traces of yesterday’s habits in my logs. Both humans and code could use a little debugging from time to time.',1, 0);
INSERT INTO POST (content, user_id, version) VALUES ('I’d rather speak honest words than beautiful ones. Even if no one reads them, I don’t want to lie to myself.',2, 0);
INSERT INTO POST (content, user_id, version) VALUES ('Every new algorithm I think of makes me feel like I understand a tiny piece of the world. Yet the hardest part is still understanding the human heart.',2, 0);

INSERT INTO GROUPS (name, version) VALUES ('A', 0);
INSERT INTO GROUPS (name, version) VALUES ('B', 0);

INSERT INTO USER_GROUP (user_id, group_id) VALUES (1, 1);
INSERT INTO USER_GROUP (user_id, group_id) VALUES (1, 2);
INSERT INTO USER_GROUP (user_id, group_id) VALUES (2, 1);
INSERT INTO USER_GROUP (user_id, group_id) VALUES (2, 2);
