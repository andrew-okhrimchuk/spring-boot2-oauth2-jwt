              О приложении:
Три микросервиса  созданных в многомодульнома Maven-e
Первый - это eureka-server, крутится на порту 8761
Второй - oauth2-jwt-server, это модуль авторизации и аутентификации. На порту 9001
Третий -oauth2-jwt-resource, это сервер ресурсов, в котором реализован простейший CRUD с сущностью Студент на базе H2 и менеджером JPA.
c разными правами доступа к ресурсам, согласно ТЗ от Ромы.
 
 1. Start 3 modules:
 
  - eureka-server
  - oauth2-jwt-server
  - oauth2-jwt-resource
  
 2. get token from:
 
 http://localhost:9001/oauth/token?username=admin&password=12345&grant_type=password
 or 
 http://localhost:9001/oauth/token?username=teacher&password=12345&grant_type=password
 or
 http://localhost:9001/oauth/token?username=student&password=12345&grant_type=password
 
 3. Open brouser on page  http://localhost:9001/swagger-ui/index.html?url=/v3/api-docs&validatorUrl=
 or
 3.1 Open brouser on page  http://localhost:9001/swagger-ui/index.html?url=/v3/api-docs&validatorUrl=
 
 4. autorithet with "Bearer .....
 
 5. use all
 
 