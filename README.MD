# 🎟 Moding(무딩)  
재상영 영화 크라우드 펀딩 서비스  
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

<br>

## 📕 목차  
1. [서비스 개요](#1장-서비스-개요)

2. [아키텍처 소개](#2장-아키텍처-소개)

3. [주요 기능](#3장-주요-기능)

4. [주요 기술](#4장-주요-기술)

5. [회고](#5장-회고)  

</br>

## 1장. 서비스 개요  
- “다시 보고 싶은 영화를, 영화관에서”**
영화를 한 번 이상 보는 N차 관람에서 출발하여 펀딩의 요청부터 대관, 영화 재상영까지 어어질 수 있는 서비스를 제공합니다.  

<br>

## 2장. 아키텍처 소개  
<p align="center">
  <img src="https://github.com/Fintechtefin/moding/assets/46569105/cb057ca2-9bc8-4fbf-8ec8-069aaf0743c8"/>  
</p>

## 기술 스택 선정 이유
|기술|선정 이유|
|---|----|
|***Spring Boot***|***Spring***과 비교했을 때 환경 설정을 쉽게 할 수 있어서 개발 생산성 증가|
|***MySQL***|구조화 된 데이터 관리 및 오픈 소스 라이센스를 따르기 때문에 무료 사용 가능|
|***Docker***|컨테이너 기반으로 여러 서버들을 쉽게 관리|
|***Redis***|캐싱 및 분산락 구현을 위한 사용|
|***ELK***|실시간 인기 순위 구현 시 검색 성능 최적화를 위해 사용|
|***Prometheus***|매트릭 수집|
|***Grafana***|***Prometheus***로 수집한 매트릭 데이터를 시각화|

<br>

## 3장. 주요 기능  
### 1. 지금 인기 있는 펀딩은?
- ***ElasticSearch***를 이용한 실시간 영화 검색 순위  
<p align="center">
  <img src="https://github.com/Fintechtefin/moding/assets/46569105/c8bb8675-e33a-4ea3-9578-ad64199beeb6" width="500" height="1000"/>  
</p>

<br>

### 2. 터치 한 번에 결제 끝!  
- 토스페이먼츠 `API`를 이용한 쉽고 빠른 결제  
<p align="center">
  <img src="https://github.com/Fintechtefin/moding/assets/46569105/3f7efeab-946c-4662-b502-7c967577fb3c" width="500" height="1000"/>
</p>

<br>

### 3. N명이 보고 있어요  
- 동시 접속자 수를 볼 수 있는 좌석 예매  
<p align="center">
  <img src="https://github.com/Fintechtefin/moding/assets/46569105/b2442613-55b5-4007-b10f-9b54d62bff1e" width="500" height="1000"/>  
</p>  

<br>

## 4장. 주요 기술
1. ***MSA*** 기반의 Modern 아키텍처 및 ***DDD*** 중심 개발
2. ***MSA***에 `HA` 도입 및 확장 가능한 시스템을 설계
3. ***ElasticSearch*** 및 ***Redis***를 활용해 1시간 단위로 실시간 랭킹 업데이트
4. ***Redisson 분산락 AOP***를 이용한 결제 및 좌석 예매 동시성 제어
5. 서비스 `DB`와 결제 `DB` 분리를 통한 보안 향상
6. ***transaction*** 처리 및 `publisher`/`subscriber` 관련 구현
7. `Proxy`에서 `WAS`단에 전달할 수 있는 Max Connection 제한
8. `멀티 모듈` 구조로 공통 모듈 분리

<br>

## 5장. 회고
**Keep**
- 민정: ***MSA*** 설계, ***Redis*** `HA`, 모니터링 구축, 운영 환경별로 스프링 프로필을 다르게 분리
- 승연: ***Git*** 컨벤션, 기술적으로 새로운 도전, `디버깅` 사용
- 태윤: 공통(`컴포넌트`, `API`, `Type`)을 파일로 분리하여 재사용, 상태 관리 라이브러리 ***Recoil***을 활용하여 필요한 `컴포넌트`에 변수 사용
- 자영: ***Jira***, ***Git*** 컨벤션, 모듈화 사용
- 승경: ***Jira*** 컨벤션을 미리 정하여 번업 차트로 가지 않은 것, ***Git*** 컨벤션, ***MSA*** 설계, `디버깅` 사용  

**Problem**
- 민정: `mock data`를 대충 넣어서 문제 발생, 일부 코드(공통 에러 코드, 모듈 간 통신 시 요청 `DTO` 및 응답 `DTO`) 중복
- 승연: 미흡한 예외 처리, 동적 쿼리 작성 시 `디버깅` 어려움, 반복되는 쿼리 수정
- 태윤: 사용하지 않는 `import`나 변수 등을 지우지 않아 빌드 시 오류 발생
- 자영: ***hook***에 대한 이해 부족, `Type` 종류와 `interface` 활용에 대한 공부 필요
- 승경: 중간에 `DB` 설계 변경, `API` 문서화를 하지 않음, 중복 있는 로직 구현  

**Try**
- 민정: 중요한 비즈니스 로직은 테스트 코드 작성하기
- 승연: ***Java*** `stream`, `filter`, `map` 적극 사용하기
- 태윤: 사용하지 않는 것은 주석 처리 또는 바로 지우기
- 자영: ***react-query*** 깊게 사용하기
- 승경: `Cache` 적용 후 ***JMeter***로 성능 테스트하기

