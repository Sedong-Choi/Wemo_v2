# TODO



1. 메모 입력시 즉시 DB에 입력
   >지금 잘 적용되지 않음.<br>
   >현재 메모 사이즈 변경시, mouse up 일때 저장하는데 keydown 추가 해야 겠다.

2. 비밀번호 찾기 시 이메일 인증 사용
   > 추가 적으로 회원 가입시 메일 인증 추가도 좋을듯 하다.
3. Front React 로 다시 만들기
   > React 공부 선행
4. Back-end spring boot 로 다시 만들기
   > Spring boot 공부 선행
5. 설정 자동완성 폼 실행 안됨.

6. 통계 페이지<br>
 ![통계 페이지 오류](/img/analysis.PNG)
 
7.작은 페이지에서 메모의 순서 정렬 뒤 저장 안되는것 해결하기
 > memo table 정령 순서 column 만들고 저장 기능 만들기 


# DONE

1. 메모 검색 후 클릭시 해당 section으로 이동하고 해당 메모만 표시(or 해당 메모 위치 및 z-index 변경)
   > 원인 : 검색중인 section의 MEMO_NUM 값 받아옴<br>
   >> 해결방법 : setTimeout() 을 사용해서 section 변경 후 MEMO_NUM 값을 찾게 하였음<br>
   > `(2020-12-07 14:10 시작 ~ 18:17 완료)`
2. 메모 검색시 휴지통에 들어가 있으면 검색되지 않게 하기
   > 검색 조건 추가(query 변경)
   >> 해결방법 : where 절에 MEMO_TRA ='N' 추가<br>
   >> `(2020-12-07 18:20 시작 ~ 18:30 완료)`
3. 메모 위치 - 값 DB에 들어가지 않도록하기
   > 데스크 탑에서는 듀얼 모니터를 써서 -값이 나온것 같다. 노트북에서는 이 오류가 발생하지 않았다.<br>
   > 다른곳에서도 이 현상이 나올 수 있으니 미리 막아 놓기.
   >> 해결방법 : ( MEMO_LEFT.substring(0,1)==="-"  || MEMO_TOP.substring(0,1)==="-" ) <br>
   >>일때 미리 0이상으로 변화해서 BACK으로 넘기기 <br>
   >> `(2020-12-07 18:50 시작 ~ 19:15 완료)`
4. DB AWS mysql로 변경하기
   > React 와 Spring boot 로 변경 후 할것.`미리 해버렸다`<br>
   > AWS RDS MySql 로 연결하여 local DB 사용하지 않기로 하였다.<br>
   > `(2020-12-08 10:00 시작 ~ 13:28 완료)`
5. EC2에 배포하기
   > 최종 version => Backend : spring , Frontend : React.js 
   > RDS 연결 한 김에 바로 배포 하였다. 
   > `(2020-12-08 14:20 시작 ~ 18:50 완료)`