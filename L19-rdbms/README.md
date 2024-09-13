# Запуск Postgres в docker

Запуск:
* `cd <эта_директория>`
* `docker-compose up -d`

PostgreSQL разворачивается в Docker с демо-базой [dvdrental](https://www.postgresqltutorial.com/postgresql-sample-database/) и PgAdmin 4 (PgAdmin в отдельном контейнере) через `docker-compose`.
* PgAdmin 4 -- http://localhost:9090 
* PostgreSQL снаружи на порту `5432`
* PostgreSQL логин `usr`, пароль `pwd`