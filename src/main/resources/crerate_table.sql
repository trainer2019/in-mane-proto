-- VXe¼:Ò­óµÇVXe
-- XL[}¼:IMUSER


-- yIÓIzSe[uí
--DROP TABLE CLIENTS;
--DROP TABLE CONTRACTS;
--DROP TABLE CONTRACTS_TRAN;
--DROP TABLE USERS;
--DROP TABLE STAFFS;
--DROP TABLE BULLETIN_BOARD;
--DROP TABLE PROJECTS;
--DROP TABLE BILL_ADDRESSES;

-- e[uì¬
CREATE TABLE USERS( 
  USER_ID VARCHAR2(10) NOT NULL
  , PASSWORD VARCHAR2(60) NOT NULL
  , USER_NAME VARCHAR2(80) NOT NULL
  , ROLE_NAME VARCHAR2(5) NOT NULL
  , LOGIN_FAILURE_COUNT NUMBER(1) DEFAULT 0 NOT NULL
  , LOGIN_DENIED_AT DATE
  , DELETED NUMBER(1) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_USERS PRIMARY KEY (USER_ID)
); 

CREATE TABLE STAFFS( 
  STAFF_NO CHAR(5) NOT NULL
  , STAFF_TYPE CHAR(2) NOT NULL
  , STAFF_NAME VARCHAR2(60) NOT NULL
  , STAFF_NAME_KANA VARCHAR2(120) NOT NULL
  , BIRTH_DATE DATE NOT NULL
  , JOINED_DATE DATE NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_STAFFS PRIMARY KEY (STAFF_NO)
); 

CREATE TABLE CLIENTS( 
  CLIENT_ID VARCHAR2(36) NOT NULL
  , CLIENT_NAME VARCHAR2(200) NOT NULL
  , SALES_STAFF_NO VARCHAR2(10) NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_CLIENTS PRIMARY KEY (CLIENT_ID)
); 

CREATE TABLE BULLETIN_BOARD( 
  BULLETIN_BOARD_ID VARCHAR2(36) NOT NULL
  , CONTENTS VARCHAR2(400) NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_BULLETIN_BOARD PRIMARY KEY (BULLETIN_BOARD_ID)
); 

CREATE TABLE PROJECTS( 
  PROJECT_ID VARCHAR2(36) NOT NULL
  , PROJECT_NAME VARCHAR2(120) NOT NULL
  , CLIENT_ID VARCHAR2(36) NOT NULL
  , BILL_ADDRESS_ID VARCHAR2(36) NOT NULL
  , SKILLS VARCHAR2(400)
  , OVERVIEWS VARCHAR2(800)
  , REMARKS VARCHAR2(800)
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_PROJECTS PRIMARY KEY (PROJECT_ID)
); 

CREATE TABLE CONTRACTS( 
  CONTRACT_ID VARCHAR2(36) NOT NULL
  , PROJECT_ID VARCHAR2(36) NOT NULL
  , STAFF_NO VARCHAR2(5) NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_CONTRACTS PRIMARY KEY (CONTRACT_ID)
); 

CREATE TABLE CONTRACTS_TRAN( 
  CONTRACT_ID VARCHAR2(36) NOT NULL
  , SUB_NO CHAR(3) NOT NULL
  , CONTRACT_PERIOD_FROM DATE NOT NULL
  , CONTRACT_PERIOD_TO DATE NOT NULL
  , CONTRACT_TYPE NUMBER(1, 0) NOT NULL
  , UNIT_PRICE NUMBER(7, 0) DEFAULT 0 NOT NULL
  , WORKING_HOURS_MIN NUMBER(3, 0)
  , WORKING_HOURS_MAX NUMBER(3, 0)
  , OVERTIME_PREMIUM_PRICE NUMBER(7, 0)
  , SHORTAGE_DEDUCTIONS_PRICE NUMBER(7, 0)
  , REMARKS VARCHAR2(800)
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_CONTRACTS_TRAN PRIMARY KEY (CONTRACT_ID, SUB_NO)
); 

CREATE TABLE BILL_ADDRESSES( 
  BILL_ADDRESS_ID VARCHAR2(36) NOT NULL
  , CLIENT_ID VARCHAR2(36) NOT NULL
  , SPECIFIED_FORMAT_TYPE NUMBER(1, 0) DEFAULT 0 NOT NULL
  , SITE NUMBER(3, 0)
  , BILL_UNIT_TYPE NUMBER(1, 0)
  , BILL_UNIT NUMBER(3, 0) DEFAULT 0
  , ZIP_CODE CHAR (7)
  , ADRESS1 VARCHAR2(100)
  , ADRESS2 VARCHAR2(100)
  , COMPANY_NAME1 VARCHAR2(100)
  , COMPANY_NAME2 VARCHAR2(100)
  , COMPANY_NAME3 VARCHAR2(100)
  , COMPANY_NAME4 VARCHAR2(100)
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATED_AT DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATED_AT DATE NOT NULL
  , CONSTRAINT PK_BILL_ADDRESSES PRIMARY KEY (BILL_ADDRESS_ID)
); 

-- Rgì¬i_¼j
comment on table USERS is '[U[}X^';
comment on column USERS.USER_ID is '[UID';
comment on column USERS.PASSWORD is 'pX[h';
comment on column USERS.USER_NAME is '[U¼';
comment on column USERS.ROLE_NAME is '[¼';
comment on column USERS.LOGIN_FAILURE_COUNT is 'OC¸sñ';
comment on column USERS.LOGIN_DENIED_AT is 'OCÛú';
comment on column USERS.DELETED is 'ítO';
comment on column USERS.UPDATER_ID is 'XVÒ';
comment on column USERS.UPDATED_AT is 'XVNú';
comment on column USERS.CREATER_ID is 'o^Ò';
comment on column USERS.CREATED_AT is 'o^Nú';

comment on table STAFFS is 'X^bt}X^';
comment on column STAFFS.STAFF_NO is 'X^btNo';
comment on column STAFFS.STAFF_TYPE is 'X^btíÊ';
comment on column STAFFS.STAFF_NAME is '¼';
comment on column STAFFS.STAFF_NAME_KANA is '¼i©Èj';
comment on column STAFFS.BIRTH_DATE is '¶Nú';
comment on column STAFFS.JOINED_DATE is 'üÐNú';
comment on column STAFFS.DELETED is 'ítO';
comment on column STAFFS.UPDATER_ID is 'XVÒ';
comment on column STAFFS.UPDATED_AT is 'XVNú';
comment on column STAFFS.CREATER_ID is 'o^Ò';
comment on column STAFFS.CREATED_AT is 'o^Nú';

comment on table CLIENTS is 'æøæ}X^';
comment on column CLIENTS.CLIENT_ID is 'æøæID';
comment on column CLIENTS.CLIENT_NAME is 'æøæ¼';
comment on column CLIENTS.SALES_STAFF_NO is 'ScÆNo';
comment on column CLIENTS.DELETED is 'ítO';
comment on column CLIENTS.UPDATER_ID is 'XVÒ';
comment on column CLIENTS.UPDATED_AT is 'XVNú';
comment on column CLIENTS.CREATER_ID is 'o^Ò';
comment on column CLIENTS.CREATED_AT is 'o^Nú';

comment on table BULLETIN_BOARD is '¨mç¹';
comment on column BULLETIN_BOARD.BULLETIN_BOARD_ID is '¨mç¹ID';
comment on column BULLETIN_BOARD.CONTENTS is 'àe';
comment on column BULLETIN_BOARD.DELETED is 'ítO';
comment on column BULLETIN_BOARD.CREATER_ID is 'o^Ò';
comment on column BULLETIN_BOARD.CREATED_AT is 'o^Nú';

comment on table PROJECTS is 'Ä}X^';
comment on column PROJECTS.PROJECT_ID is 'ÄID';
comment on column PROJECTS.PROJECT_NAME is 'Ä¼';
comment on column PROJECTS.CLIENT_ID is 'æøæID';
comment on column PROJECTS.BILL_ADDRESS_ID is '¿æID';
comment on column PROJECTS.SKILLS is 'åvZp';
comment on column PROJECTS.OVERVIEWS is 'Æ±àe';
comment on column PROJECTS.REMARKS is 'õl';
comment on column PROJECTS.DELETED is 'ítO';
comment on column PROJECTS.UPDATER_ID is 'XVÒ';
comment on column PROJECTS.UPDATED_AT is 'XVNú';
comment on column PROJECTS.CREATER_ID is 'o^Ò';
comment on column PROJECTS.CREATED_AT is 'o^Nú';

comment on table CONTRACTS is '_ñwb_îñ';
comment on column CONTRACTS.CONTRACT_ID is '_ñID';
comment on column CONTRACTS.PROJECT_ID is 'ÄID';
comment on column CONTRACTS.STAFF_NO is 'X^btNo';
comment on column CONTRACTS.DELETED is 'ítO';
comment on column CONTRACTS.UPDATER_ID is 'XVÒ';
comment on column CONTRACTS.UPDATED_AT is 'XVNú';
comment on column CONTRACTS.CREATER_ID is 'o^Ò';
comment on column CONTRACTS.CREATED_AT is 'o^Nú';

comment on table CONTRACTS_TRAN is '_ñgîñ';
comment on column CONTRACTS_TRAN.CONTRACT_ID is '_ñID';
comment on column CONTRACTS_TRAN.SUB_NO is '_ñ}Ô';
comment on column CONTRACTS_TRAN.CONTRACT_PERIOD_FROM is '_ñúÔFrom';
comment on column CONTRACTS_TRAN.CONTRACT_PERIOD_TO is '_ñúÔTo';
comment on column CONTRACTS_TRAN.CONTRACT_TYPE is '_ñíÊ';
comment on column CONTRACTS_TRAN.UNIT_PRICE is 'P¿';
comment on column CONTRACTS_TRAN.WORKING_HOURS_MIN is 'Ò­Ô(ºÀ)';
comment on column CONTRACTS_TRAN.WORKING_HOURS_MAX is 'Ò­Ô(ãÀ)';
comment on column CONTRACTS_TRAN.OVERTIME_PREMIUM_PRICE is '´ßªP¿';
comment on column CONTRACTS_TRAN.SHORTAGE_DEDUCTIONS_PRICE is '¢BTªP¿';
comment on column CONTRACTS_TRAN.REMARKS is 'õl';
comment on column CONTRACTS_TRAN.DELETED is 'ítO';
comment on column CONTRACTS_TRAN.UPDATER_ID is 'XVÒ';
comment on column CONTRACTS_TRAN.UPDATED_AT is 'XVNú';
comment on column CONTRACTS_TRAN.CREATER_ID is 'o^Ò';
comment on column CONTRACTS_TRAN.CREATED_AT is 'o^Nú';

comment on table BILL_ADDRESSES is '¿æ}X^';
comment on column BILL_ADDRESSES.BILL_ADDRESS_ID is '¿æID';
comment on column BILL_ADDRESSES.CLIENT_ID is 'æøæID';
comment on column BILL_ADDRESSES.SPECIFIED_FORMAT_TYPE is 'qæwè';
comment on column BILL_ADDRESSES.SITE is 'TCg';
comment on column BILL_ADDRESSES.BILL_UNIT_TYPE is '¿PÊíÊ';
comment on column BILL_ADDRESSES.BILL_UNIT is '¿PÊ';
comment on column BILL_ADDRESSES.ZIP_CODE is 'XÖÔ';
comment on column BILL_ADDRESSES.ADRESS1 is 'ZP';
comment on column BILL_ADDRESSES.ADRESS2 is 'ZQ';
comment on column BILL_ADDRESSES.COMPANY_NAME1 is 'ïÐ¼P';
comment on column BILL_ADDRESSES.COMPANY_NAME2 is 'ïÐ¼Q';
comment on column BILL_ADDRESSES.COMPANY_NAME3 is 'ïÐ¼R';
comment on column BILL_ADDRESSES.COMPANY_NAME4 is 'ïÐ¼S';
comment on column BILL_ADDRESSES.DELETED is 'ítO';
comment on column BILL_ADDRESSES.UPDATER_ID is 'XVÒ';
comment on column BILL_ADDRESSES.UPDATED_AT is 'XVNú';
comment on column BILL_ADDRESSES.CREATER_ID is 'o^Ò';
comment on column BILL_ADDRESSES.CREATED_AT is 'o^Nú';

