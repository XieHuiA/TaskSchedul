USE shrek_guid;


CREATE TABLE `t_job` (
    `id`            BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT
    COMMENT '主键',
    `gmt_create`    DATETIME            NOT NULL
    COMMENT '创建时间',
    `gmt_modified`  DATETIME            NOT NULL
    COMMENT '修改时间',
    `guid`          VARCHAR(128)        NOT NULL
    COMMENT 'global ID',
    `desc`          VARCHAR(512)        NOT NULL DEFAULT ''
    COMMENT '描述',
    `group_id`      BIGINT(20)          NOT NULL
    COMMENT 'job所属group',
    `expect_status` TINYINT(4)          NOT NULL DEFAULT '0'
    COMMENT '期望的运行状态：0-stopped、1-running、2-restarting',
    `conf`          LONGTEXT            NOT NULL
    COMMENT 'job配置，json格式String kv对',
    `creator`       VARCHAR(128)                 DEFAULT NULL
    COMMENT '域账号',
    PRIMARY KEY (`id`),
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1
    DEFAULT CHARSET = utf8
    COMMENT = 'job表';

CREATE TABLE `t_machine` (
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT
    COMMENT '主键',
    `gmt_create`     DATETIME            NOT NULL
    COMMENT '创建时间',
    `gmt_modified`   DATETIME            NOT NULL
    COMMENT '修改时间',
    `group_id`       BIGINT(20) UNSIGNED NOT NULL
    COMMENT '所属group',
    `ip`             VARCHAR(32)         NOT NULL
    COMMENT '机器的ip',
    `status`         TINYINT(3) UNSIGNED NOT NULL
    COMMENT '机器状态：0-offline，1-online',
    `last_heartbeat` DATETIME            NOT NULL
    COMMENT '上一次心跳时间',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_ip` (`ip`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COMMENT = 'machine表';



CREATE TABLE `t_task` (
    `id`             BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT
    COMMENT '主键',
    `gmt_create`     DATETIME            NOT NULL
    COMMENT '创建时间',
    `gmt_modified`   DATETIME            NOT NULL
    COMMENT '修改时间',
    `job_id`         BIGINT(20) UNSIGNED NOT NULL
    COMMENT 'task所属job',
    `expect_status`  TINYINT(4)          NOT NULL DEFAULT '0'
    COMMENT '期望的运行状态：0-stopped、1-running、2-restarting',
    `actual_status`  TINYINT(4)                   DEFAULT NULL
    COMMENT '实际的运行状态：0-stopped、1-running、2-restarting',
    `expect_ip`      VARCHAR(32)                  DEFAULT NULL
    COMMENT '期望运行的机器',
    `actual_ip`      VARCHAR(32)                  DEFAULT NULL
    COMMENT '实际运行的机器',
    `last_heartbeat` DATETIME            NOT NULL
    COMMENT '上一次活动时间',
    PRIMARY KEY (`id`),
    KEY `job_id_index` (`job_id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COMMENT = 'task表';



CREATE TABLE `t_monitor` (
    `id`           BIGINT(20)   NOT NULL AUTO_INCREMENT
    COMMENT '主键',
    `gmt_create`   DATETIME     NOT NULL
    COMMENT '创建时间',
    `gmt_modified` DATETIME     NOT NULL
    COMMENT '修改时间',
    `guid`          VARCHAR(128)        NOT NULL
    COMMENT 'global ID',
    `status`        TEXT                DEFAULT NULL
    COMMENT 'status info test',
    UNIQUE KEY `uk` (`guid`),
    PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    DEFAULT CHARSET = utf8
    COMMENT = '监控信息表';

