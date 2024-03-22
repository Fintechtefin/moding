### 강민정

1. **Jenkins와 GitLab을 연동하여 CI 구축**

develop 브랜치에 push, Merge request 이벤트가 발생할 때 `Webhooks`가 작동합니다.

![Untitled](https://file.notion.so/f/f/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/8441f78d-0a05-45c0-9479-4c7f7342354f/Untitled.png?id=c4fb9ac0-8c95-4e2f-923e-b438db1381c8&table=block&spaceId=ba5fa5af-d358-4bfb-9eb4-f4423babbddf&expirationTimestamp=1711238400000&signature=fb9fExf4W9WYBbtlyrBvjLCRykIg6gsJkTEA8jyqjxs&downloadName=Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/17c9e81e-db59-41c3-93f9-4b7798e3f3de/Untitled.png)

1. **결제 API 추가**

payment 컴포넌트에서 토스페이먼츠의 결제 API 연동, funding 컴포넌트에서 주문 생성 API 추가

테스트 목적으로 사용할 수 있는 웹 서버 `wiremock`을 이용해 토스페이먼츠 결제 API 연동 테스트 추가

funding의 주문 생성과 payment 컴포넌트의 토스페이먼츠 결제를 연동하기 위해 `Kafka`를 사용(funding에서 이벤트를 발생시키는 부분까지만 구현됨)

1. **user 컴포넌트와 funding 컴포넌트를 gRPC를 이용하여 통신**

user 컴포넌트가 `gRPC Server`, funding이 `gRPC client`

funding 컴포넌트는 user 컴포넌트에서 현재 로그인한 사용자 아이디를 가져옴

1. **멀티 모듈 구조로 전환**

**`gRPC`**에서 사용하는 **`proto`** 파일을 공통으로 사용하기 위해 **`멀티 모듈 구조`**로 전환

back이 루트  프로젝트이고, 'batch', 'discovery', 'funding', 'gateway', 'payment', 'reservation', 'user', 'proto'를 포함

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/f446801d-b007-47f9-84e2-f0ec3c6efa36/Untitled.png)

### 임승연

1. 네이버 소셜 로그인 구현 
2. Kmdb 에서 api를 통해 영화 데이터를 csv 로 추출하고, 원하는 형태로 파싱하여 프로젝트에 사용할 리스트만 선별하는 작업 
3. 영화 리스트를 저장할 엔티티 및 테이블 생성 
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/7343abc1-9afe-47ca-a08a-e8cda1ed2d3d/Untitled.png)
    
4. 실시간 검색어 순위 , 검색 기능 구현을 위해 Logstash 로 ElasticSearch 와 Mysql 을 동기화 하는 작업을 로컬에서 하였고, 이를 기반으로 개발 서버에 ELK 환경 구축 진행 중
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/08642d89-1187-4099-801f-ac0d8c890f77/Untitled.png)
    

### 정태윤

1. 좌석 예매
    - 좌석을 상태(선택불가, 선택가능, 선택좌석)에 따라 색상으로 구별
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/3642494d-f2b0-4afa-bbc8-cb63300c9e5e/Untitled.png)
    
    - 좌석 선택 시 최대 인원을 추가하면 선택 불가
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/c3261780-9c9e-4841-a526-653006316290/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/dd4a7bfa-ac7e-4faa-9b86-4cdcd8a7d4f2/Untitled.png)
    
    - Recoil 상태 관리를 통해 새로고침을 진행해도 데이터 유지할 수 있게 구현 진행 중
    
2. 결제
    - 토스페이먼츠 api를 연결하여 결제 진행 페이지 구현
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/1a79d9f3-d1a7-4889-a9f8-91e527edcbf3/Untitled.png)
    
    - 결제 취소, 약관 동의 등 문제 발생 시 메시지 전송
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/452be164-8e41-47a9-a082-dfe021039e72/Untitled.png)
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/0d553251-4302-4817-829b-cf6f16522bb2/Untitled.png)
    

### 조자영

1. 소셜로그인 구현 - 카카오, 네이버를 이용한 로그인, 로그인 후 jwt token localstorage에 저장
2. 영화 리스트
    
    ![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/df4cfe14-34d1-4d71-be11-a0ae99dc1aa5/Untitled.png)
    

2. 영화상세페이지 - 영화 상세정보, 펀딩정보, 유의사항

상태에 따라 버튼 상태 6번 바뀌도록 구현

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/9ab9cade-c563-4b29-a2d4-57f29ced4fb3/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/7c3bff39-a1b8-4edf-9309-3076b14c35b6/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/1e585488-0914-4852-a57c-756cbcb4b3df/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/e74a2d8a-7d84-40bb-8206-0ef7a4ece4ca/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/f3fc19c7-6b46-44aa-9144-a869a4779d9d/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/442f22a1-2e96-4c30-889c-304cab25f25c/Untitled.png)

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/d01ce9b9-a387-48fb-8366-30f2db417ac6/Untitled.png)

### 허승경

- 구현 완료

**좌석 예매 신청 API 개발**

1. 사용자가 요청한 좌석이 이미 예매된 좌석일 경우, errorCode 및 errorMessage 반환
    
    ![pm_좌석예매_이선좌_잘됨 - 복사본.PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/ff0d45ca-b949-4e71-b160-96d1abd6b0cf/pm_%EC%A2%8C%EC%84%9D%EC%98%88%EB%A7%A4_%EC%9D%B4%EC%84%A0%EC%A2%8C_%EC%9E%98%EB%90%A8_-_%EB%B3%B5%EC%82%AC%EB%B3%B8.png)
    

1. 예매 완료
    
    ![pm_좌석예매_성공(결제정보확인안함).PNG](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/20a6b7b9-61a3-43a4-a8ae-74e74310c958/pm_%EC%A2%8C%EC%84%9D%EC%98%88%EB%A7%A4_%EC%84%B1%EA%B3%B5(%EA%B2%B0%EC%A0%9C%EC%A0%95%EB%B3%B4%ED%99%95%EC%9D%B8%EC%95%88%ED%95%A8).png)
    

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/ba5fa5af-d358-4bfb-9eb4-f4423babbddf/33e8053e-f07d-4253-8b3a-1b273c48e170/Untitled.png)

**구현 중**

- **좌석 예매 신청자의 결제 완료 확인**
    
    order, payment 컴포넌트 간의 gRPC 통신을 이용하여 예매 신청자의 결제 완료 상태를 파악하는 로직 수행 중
    
- **좌석 예매 취소 API**
    
    구현한 로직에 대한 postman 확인 예정
    
- **티켓 발급 API**
    
    funding 컴포넌트, movie 컴포넌트 간의 gRPC 통신을 이용하여 사용가의 티켓 발급 로직 수행 중
