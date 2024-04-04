# 🎬다시 보고 싶은 영화를 영화관에서, MODING 
## Sub-PJT 3주차 평가 목차
1. [강민정](#강민정)
2. [임승연](#임승연)
3. [정태윤](#정태윤)
4. [조자영](#조자영)
5. [허승경](#허승경)
---


### 강민정

1. **Jenkins와 GitLab을 연동하여 CI 구축**

    develop 브랜치에 push, Merge request 이벤트가 발생할 때 `Webhooks`가 작동합니다.
    ![민정1](/uploads/c143519d7617cea6192c76f5f8ea2015/민정1.png)
    ![민정2](/uploads/47cbcc886e7b9a9d2f9f8dc917ae6862/민정2.png)

2. **결제 API 추가**

    payment 컴포넌트에서 토스페이먼츠의 결제 API 연동, funding 컴포넌트에서 주문 생성 API 추가

    테스트 목적으로 사용할 수 있는 웹 서버 `wiremock`을 이용해 토스페이먼츠 결제 API 연동 테스트 추가

    funding의 주문 생성과 payment 컴포넌트의 토스페이먼츠 결제를 연동하기 위해 `Kafka`를 사용(funding에서 이벤트를 발생시키는 부분까지만 구현됨)

3. **user 컴포넌트와 funding 컴포넌트를 gRPC를 이용하여 통신**

    user 컴포넌트가 `gRPC Server`, funding이 `gRPC client`

    funding 컴포넌트는 user 컴포넌트에서 현재 로그인한 사용자 아이디를 가져옴

4. **멀티 모듈 구조로 전환**

    **`gRPC`**에서 사용하는 **`proto`** 파일을 공통으로 사용하기 위해 **`멀티 모듈 구조`**로 전환

    back이 루트  프로젝트이고, 'batch', 'discovery', 'funding', 'gateway',  'payment', 'reservation', 'user', 'proto'를 포함

    ![민정3](/uploads/2ca16d4a6eebcc72a7cf1a7c6bdf32d1/민정3.png)



### 임승연

1. **네이버 소셜 로그인 구현**
2. **Kmdb 에서 `api`를 통해 영화 데이터를 `csv` 로 추출하고, 원하는 형태로 파싱하여 프로젝트에 사용할 리스트만 선별하는 작업** 
3. **영화 리스트를 저장할 엔티티 및 테이블 생성** 
    
    ![승연1](/uploads/3ff3dd40866b6295005f2108b2a8c090/승연1.png)
    
4. **실시간 검색어 순위 , 검색 기능 구현을 위해 `Logstash` 로 `ElasticSearch` 와 `Mysql` 을 동기화 하는 작업을 로컬에서 하였고, 이를 기반으로 개발 서버에 `ELK` 환경 구축 진행 중**
    
    ![승연2](/uploads/4c545a3d4edd36f0c91963a1c087f270/승연2.png)
    



### 정태윤

1. **좌석 예매**
    - 좌석을 상태(선택불가, 선택가능, 선택좌석)에 따라 색상으로 구별
    
    ![태윤1](/uploads/0bd5c9761abf4eb0da535679b5a7ab29/태윤1.png)
    
    - 좌석 선택 시 최대 인원을 추가하면 선택 불가
    
    ![태윤2](/uploads/64288fafaa5d6d168bf453d82d82e23f/태윤2.png)
    
    ![태윤3](/uploads/b99b0a964c34ae55fae19f69e3e8f4d8/태윤3.png)
    
    - `Recoil` 상태 관리를 통해 새로고침을 진행해도 데이터 유지할 수 있게 구현 진행 중
    
2. **결제**
    - 토스페이먼츠 `api`를 연결하여 결제 진행 페이지 구현
    
    ![태윤4](/uploads/44ec941eadf1bca8afb4369cac5ae8fc/태윤4.png)
    
    - 결제 취소, 약관 동의 등 문제 발생 시 메시지 전송
    
    ![태윤5](/uploads/24d34a7284979d1a4b3aa3a5e0265fc8/태윤5.png)
    
    ![태윤6](/uploads/52ca33059ac04bb8ac7d18d8671d3bd0/태윤6.png)






### 조자영

1. **소셜로그인 구현 - 카카오, 네이버를 이용한 로그인, 로그인 후 `jwt token localstorage`에 저장**
2. **영화 리스트**
    
    ![자영1](/uploads/d1d4572ba5e8ded42bc1a69b5c90c873/자영1.png)
    

3. **영화상세페이지 - 영화 상세정보, 펀딩정보, 유의사항**

    상태에 따라 버튼 상태 6번 바뀌도록 구현

    ![자영2](/uploads/c69d0ab7a76f983f2a9cc14691ce2ec8/자영2.png)

    ![자영3](/uploads/c20911c2331523bf3e6e9be6c3da2594/자영3.png)




### 허승경
1. **좌석 예매 신청 API (merge 전)**
    - 사용자가 요청한 좌석이 이미 예매된 좌석일 경우, `errorCode` 및 `errorMessage` 반환
    ![승경1](/uploads/e877dee3842ae7c52132aaa5e64c79b2/승경1.png)

    - 예매 완료
    
    ![승경2](/uploads/d9f305513e1aa7ccdf21b9a7481f6934/승경2.png)
    ![승경3](/uploads/57fd2683b31a446c9e0839a43e49d81e/승경3.png)

2. **`gRPC` 통신 (테스트 중)**
    - 좌석 예매 신청자의 결제 완료 확인
    -> `order`, `payment` 컴포넌트 간의 `gRPC 통신을 이용하여 예매 신청자의 결제 완료 상태를 파악하는 로직 수행 중
    - 티켓 발급
    -> `funding` 컴포넌트, `movie` 컴포넌트 간의 `gRPC` 통신을 이용하여 사용가의 티켓 발급 로직 수행 중


3. **좌석 예매 취소 API**
    - 구현한 로직에 대한 postman 확인 예정
    

