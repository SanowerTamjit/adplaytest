# Ad-Play Technology Technical Test

## Web Credential
username: admin<br/>
password: 123456

## MySQL DDL File: /db/mysql_ddl.sql

## Application Process Flow
![alt text](https://raw.githubusercontent.com/SanowerTamjit/adplaytest/master/src/main/resources/static/image/processflow.png)

## Third Party API: To get Covid Bangladesh Data
* Total Stats: https://corona-bd.herokuapp.com/stats<br/>
* District wise Stats: https://corona-bd.herokuapp.com/district<br/>

## MongoDB Operation:
* Two Collections (totalstats, districtstats)<br/>
* First Truncate the all data from MongoDB (Navigation: MongoDB->Truncate MySQL Data<br/>
* Navigate to MongoDB->Save Summary Data From API to save TotaStats Data<br/>
* Navigate to MongoDB->Save District Data From API to save DistrictStats Data<br/>
* To see all data of MongoDB Click Here<br/>

## Mysql Operation:
* Two Tables (totalstats, districtstats)<br/>
* First Truncate the all data from MySQl (Navigation: MySQL->Truncate MongoDB Data<br/>
* Navigate to MySQL->Save Summary Data From Mongo to save TotaStats Data from MongoDB<br/>
* Navigate to MySQL->Save District Data From Mongo to save DistrictStats Data from MongoDB<br/>
* To see all data of MySQL Click Here<br/>
