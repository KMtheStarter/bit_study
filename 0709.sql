desc user_constraints;
/*
c = check, NOT NULL, p = primary key, u = unique, r = reference(foreign key)
*/
select constraint_name, CONSTRAINT_TYPE from user_constraints;

/*
2. �÷� ���� ��������
���̺��� ������ �� �÷��� ���� ���������� ����ϴ� ���.
NOT NULL ���������� �÷� ���������� ������ �� �ִ�.

3. ���̺� ���� ��������
NOT NULL�� ������ ��� ���������� ����� �� �ִ�.
*/
-- �Ʒ� ���� ������ ��� �÷� ���� ����������.
-- constraint ���̺��_�÷�_���������̸� �������Ǹ��
create table colst(
bun number(3) constraint colst_bun_pk primary key,
name varchar2(10) constraint colst_name_nn not null,
age number(5) constraint colst_age_ck check(age >= 20 and age <= 30),
addr varchar2(50) default '����� ������ �ż���',
jumin varchar2(14) constraint colst_jumin_uq unique);

-- �������� Ȯ��
select constraint_name, CONSTRAINT_TYPE from user_constraints where TABLE_NAME='COLST';

-- ���̺� ���� ��������
create table talst(
bun number(3),
name varchar2(10) constraint talst_name_nn not null,
age number(3),
addr varchar2(30) default '����� ������',
jumin varchar2(14) constraint talst_jumin_nn not null,
constraint talst_bun_pk primary key(bun),
constraint talst_age_ch check(age >= 20 and age <= 30),
constraint talst_uq unique(jumin));
-- �������� Ȯ��
select constraint_name, CONSTRAINT_TYPE from user_constraints where TABLE_NAME='TALST';


create table lib(
bun number(3),
book varchar2(20),
l_date date default sysdate,
constraint lib_bun_fk foreign key(bun) references talst(bun) on delete cascade);

select owner, r_owner, table_name, constraint_type, constraint_name
from user_constraints
where table_name in('LIB', 'TALST');

insert into talst (bun, name, age, addr, jumin) values(10, '��浿', 30, '����� ������', '111111-1234567');
insert into talst (bun, name, age, addr, jumin) values(20, '������', 20, '����� ������', '111111-2234567');
insert into talst (bun, name, age, addr, jumin) values(30, '�Ӿƿ�', 20, '��õ�� ����', '111111-2111567');
-- insert into tals values(30, '�Ӿƿ�', 20, '��õ�� ����', '111111-2111567');
select * from talst;

insert into lib (bun, book) values(10, '�ڹ�å');
insert into lib (bun, book) values(20, '����Ŭå');
select * from lib;
-- ���̺� ���� �� �� cascade�Ѱ� Ȯ��
delete from talst where bun=10;

-- �������� ����
alter table talst drop constraint talst_jumin_nn;

select owner, r_owner, table_name, constraint_type, constraint_name
from user_constraints
where table_name in ('LIB', 'TALST');

-- �ǽ����̺� ����
select * from tab;
drop table talst CASCADE CONSTRAINTS;
drop table gogek;
drop table sawon_phone;
drop table sawon;
drop table dept;
drop table lib;
drop table colst;
purge recyclebin;

-- �ڵ�� �����غ���
create table dept(
deptno number(3),
dname varchar2(10),
loc varchar2(10));

create table sawon(
     sabun number(3), 
     saname varchar2(10), 
     deptno number(3), 
     sajob varchar2(10), 
     sapay number(10), 
     sahire date default sysdate, 
     sasex varchar2(6), 
     samgr number(3));
create table gogek(gobun number(3),
                  goname varchar2(10),
                  gotel varchar2(14),
                  gojumin varchar(14),
                  godam number(3));

-- �������� ����
alter table dept add constraint dept_deptno_pk primary key(deptno);
alter table dept add constraint dept_dname_uq unique(dname);
alter table sawon add CONSTRAINT sawon_sabun_pk primary key(sabun);
alter table sawon add CONSTRAINT sawon_deptno_fk foreign key (deptno) references dept(deptno) on delete cascade;
alter table sawon add CONSTRAINT sawon_sasex_ck check(sasex='����' or sasex='����');
alter table sawon add CONSTRAINT sawon_samgr_fk foreign key (samgr) references sawon(sabun) on delete cascade;
alter table gogek add CONSTRAINT gogek_gobun_pk primary key(gobun);
alter table gogek add CONSTRAINT gogek_gojumin_uq unique(gojumin);
alter table gogek add CONSTRAINT gogek_godam_fk foreign key(godam) references sawon(sabun) on delete cascade;
-- Ȯ��
select owner, r_owner, table_name, constraint_type, constraint_name
from user_constraints
where table_name in ('DEPT', 'SAWON', 'GOGEK');

