# 스프링부트에서 사용되는 로깅설정들

스프링부트에서 JDBC, Mybatis, JPA 등등을 사용할 때, 로그를 남기기 위해서는 application.properties에 추가해줘야하는 것들이 있다. 

그때 그때 찾아쓰기 위해 정리해놓기.

### JdbcTemplate SQL 로그
~~~properties
logging.level.org.springframework.jdbc=debug
~~~
main, test 모두에 추가해줘야 함.

### MyBatis SQL 로그
~~~properties
logging.level.hello.itemservice.repository.mybatis=trace
~~~

### JPA SQL 로그
~~~properties
logging.level.org.hibernate.SQL=debug
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=trace (스프링부트 3 이전)
logging.level.org.hibernate.orm.jdbc.bind=TRACE (스프링부트 3 부터는 얘 쓰기)
~~~
JPA SQL 로그와 파라미터 바인딩 뜨도록 로깅처리.

~~~properties
logging.level.org.hibernate.resource.transaction=debug
~~~
JPA 트랜잭션 정보를 로깅한다. 

### 트랜잭션 관련 로그
~~~properties
logging.level.org.springframework.transaction.interceptor=trace
~~~
트랜잭션의 시작과 종료를 알 수있는 로그. 

~~~properties
//JDBC 쓸 때
logging.level.org.springframework.jdbc.datasource.DataSourceTransactionManager=debug

//JPA 쓸 때
logging.level.org.springframework.orm.jpa.JpaTransactionManager=debug
~~~
트랜잭션이 커밋됐는지 롤백됐는지 확인할 수 있는 로그.

