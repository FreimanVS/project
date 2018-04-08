DROP SCHEMA IF EXISTS company;
CREATE SCHEMA company;
USE company;
GRANT ALL PRIVILEGES ON company.* TO 'root'@'%' IDENTIFIED BY 'pass';
GRANT ALL PRIVILEGES ON company.* TO 'root'@'localhost' IDENTIFIED BY 'pass';