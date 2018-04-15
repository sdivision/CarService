-- begin CARSERVICE_CAR_SERVICE_CENTER
create table CARSERVICE_CAR_SERVICE_CENTER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255),
    PHONE varchar(255),
    CITY_ID uuid,
    ADDRESS varchar(255),
    CREATOR_ID uuid,
    --
    primary key (ID)
)^
-- end CARSERVICE_CAR_SERVICE_CENTER
-- begin CARSERVICE_CITY
create table CARSERVICE_CITY (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DEFAULT_CITY boolean,
    NAME varchar(255),
    CODE varchar(25),
    --
    primary key (ID)
)^
-- end CARSERVICE_CITY
-- begin CARSERVICE_CUSTOMER
create table CARSERVICE_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    DTYPE varchar(31),
    --
    NAME varchar(255),
    PHONE varchar(255),
    EMAIL varchar(255),
    --
    primary key (ID)
)^
-- end CARSERVICE_CUSTOMER
-- begin CARSERVICE_REPAIR
create table CARSERVICE_REPAIR (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    DESCRIPTION text,
    CENTER_ID uuid,
    EMPLOYEE_ID uuid,
    --
    primary key (ID)
)^
-- end CARSERVICE_REPAIR
-- begin CARSERVICE_EMPLOYEE
create table CARSERVICE_EMPLOYEE (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    FIRST_NAME varchar(255),
    LAST_NAME varchar(255),
    BIRTH_DATE date,
    EMAIL varchar(255),
    SALARY decimal(19, 2),
    CENTER_ID uuid,
    --
    primary key (ID)
)^
-- end CARSERVICE_EMPLOYEE
-- begin CARSERVICE_INDIVIDUAL
create table CARSERVICE_INDIVIDUAL (
    ID uuid,
    --
    PASSPORT_NO varchar(255),
    --
    primary key (ID)
)^
-- end CARSERVICE_INDIVIDUAL
-- begin CARSERVICE_COMPANY
create table CARSERVICE_COMPANY (
    ID uuid,
    --
    INN varchar(255),
    --
    primary key (ID)
)^
-- end CARSERVICE_COMPANY
-- begin CARSERVICE_CENTER_CUSTOMER_LINK
create table CARSERVICE_CENTER_CUSTOMER_LINK (
    CUSTOMER_ID uuid,
    CAR_SERVICE_CENTER_ID uuid,
    primary key (CUSTOMER_ID, CAR_SERVICE_CENTER_ID)
)^
-- end CARSERVICE_CENTER_CUSTOMER_LINK
