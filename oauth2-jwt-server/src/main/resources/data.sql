-- The encrypted client_secret it `secret`
INSERT INTO oauth_client_details (client_id, client_secret, scope, authorized_grant_types, authorities, access_token_validity)
  VALUES ('clientId', '{bcrypt}$2a$10$vCXMWCn7fDZWOcLnIEhmK.74dvK1Eh8ae2WrWlhr2ETPLoxQctN4.', 'read,write', 'password,refresh_token,client_credentials', 'ROLE_CLIENT', 300);

-- The encrypted password is `pass`
INSERT INTO users (id, username, password, enabled) VALUES (1, 'student', '{bcrypt}$2a$10$BqcQyK27gJN8n2nvPkUGP.YZJl3hhOYlsGzAubSSk7aTbxgOQj/PK', 1);
INSERT INTO users (id, username, password, enabled) VALUES (2, 'teacher', '{bcrypt}$2a$10$BqcQyK27gJN8n2nvPkUGP.YZJl3hhOYlsGzAubSSk7aTbxgOQj/PK', 2);
INSERT INTO users (id, username, password, enabled) VALUES (3, 'admin', '{bcrypt}$2a$10$BqcQyK27gJN8n2nvPkUGP.YZJl3hhOYlsGzAubSSk7aTbxgOQj/PK', 3);

INSERT INTO authorities (username, authority) VALUES ('student', 'ROLE_STUDENT');
INSERT INTO authorities (username, authority) VALUES ('teacher', 'ROLE_TEACHER');
INSERT INTO authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
