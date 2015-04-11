# spring-boot-demo
###Spring Boot Rest Data-jpa Netty etc 

After building and running spring-boot-demo with</p> *mvn spring-boot:run*
you can try:</p>
1. telnet localhost 7000</p>
type</p>
*test*</p>
you should see</p> Hello *test* . Greetings from Netty server</p>
2. http://localhost:8080/api/customer (Get methos, lists all customers stored)</p>
3. http://localhost:8080/api/customer/kamil/adil (PUT method is supported only, creates and stores a new customer) </p>
4. http://localhost:8080/api/msg</p>
5. http://localhost:8080/api/beans (which is provided by actuator starter)</p>
6. http://localhost:8080/api/metrics (again by actuator)</p>
...

