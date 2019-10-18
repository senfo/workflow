* CREATE DATABASE workflow
* CREATE USER workflowappuser WITH CREATEDB PASSWORD 'password'
* GRANT ALL PRIVILEGES ON DATABASE workflow TO workflowappuser;
* sudo su - postgres
* cd 11/data
* vi pg_hba.conf
* Change "ident" to md5 for host 127.0.0.1/32
* Run Dropwizard with arguments "db migrate workflow-rest/src/main/resources/database.yaml"
* Run Dropwizard with arguments "server workflow-rest/src/main/resources/database.yaml"