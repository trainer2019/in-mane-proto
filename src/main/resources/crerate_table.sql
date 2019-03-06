-- �e�[�u���쐬
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

-- �R�����g�쐬�i�_�����j
comment on table USERS is '���[�U�[�}�X�^';
comment on column USERS.USERID is '���[�UID';
comment on column USERS.PASSWORD is '�p�X���[�h';
comment on column USERS.USERNAME is '���[�U��';
comment on column USERS.ROLE_NAME is '���[����';
comment on column USERS.LOGIN_FAILURE_COUNT is '���O�C�����s��';
comment on column USERS.LOGIN_DENY_TIME is '���O�C�����ۓ���';
comment on column USERS.DELETED is '�폜�t���O';
comment on column USERS.UPDATER_ID is '�X�V��';
comment on column USERS.UPDATE_TIME is '�X�V�N����';
comment on column USERS.CREATER_ID is '�o�^��';
comment on column USERS.CREATE_TIME is '�o�^�N����';
comment on table STAFFS is '�X�^�b�t�}�X�^';
comment on column STAFFS.STAFF_CODE is '�X�^�b�tCD';
comment on column STAFFS.STAFF_NAME is '����';
comment on column STAFFS.STAFF_NAME_KANA is '�����i���ȁj';
comment on column STAFFS.BIRTH_DATE is '���N����';
comment on column STAFFS.JOINED_DATE is '���ДN����';
comment on column STAFFS.DELETED is '�폜�t���O';
comment on column STAFFS.UPDATER_ID is '�X�V��';
comment on column STAFFS.UPDATE_TIME is '�X�V�N����';
comment on column STAFFS.CREATER_ID is '�o�^��';
comment on column STAFFS.CREATE_TIME is '�o�^�N����';
comment on table SALES is '�c�ƃ}�X�^';
comment on column SALES.SALES_CODE is '�c��CD';
comment on column SALES.SALES_NAME is '����';
comment on column SALES.DELETED is '�폜�t���O';
comment on column SALES.UPDATER_ID is '�X�V��';
comment on column SALES.UPDATE_TIME is '�X�V�N����';
comment on column SALES.CREATER_ID is '�o�^��';
comment on column SALES.CREATE_TIME is '�o�^�N����';
comment on table CLIENTS is '�����}�X�^';
comment on column CLIENTS.CLIENTS_CODE is '�����CD';
comment on column CLIENTS.CLIENTS_NAME is '����於';
comment on column CLIENTS.CLIENTS_NAME_ABBR is '����旪��';
comment on column CLIENTS.SALES_REPRESENTATIVE_CODE is '�S���c��CD';
comment on column CLIENTS.DELETED is '�폜�t���O';
comment on column CLIENTS.UPDATER_ID is '�X�V��';
comment on column CLIENTS.UPDATE_TIME is '�X�V�N����';
comment on column CLIENTS.CREATER_ID is '�o�^��';
comment on column CLIENTS.CREATE_TIME is '�o�^�N����';
comment on table BULLETIN_BOARD is '���m�点';
comment on column BULLETIN_BOARD.BULLETIN_BOARD_CODE is '���m�点CD';
comment on column BULLETIN_BOARD.CONTENTS is '���e';
comment on column BULLETIN_BOARD.DELETED is '�폜�t���O';
comment on column BULLETIN_BOARD.CREATER_ID is '�o�^��';
comment on column BULLETIN_BOARD.CREATE_TIME is '�o�^�N����';
comment on table PROJECTS is '�Č��}�X�^';
comment on column PROJECTS.PROJECTS_CODE is '�Č�CD';
comment on column PROJECTS.PROJECTS_NAME is '�Č���';
comment on column PROJECTS.CLIENTS_CODE is '�����CD';
comment on column PROJECTS.SKILLS is '��v�Z�p';
comment on column PROJECTS.OVERVIEWS is '�Ɩ����e';
comment on column PROJECTS.REMARKS is '���l';
comment on column PROJECTS.DELETED is '�폜�t���O';
comment on column PROJECTS.UPDATER_ID is '�X�V��';
comment on column PROJECTS.UPDATE_TIME is '�X�V�N����';
comment on column PROJECTS.CREATER_ID is '�o�^��';
comment on column PROJECTS.CREATE_TIME is '�o�^�N����';
comment on table CONTRACTS is '�_����';
comment on column CONTRACTS.CONTRACTS_CODE is '�_��CD';
comment on column CONTRACTS.CONTRACTS_SUB_CODE is '�_��}��';
comment on column CONTRACTS.PROJECTS_CODE is '�Č�CD';
comment on column CONTRACTS.CONTRACTS_ID is '�X�^�b�tCD';
comment on column CONTRACTS.CONTRACT_PERIOD_FROM is '�_�����From';
comment on column CONTRACTS.CONTRACT_PERIOD_TO is '�_�����To';
comment on column CONTRACTS.CONTRACT_TYPE is '�_����';
comment on column CONTRACTS.UNIT_PRICE is '�P��';
comment on column CONTRACTS.WORKING_HOURS_MIN is '�ғ�����(����)';
comment on column CONTRACTS.WORKING_HOURS_MAX is '�ғ�����(���)';
comment on column CONTRACTS.OVERTIME_PREMIUM_PRICE is '���ߊ������P��';
comment on column CONTRACTS.SHORTAGE_DEDUCTIONS_PRICE is '���B�T�����P��';
comment on column CONTRACTS.REMARKS is '���l';
comment on column CONTRACTS.DELETED is '�폜�t���O';
comment on column CONTRACTS.UPDATER_ID is '�X�V��';
comment on column CONTRACTS.UPDATE_TIME is '�X�V�N����';
comment on column CONTRACTS.CREATER_ID is '�o�^��';
comment on column CONTRACTS.CREATE_TIME is '�o�^�N����';