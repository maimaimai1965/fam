# Запуск приложения art (файл art.war должен находиться в этом же каталоге).
# log-файлы выводятся в каталоге logs\art:
#   профиль = db-h2-in-memory,ds-test 
#   port    = 8555 
# Пользователи DB :
#   spring.datasource.username=art
#   spring.datasource.password=art
# Соединение с БД:
#   spring.datasource.url = jdbc:h2:file:D:/java/work/idea_home/art/_db/h2/art;AUTO_SERVER=TRUE
# DB - H2 на диске.
# Используется пул соединений из embedded Tomcat.

set "CATALINA_HOME=."

java -jar art.war ^
     --server.port=8555 ^
     --spring.profiles.active=db-h2-in-file,ds-test
::     >_run.log 
::     --oracle.url=jdbc:oracle:thin:@tornado:1522:tornado2 ^
::     --debug ^
