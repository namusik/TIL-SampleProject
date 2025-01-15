# EC2 AutoScaling

## AMI (Amazon Machine Image)

![ec2이미지](./../../images/AWS/ec2이미지버튼.png)

### AMI 구성 요소

AMI는 다음과 같은 구성 요소를 포함합니다:

1.	루트 볼륨 템플릿: 인스턴스에 포함된 운영 체제와 응용 프로그램이 설치된 루트 파일 시스템의 스냅샷을 기반으로 합니다. 일반적으로 Amazon Linux, Ubuntu, Windows 등의 운영 체제가 포함됩니다.
2.	인스턴스 스토리지 구성: 인스턴스가 실행되는 동안 사용할 추가 스토리지 볼륨에 대한 설정을 정의합니다.
3.	퍼미션 설정: AMI를 사용할 수 있는 AWS 계정 권한을 정의할 수 있습니다. 퍼블릭 AMI로 설정하면 다른 사용자가 이를 사용할 수 있습니다.
4.	블록 디바이스 매핑: AMI가 시작될 때 연결될 EBS 볼륨을 정의합니다.

### AMI 유형

-	Amazon Linux AMI: AWS에서 제공하는 기본적인 Linux 기반 AMI로, Amazon Linux 및 Amazon Linux 2 AMI가 있습니다.
-	AWS Marketplace AMI: 서드파티에서 제공하는 AMI로, 특정 소프트웨어가 미리 설치된 AMI를 구입해서 사용할 수 있습니다.
-	커뮤니티 AMI: AWS 사용자들이 공유하는 AMI입니다.
-	사용자 정의 AMI: 사용자가 특정 환경을 설정한 후 이를 AMI로 저장해 여러 인스턴스에 동일하게 적용할 수 있습니다.

### AMI로 생성된 인스턴스의 활용

생성된 AMI를 사용하면 다음과 같은 유용한 기능을 사용할 수 있습니다:

-	복제 및 스케일링:
	-	동일한 설정을 가진 인스턴스를 여러 대 생성할 수 있어, 애플리케이션의 확장성을 쉽게 확보할 수 있습니다.
-	백업:
	-	중요한 데이터나 설정을 보존하기 위해 정기적으로 AMI를 생성하여 백업할 수 있습니다. 문제가 발생하면 해당 AMI를 사용하여 신속히 복구할 수 있습니다.
-	환경 유지 및 테스트:
	-	개발 및 테스트 환경에서 사용한 설정을 그대로 유지하며 새로운 인스턴스를 배포하거나 QA 테스트용 인스턴스를 쉽게 생성할 수 있습니다.
-	다른 리전 배포:
	-	생성한 AMI는 다른 AWS 리전으로 복사할 수 있어, 전 세계에 걸쳐 동일한 인프라 환경을 빠르게 구축할 수 있습니다.

### 주의 사항

-	EBS 스냅샷 비용:
	-	생성된 AMI는 EBS 스냅샷을 사용해 저장되므로, 저장된 스냅샷 용량에 따라 비용이 발생합니다.
-	AMI 업데이트 필요:
	-	인스턴스가 사용 중 업데이트나 변경 사항이 있으면, AMI를 새로 생성하여 최신 상태를 반영해야 합니다.

## 시작 템플릿 

AWS에서 EC2 인스턴스를 생성할 때 필요한 구성을 사전 정의해 두는 일종의 템플릿
인스턴스 설정을 매번 반복해서 지정할 필요 없이, 동일한 설정을 바탕으로 인스턴스를 신속하게 배포가능


## AutoScaling

