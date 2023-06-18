DROP TABLE IF EXISTS guser;
CREATE TABLE guser AS SELECT * FROM CSVREAD('classpath:guser.csv');