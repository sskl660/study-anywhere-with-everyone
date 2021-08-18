# 🗓 7차 회의 - SCRUM

### 회의 결산

- Keep : 계획 잘하기(내일 스프린트 플래닝 잘해보자!), 준형 리액션, 10분 쉬는시간, 컨설턴트님 코치님 피드백(검은 모자 대왕)
- Problem : 체력이 조금 떨어져가는게 보여서 10분 쉬는시간 더 고려해보기
- Try : 졸릴 때 한 20 - 30분정도는 놀자 노래 들으면서(대왕 쉬는 시간 1개 배치).

- 회의 시간 : 10시간

## 비전 작성

- **Study Anywhere with Everyone!**
- **Elevator pitch.**

    "IT 부트캠프 교육기관(싸피인)들을 대상으로 하는 스터디 SNS"에 해당하는

    "싸집(ssa.zip)"은

    "비대면 교육에서 함께 공부하는 것이 어려운"

    "모든 IT교육생 (싸피인)" 에게

    "기존의 독립적인 공부 방식을 제공하는 플랫폼" 과 달리

    "그룹 챌린지와 실시간 랜선 공부방" 을 가지고

    "언제 어디서나 함께 공부할 수 있는 환경" 을 제공합니다.

## 프로덕트 백로그

### 유저 스토리

**"어떤 고객은 어떤 감정이나 이득을 위해 제품에서 무엇을 원한다"**

---

1. **홈 피드** 
    - 좌측 피드
        - **공부 자극이 필요한 학생 김싸피는 홈 피드에서 나와 연관된 사람들이 어떤 공부를 하고 있는지 알고 싶다.**
        - 정리된 화면을 보고 싶어하는 유싸피는 팔로우하는 사람들, 혹은 참여한 챌린지/랜선 공부방의 소식을 최신순으로 정렬해서 보고 싶다.
    - 명예의 전당
        - 공부 자극이 필요한 학생 차싸피는 랭킹에서 내가 속한 IT 그룹에서 다들 얼마나 공부하고 있는지를 알기를 원한다.
        - 졸업한 사람들이 어떤 공부를 했는지 알고 싶은 권싸피는 졸업자들의 계정을 알고 싶다.
        - 랭킹에 오르고 싶은 김싸피는 공부를 열심히해서 해당 명예의 전당에 오르고 싶어한다.
2. **그룹 챌린지**
    - 공동 목표를 함께 이룰 **동료를 원하는 유싸피**는 챌린지를 개설하여 단기 과제를 해나가고 공유한다. (방장)
    - 다른 사람들과 **과제를 공유하며 공부하고 싶어하는 차싸피**는 동기를 얻기 위해 챌린지에 참가하여 챌린지를 진행한다. (참여자)
    - 집에서 혼자 공부하는 권싸피는 성취감을 느끼기 위해 학업을 기록하기를 원한다. (전원)
3. **프로필**
    - 친구들과 함께 챌린지를 하는 김싸피는 뒤쳐지지 않도록 달성률 100퍼센트를 채우고 싶어한다.
    - 성실하게 공부 기록을 남기고 싶어하는 유싸피는 열심히 공부해서 도장을 다 찍고 싶어한다.
    - 나를 어필하여 나와 잘 맞는 친구를 구하고 싶은 이싸피는 프로필에 보기 좋게 자신의 정보를 나타내고 싶다.
4. **랜선 공부방**
    - 혼자 공부하던 이싸피는 함께 공부하는 느낌을 받기 위해 싸집에서 다른 사람들과 실시간으로 접속 현황 및 공부 시간을 공유하고 싶어한다.
    - CS 공부 중인 이싸피는 다른 사람들과 정보를 공유하기 위해 싸집에서 채팅창으로 질의응답을 하고 싶어한다.
    - 왕관을 쓰고 싶어하는 왕싸피는 다른 사람들과 경쟁하며 공부방에서 오랫동안 함께 공부하고 싶다.

