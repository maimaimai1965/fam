# Запуск приложения art (файл art.war должен находиться в этом же каталоге).
# log-файлы выводятся в каталоге logs\art:
#   профиль = db-postgres,ds-test 
#   port    = 8555 
# Пользователи DB :
#   spring.datasource.username=postgres
#   spring.datasource.password=postgres
# Соединение с PostgreSQL:
#   spring.datasource.url = jdbc:postgresql://127.0.0.1:5432/art
# DB - H2 на диске.
# Используется пул соединений из embedded Tomcat.

set "CATALINA_HOME=."

java -jar art.war ^
     --server.port=8555 ^
     --spring.profiles.active=db-postgres,ds-test
::     >_run.log 
::     --oracle.url=jdbc:oracle:thin:@tornado:1522:tornado2 ^
::     --debug ^
