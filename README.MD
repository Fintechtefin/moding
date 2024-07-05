# 🎟 재상영 영화 크라우드 펀딩, 무딩

</br>

<div>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
  <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 
  <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> 
  <img src="https://img.shields.io/badge/redis-DC382D?style=for-the-badge&logo=redis&logoColor=white">
  <img src="https://img.shields.io/badge/elasticsearch-005571?style=for-the-badge&logo=elasticsearch&logoColor=white">
  <img src="https://img.shields.io/badge/logstash-005571?style=for-the-badge&logo=logstash&logoColor=white">
  <img src="https://img.shields.io/badge/kibana-005571?style=for-the-badge&logo=kibana&logoColor=white">
</div>
<div>
  <img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">
  <img src="https://img.shields.io/badge/nginx-009639?style=for-the-badge&logo=nginx&logoColor=white">    
  <img src="https://img.shields.io/badge/amazonec2-FF9900?style=for-the-badge&logo=amazonec2&logoColor=white">
  <img src="https://img.shields.io/badge/prometheus-E6522C?style=for-the-badge&logo=prometheus&logoColor=white">
  <img src="https://img.shields.io/badge/grafana-F46800?style=for-the-badge&logo=grafana&logoColor=white">
  <img src="https://img.shields.io/badge/linux-FCC624?style=for-the-badge&logo=linux&logoColor=black"> 
</div>
<div>
 <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black">
 <img src="https://img.shields.io/badge/node.js-339933?style=for-the-badge&logo=Node.js&logoColor=white">
 <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
</div>

## 목차
[1. 서비스 개요](#1장-서비스-개요)

[2. 서비스 소개](#2장-서비스-소개)

[3. 아키텍처 소개](#3장-아키텍처-소개)

[4. 주요 기술](#4장-주요-기술)

</br>

---
## 1장. 서비스 개요
- **프로젝트 진행 기간 : 2024.02.26 ~ 2024.04.03**

**“다시 보고 싶은 영화를, 영화관에서”**

재상영 영화 크라우드 펀딩, 무딩은 영화를 한 번 이상 보는 N차 관람에서 출발하여 펀딩의 요청부터 대관, 영화 재상영까지 어어질 수 있는 서비스를 제공합니다.


---
## 2장. 서비스 소개
### 🏆 지금 인기 있는 펀딩은?

![실검_gif](/uploads/4c6a4959ccc51a87a96bf3936d536caa/실검_gif.gif)

->**ElasticSearch**를 이용한 **실시간 영화 검색 순위** 


### 💳 터치 한 번에 결제 끝! 
![결제_gif](/uploads/531d14a161e120ef5e40360ba38439af/결제_gif.gif)

->**토스페이먼츠**API를 이용한 쉽고**빠른 결제** 


### 👀 N명이 보고 있어요
![예매_gif](/uploads/8eae973b8b4572c97cd5f480a5da181c/예매_gif.gif)
-> 긴장감 UP.**동시 접속자 수**를 볼 수 있는**좌석 예매**


---
## 3장. 아키텍처 소개
![mooding_architecture](https://github.com/Fintechtefin/moding/assets/46569105/cb057ca2-9bc8-4fbf-8ec8-069aaf0743c8)  

<br>

### 기술 스택 선정 이유
|기술|선정 이유|
|---|----|
|Spring Boot|Spring과 비교했을 때 환경 설정을 쉽게 할 수 있어서 개발 생산성 증가|
|MySQL|구조화 된 데이터 관리 및 오픈 소스 라이센스를 따르기 때문에 무료 사용 가능|
|Docker|컨테이너 기반으로 여러 서버들을 쉽게 관리|
|Redis|캐싱 및 분산락 구현을 위한 사용|
|ELK|실시간 인기 순위 구현 시 검색 성능 최적화를 위해 사용|
|Prometheus|매트릭 수집|
|Grafana|Prometheus로 수집한 매트릭 데이터를 시각화|

---
## 4장. 주요 기술
1. `MSA` 기반의 `Modern` 아키텍처 및 `DDD` 중심 개발
2. MSA에 `HA(고가용성)` 도입 및 `고가용성`의 **확장 가능**한 **시스템**을 설계
3. `ElasticSearch`  및 `Redis`를 활용해 1시간 단위로 **실시간 랭킹 업데이트**
4. `Redisson 분산락 AOP`를 이용한 **결제 및 좌석 예매** **동시성 제어**
5. `서비스 DB`와 **결제 DB**분리를 통한**보안 향상**
6. `transaction`처리 및 `publisher/subscriber` 관련 구현
7. `Proxy`에서 `WAS`단에 전달할 수 있는 `Max Connection`제한
8. 멀티 모듈 구조로 **공통 모듈 분리**

----
