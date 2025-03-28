# ECR 

완전 관리형 컨테이너 이미지 레지스트리 서비스

개발자가 도커 이미지를 안전하게 저장, 관리 및 배포할 수 있도록 지원

## 주요 특징

1.	완전 관리형 서비스:
-	ECR은 인프라 관리 없이 컨테이너 이미지를 푸시(push)하고 관리할 수 있는 기능을 제공합니다.
-	사용자 환경에 맞게 자동으로 확장되며, 설정이나 유지보수 부담이 없습니다.
2.	보안:
-	저장된 모든 이미지는 자동으로 암호화되어 안전하게 보호됩니다.
-	AWS Identity and Access Management **(IAM)와의 통합을 통해 세밀한 액세스 제어가 가능**합니다.
3.	통합:
-	Amazon ECS, Amazon EKS, AWS Fargate, Docker CLI 등과 쉽게 통합되어 컨테이너 기반 애플리케이션 배포가 간편해집니다.
-	DevOps 도구와의 원활한 연동으로 CI/CD 워크플로우에 쉽게 포함될 수 있습니다.
4.	고가용성과 내구성:
-	ECR은 Amazon S3를 기반으로 하여 높은 내구성과 가용성을 제공합니다.
-	저장된 데이터는 99.999999999%의 내구성을 보장합니다.
5.	이미지 스캔:
-	보안 취약점을 사전에 파악할 수 있도록 컨테이너 이미지를 스캔하는 기능을 제공합니다.
-	사용자는 이미지의 보안 상태를 확인하고 필요한 조치를 취할 수 있습니다.
6.	수명 주기 관리:
-	이미지 라이프사이클 정책을 설정하여 오래된 이미지나 사용하지 않는 이미지를 자동으로 삭제함으로써 스토리지 비용을 절감할 수 있습니다.

### 사용 사례

-	애플리케이션 배포: 컨테이너 이미지를 안전하게 저장하고 배포하여 애플리케이션을 빠르고 효율적으로 배포할 수 있습니다.
-	CI/CD 파이프라인: DevOps 워크플로우에 통합하여 지속적인 통합 및 배포 프로세스를 자동화할 수 있습니다.
-	멀티리전 배포: 여러 AWS 리전에서 애플리케이션을 실행하는 경우, ECR을 활용해 이미지를 쉽게 동기화할 수 있습니다.


### 이미지 태그 변경 가능성

(ECR)에서 이미지 태그의 변경 가능 여부를 설정하는 옵션

1. **Mutable (변경 가능):**
-	설명: 동일한 이미지 태그를 여러 번 푸시할 수 있습니다. 즉, **기존 태그에 새로운 이미지를 덮어쓸 수 있습니다.**
- 장점:
  -	빠르게 이미지를 업데이트해야 할 때 유용합니다.
  -	태그 이름을 고정하고 싶지만, 이미지를 자주 업데이트해야 하는 경우 적합합니다.
- 단점:
  -	 특정 태그에 대해 어느 시점에 어떤 이미지가 포함되어 있는지 추적하기 어렵습니다.
  -	 **실수로 기존 이미지를 덮어쓸 위험**이 있습니다.
2.	**Immutable (불변):**
-	설명: 이미지가 한 번 푸시되면, 해당 태그는 변경할 수 없습니다. **동일한 태그 이름으로 다시 푸시하려고 하면 오류가 발생**합니다.
- 장점:
  -	태그가 특정 이미지를 항상 참조하므로, 이미지를 추적하기 쉽습니다.
  -	배포 안정성을 높이고, 실수로 이미지를 덮어쓰는 일을 방지할 수 있습니다.
- 단점:
  -	이미지 업데이트 시 새로운 태그를 사용해야 하므로, 관리할 태그가 많아질 수 있습니다.

권장 사항

- Immutable 설정을 권장하는 경우:
  -	배포 프로세스의 안정성이 중요하거나, 보안 및 규정 준수를 엄격히 관리해야 할 때.
  -	이미지 태그의 변경 내역을 명확히 관리하고 싶은 경우.
- Mutable 설정을 권장하는 경우:
  -	개발 및 테스트 환경에서 빠른 반복 작업이 필요한 경우.
  -	동일한 태그를 사용하여 이미지를 자주 갱신해야 하는 경우.

### 암호화 설정

저장된 컨테이너 이미지를 보호하는 중요한 기능

AES-256 (기본 암호화)

- 설명:
  -	AES-256은 **Advanced Encryption Standard (AES)** 를 사용하여 데이터를 256비트 키로 암호화합니다.
  -	AWS에서 제공하는 기본 암호화 방식으로, 자동으로 활성화되며 추가 설정이 필요하지 않습니다.
- 특징:
  -	성능 최적화: AWS 관리형 키로 빠르게 암호화와 복호화를 수행합니다.
  -	비용 효율적: 추가 비용 없이 사용할 수 있는 기본 암호화 방식입니다.
  -	자동 관리: 키 관리를 AWS가 처리하므로 사용자는 별도로 관리할 필요가 없습니다.

AWS KMS (Key Management Service)

- 설명:
  -	AWS KMS는 사용자가 정의한 고객 관리형 키(Customer Managed Keys) 또는 **AWS 관리형 키(AWS Managed Keys)** 를 사용하여 데이터를 암호화합니다.
  -	사용자는 키 생성, 관리, 회전, 접근 제어를 직접 설정할 수 있습니다.
- 특징:
  -	세부적인 키 관리: 사용자 정의 키를 생성하고, 키의 사용과 액세스를 제어할 수 있습니다.
  -	고급 보안: 키에 대한 액세스 로그와 정책을 통해 보안성을 강화할 수 있습니다.
  -	다양한 통합: KMS 키는 다른 AWS 서비스와 통합하여 일관된 보안 정책을 적용할 수 있습니다.
  -	비용 발생: 고객 관리형 키 사용 시 추가 비용이 발생할 수 있습니다.

어떤 것을 선택해야 할까?

- AES-256:
  -	기본 보안이 필요하고, 추가 설정 없이 간편하게 암호화를 사용하고자 할 때 적합합니다.
- AWS KMS:
  -	규제 준수나 보안 요구사항이 높고, 암호화 키의 세부 관리와 제어가 필요한 경우에 적합합니다.


## 명령어 

```sh
# ec2에서 ecr에 로그인
aws ecr get-login-password --region ap-northeast-2 | docker login --username AWS --password-stdin <ECR 주소>

# 도커 build
docker build -t <이름> .

# 이미지에 태그를 지정
docker tag <현재 이름:태그> <ECR 주소/이름:태그>

# 새로 생성한 AWS 리포지토리로 푸시
docker push <ECR 주소/이름:태그>
```