-- like��, order by�� ���: �˻��� �������� ��ȣ�� �ֽż����� ����
select sabun, saname, deptno, sapay, sahire from sawon order by 1 desc;

-- ��ȣ�� primary key�� ������ų ��� �ڵ����� �����ϵ��� ������ �� �ִ�.
-- start with ��ȣ: ������ȣ���� ����
-- increment by: ������ȣ��� ����
create sequence sawon_seq increment by 1 start with 25;
-- nextVal: ������ �Լ��� ����
insert into sawon values(sawon_seq.nextVal, '������', 20, '����', 5000, sysdate, '����', 10);
commit;
select sabun, saname, deptno, sapay, sahire from sawon order by 1 desc;

list;


-- �ռ�������: ���ڿ� ���� ������ ||
-- as alias
select saname ||'�� ����� ' || sabun || '�Դϴ�.' from sawon;
select saname ||'�� ����� ' || sabun || '�Դϴ�.' as ���� from sawon;
select saname ||'�� ����� ' || sabun || '�Դϴ�.' ���� from sawon; -- as�� ��������
select saname ||'�� ����� ' || sabun || '�Դϴ�.' "sawon Contents" from sawon; -- ������ ����
select saname ||'�� ����� ' || sabun || '�Դϴ�.' "sawon_contents" from sawon;
select saname ||'�� ����� ' || sabun || '�Դϴ�.' "sawonContents" from sawon;

-- ����) ��浿�� �޿��� 10000�̴�. �̹��� ���ʽ��� �޿��� 50%�̴�.
select saname || '�� �޿���' || sapay || '�Դϴ�.' Message,
'�̹��� ���ʽ���' || sapay/2 || '�Դϴ�.' Bonus from sawon;

-- ����) �μ���ȣ 20 ��浿�� �޿��� 3000�̴�. 2000 ~ 3000 ������ �޿��� �޴� 20�� �μ������ ������ ����ϵ�
-- �÷��� deptno, message�� ����Ͻÿ�.
select '�μ���ȣ ' || deptno "deptno", saname || '�� �޿���' || sapay || '�̴�.' "Message"
from sawon where sapay >= 2000 and sapay <= 3000 and deptno=20;
select '�μ���ȣ ' || deptno "deptno", saname || '�� �޿���' || sapay || '�̴�.' "Message"
from sawon where sapay between 2000 and 3000 and deptno=20;

-- or ������, in() ������, not in()
-- in �����ڸ� ����ϸ� �ݺ��� �ڵ带 © �� or�� ���� �� ���� �ξ� ����.
select saname, deptno, sapay from sawon where deptno = 10 or deptno = 20 order by 2 asc;
select saname, deptno, sapay from sawon where deptno in(10, 20) order by 2 asc;

-- like ���ڿ� ������
-- % � ���� ���ڿ�
-- _ � �� ���ڿ�
select saname from sawon where saname like '%��%';
select saname from sawon where saname like '__��';

-- dual ���̺�
-- �� ������ ����� ����ϱ� ���� ���̺�.
-- �������, ���� �÷����� ���� �� ���� ����ϱ� ���� ����
select 100 * 2 result from dual;
select sysdate today from dual;
select * from dual; -- �ƹ��ǹ̾��� row�� ���� ����.

-- �����Լ�
select -10, abs(-10) from dual;
select sin(10), cos(10), tan(10), log(10, 2) from dual;
-- -2�� ������ �� �ڸ��� �ǹ�
select round(888.8888), round(888.8888, 0), round(888.8888, 2), round(888.8888, -2) from dual;
-- ������ �ڸ��� ����
select trunc(888.8888), trunc(888.8888, 0), trunc(888.8888, 2), trunc(888.8888, -2) from dual;
-- �ø�, ����
select ceil(10.001), floor(10.99) from dual;

