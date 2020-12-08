# TODO

1. 메모 위치 - 값 DB에 들어가지 않도록하기
   > 데스크 탑에서는 듀얼 모니터를 써서 -값이 나온것 같다. 노트북에서는 이 오류가 발생하지 않았다.<br>
   > 다른곳에서도 이 현상이 나올 수 있으니 미리 막아 놓기.
   >> 해결방법 : ( MEMO_LEFT.substring(0,1)==="-"  || MEMO_TOP.substring(0,1)==="-" ) <br>
   >>일때 미리 0이상으로 변화해서 BACK으로 넘기기 <br>
   >> (2020-12-07 18:50 시작 ~ 19:15 완료)
2. 메모 검색시 휴지통에 들어가 있으면 검색되지 않게 하기
   > 검색 조건 추가(query 변경)
   >> 해결방법 : where 절에 MEMO_TRA ='N' 추가<br>
   >> (2020-12-07 18:20 시작 ~ 18:30 완료)<br>
3. 메모 입력시 즉시 DB에 입력
   >지금 잘 적용되지 않음.<br>
   >현재 메모 사이즈 변경시, mouse up 일때 저장하는데 keydown 추가 해야 겠다.
3. 메모 검색 후 클릭시 해당 section으로 이동하고 해당 메모만 표시(or 해당 메모 위치 및 z-index 변경)
   > 원인 : 검색중인 section의 MEMO_NUM 값 받아옴<br>
   >> 해결방법 : setTimeout() 을 사용해서 section 변경 후 MEMO_NUM 값을 찾게 하였음<br>
   > (2020-12-07 14:10 시작 ~ 18:17 완료)<br>
4. 비밀번호 찾기 시 이메일 인증 사용
   > 추가 적으로 회원 가입시 메일 인증 추가도 좋을듯 하다.
5. Front React 로 다시 만들기
   > React 공부 선행
6. Back-end spring boot 로 다시 만들기
   > Spring boot 공부 선행
7. DB AWS mysql로 변경하기
   > React 와 Spring boot 로 변경 후 할것.
8. EC2에 배포하기
   > 최종 version
   

-------------------
