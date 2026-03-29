create user farmSupport IDENTIFIED BY farm1234;

grant connect , resource , unlimited  tablespace to farmSupport;



CREATE TABLE farmSupport.users (
    user_id    NUMBER PRIMARY KEY,
    user_name  VARCHAR2(50) UNIQUE,
    email      VARCHAR2(60) UNIQUE,
    address    VARCHAR2(200),
    user_role  VARCHAR2(20) DEFAULT 'USER' NOT NULL
);

create table farmsupport.products(
    product_id number primary key,
    product_name varchar2(60) not null,
    price number not null,
    regist_user_id number references farmSupport.users(user_id),
    regist_date date,
    stock number not null,
    image_uri varchar2(255)
);

create table farmSupport.orders(
    order_id number primary key,
    user_id number references farmsupport.users(user_id),
    total_amount number not null,
    order_date date
);

create table farmsupport.order_details(
    order_id number,
    order_detail_id number generated always as identity,
    prices number not null,
    product_id number references farmsupport.products(product_id),
    primary key (order_id,order_detail_id),
    foreign key order_id references farmsupport.orders(order_id)
)
;

-- テーブルが存在するか、行数を数える
SELECT COUNT(*) FROM farmSupport.users;

-- 紐付いている制約も含めて一度削除
DROP TABLE farmSupport.users CASCADE CONSTRAINTS;

-- Identity付きで再作成
CREATE TABLE farmSupport.users(
    user_id    NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_name  VARCHAR2(50) UNIQUE,
    email      VARCHAR2(60) UNIQUE,
    address    VARCHAR2(200),
    user_role  VARCHAR2(20) DEFAULT 'USER' NOT NULL
);