-- �����, �޿�, ����(�޿�/12), ����(�޿��� 3.3%)�� ����Ͻÿ�.
-- ��, ������ �� ������ �ݿø��ϰ�, ������ �� ������ �����Ͻÿ�.
select saname, sapay, round(sapay/12, -1) "earn/month", floor(sapay*0.033) "vat" from sawon;
select saname, sapay, round(sapay/12, -1) "earn/month", trunc(sapay*0.033, -1) "vat" from sawon;

-- lower: ���ڿ� ��ü�� �ҹ��ڷ� ��ȯ
-- upper: ���ڿ� ��ü�� �빮�ڷ� ��ȯ
-- initcap: ���ڿ��� ù ��° ���ڸ� �빮�ڷ� ��ȯ
-- length: ���ڿ� ���̸� ���ϴ� �Լ�
-- lengthb: ������ ���˿� ���� ���ڿ��� ����Ʈ ���� ��ȯ
select lower('kOstal88 OraclE'), upper('kOstal99 OraclE'), initcap('kOstal99 OraclE') from dual;
select length('Kostal88 Oracle') from dual;
select lengthb('Kostal88'), lengthb('����Ŭ') from dual;
select * from nls_database_parameters where parameter like '%CHARACTERSET%';
-- AL32UTF8: �ѱ� ���� ����� ����, 3����Ʈ ������ ����.
-- KO16KSC5601(�ѱۿϼ���),
-- KO16KSC5601, MSWIN949 -> 2����Ʈ
-- UTF8 -> 3����Ʈ

-- trim: �¿� ������ ����
-- ltrim, rtrim: ���� ����
select trim(' kostal88 '), ltrim(' kostal88 '), rtrim(' kostal88 ') from dual;
-- ���ڿ��� ���̷� ��
select length(trim(' kostal88 ')), length(ltrim(' kostal88 ')), length(rtrim(' kostal88 ')) from dual;
-- Ư�� ���ڿ� ã�Ƽ� ����
select trim('��' from '��kostan ����������') from dual;

-- instr �Լ�: ���ڿ� �߿��� ������ Ư�� ���ڰ� ���Ե� ��ġ�� ��ȯ�ϴ� �Լ�.
-- instr(���ڿ�, ã�� ���ڿ�, �˻�����, n��°)
select instr('kosta_kosta', 'k', 1, 2) from dual;
select instr('kosta_kosta', 'k', 1, 1) from dual;
select instr('kosta_kosta', 'k', -1, 1) from dual;
select instr('kosta_kosta', 'k', -1, 2) from dual;
select instr('kosta_kosta', 'a') from dual;

-- substr(���ڿ�, n���� n��)
-- ����Ŭ �ε����� 1���� ������ ��!
select substr('Welcome to Oracle', 5, 3) from dual;
select substr('Welcome to Oracle', -3, 3) from dual;

-- ���������� ���� �ǽ� ���̺�
create table memphone(
    num number(3) constraint memphone_num_pk primary key,
    name varchar2(20),
    pnum varchar2(25));
create sequence memphone_seq increment by 1 start with 1;

insert into memphone values(memphone_seq.nextVal,'��浿','02)567-1267');
insert into memphone values(memphone_seq.nextVal,'�뷡��','032)567-1267');
insert into memphone values(memphone_seq.nextVal,'������','032)567-1267');
insert into memphone values(memphone_seq.nextVal,'���ȣ','051)567-1267');
insert into memphone values(memphone_seq.nextVal,'������','02)567-1267');
insert into memphone values(memphone_seq.nextVal,'�ڼ���','02)567-1267');
insert into memphone values(memphone_seq.nextVal,'������','062)567-1267');

-- ����) memphone ���̺��� ������� ��ȭ��ȣ �߿� �����, ����, ��ȭ��ȣ ���·� ���
select
    name as "�����",
    substr(pnum, 1, instr(pnum, ')', 1, 1)-1) "����",
    substr(pnum, instr(pnum, ')', 1 , 1)+1) "��ȭ��ȣ"
    from memphone;
    
-- ����Ʈ ���� �ڸ��� (�׸�, ���� ����Ʈ, ����Ʈ ũ��)
select substrb('��浿�Դϴ�.', 1, 3) from dual;
select substrb('��浿�Դϴ�.', 4, 3) from dual;
select substrb('��浿�Դϴ�.', 7, 3) from dual;
select substrb('��浿�Դϴ�.', 4, 6) from dual;

-- ���� ä��� �Լ�
select rpad(saname, 8, 'x'), lpad(saname, 8, 'x') from sawon;

