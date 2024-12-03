CREATE TABLE `fish`
(
    `id`         bigint  NOT NULL AUTO_INCREMENT,
    `catch_date` datetime(6)  DEFAULT NULL,
    `name`       varchar(255) DEFAULT NULL,
    `price`      decimal NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `fish_file_name`
(
    `fish_id`   bigint NOT NULL,
    `file_name` varchar(255),
    CONSTRAINT fish_image_fk
        FOREIGN KEY (fish_id)
            REFERENCES `fish` (id)
);