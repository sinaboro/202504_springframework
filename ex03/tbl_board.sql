drop table tbl_board; drop SEQUENCE seq_board;

create SEQUENCE seq_board;

create table tbl_board( bno number(10,0), title varchar2(200) not null, content varchar2(2000) not null, writer varchar2(50) not null, regdate date default sysdate, --작성일 updatedate date default sysdate --수정일
);

alter table tbl_board add CONSTRAINT pk_board primary key(bno);

SELECT constraint_name FROM user_constraints WHERE table_name = 'TBL_BOARD' AND constraint_type = 'P';

SELECT constraint_name FROM user_constraints WHERE table_name = 'BOARD' AND constraint_type = 'P';