-- ����) �����̺��� �ֹι�ȣ ���ڸ��� *�� ����ó���ؼ� ����Ͻÿ�.
select rpad(substr(gojumin, 1, 8), 14, '*') from gogek;

-- decode: ����Ŭ������ �����ϴ� SQL�Լ�
-- decode(���ذ�, ����1, ���1, ����2, ���2, ..., �׿��� ���) �÷��� 
-- case �Լ� : decode ����� Ȯ���� �Լ�
-- case [���ذ�] when ����1 then ���1
-- when ����2 then ���2
-- else �׿ܰ��
-- end "�÷���"
-- ����) decode �Լ��� ���ذ��� ���ϴ� �÷����� "=" �񱳸� ���ؼ��� ���ǰ� ��ġ�ϴ� ��쿡
-- �ٸ� ������ ��ġ ������ case �Լ��� �̿ܿ��� ���,��,���� ����� ���� �پ��� �񱳰� �����ϴ�.
-- [] �� ������ �Ǹ� when������ ���� �� �ִ�.
select saname, deptno,
decode (deptno, 10, '�Ѱ���', 20, '������', 30, '�ѹ���', 50, '�����', '���ߺ�')
"�ӽúμ���" from sawon order by 2 asc;

select saname, deptno,
case deptno when 10 then '������'
when 20 then '������'
else '�����' end "�ӽúμ���" from sawon order by 2 asc;

select saname, deptno,
case when deptno = 10 then '������'
when deptno = 20 then '������'
else '�����' end "�ӽúμ���" from sawon order by 2 asc;

-- ����1) �����̺��� ����, �ֹι�ȣ, ������ ���
select goname, gojumin,
decode (substr(gojumin, 8, 1), 1, '����', 2, '����', 3, '����', 4, '����')
"����" from gogek;

-- ����2) �����, �޿�, ���ʽ� ���
-- ��, ���ʽ��� �޿��� 1000 �̸� -> �޿��� 10%
--                  1000 ~ 2000 -> �޿��� 15%
--                  2000 �ʰ� -> �޿��� 20%
--                  null -> 0
select saname "�����", sapay "����",
case when sapay < 1000 then sapay * 0.1
when sapay >= 1000 and sapay < 2000 then sapay * 0.15
when sapay >= 2000 then sapay * 0.2
else 0 end "���ʽ�" from sawon;

-- yyyy �⵵ ǥ��, cc ���� ǥ��
select sysdate, to_char(sysdate,'yyyy'), to_char(sysdate,'CC') from dual;
select sysdate, to_char(sysdate,'YEAR') from dual; -- ����ǥ��
select sysdate, to_char(sysdate,'yy') from dual; -- �⵵ 2�ڸ�
select sysdate, to_char(sysdate,'month'), to_char(sysdate,'mon') from dual; -- �� 
select sysdate, to_char(sysdate,'q') from dual; -- �б� 
select sysdate, to_char(sysdate,'d') from dual; -- ���� (1~7���� ,1 : �Ͽ���)
select sysdate, to_char(sysdate,'dy') from dual; -- ���� �ѱ�
select sysdate, to_char(sysdate,'day') from dual;
select sysdate, to_char(sysdate,'dd') from dual; -- ��
select sysdate, to_char(sysdate,'ddd') from dual; -- 365��
select sysdate, to_char(sysdate,'hh'),to_char(sysdate,'hh24') from dual; -- �ð�
select sysdate, to_char(sysdate,'mi') from dual;
select sysdate, to_char(sysdate,'ss') from dual;

select sysdate, to_char(sysdate, 'yyyy') || '�� ' || to_char(sysdate, 'mm') || '�� ' || to_char(sysdate, 'dd') || '�� '
|| to_char(sysdate, 'day') || '�̸� ' || to_char(sysdate, 'q') || '�б�, ������ 1�� �߿�' || to_char(sysdate, 'ddd') || '�� ���ҽ��ϴ�.'
"SS" from dual;

select sysdate, to_char(sysdate, 'yyyy"��" mm"��" d"��" day"�̸�') "ss" from dual;

-- ����3) �����, �Ի���, �ٹ��Ⱓ([xx�� xx����])�� ����Ͻÿ�.
select saname, sahire, trunc(months_between(sysdate, sahire)/12, 0) || '��'
|| trunc(mod(months_between(sysdate, sahire), 12), 0) || '����' �ٹ��Ⱓ
from sawon;

