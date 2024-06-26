# Java I/O

## 개념
- Java I/O (Input/Output) 기능을 제공하는 표준 라이브러리 패키지
- 파일과 스트림을 통해 데이터를 읽고 쓰는 데 사용됨


## 특징 
- java.io 패키지를 통해 기능을 제공
- 파일 읽기/쓰기, 네트워크 연결 또는 데이터 소스와 같은 입력 및 출력 잡업에 사용됨.


## Stream

- 데이터 소스로부터 데이터를 읽어 들이거나 데이터를 목적지로 보내는 데 사용되는 추상적인 개념
- 데이터가 순차적으로 전송되며, 이러한 특성 때문에 스트림은 연속적인 데이터 흐름을 처리하는데 매우 적합

#### 특징 
- 단방향성: 스트림은 단방향 통신만을 지원합니다. 즉, 입력 스트림은 데이터를 읽는 데만 사용되고, 출력 스트림은 데이터를 쓰는 데만 사용
- 순차적 접근: 스트림을 통해 데이터는 순차적으로 처리됩니다. 한 번에 하나의 데이터 단위(바이트나 문자)를 순서대로 읽거나 씁니다.
- 버퍼링: 일부 스트림은 성능 향상을 위해 내부적으로 버퍼를 사용합니다. 이를 통해 데이터를 일정량 모은 다음에 한 번에 처리할 수 있어 입출력 효율을 높일 수 있습니다.

#### 바이트 기반 스트림
- 데이터를 8비트 바이트 단위로 처리
- 주로 이미지, 오디오 파일 등 바이너리 데이터를 다룰 때 사용

InputStream / OutputStream: 모든 바이트 기반 입력 스트림과 출력 스트림의 상위 클래스입니다.
FileInputStream / FileOutputStream: 파일에서 데이터를 읽거나 파일에 데이터를 쓰는 데 사용됩니다.
BufferedInputStream / BufferedOutputStream: 버퍼링을 통해 입출력 효율을 높일 수 있습니다.
DataInputStream / DataOutputStream: 기본 타입 데이터(int, float 등)를 바이트 스트림으로 처리할 수 있습니다.

## InputStream

### 개념
- 바이트 기반 입력 스트림의 모든 클래스의 슈퍼 클래스
- 바이너리 데이터(이미지, 비디오 파일 등)와 텍스트 데이터 모두를 읽는 데 사용
- 데이터를 바이트 단위로 읽습니다. 즉, 읽어들인 데이터는 바이트 배열로 처리되거나 바이트 단위로 처리

## InputStreamReader
- 텍스트 데이터를 처리해야 한다면 InputStreamReader를 사용하고, 바이너리 데이터를 처리해야 한다면 InputStream을 직접 사용

## 문자 기반 스트림
- 데이터를 문자 단위로 처리
- 주로 텍스트 데이터를 처리할 때 사용되며, 문자 인코딩을 자동으로 처리할 수 있어 다양한 텍스트 형식과 호환

Reader / Writer: 모든 문자 기반 입력 스트림과 출력 스트림의 상위 클래스입니다.
FileReader / FileWriter: 텍스트 파일에서 데이터를 읽거나 파일에 텍스트 데이터를 쓰는 데 사용됩니다.
BufferedReader / BufferedWriter: 버퍼링을 통해 입출력 효율을 높이며, 텍스트 데이터를 쉽게 읽고 쓸 수 있습니다.
PrintWriter: 파일이나 스트림에 데이터를 출력할 때 유용한 메서드를 제공합니다.
기타 중요 클래스
RandomAccessFile: 파일에 대한 랜덤 액세스를 가능하게 합니다. 파일의 어느 부분에서나 읽기/쓰기 작업을 할 수 있습니다.
ObjectInputStream / ObjectOutputStream: 객체 단위의 입출력을 가능하게 하는 스트림으로, 객체 직렬화에 사용됩니다.

