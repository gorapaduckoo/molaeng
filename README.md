<img src='https://user-images.githubusercontent.com/67768976/200231914-2e0d74b6-b1d0-41a5-b47b-2344a92b47b1.png' width='30%' />

# 🍴 모랭(모두의 냉장고)
사용자가 보유한 재료를 기반으로 레시피 검색 기능을 제공하고, 조리해 먹은 레시피 기록과 그에 따른 절약 현황 및 가계부 기능을 제공하는 모바일 웹 서비스

# 💭 기획 배경
### 최근 증가하고 있는 1인 가구는..

✔ 외식/배달로 인한 영양 불균형과 낮은 식생활 만족도

✔ 유통기한 내로 사용하지 못해 낭비되는 식재료

✔ 이로 인한 지출 증가와 환경오염

### 그래서 기획하였습니다.

가지고 있는 식재료를 활용해 만들 수 있는 레시피를 추천해 주고, 

해당 레시피에 필요한 조리 비용을 계산하여 외식비와 비교해 주는 기능을 담은 서비스

# 프로젝트 기간
**2022.08.22 ~ 2022.10.07**

# 👪 팀원 소개
|**이름**|**역할**|
|:---:|:---:|
|이나경|팀장, BE, FE, Infra|
|김승준|BE, FE|
|김지건|BE, FE|
|박세원|BE, FE|
|양희경|BE, FE|
|이지영|BE, FE|

# 🍴 핵심 기능

## 1. 검색 기능

### 재료 기반 레시피 검색
재료 키워드를 선택하고, 선택된 재료를 사용하는 레시피를 검색

![재료로검색](https://user-images.githubusercontent.com/67768976/200232068-38839f53-419f-4541-ace9-88702ea78b46.gif)

### 레시피 이름으로 검색
자동완성 기능

![레시피이름으로검색](https://user-images.githubusercontent.com/67768976/200232502-602f5007-ba2e-4c48-8353-5256c1a7f2f0.gif)

## 2. 조리 비용 VS 외식비 가격 비교

상세 페이지의 재료 태그 선택 시, 활성화 된 재료에 따라 조리 비용이 변화

![재료에따라가격변화](https://user-images.githubusercontent.com/67768976/200232521-edb94426-6e70-4432-aaf8-012ad176b74c.gif)

필요한 식재료 분량에 따라 음식을 조리하는 데 필요한 비용을 계산하고, 똑같은 레시피를 밖에서 사 먹었을 때와 가격을 비교

![조리비외식비비교](https://user-images.githubusercontent.com/67768976/200232550-b589f651-ab1a-4528-8599-c10887c3e874.gif)

## 3. 모랭일기

레시피 상세 페이지에서 조리해 먹은 레시피를 저장

![모랭일기등록](https://user-images.githubusercontent.com/67768976/200232560-f249c6d2-617a-4caa-8b12-cf7f2a056998.gif)

주차별/월별로 절약한 금액 내역을 시각화하여 제공

모랭일기 페이지에서 그동안 등록한 레시피 정보를 캘린더에서 확인 가능

![모랭일기그래프레시피](https://user-images.githubusercontent.com/67768976/200232571-c6ef3f39-5de5-4f1b-a626-01a95961b4ee.gif)

# 🍴 그 외 기능
- 소셜 로그인(KaKao)
- 레시피 찜/최근 본 레시피 조회 기능

# 📃 기능 명세서
![기능명세서1](https://user-images.githubusercontent.com/67768976/225080811-14d35ba7-19bd-42b3-af7b-55b1eec03b81.png)
![기능명세서2](https://user-images.githubusercontent.com/67768976/200232193-2200b261-2118-4c0c-a1d4-2fd3961ac5da.png)
![기능명세서3](https://user-images.githubusercontent.com/67768976/200232222-02e29646-774f-4877-af50-9c5a1db3bdce.png)

# 📃 ERD
![ERDiagram](https://user-images.githubusercontent.com/67768976/200232258-8fa2a35b-dbf0-4e17-8c70-43c71dcbc888.png)

# 📃 와이어프레임
![와이어프레임1](https://user-images.githubusercontent.com/67768976/200232293-0a1b80dc-cde2-4e5e-a23f-775b125aea51.png)
![와이어프레임1](https://user-images.githubusercontent.com/67768976/200232301-725e4873-f2fb-4779-95bf-855fe2d2984f.png)

# 📃 컴포넌트 구조도
![컴](https://user-images.githubusercontent.com/67768976/200232338-4da93565-3c57-4636-bd96-314e639c8bbc.png)

# 💻 개발 환경 및 기술 스택
<img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">

<img src="https://img.shields.io/badge/gradle-02303A?style=for-the-badge&logo=gradle&logoColor=white">

<img src="https://img.shields.io/badge/python-3776AB?style=for-the-badge&logo=python&logoColor=white">

<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">

<img src="https://img.shields.io/badge/Apache Hadoop-66CCFF?style=for-the-badge&logo=ApacheHadoop&logoColor=white">

<br>

<img src="https://img.shields.io/badge/vue.js-4FC08D?style=for-the-badge&logo=vue.js&logoColor=white">

<img src="https://img.shields.io/badge/vuetify-1867C0?style=for-the-badge&logo=Vuetify&logoColor=white">

<img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">

<img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white">

<br>

<img src="https://img.shields.io/badge/amazonaws-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white">

<img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white">

<img src="https://img.shields.io/badge/NGINX-009639?style=for-the-badge&logo=NGINX&logoColor=white">

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white">

<img src="https://img.shields.io/badge/Jenkins-D24939?style=for-the-badge&logo=Jenkins&logoColor=white">

### 🖱️ 백엔드
- IDE : IntelliJ 2022.2
- JAVA : 8
- Framework : Spring boot 2.7.4
- Build : Gradle 7.5
- WAS : Tomcat
- DBMS : MySql 8.0
- DB API : JPA

### 🖱️ 프론트
- IDE : VS Code 1.70.1
- Framework :
    - Vue2 5.0.8
    - Vuetify  2.6.0
    - Chart.js 3.9.1

### 🖱️ 데이터 정제
- Hadoop
- Python
- JSoup

### 🖱️ 서버
- AWS
- Docker
- Nginx
- Jenkins

### 🖱️ 협업
- Git
- Jira

# 📃 아키텍처 구조
![아키텍쳐구조도](https://user-images.githubusercontent.com/67768976/200232427-563eb226-a5a7-4683-8a66-923ec2ef07ec.png)