1.	Auto Scaling Group 정의:
-	ASG는 EC2 인스턴스가 포함된 그룹입니다. 설정한 기준에 따라 인스턴스 수를 조정할 수 있습니다.
-	그룹은 기본적으로 최소 인스턴스 수, 최대 인스턴스 수, 원하는 인스턴스 수를 지정하여, ASG가 항상 특정 범위 내에서 인스턴스 수를 유지하도록 설정합니다.
2.	확장 및 축소 정책:
-	스케일 아웃(확장): 트래픽 증가 또는 특정 메트릭 임계값을 초과할 경우 인스턴스 수를 늘립니다.
-	스케일 인(축소): 트래픽 감소 또는 특정 메트릭이 낮아질 때 인스턴스 수를 줄여서 비용을 절감합니다.
3.	자동 복구:
-	Auto Scaling Group은 특정 인스턴스가 장애를 일으키거나 종료된 경우, 자동으로 새로운 인스턴스를 생성하여 그룹을 원하는 상태로 복구할 수 있습니다. 이를 통해 인프라의 가용성을 높일 수 있습니다.
4.	헬스 체크:
-	인스턴스의 상태를 지속적으로 모니터링하고 문제가 발생한 인스턴스를 감지하여 대체하는 기능입니다. ELB(Elastic Load Balancer) 헬스 체크 또는 EC2 인스턴스 상태 헬스 체크를 사용할 수 있습니다.
5.	Launch Template 및 Launch Configuration:
-	ASG는 인스턴스를 생성할 때 시작 템플릿(Launch Template) 또는 **시작 구성(Launch Configuration)**을 사용하여 인스턴스 설정(AMI, 인스턴스 유형, 보안 그룹 등)을 정의합니다. 시작 템플릿을 사용하면 인스턴스의 설정을 관리하기가 쉬워지고, 다양한 설정 버전을 지원하여 유연한 관리를 가능하게 합니다.
6.	스케줄링된 스케일링:
-	특정 시간에 따라 인스턴스 수를 조정하는 스케줄링 설정도 가능합니다. 예를 들어, 매일 오전 9시에 인스턴스 수를 늘리고 오후 6시에 줄이는 방식으로 트래픽 패턴에 맞춰 효율적으로 리소스를 활용할 수 있습니다.
7.	예측 확장(Predictive Scaling):
-	기계 학습을 활용해 과거 트래픽 데이터를 바탕으로 향후 트래픽 패턴을 예측하고 자동으로 인스턴스 수를 조정하는 기능입니다. 예상되는 수요에 대비해 사전에 확장을 적용해, 예기치 못한 부하에도 신속하게 대응할 수 있습니다.

주요 구성 요소

-	최소, 최대, 원하는 용량:
-	최소 용량: 그룹 내에 항상 유지할 최소 인스턴스 수
-	최대 용량: 그룹 내에 허용되는 최대 인스턴스 수
-	원하는 용량: 현재 목표로 하는 인스턴스 수. ASG는 이 목표 인스턴스 수를 유지하려고 합니다.
-	ELB (Elastic Load Balancer)와의 통합:
-	ELB와 통합하여 확장되는 인스턴스들이 자동으로 로드 밸런서에 등록되며, 이를 통해 로드 밸런싱이 자동화됩니다. ELB 헬스 체크를 통해 비정상 인스턴스를 감지하고 대체할 수 있습니다.

사용 예시

1.	웹 애플리케이션 확장:
-	방문자가 몰리는 시간대에는 자동으로 인스턴스를 확장하고, 사용자 수가 줄어드는 야간에는 인스턴스를 축소하여 서버 자원을 효율적으로 사용합니다.
2.	비정상 인스턴스 자동 대체:
-	트래픽이 몰릴 때 인스턴스가 실패하더라도, ASG가 자동으로 대체 인스턴스를 생성하여 서비스 가용성을 보장할 수 있습니다.
3.	이벤트 기반 확장:
-	특정 이벤트, 예를 들어 마케팅 캠페인, 신규 서비스 출시, 시즌 행사 등을 위해 일시적으로 확장이 필요한 경우 ASG가 이를 자동으로 처리하도록 설정할 수 있습니다.

장점 및 고려 사항

-	장점: 인프라 자원을 유연하게 활용할 수 있고, 장애 발생 시 자동 복구로 인프라 가용성을 보장하여 운영 부담을 줄일 수 있습니다.
-	고려 사항: 확장과 축소가 발생할 때 부하 분산을 위해 인스턴스의 시작 및 종료 시간이 필요하므로, 신속한 반응이 필요한 서비스에서는 이러한 대기 시간을 고려한 설정이 필요합니다.

### 상태 확인 유예 기간

새로 시작된 인스턴스가 “정상 상태”로 간주되기까지 대기하는 시간을 설정하는 옵션

주요 기능과 필요성

