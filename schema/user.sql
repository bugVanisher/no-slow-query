-- Create syntax for TABLE 'app_info'
CREATE TABLE `app_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(100) NOT NULL COMMENT 'app_name',
  `group_id` bigint(20) NOT NULL COMMENT '组ID',
  `status_` smallint(3) NOT NULL COMMENT '状态,1生效，2未生效',
  `ctime` int(11) NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='APP信息表';

-- Create syntax for TABLE 'group_info'
CREATE TABLE `group_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_name` varchar(100) NOT NULL COMMENT '组名',
  `ctime` int(11) NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='组信息表';

-- Create syntax for TABLE 'group_member_relation'
CREATE TABLE `group_member_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` bigint(20) NOT NULL COMMENT '组ID',
  `user_id` bigint(20) NOT NULL COMMENT '状态,1生效，2未生效',
  `ctime` int(11) NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户和组关系表';

-- Create syntax for TABLE 'user'
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '账号',
  `password` varchar(100) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT '' COMMENT '昵称',
  `emp_id` int(16) NOT NULL COMMENT '工号',
  `email_address` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `ctime` int(11) NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';