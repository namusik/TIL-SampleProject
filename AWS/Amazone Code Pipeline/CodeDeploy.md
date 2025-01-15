# CodeDeploy

다양한 컴퓨팅 환경(예: Amazon EC2, Lambda, 온프레미스 서버 등)에 애플리케이션을 자동으로 배포하고 업데이트할 수 있도록 지원

## AWS CodeDeploy의 주요 특징

1.	자동화된 배포: 배포 프로세스를 자동화하여 일관되고 신속한 배포가 가능합니다. 이를 통해 수동 배포에서 발생할 수 있는 오류를 줄이고 배포 시간을 단축할 수 있습니다.
2.	배포 유형 지원:
	-	**In-place 배포**: EC2 인스턴스나 온프레미스 서버에서 현재 실행 중인 애플리케이션을 중지하고 새 버전으로 업데이트하는 방식입니다.
	-	**Blue/Green 배포**: 새 버전의 애플리케이션을 기존 버전과 별도로 배포하여, 테스트를 마친 후 트래픽을 점진적으로 새 버전으로 전환하는 방식입니다. 오류가 발생하면 쉽게 이전 버전으로 롤백할 수 있어 안전한 배포가 가능합니다.
3.	다양한 컴퓨팅 환경 지원:
	-	**Amazon EC2**: EC2 인스턴스에서 실행되는 애플리케이션을 배포할 수 있습니다.
	-	AWS Lambda: 서버리스 환경의 Lambda 함수 배포도 지원합니다.
	-	온프레미스 서버: 자체 데이터센터에서 운영하는 서버에도 배포가 가능하며, 하이브리드 클라우드 환경에서도 사용할 수 있습니다.
4.	**배포 수명주기 훅(Hook)**: CodeDeploy는 배포 단계별로 특정 작업을 실행할 수 있도록 **AppSpec 파일을 통해 배포 수명주기 훅을 제공**합니다. 각 단계에서 스크립트나 커맨드를 실행하여 배포 전후에 필요한 작업을 수행할 수 있습니다.
5.	자동 롤백: 배포 중 문제가 발생하면 자동으로 이전 상태로 롤백할 수 있어, 애플리케이션의 안정성을 유지할 수 있습니다. 이 기능은 배포 실패 시 가용성에 미치는 영향을 최소화합니다.
6.	모니터링 및 로깅: 배포 상태를 실시간으로 모니터링하고, Amazon CloudWatch와 통합하여 배포 성능과 오류를 추적할 수 있습니다. 배포 로그는 CloudWatch Logs에 저장하여 분석에 활용할 수 있습니다.

## 배포 설정 파일(AppSpec 파일)

CodeDeploy는 **appspec.yaml**이라는 설정 파일을 사용하여 배포 단계를 정의합니다. 이 파일은 애플리케이션 배포 위치, 배포 후 실행할 스크립트 등 여러 세부 사항을 설정합니다. 주요 항목은 다음과 같습니다:
-	**version**: AppSpec 파일의 버전을 지정합니다.
-	**os**: 배포 대상 운영체제(Linux 또는 Windows)를 지정합니다.
-	**files**: 배포할 파일의 경로와 대상 경로를 지정합니다.
-	**hooks**: 배포 수명주기의 각 단계에서 실행할 명령어 또는 스크립트를 정의합니다. 예를 들어, BeforeInstall, AfterInstall, ApplicationStart, ValidateService 단계가 있습니다.

## AWS CodeDeploy의 배포 흐름 예시

1.	배포 시작: CodeDeploy는 지정된 컴퓨팅 환경에서 애플리케이션 배포를 시작합니다.
2.	애플리케이션 업데이트: AppSpec 파일에 정의된 대로 애플리케이션 파일과 애셋을 대상 인스턴스에 배포합니다.
3.	수명주기 훅 실행: 배포 전후로 필요한 커맨드나 스크립트를 실행하여 애플리케이션을 설정합니다.
4.	모니터링 및 확인: CloudWatch와 통합하여 배포 상태를 모니터링하고, 오류 발생 시 자동으로 롤백할 수 있습니다.


## appspec.yml

https://docs.aws.amazon.com/ko_kr/codedeploy/latest/userguide/reference-appspec-file-example.html