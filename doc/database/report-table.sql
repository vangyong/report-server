-- ----------------------------
--  Table structure for `report_order`
-- ----------------------------
DROP TABLE IF EXISTS `report_order`;
CREATE TABLE `report_order` (
  `order_id` varchar(36) COLLATE utf8_bin NOT NULL COMMENT 'banner主键',
  `nick_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '微信昵称',
  `address_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '图片url',
  `address_detail` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '调整url',
  `scheme_id` varchar(36) COLLATE utf8_bin DEFAULT NULL COMMENT '方案id',
  `scheme_detail` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '方案明细',
  `pay_type` int(2) DEFAULT NULL COMMENT '支付方式',
  `status` int(2) DEFAULT NULL COMMENT '状态(1:正常,0:停用)',
  `express_order` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '运单号',
  `pay_money` decimal(10,0) DEFAULT NULL COMMENT '支付金额',
  `express_total` int(6) DEFAULT NULL COMMENT '运单数量',
  `alipay_account` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `real_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='报单';

-- ----------------------------
--  Table structure for `report_scheme`
-- ----------------------------
DROP TABLE IF EXISTS `report_scheme`;
CREATE TABLE `report_scheme` (
  `scheme_id` varchar(36) COLLATE utf8_bin NOT NULL COMMENT 'banner主键',
  `scheme_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '方案明细',
  `address_id` varchar(36) COLLATE utf8_bin DEFAULT NULL,
  `address_detail` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `status` int(2) DEFAULT NULL COMMENT '状态(1:正常,0:停用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`scheme_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='方案';

-- ----------------------------
--  Table structure for `report_address`
-- ----------------------------
DROP TABLE IF EXISTS `report_address`;
CREATE TABLE `report_address` (
  `address_id` varchar(64) COLLATE utf8_bin NOT NULL COMMENT 'banner主键',
  `short_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '简称',
  `address_detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '地址详细',
  `status` int(2) DEFAULT NULL COMMENT '状态(1:正常,0:停用)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='收货地址';

SET FOREIGN_KEY_CHECKS = 1;