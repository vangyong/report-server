CREATE USER 'report'@'localhost' IDENTIFIED BY 'report';
flush privileges;

GRANT SELECT, INSERT,UPDATE,DELETE ON report.* TO 'report'@'localhost' identified by "report";
flush privileges;