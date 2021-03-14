CREATE TABLE roles(
	id INTEGER PRIMARY KEY,
	role_name VARCHAR(20) NOT NULL
);

CREATE TABLE users(
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) UNIQUE,
	passcode VARCHAR(50) NOT NULL,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(150) UNIQUE NOT NULL,
	role_id INTEGER NOT NULL REFERENCES roles(id)
);

CREATE TABLE reimbursement_type(
	id INTEGER PRIMARY KEY,
	r_type VARCHAR(10) NOT NULL
);

CREATE TABLE reimbursement_status(
	id INTEGER PRIMARY KEY,
	status VARCHAR(10) NOT NULL
);

CREATE TABLE reimbursement(
	id SERIAL PRIMARY KEY,
	amount INTEGER DEFAULT 0 NOT NULL,
	submitted TIMESTAMP,
	resolved TIMESTAMP,
	description VARCHAR(250),
	author INTEGER,
	resolver INTEGER,
	status_id INTEGER NOT NULL REFERENCES reimbursement_status(id),
	type_id INTEGER NOT NULL REFERENCES reimbursement_type(id)
);

INSERT INTO roles (id, role_name)
	VALUES (1, 'Manager'), (2, 'Employee');
	
INSERT INTO reimbursement_type (id, r_type)
	VALUES (1, 'LODGING'), (2, 'TRAVEL'), (3, 'FOOD'), (4, 'OTHER');

INSERT INTO reimbursement_status (id, status)
	VALUES (1, 'PENDING'), (2, 'ACCEPTED'), (3, 'DENIED');

INSERT INTO users (username, passcode, first_name, last_name, email, role_id)
	VALUES ('lonelyboi', 'makedemsmonies', 'Yu', 'Ishigami', 'SATreasurer@shuchiinmail.com', 1),
			('IceQueen11', 'I<3Shirogane', 'Kaguya', 'Shinomiya', 'SAVicePresident@shuchiinmail.com', 2),
			('Ai_detective', 'leaveit2me', 'Chika', 'Fujiwara', 'SASecretary@shuchiinmail.com', 2);

INSERT INTO users (username, passcode, first_name, last_name, email, role_id)
	VALUES ('classPrez', 'kaguyaDaisuke', 'Miyuki', 'Shirogane', 'SAPresident@shuchiinmail.com', 1);

INSERT INTO reimbursement (amount, submitted, description, author, status_id, type_id)
	VALUES (2.50, CURRENT_TIMESTAMP, 'Bus fares', 2, 1, 2), (5377.93, '2016-06-22 19:10:25-07', 'Disney Lodging', 3, 3, 1),
			(50, CURRENT_TIMESTAMP, 'Stockpile for Culture Festival', 2, 2, 3);

SELECT * FROM users;
SELECT * FROM reimbursement WHERE reimbursement.id = 1 ;


	
	
	
	