1.	대기 시간 제공:
-	새로운 인스턴스가 부팅되고 애플리케이션이 완전히 시작되려면 시간이 필요합니다. 이 대기 시간이 설정되면 Auto Scaling Group은 인스턴스가 준비될 때까지 상태 확인을 유예합니다.
2.	불필요한 인스턴스 교체 방지:
-	유예 기간 동안은 상태 확인 결과에 관계없이 인스턴스를 정상으로 간주합니다. 이를 통해 초기화 중에 발생할 수 있는 임시적인 상태 오류로 인해 인스턴스가 불필요하게 교체되는 상황을 방지할 수 있습니다.
3.	인스턴스 상태 확인과 상호작용:
-	상태 확인 유예 기간이 지나면 인스턴스의 상태가 실제로 확인되기 시작합니다. 이후 ELB(Elastic Load Balancer)나 EC2 상태 확인을 기반으로 인스턴스가 정상 상태인지 감지하고, 비정상 상태로 판별되면 인스턴스를 교체하게 됩니다.

사용 예시

-	웹 서버 초기화: 예를 들어, 웹 서버가 시작된 후 완전히 준비되기까지 3분이 걸린다면, 상태 확인 유예 기간을 최소 180초 이상으로 설정해야 합니다. 이를 통해 서버가 준비 중일 때 상태 확인에 실패해 교체되는 불필요한 과정을 방지할 수 있습니다.
-	데이터베이스 연결 지연: 만약 애플리케이션 서버가 부팅 후 데이터베이스와의 연결을 설정하는 데 추가 시간이 필요하다면, 그 시간을 고려해 유예 기간을 늘려 줄 수 있습니다.

최적의 유예 기간 설정 고려 사항

1.	인스턴스 초기화 시간: 인스턴스가 부팅되고 **애플리케이션이 완전히 시작되기까지 걸리는 시간을 고려해 유예 기간을 설정**합니다.
2.	서비스 가용성 요구사항: **유예 기간이 너무 길면 비정상 인스턴스가 교체되는 데까지 시간이 더 소요되므로, 가능한 한 짧지만 안정적인 기간을 설정**하는 것이 좋습니다.
3.	애플리케이션 부하와 성능: **트래픽이 많은 서비스라면 유예 기간을 짧게 설정해 빠르게 상태 확인을 시작**하는 것이 도움이 될 수 있습니다.

## 동적 크기 조정 정책 

트래픽이나 리소스 사용량에 따라 EC2 인스턴스의 개수를 자동으로 조정하는 방법
동적 크기 조정은 실시간 모니터링 데이터를 바탕으로 필요한 리소스만큼 인스턴스를 추가(스케일 아웃)하거나 제거(스케일 인)하여 효율적인 리소스 관리를 가능


### 동적 크기 조정 정책 유형

AWS는 동적 크기 조정 정책을 몇 가지 유형으로 제공합니다:

1.	단계 크기 조정 (Step Scaling Policy):
-	**CloudWatch** 경보를 기반으로 **특정 임계값이 초과될 때** 미리 정의된 크기만큼 인스턴스를 조정합니다.
-	예를 들어, CPU 사용률이 **80%를 초과하면 2개의 인스턴스를 추가**하거나, **CPU 사용률이 20% 이하로 떨어지면 1개의 인스턴스를 줄이는 방식**으로 동작합니다.
-	임계값의 수준에 따라 여러 단계의 조정 동작을 설정할 수 있어, 트래픽 변화에 유연하게 대응할 수 있습니다.
2.	단순 크기 조정(Simple Scaling Policy):
-	**CloudWatch** 경보를 설정해 **특정 조건을 충족하면 단일 작업을 수행하도록 설정**할 수 있습니다. 예를 들어, 트래픽이 증가하면 일정 수의 인스턴스를 추가하는 방식입니다.
-	설정이 간단하지만 조정이 시작된 후 임계값 조건이 다시 충족될 때까지는 대기 시간(Cooldown Period)을 둬야 하므로, 대규모 트래픽 변화에 대한 유연성은 다소 떨어질 수 있습니다.
3. 대상 추적 크기 조정(Target Tracking Scaling Policy):
-	지표의 목표값을 설정하고, 이를 기준으로 ASG가 자동으로 인스턴스를 조정하는 방식입니다.
-	예를 들어, **평균 CPU 사용률을 50%로 유지하도록 설정**하면, ASG가 이를 목표로 설정해 CPU 사용률이 높아지면 인스턴스를 추가하고 낮아지면 인스턴스를 줄입니다.
-	목표 추적 기반 조정은 리소스 사용률을 일정하게 유지하고 싶을 때 유용하며, 필요에 따라 인스턴스 수를 조정해 목표 지표를 맞추는 자동화된 방식으로 자주 사용됩니다.

