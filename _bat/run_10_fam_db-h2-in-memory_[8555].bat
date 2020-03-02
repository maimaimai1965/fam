# Запуск приложения fam (файл art.war должен находиться в этом же каталоге).
# log-файлы выводятся в каталоге _logs\fam:
#   профиль = db-h2-in-memory,ds-test 
#   port    = 8555 
# Пользователи DB :
#   spring.datasource.username=fam
#   spring.datasource.password=fam
# Соединение с БД:
#   spring.datasource.url = jdbc:h2:mem:fam
# DB - H2 в памяти.
# Используется пул соединений из embedded Tomcat.

#set "CATALINA_HOME=."

java -jar fam.war ^
     --server.port=8555 ^
     --spring.profiles.active=db-h2-in-memory
::     >_run.log 
::     --oracle.url=jdbc:oracle:thin:@tornado:1522:tornado2 ^
::     --debug ^
