# Ham-Diary App (Android)

> ### 소개
귀여운 햄스터 감정 아이콘과 함께 하루를 기록할 수 있는 간단한 일기장 어플입니다.   
기능을 간략히 소개하자면 다음과 같습니다.
1. 일기 생성, 삭제, 조회
2. 햄스터 아이콘으로 감정 표현
3. 날짜별 감정 기록 조회 
<br/><br/>

> ### Work Flow
![HamDiary_01](https://user-images.githubusercontent.com/69359774/174020210-4a535a79-e7c0-42bd-9424-e2e782336f70.png)
![HamDiary_02](https://user-images.githubusercontent.com/69359774/174020218-9dc4df18-284d-44d1-8819-a3551efca2bb.png)
<br/><br/>

> ### 프로그램 구조
|액티비티|설명|
|:------:|:---:|
|SplashAcitivity|앱 실행시 1초간 보이는 화면|
|MainActicity|날짜 선택 시 SlectEmotionActivity 혹은 ReadDiaryActicity 이동<br/>감정 기록 보기 선택 시 EmotionsActivity로 이동|
|SelectEmotionActivity|감정 선택 화면|
|WritingDiaryActicity|일기 작성 화면|
|ReadDiaryActicity|일기 조회 화면|
|EmotionsActivity|감정을 모아볼 수 있는 화면| 

<br/><br/>

> ### 개발 환경
Windows 11 (x64)   
Android Studio 2021.1.1 (x64)
