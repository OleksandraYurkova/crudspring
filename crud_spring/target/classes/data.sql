INSERT INTO users (username, password, enabled) VALUES
('admin', '$2a$10$KIX8k7jaV6DyNiJfBzHj7e7UubkGrIXUoCkO7tN1WUDdPv8tUqKH6', true), -- password: admin123
('user', '$2a$10$9RvMyuHVGsELNRdWhcdPp.OumB4KAVVxZMPtmiA4L9MnGS4Y4ro5u', true); -- password: user123

INSERT INTO roles (name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1), (2, 2);
