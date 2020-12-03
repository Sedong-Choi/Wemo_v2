

CREATE TABLE MEMBER
(
    USER_EMAIL VARCHAR(100) PRIMARY KEY,
    USER_PASS  VARCHAR(30),
    AUTH_TYPE  VARCHAR(100) DEFAULT ('WEMO'),
    USER_NICK  VARCHAR(30),
    USER_FORM1 TEXT(4000)          DEFAULT ('No Form Now'),
    USER_FORM2 TEXT(4000)          DEFAULT ('No Form Now'),
    USER_FORM3 TEXT(4000)          DEFAULT ('No Form Now'),
    USER_SUB   VARCHAR(30)  DEFAULT ('STUDY')
);


CREATE TABLE MEMO(
                     USER_EMAIL VARCHAR(100) REFERENCES MEMBER(USER_EMAIL),
                     MEMO_NUM INT PRIMARY KEY,
                     MEMO_SUB VARCHAR(30) NOT NULL,
                     MEMO_POSITION VARCHAR(20) DEFAULT('ABSOLUTE') NOT NULL,
                     MEMO_TOP VARCHAR(20),
                     MEMO_LEFT VARCHAR(20),
                     MEMO_COLOR VARCHAR(20),
                     MEMO_WIDTH VARCHAR(20),
                     MEMO_HEIGHT VARCHAR(20),
                     MEMO_ZID INT,
                     MEMO_TEX TEXT(4000),
                     MEMO_DATE VARCHAR(10),
                     PREV_TEX TEXT(4000),
                     MEMO_PRE VARCHAR(10),
                     MEMO_FAV VARCHAR(3) DEFAULT('N'),
                     MEMO_LOC VARCHAR(3) DEFAULT('N'),
                     MEMO_TRA VARCHAR(3) DEFAULT('N'),
                     MEMO_KEYW VARCHAR(20)
);

CREATE TABLE CALENDAR(
                         USER_EMAIL VARCHAR(100),			/*���̵�*/
                         MEMO_NUM INT,
                         MEMO_SUB VARCHAR(30),				/*ī�װ�*/
                         MEMO_TEX TEXT(4000),						/*���� ����*/
                         MEMO_DATE VARCHAR(10) NOT NULL,			/*���� �ۼ���*/
                         MEMO_PRE VARCHAR(10),						/*���� �ۼ���*/
                         PREV_TEX TEXT(4000),						/*���� �ۼ�����*/
                         start VARCHAR(30),
                         end VARCHAR(30),
                         CALENDAR_USERNAME VARCHAR(20),
                         CALENDAR_TEXTCOLOR VARCHAR(20),
                         CALENDAR_ALLDAY VARCHAR(20),
                         MEMO_COLOR VARCHAR(20),
                         title VARCHAR(100)
);
insert into member values('se', 'se', 'WEMO', 'se', 'No Form Now', 'No Form Now', 'No Form Now',
                          'STUDY') ;