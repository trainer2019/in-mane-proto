-- テーブル作成
CREATE TABLE USERS( 
  USERID VARCHAR2(10) NOT NULL
  , PASSWORD VARCHAR2(60) NOT NULL
  , USERNAME VARCHAR2(100) NOT NULL
  , ROLE_NAME VARCHAR2(10) NOT NULL
  , LOGIN_FAILURE_COUNT NUMBER(10) DEFAULT 0 NOT NULL
  , LOGIN_DENY_TIME DATE
  , DELETED NUMBER(1) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATE_TIME DATE
  , CREATER_ID VARCHAR2(10)
  , CREATE_TIME DATE
  , CONSTRAINT PK_USER_INFO PRIMARY KEY (USERID)
); 

CREATE TABLE STAFFS( 
  STAFF_CODE VARCHAR2(5) NOT NULL
  , STAFF_NAME VARCHAR2(50) NOT NULL
  , STAFF_NAME_KANA VARCHAR2(200) NOT NULL
  , BIRTH_DATE NUMBER(10, 0) DEFAULT 0 NOT NULL
  , JOINED_DATE DATE NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATE_TIME DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATE_TIME DATE NOT NULL
  , CONSTRAINT PK_STAFFS PRIMARY KEY (STAFF_CODE)
); 

CREATE TABLE SALES( 
  SALES_CODE VARCHAR2(5) NOT NULL
  , SALES_NAME VARCHAR2(50) NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATE_TIME DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATE_TIME DATE NOT NULL
  , CONSTRAINT PK_SALES PRIMARY KEY (SALES_CODE)
); 

CREATE TABLE CLIENTS( 
  CLIENTS_CODE VARCHAR2(5) NOT NULL
  , CLIENTS_NAME VARCHAR2(120) NOT NULL
  , CLIENTS_NAME_ABBR VARCHAR2(60) NOT NULL
  , SALES_REPRESENTATIVE_CODE VARCHAR2(10) NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATE_TIME DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATE_TIME DATE NOT NULL
  , CONSTRAINT PK_CLIENTS PRIMARY KEY (CLIENTS_CODE)
); 

CREATE TABLE BULLETIN_BOARD( 
  BULLETIN_BOARD_CODE VARCHAR2(5) NOT NULL
  , CONTENTS VARCHAR2(200) NOT NULL
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATE_TIME DATE NOT NULL
  , CONSTRAINT PK_BULLETIN_BOARD PRIMARY KEY (BULLETIN_BOARD_CODE)
); 

CREATE TABLE PROJECTS( 
  PROJECTS_CODE VARCHAR2(8) NOT NULL
  , PROJECTS_NAME VARCHAR2(100) NOT NULL
  , CLIENTS_CODE VARCHAR2(50) NOT NULL
  , SKILLS VARCHAR2(200)
  , OVERVIEWS VARCHAR2(300)
  , REMARKS VARCHAR2(200)
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATE_TIME DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATE_TIME DATE NOT NULL
  , CONSTRAINT PK_PROJECTS PRIMARY KEY (PROJECTS_CODE)
); 

CREATE TABLE CONTRACTS( 
  CONTRACTS_CODE VARCHAR2(13) NOT NULL
  , CONTRACTS_SUB_CODE VARCHAR2(3) NOT NULL
  , PROJECTS_CODE VARCHAR2(50) NOT NULL
  , CONTRACTS_ID VARCHAR2(50) NOT NULL
  , CONTRACT_PERIOD_FROM DATE NOT NULL
  , CONTRACT_PERIOD_TO DATE NOT NULL
  , CONTRACT_TYPE VARCHAR2(200) NOT NULL
  , UNIT_PRICE NUMBER(7, 0) DEFAULT 0 NOT NULL
  , WORKING_HOURS_MIN NUMBER(3, 0)
  , WORKING_HOURS_MAX NUMBER(3, 0)
  , OVERTIME_PREMIUM_PRICE NUMBER(7, 0)
  , SHORTAGE_DEDUCTIONS_PRICE NUMBER(7, 0)
  , REMARKS VARCHAR2(200)
  , DELETED NUMBER(1, 0) DEFAULT 0 NOT NULL
  , UPDATER_ID VARCHAR2(10)
  , UPDATE_TIME DATE
  , CREATER_ID VARCHAR2(10) NOT NULL
  , CREATE_TIME DATE NOT NULL
  , CONSTRAINT PK_CONTRACTS PRIMARY KEY (CONTRACTS_CODE)
); 

