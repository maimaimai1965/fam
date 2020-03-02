# ������ ���������� fam (���� art.war ������ ���������� � ���� �� ��������).
# log-����� ��������� � �������� _logs\fam:
#   ������� = db-h2-in-memory,ds-test 
#   port    = 8555 
# ������������ DB :
#   spring.datasource.username=fam
#   spring.datasource.password=fam
# ���������� � ��:
#   spring.datasource.url = jdbc:h2:mem:fam
# DB - H2 � ������.
# ������������ ��� ���������� �� embedded Tomcat.

#set "CATALINA_HOME=."

java -jar fam.war ^
     --server.port=8555 ^
     --spring.profiles.active=db-h2-in-memory
::     >_run.log 
::     --oracle.url=jdbc:oracle:thin:@tornado:1522:tornado2 ^
::     --debug ^
