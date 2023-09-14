# Spring Exception

## 서블릿의 예외 처리 방식

### Exception(예외)

#### 자바 흐름

자바 main() 실행 -> main 쓰레드 생성 -> 도중에 예외 발생 -> main () 넘어서 예외가 던저짐 -> 예외 정보를 남기고 쓰레드 종료

#### 서블릿 흐름

요청마다 쓰레드가 할당되고, 서블릿 컨테이너 안에서 실행됨. 컨트롤러에서 예외가 발생 -> 인터셉터 -> 서블릿 -> 필터 -> WAS 까지 예외가 전달된다.

### response.sendError(HTTP 상태 코드, 오류 메시지)