-- コメント作成（論理名）
comment on table USERS is 'ユーザーマスタ';
comment on column USERS.USERID is 'ユーザID';
comment on column USERS.PASSWORD is 'パスワード';
comment on column USERS.USERNAME is 'ユーザ名';
comment on column USERS.ROLE_NAME is 'ロール名';
comment on column USERS.LOGIN_FAILURE_COUNT is 'ログイン失敗回数';
comment on column USERS.LOGIN_DENY_TIME is 'ログイン拒否日時';
comment on column USERS.DELETED is '削除フラグ';
comment on column USERS.UPDATER_ID is '更新者';
comment on column USERS.UPDATE_TIME is '更新年月日';
comment on column USERS.CREATER_ID is '登録者';
comment on column USERS.CREATE_TIME is '登録年月日';
comment on table STAFFS is 'スタッフマスタ';
comment on column STAFFS.STAFF_CODE is 'スタッフCD';
comment on column STAFFS.STAFF_NAME is '氏名';
comment on column STAFFS.STAFF_NAME_KANA is '氏名（かな）';
comment on column STAFFS.BIRTH_DATE is '生年月日';
comment on column STAFFS.JOINED_DATE is '入社年月日';
comment on column STAFFS.DELETED is '削除フラグ';
comment on column STAFFS.UPDATER_ID is '更新者';
comment on column STAFFS.UPDATE_TIME is '更新年月日';
comment on column STAFFS.CREATER_ID is '登録者';
comment on column STAFFS.CREATE_TIME is '登録年月日';
comment on table SALES is '営業マスタ';
comment on column SALES.SALES_CODE is '営業CD';
comment on column SALES.SALES_NAME is '氏名';
comment on column SALES.DELETED is '削除フラグ';
comment on column SALES.UPDATER_ID is '更新者';
comment on column SALES.UPDATE_TIME is '更新年月日';
comment on column SALES.CREATER_ID is '登録者';
comment on column SALES.CREATE_TIME is '登録年月日';
comment on table CLIENTS is '取引先マスタ';
comment on column CLIENTS.CLIENTS_CODE is '取引先CD';
comment on column CLIENTS.CLIENTS_NAME is '取引先名';
comment on column CLIENTS.CLIENTS_NAME_ABBR is '取引先略称';
comment on column CLIENTS.SALES_REPRESENTATIVE_CODE is '担当営業CD';
comment on column CLIENTS.DELETED is '削除フラグ';
comment on column CLIENTS.UPDATER_ID is '更新者';
comment on column CLIENTS.UPDATE_TIME is '更新年月日';
comment on column CLIENTS.CREATER_ID is '登録者';
comment on column CLIENTS.CREATE_TIME is '登録年月日';
comment on table BULLETIN_BOARD is 'お知らせ';
comment on column BULLETIN_BOARD.BULLETIN_BOARD_CODE is 'お知らせCD';
comment on column BULLETIN_BOARD.CONTENTS is '内容';
comment on column BULLETIN_BOARD.DELETED is '削除フラグ';
comment on column BULLETIN_BOARD.CREATER_ID is '登録者';
comment on column BULLETIN_BOARD.CREATE_TIME is '登録年月日';
comment on table PROJECTS is '案件マスタ';
comment on column PROJECTS.PROJECTS_CODE is '案件CD';
comment on column PROJECTS.PROJECTS_NAME is '案件名';
comment on column PROJECTS.CLIENTS_CODE is '取引先CD';
comment on column PROJECTS.SKILLS is '主要技術';
comment on column PROJECTS.OVERVIEWS is '業務内容';
comment on column PROJECTS.REMARKS is '備考';
comment on column PROJECTS.DELETED is '削除フラグ';
comment on column PROJECTS.UPDATER_ID is '更新者';
comment on column PROJECTS.UPDATE_TIME is '更新年月日';
comment on column PROJECTS.CREATER_ID is '登録者';
comment on column PROJECTS.CREATE_TIME is '登録年月日';
comment on table CONTRACTS is '契約情報';
comment on column CONTRACTS.CONTRACTS_CODE is '契約CD';
comment on column CONTRACTS.CONTRACTS_SUB_CODE is '契約枝番';
comment on column CONTRACTS.PROJECTS_CODE is '案件CD';
comment on column CONTRACTS.CONTRACTS_ID is 'スタッフCD';
comment on column CONTRACTS.CONTRACT_PERIOD_FROM is '契約期間From';
comment on column CONTRACTS.CONTRACT_PERIOD_TO is '契約期間To';
comment on column CONTRACTS.CONTRACT_TYPE is '契約種別';
comment on column CONTRACTS.UNIT_PRICE is '単価';
comment on column CONTRACTS.WORKING_HOURS_MIN is '稼働時間(下限)';
comment on column CONTRACTS.WORKING_HOURS_MAX is '稼働時間(上限)';
comment on column CONTRACTS.OVERTIME_PREMIUM_PRICE is '超過割増分単価';
comment on column CONTRACTS.SHORTAGE_DEDUCTIONS_PRICE is '未達控除分単価';
comment on column CONTRACTS.REMARKS is '備考';
comment on column CONTRACTS.DELETED is '削除フラグ';
comment on column CONTRACTS.UPDATER_ID is '更新者';
comment on column CONTRACTS.UPDATE_TIME is '更新年月日';
comment on column CONTRACTS.CREATER_ID is '登録者';
comment on column CONTRACTS.CREATE_TIME is '登録年月日';