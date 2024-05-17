# 자바 제네릭

## 특징 
### 컴파일 시점에만 존재하고 런타임에는 소거(Type Erasure)
1. 백워드 호환성 (Backward Compatibility)
제네릭이 자바 5에서 도입되었을 때, 이미 수많은 코드와 라이브러리가 자바로 작성되어 있었습니다. 제네릭의 도입이 기존 코드와의 호환성을 깨뜨리지 않도록, 기존 클래스와 인터페이스가 제네릭이 적용된 코드와 함께 동작할 수 있어야 했습니다. 타입 소거 방식은 제네릭을 사용하는 새 코드가 기존의 비제네릭 코드와 문제 없이 함께 동작할 수 있게 만들어줍니다.
2. 효율성 (Efficiency)
타입 소거를 사용함으로써, 자바 런타임은 타입 파라미터가 없는 것처럼 행동할 수 있습니다. 즉, 제네릭을 사용하더라도 런타임 시에 추가적인 메모리 사용이나 성능 저하가 없습니다. 각 객체 타입마다 별도의 클래스 버전을 유지할 필요가 없기 때문에, 메모리 사용량과 로딩 시간이 최적화됩니다.
3. 간소화된 가상 머신 (Virtual Machine Simplicity)
제네릭 타입 정보가 런타임에 소거되므로, 자바 가상 머신(JVM)은 제네릭 타입에 대해 별도로 처리할 필요가 없습니다. 이는 JVM의 설계와 구현을 단순화시키며, 제네릭 이전에 작성된 코드와의 호환성을 유지하는 데 도움이 됩니다.
4. 부작용
물론 타입 소거에는 몇 가지 단점도 있습니다. 예를 들어, 런타임에 타입 정보가 소거되기 때문에, 특정 타입의 객체만을 처리해야 할 때(예를 들어, 리스트에서 특정 타입의 모든 요소를 처리하는 경우) 컴파일 시간에만 타입 안전성을 보장할 수 있습니다. 런타임에는 이러한 타입 정보를 활용할 수 없어, 타입 캐스팅이 필요한 경우 ClassCastException과 같은 예외 처리를 수행해야 할 수도 있습니다.