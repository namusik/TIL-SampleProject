# Testcontainers

## 정의 

    JUnit test를 지원하는 Java 라이브러리.

    외부에서 따로 DB를 설정하거나 별도의 프로그램, 스크립트를 실행할 필요 없이

    자바 언어만으로 docker 컨테이너를 실행할 수 있음.

## 장점 

    1. 데이터 액세스 레이어 통합 테스트 

        MySQL, Oracle DB의 컨테이너화된 인스턴스를 사용해 데이터 액세스 레이어 부분의 코드를 테스트 할 수 있음. 

    2. 통합 테스트 

        전체 애플리케이션 통합 테스트 지원

    3. UI/인수 테스트 

        셀레니움을 지원해 각 테스트가 독립된 인스턴스의 브라우저에서 실행됨. 


    테스트 이전에 H2, PostgreSQL등 Docker Container를 따로 띄우지 않아도 자동으로 테스트할때 
    
    DB Contatiner를 자동으로 띄워주고 테스트가 종료되면 컨테이너도 같이 종료시켜주는 역할을 하는 라이브러리. 

## 사용방법 

    @TestContainers 

        테스트 위해 해당 어노테이션 사용

        docker로 운영 DB와 동일한 환경의 DB인스턴스를 생성. 

        테스트 인스턴스 분리를 통해 테스트 및 테스트 데이터의 멱등성을 높임.    