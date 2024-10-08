# VisualVM

- Java 애플리케이션의 성능을 분석하고 모니터링할 수 있는 도구

## Profiler

### CPU Profiler

#### 목적
- 애플리케이션이 CPU를 어떻게 사용하는지 분석
- 어떤 메소드가 CPU 시간을 가장 많이 소모하는지, CPU 사용률이 높은 코드 경로를 식별
- 성능 병목 현상을 찾고 최적화 포인트를 결정하는 데 매우 유용

#### 결과
- Call Tree
  - 호출 트리
  - 특정 메소드가 호출된 경로와 그 메소드가 전체 실행 시간 중 차지하는 비율을 보여줌.
- Hot Spots
  - 가장 CPU 시간을 많이 소비하는 메소드 리스트

- Total Time
  - 특정 메소드와 하위 메소드들이 실행되는데 소요된 전체 시간
- Totla Time (CPU)
  - 메소드가 실제로 CPU에서 실행된 순수 시간
  - I/O 작업 등으로 대기하는 시간을 제외한, CPU에서 해당 메소드가 실행된 시간을 측정
- Self Time
  - 특정 메소드의 내부에서만 소비되 시간
  - 메소드 자체가 얼마나 많은 시간을 소비했는지 정보
- Self Time (CPU)
  - 해당 메소드 내부가 CPU에서 직접적으로 소비한 시간
- Invocations
  - 특정 메소드가 호출된 횟수
  - 
#### 최적화팁
- CPU 사용률일 높은 메소드를 최적화하거나 불필요한 반복 호출을 줄이기