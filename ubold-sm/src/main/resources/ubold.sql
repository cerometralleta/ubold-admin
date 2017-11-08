/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : ubold

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2017-11-08 22:39:17
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tb_rbac_func
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_func`;
CREATE TABLE `tb_rbac_func` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `code` varchar(50) DEFAULT NULL COMMENT '操作编码',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `url` varchar(100) DEFAULT NULL COMMENT '拦截URL',
  `parent` varchar(50) DEFAULT NULL COMMENT '父菜单',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='功能操作表';

-- ----------------------------
-- Records of tb_rbac_func
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_menu`;
CREATE TABLE `tb_rbac_menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `url` varchar(100) DEFAULT NULL COMMENT 'URL',
  `parent` varchar(50) DEFAULT NULL COMMENT '父菜单',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单表';

-- ----------------------------
-- Records of tb_rbac_menu
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_permission`;
CREATE TABLE `tb_rbac_permission` (
  `id` varchar(32) NOT NULL,
  `permission_name` varchar(50) DEFAULT NULL COMMENT '权限名',
  `type` tinyint(4) DEFAULT NULL COMMENT '角色id',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';

-- ----------------------------
-- Records of tb_rbac_permission
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_permission_func
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_permission_func`;
CREATE TABLE `tb_rbac_permission_func` (
  `id` varchar(32) NOT NULL,
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限id',
  `func_id` varchar(32) DEFAULT NULL COMMENT '菜单id',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限功能关联表';

-- ----------------------------
-- Records of tb_rbac_permission_func
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_permission_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_permission_menu`;
CREATE TABLE `tb_rbac_permission_menu` (
  `id` varchar(32) NOT NULL,
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限id',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜单id',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限菜单关联表';

-- ----------------------------
-- Records of tb_rbac_permission_menu
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_role`;
CREATE TABLE `tb_rbac_role` (
  `id` varchar(32) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL COMMENT '角色名',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_rbac_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_role_permission`;
CREATE TABLE `tb_rbac_role_permission` (
  `id` varchar(32) NOT NULL,
  `permission_id` varchar(32) DEFAULT NULL COMMENT '权限id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限关联表';

-- ----------------------------
-- Records of tb_rbac_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_usergroup
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_usergroup`;
CREATE TABLE `tb_rbac_usergroup` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `parent` varchar(50) DEFAULT NULL COMMENT '父用户组',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户组表';

-- ----------------------------
-- Records of tb_rbac_usergroup
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_user_group
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_user_group`;
CREATE TABLE `tb_rbac_user_group` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `group_id` varchar(32) DEFAULT NULL COMMENT '用户组id',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户用户组关联表';

-- ----------------------------
-- Records of tb_rbac_user_group
-- ----------------------------

-- ----------------------------
-- Table structure for tb_rbac_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_rbac_user_role`;
CREATE TABLE `tb_rbac_user_role` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(32) DEFAULT NULL COMMENT '角色id',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色关联表';

-- ----------------------------
-- Records of tb_rbac_user_role
-- ----------------------------

-- ----------------------------
-- Table structure for tb_sm_dataview
-- ----------------------------
DROP TABLE IF EXISTS `tb_sm_dataview`;
CREATE TABLE `tb_sm_dataview` (
  `id` varchar(50) NOT NULL,
  `dataviewcode` varchar(20) DEFAULT NULL COMMENT '值',
  `dataviewname` varchar(30) DEFAULT '0' COMMENT '文本值',
  `sqlid` varchar(32) DEFAULT NULL COMMENT 'sqlid',
  `options` text COMMENT 'options',
  `columns` text COMMENT 'columns',
  `treeoptions` text,
  `buttons` text COMMENT '所属字典',
  `datafilters` text,
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sm_dataview
-- ----------------------------
INSERT INTO `tb_sm_dataview` VALUES ('125foakcfeazdfeaeqrazf458e5r4ewr', 'DV10000000000001', 'DATAVIEW查询视图', 'SM10000000000002', '{\"cache\":false,\"cardView\":false,\"checkboxHeader\":true,\"classes\":\"table table-hover\",\"clickToSelect\":true,\"data\":[],\"detailView\":false,\"editView\":false,\"escape\":false,\"exportDataType\":\"basic\",\"height\":0,\"idField\":\"id\",\"maintainSelected\":false,\"method\":\"post\",\"minimumCountColumns\":0,\"onlyInfoPagination\":false,\"pageSize\":\"50\",\"pagination\":true,\"paginationLoop\":false,\"search\":false,\"searchOnEnterKey\":false,\"searchTimeOut\":0,\"showColumns\":true,\"showExport\":true,\"showFooter\":false,\"showHeader\":true,\"showPaginationSwitch\":false,\"showRefresh\":true,\"showToggle\":true,\"sidePagination\":\"server\",\"silentSort\":false,\"singleSelect\":false,\"smartDisplay\":true,\"sortStable\":false,\"sortable\":true,\"strictSearch\":false,\"striped\":false,\"trimOnSearch\":0,\"url\":\"/SM10000000000002\",\"version\":\"version\"}', '[{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"id\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":0,\"insert\":false,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"id\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":1,\"insert\":true,\"maxlength\":32,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlname\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":2,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlname\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"selectsql\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":3,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"selectsql\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlexpand\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":4,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlexpand\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"datasource\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":5,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"datasource\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"TINYINT\",\"escape\":false,\"falign\":\"middle\",\"field\":\"cache\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":6,\"insert\":true,\"maxlength\":4,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"cache\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"TINYINT\",\"escape\":false,\"falign\":\"middle\",\"field\":\"status\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":7,\"insert\":true,\"maxlength\":4,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"status\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"groupid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":8,\"insert\":true,\"maxlength\":32,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"groupid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"mastertable\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":9,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"mastertable\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"mastertableid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":10,\"insert\":true,\"maxlength\":20,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"mastertableid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqldesc\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":11,\"insert\":true,\"maxlength\":100,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqldesc\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"version\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":12,\"insert\":false,\"maxlength\":10,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"版本号\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":13,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":14,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":15,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":16,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false}]', '{\"enable\":false,\"show\":false}', '[{\"btnsize\":\"btn-xs\",\"color\":\"btn-default\",\"id\":\"create\",\"location\":\"nav\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"增加\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-danger\",\"id\":\"delete\",\"location\":\"row\",\"option\":\"service\",\"size\":\"lg\",\"title\":\"删除\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-warning\",\"id\":\"update\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"修改\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-info\",\"id\":\"retrieve\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"查看\"}]', '[{\"dataType\":\"VARCHAR\",\"expression\":\"=\",\"field\":\"sqlid\",\"fieldType\":\"text\",\"title\":\"sqlid\"},{\"dataType\":\"VARCHAR\",\"expression\":\"=\",\"field\":\"sqlname\",\"fieldType\":\"text\",\"title\":\"sqlname\"}]', 'DATAVIEW数据查询视图选择器', '23', null, '2017-10-30 22:31:00', null, '2017-11-08 22:08:21');
INSERT INTO `tb_sm_dataview` VALUES ('c14c6efbaa254676bcbe955b632a1f57', 'DV10000000000000', 'SQLDEFINE查询视图', 'SM10000000000002', '{\"cache\":false,\"cardView\":false,\"checkboxHeader\":false,\"classes\":\"table table-hover\",\"clickToSelect\":false,\"data\":[],\"detailView\":false,\"editView\":false,\"escape\":false,\"exportDataType\":\"basic\",\"height\":0,\"idField\":\"id\",\"maintainSelected\":true,\"method\":\"post\",\"minimumCountColumns\":0,\"onlyInfoPagination\":false,\"pageNumber\":\"1\",\"pageSize\":\"50\",\"pagination\":true,\"paginationLoop\":false,\"queryParamsType\":\"undefined\",\"search\":false,\"searchOnEnterKey\":false,\"searchTimeOut\":3,\"showColumns\":true,\"showExport\":true,\"showFooter\":false,\"showHeader\":true,\"showPaginationSwitch\":false,\"showRefresh\":true,\"showToggle\":true,\"sidePagination\":\"server\",\"silentSort\":false,\"singleSelect\":false,\"smartDisplay\":true,\"sortStable\":false,\"sortable\":true,\"strictSearch\":false,\"striped\":false,\"trimOnSearch\":0,\"uniqueId\":\"id\",\"url\":\"/SM10000000000002\",\"version\":\"version\"}', '[{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"id\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":0,\"insert\":false,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"id\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":1,\"insert\":true,\"maxlength\":32,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlname\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":2,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlname\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"selectsql\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":3,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"selectsql\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlexpand\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":4,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlexpand\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"datasource\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":5,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"datasource\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"TINYINT\",\"escape\":false,\"falign\":\"middle\",\"field\":\"cache\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":6,\"insert\":true,\"maxlength\":4,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"cache\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"TINYINT\",\"escape\":false,\"falign\":\"middle\",\"field\":\"status\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":7,\"insert\":true,\"maxlength\":4,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"status\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"groupid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":8,\"insert\":true,\"maxlength\":32,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"groupid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"mastertable\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":9,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"mastertable\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"mastertableid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":10,\"insert\":true,\"maxlength\":20,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"mastertableid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqldesc\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":11,\"insert\":true,\"maxlength\":100,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqldesc\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"version\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":12,\"insert\":false,\"maxlength\":10,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"版本号\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":13,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":14,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":15,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":16,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true}]', '{\"enable\":true,\"scope\":\"ALL\",\"show\":false,\"width\":\"2\"}', '[{\"btnsize\":\"btn-xs\",\"color\":\"btn-default\",\"id\":\"create\",\"location\":\"nav\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"增加\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-danger\",\"id\":\"delete\",\"location\":\"row\",\"option\":\"service\",\"size\":\"lg\",\"title\":\"删除\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-warning\",\"id\":\"update\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"修改\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-info\",\"id\":\"retrieve\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"查看\"}]', '[{\"dataType\":\"VARCHAR\",\"expression\":\"=\",\"field\":\"sqlid\",\"fieldType\":\"text\",\"title\":\"sqlid\"},{\"dataType\":\"VARCHAR\",\"expression\":\"=\",\"field\":\"sqlname\",\"fieldType\":\"text\",\"title\":\"sqlname\"}]', 'SQLDEFINE数据查询视图', '17', null, '2017-10-30 22:31:00', null, '2017-11-08 22:08:33');
INSERT INTO `tb_sm_dataview` VALUES ('23f5a44680ff430ba45728015bc309a3', 'DV20171028203619', '数据字典查询视图', 'SM10000000000003', '{\"cache\":false,\"cardView\":false,\"checkboxHeader\":false,\"classes\":\"table table-hover\",\"clickToSelect\":false,\"data\":[],\"detailView\":false,\"editView\":false,\"escape\":false,\"exportDataType\":\"basic\",\"height\":0,\"idField\":\"id\",\"maintainSelected\":true,\"method\":\"post\",\"minimumCountColumns\":0,\"onlyInfoPagination\":false,\"pageNumber\":\"1\",\"pageSize\":\"50\",\"pagination\":true,\"paginationLoop\":false,\"queryParamsType\":\"undefined\",\"search\":false,\"searchOnEnterKey\":false,\"searchTimeOut\":3,\"showColumns\":true,\"showExport\":false,\"showFooter\":false,\"showHeader\":true,\"showPaginationSwitch\":false,\"showRefresh\":true,\"showToggle\":true,\"sidePagination\":\"server\",\"silentSort\":false,\"singleSelect\":false,\"smartDisplay\":true,\"sortStable\":false,\"sortable\":true,\"strictSearch\":false,\"striped\":false,\"trimOnSearch\":0,\"uniqueId\":\"id\",\"url\":\"/SM10000000000003\",\"version\":\"version\"}', '[{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"id\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":0,\"insert\":false,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"id\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"value\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":1,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"value\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"text\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":2,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"text\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"remark\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":3,\"insert\":true,\"maxlength\":500,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"remark\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"parent\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":4,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"parent\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"idx\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":5,\"insert\":true,\"maxlength\":5,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"idx\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"version\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":6,\"insert\":false,\"maxlength\":10,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"版本号\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":7,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":8,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":9,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":10,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true}]', '{\"enable\":true,\"foreignKey\":\"id\",\"idKey\":\"id\",\"name\":\"text\",\"pIdKey\":\"parent\",\"scope\":\"CHILD\",\"show\":true,\"sqlId\":\"SM10000000000003\",\"width\":\"2\"}', '[{\"btnsize\":\"btn-xs\",\"color\":\"btn-default\",\"id\":\"create\",\"location\":\"nav\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"增加\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-danger\",\"id\":\"delete\",\"location\":\"row\",\"option\":\"service\",\"size\":\"lg\",\"title\":\"删除\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-warning\",\"id\":\"update\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"修改\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-info\",\"id\":\"retrieve\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"查看\"}]', '[]', '数据字典数据视图', '16', null, '2017-10-30 22:31:00', null, '2017-11-08 22:02:12');
INSERT INTO `tb_sm_dataview` VALUES ('20b72ad3661e410f84513fdb0380efae', 'DV20171028203613', '测试视图1111', 'SM10000000000003', '{\"cache\":false,\"cardView\":false,\"checkboxHeader\":false,\"classes\":\"table table-hover\",\"clickToSelect\":false,\"data\":[],\"detailView\":false,\"editView\":false,\"escape\":false,\"exportDataType\":\"basic\",\"height\":0,\"idField\":\"id\",\"maintainSelected\":true,\"method\":\"post\",\"minimumCountColumns\":0,\"onlyInfoPagination\":false,\"pageNumber\":\"1\",\"pageSize\":\"50\",\"pagination\":true,\"paginationLoop\":false,\"queryParamsType\":\"undefined\",\"search\":false,\"searchOnEnterKey\":false,\"searchTimeOut\":3,\"showColumns\":true,\"showExport\":false,\"showFooter\":false,\"showHeader\":true,\"showPaginationSwitch\":false,\"showRefresh\":true,\"showToggle\":true,\"sidePagination\":\"server\",\"silentSort\":false,\"singleSelect\":false,\"smartDisplay\":true,\"sortStable\":false,\"sortable\":true,\"strictSearch\":false,\"striped\":false,\"trimOnSearch\":0,\"uniqueId\":\"id\",\"url\":\"/SM10000000000003\",\"version\":\"version\"}', '[{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"id\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":0,\"insert\":false,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"id\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"value\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":1,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"value\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"text\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":2,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"text\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"remark\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":3,\"insert\":true,\"maxlength\":500,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"remark\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"parent\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":4,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"parent\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"idx\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":5,\"insert\":true,\"maxlength\":5,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"idx\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"version\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":6,\"insert\":false,\"maxlength\":10,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"版本号\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"createUser\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":7,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"createTime\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":8,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"创建时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"lastUpdateUser\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":9,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新者\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"lastUpdateTime\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":10,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"更新时间\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false}]', '{\"enable\":true,\"scope\":\"ALL\",\"show\":false,\"width\":\"2\"}', '[]', '[{\"dataType\":\"VARCHAR\",\"expression\":\"=\",\"field\":\"value\",\"fieldType\":\"text\",\"title\":\"value\"},{\"dataType\":\"VARCHAR\",\"expression\":\"=\",\"field\":\"text\",\"fieldType\":\"text\",\"title\":\"text\"}]', '进击的巨人', '16', null, '2017-10-30 22:31:00', null, '2017-11-07 00:00:29');
INSERT INTO `tb_sm_dataview` VALUES ('54d6612cb22e4ba98cd080be67ab2940', 'DV20171028203614', 'gggggggggg', 'SM10000000000002', '{\"cache\":false,\"cardView\":false,\"checkboxHeader\":false,\"classes\":\"table table-hover\",\"clickToSelect\":false,\"data\":[],\"detailView\":false,\"editView\":false,\"escape\":false,\"exportDataType\":\"basic\",\"height\":0,\"idField\":\"id\",\"maintainSelected\":true,\"method\":\"post\",\"minimumCountColumns\":0,\"onlyInfoPagination\":false,\"pageNumber\":\"1\",\"pageSize\":\"50\",\"pagination\":true,\"paginationLoop\":false,\"queryParamsType\":\"undefined\",\"search\":false,\"searchOnEnterKey\":false,\"searchTimeOut\":3,\"showColumns\":true,\"showExport\":false,\"showFooter\":false,\"showHeader\":true,\"showPaginationSwitch\":false,\"showRefresh\":true,\"showToggle\":true,\"sidePagination\":\"server\",\"silentSort\":false,\"singleSelect\":false,\"smartDisplay\":true,\"sortStable\":false,\"sortable\":true,\"strictSearch\":false,\"striped\":false,\"trimOnSearch\":0,\"uniqueId\":\"id\",\"url\":\"/SM10000000000002\",\"version\":\"version\"}', '[{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"id\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":0,\"insert\":false,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"id\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":1,\"insert\":true,\"maxlength\":32,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlname\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":2,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlname\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"selectsql\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":3,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"selectsql\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqlexpand\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":4,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqlexpand\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"datasource\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":5,\"insert\":true,\"maxlength\":30,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"datasource\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"TINYINT\",\"escape\":false,\"falign\":\"middle\",\"field\":\"cache\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":6,\"insert\":true,\"maxlength\":4,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"cache\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"TINYINT\",\"escape\":false,\"falign\":\"middle\",\"field\":\"status\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":7,\"insert\":true,\"maxlength\":4,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"status\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"groupid\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":8,\"insert\":true,\"maxlength\":32,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"groupid\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"mastertable\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":9,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"mastertable\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"mastertableId\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":10,\"insert\":true,\"maxlength\":20,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"mastertableId\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"sqldesc\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":11,\"insert\":true,\"maxlength\":100,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"sqldesc\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"right\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DECIMAL\",\"escape\":false,\"falign\":\"middle\",\"field\":\"version\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":12,\"insert\":false,\"maxlength\":10,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"版本号\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":false},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":13,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"create_user\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"create_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":14,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"create_time\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"VARCHAR\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_user\",\"fieldType\":\"text\",\"halign\":\"center\",\"idx\":15,\"insert\":true,\"maxlength\":50,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"last_update_user\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true},{\"align\":\"center\",\"cardVisible\":true,\"checkbox\":false,\"clickToSelect\":false,\"colspan\":0,\"dataType\":\"DATETIME\",\"escape\":false,\"falign\":\"middle\",\"field\":\"last_update_time\",\"fieldType\":\"datetimepicker\",\"halign\":\"center\",\"idx\":16,\"insert\":true,\"maxlength\":19,\"radio\":false,\"rowspan\":0,\"searchFormatter\":false,\"searchable\":false,\"sortable\":false,\"switchable\":true,\"title\":\"last_update_time\",\"uniqueCheck\":false,\"updateType\":\"hide\",\"valign\":\"middle\",\"view\":true,\"visible\":true}]', '{\"enable\":true,\"scope\":\"ALL\",\"show\":false,\"width\":\"2\"}', '[{\"btnsize\":\"btn-xs\",\"color\":\"btn-default\",\"id\":\"create\",\"location\":\"nav\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"增加\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-danger\",\"id\":\"delete\",\"location\":\"row\",\"option\":\"service\",\"size\":\"lg\",\"title\":\"删除\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-warning\",\"id\":\"update\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"修改\"},{\"btnsize\":\"btn-xs\",\"color\":\"btn-info\",\"id\":\"retrieve\",\"location\":\"row\",\"option\":\"modal\",\"size\":\"lg\",\"title\":\"查看\"}]', '[]', 'BBBBBB56666666666', '16', null, '2017-10-30 22:31:00', null, '2017-11-08 21:50:12');

-- ----------------------------
-- Table structure for tb_sm_formview
-- ----------------------------
DROP TABLE IF EXISTS `tb_sm_formview`;
CREATE TABLE `tb_sm_formview` (
  `id` varchar(50) NOT NULL,
  `url` varchar(30) DEFAULT '0' COMMENT 'URL',
  `sqlid` varchar(32) DEFAULT NULL COMMENT 'sqlid',
  `columns` varchar(32) DEFAULT NULL COMMENT 'columns',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sm_formview
-- ----------------------------

-- ----------------------------
-- Table structure for tb_sm_sqldefine
-- ----------------------------
DROP TABLE IF EXISTS `tb_sm_sqldefine`;
CREATE TABLE `tb_sm_sqldefine` (
  `id` varchar(50) NOT NULL,
  `sqlid` varchar(32) DEFAULT NULL COMMENT 'sqlid',
  `sqlname` varchar(30) DEFAULT '0' COMMENT 'URL',
  `selectsql` varchar(30) DEFAULT '0' COMMENT 'selectsql',
  `sqlexpand` varchar(30) DEFAULT '0' COMMENT 'sqlexpand',
  `datasource` varchar(30) DEFAULT '' COMMENT 'datasource',
  `cache` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `groupid` varchar(32) DEFAULT NULL,
  `mastertable` varchar(50) DEFAULT NULL,
  `mastertableid` varchar(20) DEFAULT NULL,
  `sqldesc` varchar(100) DEFAULT NULL,
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sm_sqldefine
-- ----------------------------
INSERT INTO `tb_sm_sqldefine` VALUES ('125foakcfeazdfeaeqrazf458e5r4bbbb', 'SM10000000000001', '数据视图', 'select * from tb_sm_dataview', '0', '', null, '1', null, 'tb_sm_dataview', 'id', 'TB_SM_DATAVIEW', '0', null, '2017-11-28 20:18:59', null, null);
INSERT INTO `tb_sm_sqldefine` VALUES ('c57ec2901bc9448b9635dcc163482cb5', 'SM10000000000003', '查询数据字典', 'select * from tb_user_dict', null, null, null, null, null, 'tb_user_dict', 'id', 'TB_BASIC_DICT查询11', '0', null, '2017-11-28 20:18:59', null, null);
INSERT INTO `tb_sm_sqldefine` VALUES ('125foakcfeazdfeaeqrazf458e5r4cccc', 'SM10000000000002', 'SQL自定义视图', 'select * from tb_sm_sqldefine', '0', '', null, '1', null, 'tb_sm_sqldefine', 'id', 'TB_SM_SQLDEFINE查询', '0', null, '2017-11-28 20:18:59', null, null);
INSERT INTO `tb_sm_sqldefine` VALUES ('69cdacd0431e4b7783ea7533155d228d', 'SM20171031210352', '3333333333', '11', '11', '11', '11', '2', null, '1', '1', 'fffffffffffffffffff2222222222呜呜呜2222', '0', null, '2017-11-28 20:18:59', null, null);

-- ----------------------------
-- Table structure for tb_user_dict
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_dict`;
CREATE TABLE `tb_user_dict` (
  `id` varchar(50) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL COMMENT '值',
  `text` varchar(50) DEFAULT '0' COMMENT '文本值',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `parent` varchar(50) DEFAULT NULL COMMENT '所属字典',
  `idx` decimal(5,0) DEFAULT NULL,
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_dict
-- ----------------------------
INSERT INTO `tb_user_dict` VALUES ('0155952ad3c1g534BVKTUBKcXaR8698e', 'COMPONENTTYPE', '控件', '控件类型', '', '0', '0', null, '2016-11-02 15:10:19', 'admin', '2016-11-24 02:37:08');
INSERT INTO `tb_user_dict` VALUES ('015595f06985j3JXb9gCN86GFPKf51KV', 'TEXT', '文本框', null, '0155952ad3c1g534BVKTUBKcXaR8698e', '1111', '0', null, '2016-11-10 16:17:52', 'admin', '2016-11-02 23:46:12');
INSERT INTO `tb_user_dict` VALUES ('0157b30549808Tkk1FaOHO5QPUDF0328', 'DROPDOWN', '下拉框', null, '0155952ad3c1g534BVKTUBKcXaR8698e', null, '0', null, '2016-11-18 16:08:32', 'admin', '2016-11-03 08:41:02');
INSERT INTO `tb_user_dict` VALUES ('0157b3074bb6TQRITOWBifdg64e11dIQ', 'SELECTOR', '选择器', '889999999999999', '0155952ad3c1g534BVKTUBKcXaR8698e', '3366', '0', null, '2016-11-20 00:08:57', 'admin', '2016-11-19 11:25:38');
INSERT INTO `tb_user_dict` VALUES ('0157b309ff4bcca8aV2kMHQ9dYBF6eF2', 'TEXTAREA', '文本域', null, '0155952ad3c1g534BVKTUBKcXaR8698e', null, '0', null, '2016-11-01 16:10:11', 'admin', '2016-11-03 08:41:14');
INSERT INTO `tb_user_dict` VALUES ('0157b30b256fLaUHRG8mh8i1iA6l5dgV', 'GENERATECODE', '自动编码', null, '0155952ad3c1g534BVKTUBKcXaR8698e', null, '0', null, '2016-11-23 16:11:22', 'admin', '2016-11-03 10:10:51');
INSERT INTO `tb_user_dict` VALUES ('0157b30c0096jl2AUYT0bKfImlFG15H0', 'CHECKBOX', '单选框', '单选框', '0155952ad3c1g534BVKTUBKcXaR8698e', '1', '0', null, null, null, null);
INSERT INTO `tb_user_dict` VALUES ('0157b30c0096jl2AUYT0bKfImlFG15H1', 'DATEPICKER', '日期', null, '0155952ad3c1g534BVKTUBKcXaR8698e', null, '0', null, null, null, null);

-- ----------------------------
-- Table structure for tb_user_info
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_info`;
CREATE TABLE `tb_user_info` (
  `id` varchar(32) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `version` decimal(10,0) DEFAULT '0',
  `create_user` varchar(50) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_update_user` varchar(50) DEFAULT NULL,
  `last_update_time` datetime DEFAULT NULL,
  KEY `idx_unqiue_id` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_info
-- ----------------------------
