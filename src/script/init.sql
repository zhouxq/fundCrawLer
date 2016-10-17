CREATE TABLE `tbl_mail` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tbl_company_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `code` int(20) DEFAULT NULL,
  `name` varchar(200) DEFAULT NULL,
  `create_time` varchar(20) DEFAULT NULL,
  `fund_num` int(10) DEFAULT NULL,
  `handler` varchar(200) DEFAULT NULL,
  `scale` double(10,2) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tbl_fund_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL,
  `code` varchar(20) DEFAULT NULL,
  `handler` varchar(200) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  `company_code` varchar(20) DEFAULT NULL,
  `company_name` varchar(200) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tbl_fund_net_worth` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `fund_code` varchar(20) DEFAULT NULL,
  `time` varchar(20) DEFAULT NULL,
  `net_worth` float(10,4) DEFAULT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `fund_code, time` (`fund_code`,`time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;