-- ó������ �����ϴ� ���� ������ ��¥
-- : �ش� ��¥�κ��� �����ؼ� ��õ� ������ ������ �ش� �Ǵ� ��¥�� ��ȯ�ϴ� �Լ�
-- : ������ �������� ���� ����� ���� �������� �������� ��ȯ��.
select next_day(sysdate, '��') from dual;
-- ������ ��/���� ������ ��¥ (�̴��� ������ ��¥��..)
select last_day(sysdate) from dual;
-- add_months: Ư�� ���� ���� ���� ��¥�� �����ִ� �Լ�
select saname, sahire, add_months(sahire, 5) from sawon;

-- ��¥�� ����Ǵ� round/trunc �Լ�
-- �������� ������ �ݿø�
select saname, sahire, round(sahire, 'yyyy') from sawon;
-- �� ���� �����Լ� ['��-����']
select sysdate + to_yminterval('01-00') from dual; -- 1�� 0���� ����
-- ['�� ��:��:��']
select sysdate + to_dsinterval('100 00:00:00') from dual; -- 100�� 0��0��0�� ����

-- �Ի��� ���� �ٹ��� ���� ����Ͽ� �μ���ȣ, �̸�, �ٹ��� ���� ���
select deptno, saname, sahire, sysdate, trunc(to_char(sysdate - sahire + 1), 0) from sawon;

-- ��� ����� 60���� ���� ���� �������� �� ��, �� ��, �� �� �ΰ��� ���� ��, �̸�, �Ի���, ������ ��¥�� ����Ͻÿ�.
select saname, sahire, next_day(sahire+60, '��') from sawon;
select deptno, saname, sahire, sysdate, (trunc(to_char(sysdate - sahire + 1), 0)) as worked from sawon;

-- DENSE_RANK �Լ��� ���������� 2������ �ο��Ѵ�.
-- ���ϼ����� ������ ���� ������ ����ϱ� ���� ���ȴ�.
select gobun, godam, goname, rank() over(order by godam desc) "����"
from gogek where godam is not null;
select gobun, godam, goname, dense_rank() over(order by godam desc) "����"
from gogek where godam is not null;

-- ����) �μ���� �޿��� ���� ������ ������ ����Ͻÿ� . (�� �� ����)
-- �μ���ȣ,  ����̸�, �޿�, ����
-- partition by: �÷����� �������� ����, �����ÿ��� ��ü ������ ������� ���� �ο�
select deptno, saname, sapay, rank() over(partition by deptno order by sapay desc) "����"
from sawon where sapay is not null;

-- ����Լ�, �����Լ�, �׷��Լ�
-- sum, avg, count, max, min �Լ�
-- �׷��Լ��� �Ϲ������÷� �Բ� ����ϱ� ���ؼ� group by �Ϲ������÷�, ... �����۾� ����
-- nvl -> null�� ġȯ
-- ����Ŭ �ν��Ͻ��� ����Ͽ� ����ϴ°� ���� ȿ�����̱� �ϴ�.
select sum(sapay)/21, avg(nvl(sapay, 0)), count(*), max(sapay), min(sapay) from sawon;

-- �μ��� �޿� �հ踦 ���
-- ��, 10��, 20�� �μ��� ���
-- ��, �޿� �հ谡 15000�̻��� �׷츸 ���
select deptno, sum(sapay) from sawon
group by deptno having (deptno=10 or deptno=20) and sum(sapay) >= 15000;
select deptno, sum(sapay) from sawon
where deptno=10 or deptno=20 group by deptno having sum(sapay) >= 15000;
-- where: �������� ����Լ� ��� �Ұ���. from ���� ���� ���� �м��ϱ� ����.
-- having: ����Լ� ��� ����.

-- ����) ����, ��å�� �ο����� �޿��հ踦 ���
-- ��, ���ڸ� �׷�ȭ�ϰ� �ο��� 2�� ������ ����� ����ϵ�, �ο����� ���� ������ ����
select sasex, sajob, count(*), sum(sapay) from sawon where sasex='����' group by sasex, sajob having count(*) <= 2 order by 1 desc;
select sasex, sajob, count(nvl(sajob, 0)), sum(sapay) from sawon where  sasex='����'
group by sasex, sajob having count(nvl(sajob, 0)) <= 2 order by sajob;

