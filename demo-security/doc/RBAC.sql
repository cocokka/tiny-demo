--RBAC 表
CREATE TABLE users
(
    user_id  INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    email    VARCHAR(255)
);
CREATE TABLE roles
(
    role_id   INT PRIMARY KEY AUTO_INCREMENT,
    role_name VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE permissions
(
    permission_id   INT PRIMARY KEY AUTO_INCREMENT,
    permission_name VARCHAR(255) NOT NULL UNIQUE
);
CREATE TABLE role_permissions
(
    role_permission_id INT PRIMARY KEY AUTO_INCREMENT,
    role_id            INT,
    permission_id      INT,
    FOREIGN KEY (role_id) REFERENCES roles (role_id),
    FOREIGN KEY (permission_id) REFERENCES permissions (permission_id)
);
CREATE TABLE user_roles
(
    user_role_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id      INT,
    role_id      INT,
    FOREIGN KEY (user_id) REFERENCES users (user_id),
    FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
