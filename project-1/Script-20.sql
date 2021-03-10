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
	author INTEGER FOREIGN KEY,
	resolver INTEGER FOREIGN KEY,
	status_id INTEGER NOT NULL REFERENCES reimbursement_status(id),
	type_id INTEGER NOT NULL REFERENCES reimbursement_type(id)

);

INSERT INTO roles (id, role_name)
	VALUES (1, 'Manager'), (2, 'Employee');
	
INSERT INTO reimbursement_type (id, r_type)
	VALUES (1, 'LODGING'), (2, 'TRAVEL'), (3, 'FOOD'), (4, 'OTHER');

INSERT INTO reimbusement_status (id, status)
	VALUES (1, 'PENDING'), (2, 'RESOLVED');

INSERT INTO users (username, passcode, first_name, last_name, email, role_id)
	VALUES ('lonelyboi', 'makedemsmonies', 'Yu', 'Ishigami', 'SATreasurer@shuchiinmail.com', 1),
			('IceQueen11', 'I<3Shirogane', 'Kaguya', 'Shinomiya', 'SAVicePresident@shuchiinmail.com', 2),
			('Ai_detective', 'leaveit2me', 'Chika', 'Fujiwara', 'SASecretary@shuchiinmail.com', 2);

	
	
	
	
	
	