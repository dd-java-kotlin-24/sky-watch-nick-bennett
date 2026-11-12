-- Generated 2026-11-12 14:24:14-0700 for database version 1

CREATE TABLE IF NOT EXISTS `user`
(
    `user_id`      INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `oauth_key`    TEXT                              NOT NULL,
    `display_name` TEXT                              NOT NULL,
    `avatar`       TEXT,
    `created`      INTEGER                           NOT NULL
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_user_oauth_key` ON `user` (`oauth_key`);

CREATE UNIQUE INDEX IF NOT EXISTS `index_user_display_name` ON `user` (`display_name`);

CREATE TABLE IF NOT EXISTS `favorite`
(
    `favorite_id`  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    `external_key` TEXT                              NOT NULL,
    `description`  TEXT                              NOT NULL,
    `thumbnail`    TEXT                              NOT NULL,
    `image`        TEXT                              NOT NULL,
    `favorited`    INTEGER                           NOT NULL,
    `user_id`      INTEGER                           NOT NULL,
    FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON UPDATE NO ACTION ON DELETE CASCADE
);

CREATE UNIQUE INDEX IF NOT EXISTS `index_favorite_external_key` ON `favorite` (`external_key`);

CREATE INDEX IF NOT EXISTS `index_favorite_user_id` ON `favorite` (`user_id`);