-- ����) �Ի�⵵ �� �޿��հ�� �ִ� �޿��� ���
select to_char(sahire, 'yy')||'��' as year, sum(sapay), max(sapay) from sawon group by to_char(sahire, 'yy');

-- 5�� �׷�ȭ�Ͽ� ��� �޿��� ����ϱ� (rownum)
select ceil(rownum/5), avg(nvl(sapay, 0)) from sawon group by ceil(rownum/5);

-- �÷������� �׷�ȭ
select sum(decode(sajob, '���', sapay, 0)) "���",
sum(decode(sajob, '�븮', sapay, 0)) "�븮",
sum(decode(sajob, '����', sapay, 0)) "����",
sum(decode(sajob, '�̻�', sapay, 0)) "�̻�",
sum(sapay) "�հ�"
from sawon;

-- ����) sawon ���̺��� �μ���ȣ�� 10�� ������� �μ���ȣ�� 30�� ������� ���� ����Ͻÿ�.
select count(*), deptno from sawon where deptno=10 or deptno=30 group by deptno;
select count(decode(deptno, '10', 1)), count(decode(deptno, '30', 1)) from sawon;

-- *** Join(����) ***
--����ϴ� �÷��� �� �� �̻��� ���̺� �����ϴ� ��� ���Ǵ� ��� ���� ���
--32������ ����
--����
1. eque(inner) join : ���εǴ� ���̺� ���� ���ǿ� �����ϴ� �ุ�� ����
2. outer join(left & right) : ���εǴ� ���̺� ���� ���ǿ� �����ϴ� ���
������ ���̺��� ��� �����Ͱ� ����
3. cross join : ���εǴ� ���̺��� � ���赵 ���� ���(table*table)->īƼ�� ���δ�Ʈ
------------------------
4. self join : �ڱ� �ڽ��� ���̺�� ���εǴ� ���
--����
1.T-SQL ����:where������, ���̺���� �����Ͽ� ǥ��
-select~ : ���ϴ� �÷� ����
-from~ : ����� �÷��� ���� ���̺� ����
-where~ : ���� ���̺��� ���� �Ǵ� ������ ǥ��
2.Ansi ���� :on������, ���������� ǥ��
--���εǴ� ���̺��� ���� �÷����� ���� ���! -ansi
select saname, deptno, dname from sawon natural join dept; -- alias�� ���ָ� ������ ��������.
select saname, deptno, dname from sawon join dept using(deptno);

-- �����, �μ���ȣ, �μ����� ���
select s.saname, s.deptno, d.dname from sawon s, dept d where s.deptno=d.deptno;
select s.saname, s.deptno, d.dname from sawon s join dept d on (s.deptno=d.deptno); -- ansi

-- ����, ��ȭ��ȣ, ����ڸ��� ���
select count(*) from gogek;
select g.goname, g.gotel, s.saname from gogek g, sawon s where g.godam = s.sabun;
select g.goname, g.gotel, s.saname from gogek g, sawon s where g.godam = s.sabun(+); -- left outer join
select g.goname, g.gotel, s.saname from gogek g left outer join sawon s on(g.godam=s.sabun);

-- ����, ����ڸ�, ��å, �μ����� ���
select g.goname, nvl(s.saname, 'none') as �����, nvl(s.sajob, 'none') as ��å, nvl(d.dname, 'none') as �μ�
from (gogek g left outer join sawon s on(g.godam=s.sabun))
left outer join dept d on(s.deptno=d.deptno);

select g.goname, nvl(s.saname, 'none') as �����, nvl(s.sajob, 'none') as ��å, nvl(d.dname, 'none') as �μ�
from gogek g, sawon s, dept d where g.godam=s.sabun(+) and s.deptno=d.deptno(+);

-- �μ�(��)�� �޿��հ踦 ���
select d.dname, sum(s.sapay) from dept d join sawon s on(d.deptno=s.deptno) group by d.dname;

-- ����2) 1980~1983�� ���̿� �Ի�� �� �μ��� ������� �μ���ȣ, �μ���, �Ի�⵵(1980, 1981, 1982, 1983)�� ����Ͻÿ�.
select saname, deptno, sahire from sawon
where sahire between '1980/01/01' and '1983/12/31';
select count(*) from sawon
where sahire between '1980/01/01' and '1983/12/31';

