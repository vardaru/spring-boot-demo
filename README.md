# spring-boot-demo
Spring Boot Rest Data-jpa Netty etc 

After building and running spring-boot-demo with mvn spring-boot:run
you can try:
1. telnet localhost 7000
type "test"
you should see Hello test . Greetings from Netty server
2. http://localhost:8080/api/all
3. http://localhost:8080/api/msg
4. http://localhost:8080/api/beans (which is provided by actuator starter)
5. http://localhost:8080/api/metrics (again by actuator)
...

