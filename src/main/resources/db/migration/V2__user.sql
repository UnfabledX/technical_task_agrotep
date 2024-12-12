CREATE TABLE IF NOT EXISTS `user`
(
    `id`        bigint       NOT NULL AUTO_INCREMENT,
    `user_name` varchar(255) NOT NULL,
    `password`  varchar(255) NOT NULL,
    `role`      varchar(12)  NOT NULL,
    PRIMARY KEY (`id`)
);

INSERT INTO user VALUES
#                    password admin
                     (default, 'admin', '$2a$10$B2BzOkl2NJaO9WIJO4890e7bEQ4WjOhqlM8cxrPnLefp3lKgJIqwq', 'ADMIN'),
#                    password user
                     (default, 'user', '$2a$10$k523UELmuO1caBTsGsuUyuT9LYtW8nAp.pDGpH0gp77Af28QOr9nC', 'USER');