select d.dname, s.deptno,
count(decode(to_char(s.sahire, 'yyyy'), '1980', 1)) as "1980���Ի�",
count(decode(to_char(s.sahire, 'yyyy'), '1981', 1)) as "1981���Ի�",
count(decode(to_char(s.sahire, 'yyyy'), '1982', 1)) as "1982���Ի�",
count(decode(to_char(s.sahire, 'yyyy'), '1983', 1)) as "1983���Ի�"
from sawon s, dept d
where s.deptno=d.deptno and s.sahire between '1980/01/01' and '1983/12/31'
group by s.deptno, d.dname;

-- ���� �� subquery
select dname from dept where deptno = (select deptno from sawon where saname='������');

-- 10�� �μ��� �ٹ��ϴ� ����� �̸��� 10�� �μ��� �μ����� �������� ���
select s.saname, d.dname from sawon s, dept d where s.deptno=d.deptno and s.deptno='10';
-- ���� ������ ����ص� ���� ����� �Ȱ��� ������ �� �� �ִ�. (�ζ��� ��)
select s.saname, d.dname from sawon s, (select deptno, dname from dept where deptno='10') d where s.deptno=d.deptno;

-- ������� ��� �޿����� �� ���� �޿��� ���� ����� �˻��غ���.
-- �켱 ��� �޿����� ���غ���
select avg(nvl(sapay, 0)) from sawon;
-- ������� ��� �޿��� �ϳ��� ������ ����Ǳ� ������ ���� �� ���������� ����ϸ� �ȴ�.
select saname, sapay from sawon
where sapay > (select avg(sapay) from sawon);

-- ����) �μ� ��ȣ�� 20�� ��� �� �μ� ��ȣ�� 30�̸鼭 �ִ�޿��� �޴� ������� �޿��� ���� �޴� ��� �˻�
select saname, sapay, deptno from sawon
where deptno=20 and sapay > (select max(sapay) from sawon where deptno=30);

-- ����) ��浿�� ���� �ִ� �μ��� ��� ����� ���, �̸�, �Ի���, �޿��� ����Ͻÿ�.
select sabun, saname, sahire, sapay from sawon
where deptno = (select deptno from sawon where saname='��浿');

-- ����) �μ���ġ�� ������ ��� ����� �̸�, �μ���ȣ�� ����Ͻÿ�.
select s.saname, s.deptno, d.loc from sawon s, dept d
where s.deptno=d.deptno and s.deptno=(select deptno from dept where loc = '����');
-- �̷� ���, ���꿡 �ٸ� �μ��� �ڸ��� ������ ������ ����.
-- �׷� ����� ������ ������ ���������� �����ؾ���. unique�ϸ� ��������...

-- ���� �� subquery
select count(*) from sawon where saname = '������';
select deptno, count(*) from sawon where saname = '������' group by deptno;

select saname, sahire, deptno from sawon
where deptno = (select daptno from sawon where saname = '������');

select saname, sahire, deptno from sawon where deptno
In(select deptno from sawon where saname = '������');

-- �޿��� 3000�̻� �޴� ����� �Ҽӵ� �μ��� ������ �μ����� �ٹ��ϴ� ������� ������ ���

-- all������: ���� ������ �� ������ ������������
-- �˻��� ��� ���� ��� ��ġ�ϸ� ���� �ȴ�.
select sapay from sawon;
select max(sapay) from sawon where deptno=30;
select saname, sapay from sawon where sapay > ALL(select sapay from sawon where deptno=30);

-- SOME
-- �μ� ��ȣ�� 30�� ������� �޿� �� ���� ���� ������
-- ���� �޿��� ���� ����� �̸��� �޿��� ���
select min(sapay) from sawon where deptno=30;
select saname, sapay from sawon
where sapay > SOME(select sapay from sawon where deptno=30);

-- EXIST: ���������� �������� ���������� ��� �߿��� �����ϴ� ���� �ϳ��� �����ϸ� ��.
-- �μ���ȣ�� 10�� ����� �����ϸ� ��� �μ��� �̸��� ���!
select dname from dept where exists
(select sabun from sawon where deptno=180);
select dname from dept where not exists
(select sabun from sawon where deptno=80);

