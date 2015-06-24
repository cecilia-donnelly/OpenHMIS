CREATE TABLE `PROJECT_PARTICIPATION` (
  `PROJ_PARTICIP_KEY` bigint(20) NOT NULL AUTO_INCREMENT,
  `CLIENT_KEY` bigint(20) NOT NULL,
  `PROJECT_KEY` bigint(20) NOT NULL,
  `ENTRY_DATE` date DEFAULT NULL,
  `EXIT_DATE` date DEFAULT NULL,
  `REASON_LEAVING_CODE` int(11) DEFAULT NULL,
  `DESTINATION_CODE` int(11) DEFAULT NULL,
  `DEST_FUND_SRC_CODE` int(11) DEFAULT NULL,
  `TRANSITION_CODE` int(11) DEFAULT NULL,
  `VETERAN_INFO_KEY` bigint(20) NOT NULL,
  `S1_CASH_KEY` bigint(20) DEFAULT NULL,
  `S2_CASH_KEY` bigint(20) DEFAULT NULL,
  `S3_CASH_KEY` bigint(20) DEFAULT NULL,
  `S5_CASH_KEY` bigint(20) DEFAULT NULL,
  `S1_BENEFITS_KEY` bigint(20) DEFAULT NULL,
  `S2_BENEFITS_KEY` bigint(20) DEFAULT NULL,
  `S3_BENEFITS_KEY` bigint(20) DEFAULT NULL,
  `S5_BENEFITS_KEY` bigint(20) DEFAULT NULL,
  `S1_DISABILITIES_KEY` bigint(20) DEFAULT NULL,
  `S2_DISABILITIES_KEY` bigint(20) DEFAULT NULL,
  `S3_DISABILITIES_KEY` bigint(20) DEFAULT NULL,
  `S5_DISABILITIES_KEY` bigint(20) DEFAULT NULL,
  `S1_STATUS_KEY` bigint(20) DEFAULT NULL,
  `S2_STATUS_KEY` bigint(20) DEFAULT NULL,
  `S3_STATUS_KEY` bigint(20) DEFAULT NULL,
  `S5_STATUS_KEY` bigint(20) DEFAULT NULL,
  `REC_ACTIVE_GCT` int(11) DEFAULT '1',
  `ENTRY_DATE_TIME` timestamp NULL DEFAULT NULL,
  `ENTRY_USER_KEY` bigint(20) DEFAULT NULL,
  `LOG_DATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `LOG_USER_KEY` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`PROJ_PARTICIP_KEY`),
  KEY `PP_PK_IDX` (`PROJECT_KEY`),
  KEY `PP_CK_IDX` (`CLIENT_KEY`),
  KEY `PP_ENTRYDATE_IDX` (`ENTRY_DATE`),
  KEY `PP_EXITDATE_IDX` (`EXIT_DATE`),
  KEY `PP_DEST_IDX` (`DESTINATION_CODE`),
  KEY `PP_VETERAN_IDX` (`VETERAN_INFO_KEY`),
  KEY `PP_S1_CASH_IDX` (`S1_CASH_KEY`),
  KEY `PP_S3_CASH_IDX` (`S3_CASH_KEY`),
  KEY `PP_S1_DISABILITIES_IDX` (`S1_DISABILITIES_KEY`),
  KEY `PP_S1_BENEFITS_IDX` (`S1_BENEFITS_KEY`),
  KEY `PP_S1_STATUS_IDX` (`S1_STATUS_KEY`),
  KEY `PP_REC_ACTIVE_IDX` (`REC_ACTIVE_GCT`),
  KEY `PP_S3_STATUS_IDX` (`S3_STATUS_KEY`),
  KEY `PP_S3_BENEFITS_IDX` (`S3_BENEFITS_KEY`),
  KEY `PP_S3_DISABILITIES_IDX` (`S3_DISABILITIES_KEY`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;