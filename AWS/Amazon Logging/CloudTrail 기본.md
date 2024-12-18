# CloudTrail

AWS 계정에서 이루어지는 API 호출 및 이벤트를 추적하고 기록하는 서비스
누가, 언제, 어떤 리소스에 대해 어떤 작업을 수행했는지 확인

## 기능 

1.	이벤트 기록: AWS 계정에서 발생하는 모든 API 호출을 기록합니다. API 호출은 AWS Management Console, SDK, CLI 등을 통해 발생할 수 있으며, EC2 인스턴스 생성, S3 버킷 접근 등과 같은 활동이 포함됩니다.
2.	지속적인 모니터링: CloudTrail은 지속적으로 이벤트를 캡처하여 S3 버킷에 저장하고, 선택적으로 Amazon CloudWatch Logs로 전송하여 실시간 모니터링을 할 수 있습니다. 이를 통해 이상 징후나 보안 위협을 신속히 감지할 수 있습니다.
3.	관리 이벤트 및 데이터 이벤트: 관리 이벤트는 계정 리소스 관리에 관한 기록을 포함하며, 데이터 이벤트는 S3나 Lambda와 같은 특정 서비스의 개별 객체나 함수 수준의 호출을 기록합니다. 데이터 이벤트는 주로 중요한 리소스에 대한 접근 기록을 남기기 위해 사용됩니다.
4.	다양한 로그 분석: 기록된 로그는 AWS CloudTrail 콘솔, Athena, Amazon CloudWatch를 통해 조회 및 분석할 수 있습니다. 이를 통해 특정 이벤트에 대한 자세한 정보를 확인하거나, 이벤트 패턴 분석 등을 수행할 수 있습니다.
5.	멀티 리전 및 멀티 계정: CloudTrail은 멀티 리전 및 멀티 계정을 지원하여 AWS 환경 전반에 대한 일관된 모니터링이 가능합니다. 여러 계정에 대한 이벤트를 통합할 수 있어 관리가 편리해집니다.
6.	보안 및 감사: CloudTrail은 중요한 보안 정보를 제공합니다. 예를 들어, 계정 내에서 권한이 없는 사용자가 리소스에 접근하려 할 경우, 이 기록을 통해 보안 침해 시도를 추적할 수 있습니다.

## 저장 디렉토리

1. cloudtrail 디렉토리

-	내용: 이 디렉토리는 **AWS CloudTrail 이벤트 로그 파일이 저장되는 위치**입니다. 여기에는 AWS에서 발생한 API 호출과 관련된 세부 정보가 기록됩니다.
-	로그 파일 구성: 각 로그 파일은 **JSON 형식**으로 기록되며, 특정 시간대에 발생한 API 호출 이벤트가 포함됩니다. 파일에는 API 호출이 발생한 시각, 실행 주체(사용자, 서비스 등), 리소스 정보, 액션 유형 등이 상세히 기록됩니다.
-	주요 목적: CloudTrail 로그 파일 자체가 API 활동에 대한 기록이므로, 추후 보안 감사나 문제 해결 시 사용됩니다.

2. cloudtrail-digest 디렉토리

-	내용: 이 디렉토리는 CloudTrail **로그 파일의 무결성을 검증하는 데 사용되는 다이제스트 파일(digest file)을 저장**합니다. 다이제스트 파일에는 **CloudTrail 로그의 해시값이 포함**되어 있으며, 특정 시간 동안 기록된 로그 파일이 변조되지 않았음을 보장하기 위한 용도로 사용됩니다.
-	로그 파일 구성: 다이제스트 파일에는 SHA-256 해시가 포함되며, 각 로그 파일에 대한 해시값이 나열되어 있습니다. 이를 통해 로그 파일이 수정되거나 삭제되지 않았음을 확인할 수 있습니다.
-	주요 목적: cloudtrail-digest 디렉토리는 보안 및 규정 준수 요구 사항을 충족하기 위한 검증 자료를 제공합니다. 로그 무결성 검증을 통해 감사 또는 조사 시점에서 해당 로그 파일이 안전하게 보관되었음을 보장합니다.