-- 10�� �μ��߿��� 30�� �μ����� ���� ��å�� ���
-- �μ���ȣ, �̸�, ��å, �μ���, ����, �޿��� ����ϵ� �޿��� ���� ������ ����Ͻÿ�.
select s.deptno, s.saname, s.sajob, d.dname, d.loc, s.sapay from sawon s, dept d
where s.deptno = d.deptno and s.deptno = 10 and
s.sajob not in (select sajob from sawon where deptno=30) order by s.sapay desc;
-- �� ���
select s.deptno, s.saname, s.sajob, d.dname, d.loc, s.sapay
from sawon s, (select deptno, dname, loc from dept where deptno = 10) d
where s.deptno = d.deptno and s.deptno = 10 and
s.sajob not in (select sajob from sawon where deptno=30) order by s.sapay desc;

-- table ����
create table dept01 as select * from dept;
select * from dept01;
create table dept02 as select deptno, dname from dept;
select * from dept02;
-- �������� �������� �����ؼ� ���̺��� ������ ����
create table dept03 as select * from dept where 1=0;
select * from dept03;

-- 20�� �μ��� ������� �μ����� 40�� �μ��� �����ϰ� ����
update dept01 set (dname, loc) =
(select dname, loc from dept where deptno=40) where deptno=20;
select * from dept01;
rollback;

-- ��� ���̺��� �μ����� �������� ����� ��� �����Ͻÿ�.
create table sawon2 as select * from sawon;
delete from sawon2 where deptno = (select deptno from dept where dname='������');
select * from sawon2;
-- ������ ���̺� �츮��
drop table sawon2;
show recyclebin;
flashback table sawon2 to before drop;
select * from sawon2;
purge table sawon2;
purge recyclebin;

-- view
create table dept_cp as select * from dept;
create table sawon_cp as select * from sawon;
-- �ܼ� ��
create force view sawon_view1 as select * from sawon_cp;
select * from tab;
select * from sawon_view1;

select view_name, text from user_views;
drop view sawon_view1;

create force view dept_view1 as select * from dept_cp;
insert into dept_view1 values(60, '�����', '����');
select * from dept_view1;
select * from dept_cp;
rollback;
-- �ܼ��信�� DML ��ɾ� ����� �Ұ����� ���
-- �� ���Ƕ� ���Ե��� ���� �⺻ ���̺��� �÷��� not null �������� ���ԵǾ�
-- ���� �÷��� ���
-- dinstinct ������ ���, �׷��Լ� group by ���� ������ ���

-- ���� ��
create view sawon_dept_view as
select s.saname, s.deptno, d.dname from sawon s, dept d where s.deptno=d.deptno;
select * from sawon_dept_view;

-- with check option: ���ǿ� ���Ǿ��� �÷����� �並 ���� ������ ���� �Ұ�
create or replace view with_chk_op_view as
select saname, sapay, deptno from sawon_cp where
deptno = 30 with check option;

select * from with_chk_op_view;
update with_chk_op_view set deptno=20 where sapay=400; -- ����!
update with_chk_op_view set sapay=500 where deptno=30; -- ����

rollback;

-- with read only: �б� ���� ��
create or replace view with_read_only_view as
select saname, sapay, deptno from sawon_cp where
deptno=30 with read only;
select * from with_read_only_view;
update with_read_only_view set deptno=20 where sapay = 400; -- �ȵ�~

-- ���ν���
create or replace procedure p_sawon
    (deptnonum IN number, cur OUT SYS_REFCURSOR) -- IN�� �޴� ��, OUT�� �������� ��. �����ް� Ŀ�� ������.
is
begin
    open cur for
    -- select�� Ŀ���� open. ������ cur
    -- ResultSet rs
    select * from sawon_px
    where deptno = deptnonum;
end;
/
/
create table sawon_px as select * from sawon where 1=0;
select * from sawon_px;
insert into sawon_px values (1, 'ȫ�浿', 10, 'ȸ��', 1000,sysdate, '����', '1');
create sequence sa_px_seq start with 1 increment by 1;
/
/;
-- �Է� ���ν���
create or replace procedure ps_in
(
sname in sawon_px.saname%type,
dno in sawon_px.deptno%type,
sjob in sawon_px.sajob%type,
spay in sawon_px.sapay%type,
ssex in sawon_px.sasex%type,
ssmgr in sawon_px.samgr%type
)
is
begin
insert into sawon_px 
(sabun,saname,deptno,sajob,sapay,sahire,sasex,samgr) 
values(sa_px_seq.nextVal,sname,dno,sjob,spay,sysdate,ssex,ssmgr);
end;
/
/