### 인스턴스 워밍업
새로 추가된 인스턴스가 트래픽을 안정적으로 처리할 수 있는 상태에 도달하기까지 대기하는 시간을 설정하는 옵션

인스턴스 워밍업의 주요 기능

1.	초기화 시간 설정:
-	인스턴스 워밍업 시간 동안, 새로 추가된 인스턴스는 아직 완전히 준비되지 않은 상태로 간주됩니다. 이 시간 동안에는 Auto Scaling Group이 해당 인스턴스를 별도로 관리하여 추가적인 조정 작업에 반영하지 않습니다.
2.	조정 정책과의 상호작용:
-	워밍업 시간이 설정되면, 새 인스턴스가 트래픽을 처리할 준비가 될 때까지 대기하여 **불필요한 스케일 아웃(확장)**을 방지합니다.
-	예를 들어, CPU 사용률이 높아 인스턴스를 추가했는데도 초기화 시간이 필요한 경우, 워밍업 동안 추가 조정이 발생하지 않도록 하여 불필요한 비용 발생을 방지할 수 있습니다.
3.	효율적인 자원 사용 및 비용 절감:
-	워밍업 설정을 통해, ASG는 새 인스턴스가 트래픽을 처리할 준비가 완료되기 전까지 다른 조정 동작을 기다리게 합니다. 이를 통해 과도한 스케일 아웃이 발생하지 않도록 하여 비용을 절감할 수 있습니다.

인스턴스 워밍업 설정 예시

-	예를 들어, 애플리케이션 서버가 인스턴스 시작 후 약 3분간 초기화 과정을 거친다고 가정하면, 인스턴스 워밍업을 180초로 설정할 수 있습니다. 이렇게 하면 3분이 지나기 전에는 추가 확장 조정이 발생하지 않으므로, 인스턴스가 준비되는 동안 다른 조정이 필요 없는 상황을 유지하게 됩니다.

인스턴스 워밍업 설정의 중요성

-	장애 방지: 초기화 중에 과도하게 확장되지 않도록 하여, 자원 낭비를 줄이면서 시스템 장애를 방지할 수 있습니다.
-	조정 간섭 최소화: 초기화 중인 인스턴스가 추가 스케일링 대상에 포함되지 않도록 관리하여, 조정이 반복되는 현상을 방지할 수 있습니다.

고려 사항

-	적절한 워밍업 시간 설정: 인스턴스가 완전히 초기화되고, 애플리케이션이 정상적으로 트래픽을 처리할 수 있는 시간을 정확히 설정하는 것이 중요합니다. 워밍업 시간이 너무 짧으면 조정이 빈번해질 수 있으며, 너무 길면 리소스 부족 현상이 발생할 수 있습니다.
-	스케일 아웃 정책의 조정: 워밍업 설정과 스케일 아웃(확장) 정책이 잘 맞아야 최적의 성능과 비용 효율을 달성할 수 있습니다.


## 종료 정책 

1.	기본 종료 정책(Default Termination Policy):
-	기본 종료 정책을 사용하면 ASG는 다음과 같은 순서로 인스턴스를 종료할 인스턴스를 결정합니다:
1.	다중 가용 영역(Multi-AZ) 사용 시, 인스턴스가 포함된 가용 영역(AZ) 중 **인스턴스가 더 많은 AZ를 우선**하여 선택합니다. 이는 인스턴스의 균형을 유지하고자 하는 목적입니다.
2.	동일한 가용 영역 내에서 구형(Oldest) Launch Configuration 또는 Launch Template을 사용하는 인스턴스를 먼저 종료합니다. 이는 최신 설정으로만 인스턴스가 남도록 해 주며, 구형 설정이 없으면 최신 설정의 인스턴스를 종료합니다.
3.	만약 같은 설정을 사용하고 있다면, **구형(Oldest) 인스턴스를 먼저 종료**합니다.
4.	위의 기준이 동일할 경우 랜덤하게 인스턴스를 선택하여 종료합니다.
이러한 기본 정책은 ASG 내 인스턴스 균형을 유지하고, 비용 절감을 극대화하면서 최신 환경을 유지하도록 설정되어 있습니다.