### 플래닝 포커 + 우선순위

---

1. ~~요구사항 분석(기획)~~
    1. ~~아이디어 정의~~
    2. ~~요구사항 분석~~
    3. ~~비전 작성~~
    4. ~~유저 스토리 작성~~
2. 설계
    1. ~~와이어 프레임 작성~~
    2. UI/UX 설계 ⇒ 16
        - UI 스타일 가이드 정의 ⇒ 2
        - Prototype 만들기 (완전 완성품) ⇒ 최소 10
            - 컨셉 색깔 정하기 : 정확한 하나의 색깔 코드 (혹은 팔레트)
            - 레퍼런스(ex : 좋은 웹사이트들)을 많이 보고 분석하기
            - 로고 만들기, 글씨체 정하기
            - 프로토타입을 만들면서 구현이 가능한가도 분석
        - Layout 설계 ⇒ 2
        - Component 설계 ⇒ 2
    3. Database 설계 (공부시간까지 포함한 시간) ⇒ 12
        - 요구사항 분석 (데이터베이스 정의) ⇒ 2
        - 개념적 설계(ER 다이어 그램) ⇒ 3
        - 논리적 설계(테이블 정의, 제약 조건, 정규화) ⇒ 5
        - 물리적 설계(SQL 작성 +인덱스 설계) ⇒ 2
    4. Architecture 설계 ⇒ 2

        [프로젝트 계획(10) - 아키텍쳐 설계 [소프트웨어공학]](https://luv-n-interest.tistory.com/394)

    5. API 설계 ⇒ 5 + 5

        [백엔드가 이정도는 해줘야 함 - 4. API 설계 원칙과 직렬화 포맷 결정](https://velog.io/@city7310/%EB%B0%B1%EC%97%94%EB%93%9C%EA%B0%80-%EC%9D%B4%EC%A0%95%EB%8F%84%EB%8A%94-%ED%95%B4%EC%A4%98%EC%95%BC-%ED%95%A8-4.-API-%EC%84%A4%EA%B3%84-%EC%9B%90%EC%B9%99%EA%B3%BC-%EC%A7%81%EB%A0%AC%ED%99%94-%ED%8F%AC%EB%A7%B7-%EA%B2%B0%EC%A0%95)

3. 환경 설정(Front, Back, 클라우드 서버, Docker 등...) ⇒ 10

    +기능 빼고 생각!

4. **기본 웹 기능으로 사이트를 이용 할 수 있다.**  ⇒ 15 | 30
    1. 웰컴 페이지
    2. 회원 가입 및 수정
    3. 로그인
    4. Nav bar 만들기
    5. ( + 기능 : Nav bar UI 디자인- 해 떨어지기, 배경이 낮에서 밤으로 변하게 하기)

    `백엔드 필요한 능력` : JPA, JWT 

5. **팔로우 팔로잉 기능으로 관계 형성이 가능하다.** ⇒ 5 | 5
    1. 상대방 팔로우 하기
    2. 내 팔로워/팔로잉 조회 및 삭제
    3. 상대방 팔로워/ 팔로잉 조회 
6. **그룹 챌린지로 함께 도전과제를 공유하며 수행 할 수 있다.**  ⇒ 25  | 15
    1. 그룹 챌린지 생성하기
    2. 그룹 챌린지 목록 검색 기능 생성
    3. 그룹 챌린지 목록에 들어가서 챌린지 참여
        1. 그룹 챌린지 가입 시 챌린지의 가입자로 등록
        2. 그룹 챌린지 가입 시 내 페이지에 추가
    4. 과제 제출 및 조회 기능 구현
    5. 진행 현황 조회(챌린지 과제 제출 진행 달성률 및 스티커 채우는 것)
7. **프로필로 자신의 활동내역과 정보를 공유 할 수 있다.** ⇒ 20 | 5
    1. 개인 정보(개발스타일, 희망직군, 기술스택, 관심사, MBTI, 그 외 기타 각오, 소감, 소개 멘트 등) 편집 기능
    2. 프로필 사진 변경 기능 
    3. (+ 기능 : 졸업하면 학사모 씌우기)
    4. 챌린지 기록(현재 참여중인 챌린지 + 이전에 참여한 챌린지 기록)
    5. 챌린지 기록상의 챌린지 명 클릭시 챌린지로 이동
    6. (+ 기능 : 쿠폰별 과제클릭시 과제 확인)
    7. (+ 기능 : 랜각공 참여 누적 시간)
    8. 팔로우 팔로워 확인 가능
    9. 블로그, 깃허브 링크 편집
    10. 챌린지 완주 갯수 카운팅
    11. (+ 기능: 뱃지와 칭호)
8. **피드로 유용한 정보를 실시간으로 얻을 수 있다.** ⇒ (실시간여부따라) 20  |  30
    1. 팔로우하는 사람의 챌린지 소식(가입소식 + 과제 제출 소식 + 완주 소식) (소식 다이어트 가능)
    2. 팔로우하는 사람의 랜선 공부방 소식(접속 소식) (+ 기능 들어갈때 메시지)
    3. (팔로우하는 사람의 팔로잉 소식)

    `백엔드 필요한 능력` : 웹소켓

9. **명예의 전당으로 동기부여 될 정보를 얻을 수 있다.** ⇒ 2 | 3
    1. 졸업자 현황(랜덤으로 보여줌)
    2. 월간 챌린지 참여 TOP 5 (챌린지 완주 갯수)
    3. 주간 랜각공 공부시간 TOP 5
10. **랜선 공부방으로 실시간 공부 유저와 함께 할 수 있다.** ⇒ 25  |  40
    1. 사용자의 실시간 접속 여부 처리 - 시간 카운팅 
    2. 1시간마다 세션 연장 여부 Alert 창 띄우기
    3. 이모지 디자인 및 랜덤 배분
    4. 사용자 랭킹 디자인 및 이름 표시
    5. 프로필 요약본 모달.
    6. ( + 기능 : 이모지 사이의 내 위치 표시)
    7. 공부방 채팅 기능 구현.
    8. 공부방 채팅 카테고리 전환 기능.

11. **DM 기능과 접속자 확인 기능으로 서로 소통이 가능하다.** ⇒ 20 | 15
    1. 이중 사이드바 형태
    2. 1:1 채팅 기능
    3. 채팅 메시지 수신 상태
    4. ( + 기능 : 팔로우하는 사람들의 실시간 상태 표시(접속 상태) )

    이번주 + 3 + 1(발표주) ⇒ 일주일에 50시간 '최소' 필요 ⇒ 하루 7시간

    (예상)

    프론트: 150시간, 
    백: 177시간

12. (배포 ⇒  | 10 )
13. (테스팅 ⇒  | ? ) *우선순위는 뒤로*
14. 발표 준비
15. 영상 제작

## 릴리즈 플래닝


### 1 ~ 2주차

1. 기획
2. 설계
3. 환경설정

### 3주차

1. **기본 웹 기능으로 사이트를 이용 할 수 있다.**
2. **팔로우 팔로잉 기능으로 관계 형성이 가능하다.** 
3. **그룹 챌린지로 함께 도전과제를 공유하며 수행 할 수 있다.** 

### 4주차

1. **프로필로 자신의 활동내역과 정보를 공유 할 수 있다.**
2. **피드로 유용한 정보를 실시간으로 얻을 수 있다.** ⇒ (실시간여부따라)
3. **명예의 전당으로 동기부여 될 정보를 얻을 수 있다.** 

### 5주차

1. **랜선 공부방으로 실시간 공부 유저와 함께 할 수 있다.**
2. **DM 기능과 접속자 확인 기능으로 서로 소통이 가능하다.** 

### 6주차

1. 배포
2. 발표
3. 영상 제작