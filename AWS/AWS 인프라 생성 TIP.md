# AWS 인프라 생성 TIP

1. VPC
   1. VPC를 만들면 메인 라우팅 테이블이 자동으로 만들어짐
      1. 이 테이블은 모든 서브넷과 자동으로 연결됨
   2. 이 때 CIDR 설계를 잘 해야될듯?
2. 서브넷
   1. 이름지을 때, 가용영역 (a~d)에 따라서 서브넷 이름에도 a~d 적어주면 좋을 듯하다.
   2. private, public, rds 서브넷을 나눠주면 좋음.
3. 인터넷 게이트웨이
4. 라우팅 테이블
   1. 퍼블릭 테이블은 인터넷게이트웨이, 퍼블릿 서브넷 연결
   2. 프라이빗 테이블은 NAT 게이트웨이, 프라이빗 서브넷 연결
5. NAT 게이트웨이
   1. 퍼블릭 서브넷에 있어야 한다.
6. 보안그룹
   1. bastion 인스턴스 보안그룹
      1. SSH - 내 IP
   2. private 인스턴스 보안그룹
      1. SSH - bastion 인스턴스에 적용된 보안그룹을 선택
      2. HTTP/HTTPS alb 보안그룹을 소스로 인바운드 추가
   3. alb 보안그룹
      1. HTTP, HTTPS Anywhere IPv4 inbound 설정 
   4. RDS 보안그룹
      1. bastion 서버 보안그룹, private app ec2 보안그룹 MYSQL/Aurora 유형 3306
7. 인스턴스
   1. bastion 인스턴스 
      1. public subnet에
      2. 탄력적 IP 할당 필요
      3. public 보안그룹 설정
   2. private app 인스턴스
      1. private subnet에
      2. private 보안그룹 설정
   3. openVpn 용 인스턴스
      1. openvpn API 사용해야 함. OpenVPN Access Server
      2. public subnet에
      3. 보안그룹 자동생성 그대고 사용
      4. 탄력적 IP 할당 필요
      5. SSH 붙고 나면 admin 주소를 받을 수 있음
8. 탄력적 IP
9.  Route53
    1.  레코드 생성 
        1.  A 유형
        2.  별칭 선택해서 만든 로드밸런서 선택
10. ACM
    1.  Route53에서 등록한 도메인을 가지고 생성
    2.  보통 *.을 도메인 앞에 붙여서 등록
    3.  생성 후에 `Route53에서 레코드 생성` 클릭해서 인증
    4.  인증이 되면 Route53 호스팅 영역에 CNAME 유형의 DNS 레코드가 생성된다.
11. RDS
    1.  서브넷 그룹을 선택할 때, db 전용으로 만든 서브넷을 선택
    2.  데이터베이스 
        1.  EC2 연결 안함으로 선택
        2.  퍼블릭 액세스 아니요
        3.  서브넷 그룹 위에 만든 거 선택
        4. 기존에 만든 보안그룹 선택
12. 로드밸런서
    1.  대상 그룹
        1.	•	대상 그룹의 포트 설정: 기본적으로 ELB가 대상에게 트래픽을 전달할 때 사용할 포트. 하지만 대상 인스턴스를 등록할 때 별도로 포트를 지정하면, 이 설정은 무시됩니다.
	         •	등록된 대상의 포트 설정: 대상 인스턴스가 ELB로부터 트래픽을 받을 실제 포트. 대상 그룹의 기본값보다 우선 적용됩니다.
         2. 8080포트로 받는 대상그룹 만들 때, 상태검사 포트를 재정의해서 바꿔줘야 한다.
   1. 로드밸런서
      1. 일반적으로 **퍼블릭 서브넷(Public Subnet)** 에 배치
      2. 로드밸런서 전용 보안그룹 선택
      3. 리스너 포트에 따라 대상그룹 선택
      4. ACM 인증서 선택
      5. 리스너 
         1. 80으로 들어온건 443 리다이렉션
         2. 443들어오는 거에 호스트 조건을 줄 수 도 있다. 일치하는 위에서 생성한 도메인만 받기위해.
      6. 로드밸런서 DNS 주소를 사용해서 접속할 수 도 있음.
13. ㅇㅇ
14. ㅇㅇ 
