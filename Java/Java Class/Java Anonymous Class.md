# Anonymous Class

## 정의
이름이 없는 내부 클래스

전혀 새로운 클래스를 익명으로 사용하는 것이 아니라, 이미 정의되어 있는 클래스의 멤버들을 재정의 하여 사용할 필요가 있을때 그리고 그것이 일회성으로 이용될때 사용하는 기법

## 사용방법
~~~java
new ParentClass(...){
    .....
}
~~~

## 특징
클래스 정의와 동시에 객체를 생성.

익명클래스는 이름이 없기 때문에 생성자를 가질 수 없고 사실 필요도 없다.


## 참고
https://www.baeldung.com/java-anonymous-classes