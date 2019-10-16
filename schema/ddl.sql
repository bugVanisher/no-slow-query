-- Create syntax for TABLE 'new_sql_explain'
CREATE TABLE `new_sql_explain` (
  `eid` bigint(20) NOT NULL COMMENT '主键',
  `id` bigint(20) NOT NULL COMMENT 'new sql id',
  `select_type` varchar(50) DEFAULT '' COMMENT '类型',
  `tablename` varchar(80) DEFAULT '' COMMENT '表',
  `partitions_` varchar(100) DEFAULT '' COMMENT 'partitions',
  `type` varchar(50) DEFAULT '' COMMENT '查询类型',
  `possible_keys` varchar(1024) DEFAULT '' COMMENT '可能的索引',
  `key_` varchar(100) DEFAULT '0' COMMENT '实际使用的索引',
  `key_len` varchar(50) DEFAULT '0' COMMENT '索引长度',
  `ref` varchar(50) DEFAULT '' COMMENT '关联类型',
  `rows_` varchar(50) DEFAULT '0' COMMENT '扫描的行数',
  `filtered` varchar(50) DEFAULT '' COMMENT '过滤',
  `Extra` varchar(150) DEFAULT '' COMMENT '扩展信息',
  `slevel` varchar(100) DEFAULT '' COMMENT '严重等级',
  `new_sql` text COMMENT 'sql语句,冗余',
  `ctime` int(13) NOT NULL COMMENT '生成时间',
  `utime` int(13) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`eid`),
  KEY `idx_slevel_ctime` (`slevel`,`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='explain结果表';

-- Create syntax for TABLE 'new_sql_info'
CREATE TABLE `new_sql_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'new sql id',
  `app_name` varchar(100) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `uniq_id` bigint(20) DEFAULT '0' COMMENT '唯一SQL',
  `tablename` varchar(80) NOT NULL DEFAULT '' COMMENT '表',
  `db_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '数据库关联ID',
  `db_name` varchar(100) NOT NULL DEFAULT '' COMMENT '数据库名',
  `sql_md5` varchar(128) NOT NULL COMMENT '新sql的md5值',
  `new_sql` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '新sql',
  `num` int(13) NOT NULL DEFAULT '1' COMMENT '出现次数',
  `ctime` int(13) NOT NULL COMMENT '生成时间',
  `utime` int(13) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_appname_dbname_sql_md5` (`app_name`,`db_name`,`sql_md5`),
  KEY `idx_table_ctime` (`tablename`,`ctime`),
  KEY `uniq_id` (`uniq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1570362584323 DEFAULT CHARSET=utf8 COMMENT='新增sql表';

-- Create syntax for TABLE 'operation_record'
CREATE TABLE `operation_record` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `app_name` varchar(100) NOT NULL DEFAULT '' COMMENT '应用',
  `uniq_id` bigint(20) DEFAULT NULL COMMENT '参数化SQL id',
  `handle_status` smallint(3) NOT NULL COMMENT '状态,1未处理，2已忽略,3已处理',
  `comment` varchar(100) DEFAULT '' COMMENT '备注',
  `operator_name` varchar(50) NOT NULL DEFAULT '' COMMENT '操作人',
  `ctime` bigint(20) NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`),
  KEY `uniq_id` (`uniq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='操作记录表';

-- Create syntax for TABLE 'sql_source'
CREATE TABLE `sql_source` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'new sql id',
  `app_name` varchar(20) NOT NULL DEFAULT '' COMMENT '哪个应用',
  `label` varchar(50) NOT NULL DEFAULT '' COMMENT '来源环境',
  `trace` varchar(2000) NOT NULL DEFAULT '' COMMENT '调用堆栈',
  `ctime` int(13) NOT NULL COMMENT '生成时间',
  `utime` int(13) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1570362584323 DEFAULT CHARSET=utf8 COMMENT='sql来源';

-- Create syntax for TABLE 'template_sql'
CREATE TABLE `template_sql` (
  `id` bigint(20) NOT NULL COMMENT '主键id',
  `app_name` varchar(100) NOT NULL DEFAULT '0' COMMENT '应用ID',
  `tablename` varchar(80) NOT NULL DEFAULT '' COMMENT '表',
  `template_sql` text CHARACTER SET utf8 COLLATE utf8_bin COMMENT '新sql',
  `template_sql_md5` varchar(128) NOT NULL COMMENT '新sql的md5值',
  `label` varchar(30) NOT NULL DEFAULT '' COMMENT '第一次发现,来源标签',
  `notice_level` smallint(3) DEFAULT '0' COMMENT '关注等级',
  `handle_status` smallint(3) NOT NULL COMMENT '状态,1未处理，2已忽略,3已处理',
  `sql_type` smallint(3) NOT NULL DEFAULT '1' COMMENT 'sql类型,1参数化，2无参数化',
  `ctime` int(11) NOT NULL COMMENT '生成时间',
  `utime` int(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_appname_sql_md5` (`app_name`,`template_sql_md5`),
  KEY `idx_table_ctime` (`tablename`,`ctime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数化sql';

CREATE TABLE `db_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `app_name` varchar(100) NOT NULL DEFAULT '' COMMENT '应用ID',
  `mhost` varchar(100) DEFAULT '' COMMENT '主机',
  `mport` varchar(100) DEFAULT '' COMMENT '端口',
  `muser` varchar(100) DEFAULT '' COMMENT '用户名',
  `mpwd` varchar(100) DEFAULT '' COMMENT '密码',
  `mdb` varchar(100) DEFAULT '' COMMENT '数据库',
  `ctime` int(13) NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1570362584300 DEFAULT CHARSET=utf8 COMMENT='数据库信息';