### 기본 휴지 기간 

인스턴스가 추가되거나 삭제된 후, 새로운 스케일링 동작을 수행하기 전에 기다리는 시간

기본 휴지 기간의 동작 방식

-	기본값: Auto Scaling Group의 기본 휴지 기간은 **300초(5분)**로 설정되어 있습니다. 이 시간은 상황에 따라 짧게 또는 길게 조정할 수 있습니다.
-	작동 방식: 인스턴스 추가/제거 후 기본 휴지 기간이 시작되면, 해당 시간이 지나기 전까지 Auto Scaling Group은 새로운 스케일링 작업(스케일 인/아웃)을 수행하지 않습니다.

대상 추적 크기 조정(Target Tracking Scaling)과의 관계

-	인스턴스 워밍업 시간: 대상 추적 크기 조정 정책을 사용할 때는 기본 휴지 기간 대신 인스턴스 워밍업 시간을 적용하는 것이 일반적입니다.
-	인스턴스가 트래픽을 처리할 준비가 될 때까지의 시간이 워밍업 시간으로 설정되어, 이 시간이 지나야 다음 스케일링 작업이 발생합니다.
-	이때 인스턴스 워밍업 시간이 기본 휴지 기간을 대체하여, 인스턴스가 준비될 때까지 스케일링 동작이 일어나지 않도록 보장합니다.

기본 휴지 기간 설정 시 고려사항

1.	인스턴스 초기화 시간: 애플리케이션이 부팅되어 트래픽을 처리할 준비가 되는 시간을 고려하여 휴지 기간을 설정해야 합니다. 너무 짧게 설정하면 초기화 중 추가 스케일링이 발생할 수 있습니다.
2.	트래픽 패턴: 트래픽이 급격하게 변동하는 경우 기본 휴지 기간을 짧게 설정하여 빠르게 반응할 수 있도록 조정할 수 있지만, 이로 인해 불필요한 스케일링 동작이 발생할 수 있어 신중하게 설정해야 합니다.
3.	다양한 스케일링 정책 적용 시: 대상 추적 기반 스케일링처럼 인스턴스 워밍업 시간이 적용되는 경우, 기본 휴지 기간보다 워밍업 시간을 우선 설정하여 인스턴스 준비 상태를 반영하는 것이 좋습니다.

인스턴스가 3개로 스케일 아웃된 후 CPU 사용률이 1%로 떨어지더라도 300초가 지나야만 Auto Scaling Group(ASG)이 스케일 인(인스턴스 축소)을 시작

### 차이 

동적 크기 조정 정책을 사용하는 경우, 기본 휴지 기간 대신 인스턴스 워밍업 시간이 우선 적용됨

대상 추적 크기 조정에서 인스턴스 워밍업 시간을 설정했다면, 워밍업 시간이 300초보다 짧게 설정된 경우 휴지 기간이 더 이상 적용되지 않으며, 워밍업 시간이 지나면 CPU 사용률이 낮아질 때 즉시 스케일 인이 가능

기본 휴지 기간(300초)은 무시되며, 인스턴스 워밍업 시간(10초)이 우선 적용되므로 스케일 아웃 후 10초 뒤부터 CloudWatch가 CPU 사용률이 낮아진 것을 감지하여 스케일 인을 시작

-	기본 휴지 기간: 스케일 인/아웃 후 일정 대기 시간을 두어 추가 스케일링을 제한하여 안정성을 높임.
-	상태 확인 유예 기간: 초기화 중인 인스턴스의 상태 확인을 지연하여 불필요한 교체를 방지함.
-	인스턴스 워밍업: 추가된 인스턴스가 준비되기 전 추가 스케일 아웃 동작을 제한하여 비용 절감 및 효율적 스케일링을 보장함.

## 참고

https://www.youtube.com/watch?v=N8TB_6AbaM4