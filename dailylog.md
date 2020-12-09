# Dailylog.detail

## 2020-12-03
**기본 java project 생성**<br>
**Wemo project 소스파일 import**
STS 에서 Spring MVC project로 만들었다.<br>
설정은 최대한 유지하면서 IntelliJ로 옮겨야 겠다.<br>
이전에 한번 옮기는 것을 연습했을 때 여러 시행착오를 많이 겪었다.<br>
이번에도 많은 시행 착오가 있었고 그것에 대해 이야기해 보겠다.<br>
> Spring Framework를 공부하면서 느낀점은 기본 세팅이 가장 어려 운것 같다.

### 1. Create Maven Project
 기본 틀이 없는 maven project 부터 만든 이후에 Add Framework Support를 통해 Spring-mvc를 추가했다.<br>
 그러면 package icon과 비슷한 web 폴더 및 index.jsp, WEB-INF/*.xml 파일이 생성된다.<br>
 처음에 web 폴더 안에 들어있는 파일들을 STS에서와 구조가 같게 만들려고 위치를 src/main/webapp 으로 옮겼더니 server가 실행되지 않았다.(중요!)<br>
> 구조 변경 후 설정 파일의 경로 문제로 설정이 들어가 있는 xml파일을 찾지 못해 실행이 안되었던 것이다.

 추가적으로 mysql과 연동을 해야 했지만, cannot load jdbc driver class 오류가 났다.
> 이 오류는 tomcat library에 mysql-connector-java.8.0.xx.jar 를 넣어 줬더니 해결됐다.

 다시 접속해보니 server time zone 에러가 발생했다.
> 설정 파일 applicationContext.xml 의 bean id="dataSource"의 url 설정을 다음과 같이 바꾸면 해결된다.
> <property value="jdbc:mysql://127.0.0.1:3306/wemo_data?serverTimezone=Asia/Seoul" name="url"/>
> <br> 이 오류는 spring 세팅할때마다 뜨는것 같다.
---------------

## 이제 회원가입과 로그인 가능 상태가 되었다.
회원 가입을 하면 첫 메모가 자동으로 입력되게 세팅했는데 지금은 입력이 안된다.<br>
다음 목표는 메모를 입력하는것 까지 복원하는 것이다.

2020-12-03 END!
---------------

## 2020-12-04
**회원 가입 및 로그인 가능**
 그러나 MEMO TABLE 설계가 잘못되있었다.
> 모든 메모를 한 TABLE 에 입력하게 만들었다.(회원 가입시 최초 입력 memo 쿼리도 잘못되었다.)<br>
> 회원가입시 PK인 MEMO_NUM 이 항상 1로 되어있었고, 회원 가입시 memo insert method 로 연결되어있지 않았다.
 

 **해결방안**
 1. 계정별로 새로운 MEMO TABLE을 만들기 <br>
 장점 : 사용자가 많아져도 MEMO 데이터 관리가 편할것이다.<br>
 단점 : 사용자가 많아지면 TABLE의 개수가 많아진다.<br>
 2. MEMO TABLE 하나만 사용하기<br>
 장점 : 쿼리 작성 편리<br>
 단점 : MEMO 을 개수가 많아지면 데이터를 불러올때 시간이 많이 소요될듯하다.<br>
> 나는 우선 하나의 테이블에 입력하기로 결정 했다.<br>
> Memo.xml 에서 memoForNewAccount 부분은 수정했다.<br>
> 그 다음 MemberController 에서 회원가입시 최초 memo insert 부분은 연결하였다.(회원가입시 기본 메모 입력 잘 됨)<br>

새로운 문제가 발생했다.<br>
브라우저에서 메모의 위치 좌표를 받아 저장하는데 웹 페이지 밖의 위치 좌표가 할당되어 새로고침시 메모가 보이지 않는다.<br>
좌표가 - 가 되는것은 막아둬야 겠다. 또한 DB도 조금더 괜찮은 방향으로 변경할 생각을 해봐야 겠다.

2020-12-04 END!
-------------

## 2020-12-07
**검색된 메모 클릭시 section으로 이동 및 해당 메모 만 보이기**
이전 기능 : 전체 메모에서 검색 & 현재 section에 있을 시에만 선택 메모만 표시<br>
변경된 기능 : 
 * 검색된 메모 클릭시 해당 메모 section으로 이동 및 해당 메모만 보여지게 함.
 * 휴지통에 있는 메모는 검색이 안됨.

**메모 위치 - 일때 DB에 입력 방지**
 * 데스크 탑에서 있던 오류
 * memo poroperties 의 MEMO_TOP, MEMO_LEFT의 첫 글자가 - 일때 
   MATH.random()을 사용해 양수로 변환 후 값을 넘긴다


2020-12-07 END!
----------------
## 2020-12-08
**AWS RDS 연결**
 * AWS RDRDS로 MySql생성 후 database.properties 생성했다. 어제까지만 해도 로드가 되었던 JDBC Driver가 로드가 안된다..
 `경로 설정이 잘못된것 같다`
 * 경로 고치던 중 context.xml 들의 mapping 이 안되는 오류가 생겼다.(11:28)
 `git rollback 해서 다시 시작했다.`
 * rollback 후 url 을 AWS RDS endpoint/`사용할SchemaName`로 설정 후 연결 해 주었더니 잘 연결되었다.
 * 추가 적으로 oracle 에서는 column이름이 대소문자 구분을 안하였는데 , MySql은 대소문자 구분을 해서 .xml 부분을 수정해주었다.
 
**EC2 배포**
 * 우선 SPRING FRAMEWORK 구조로 바꿨다. web > src/main/webapp 으로 변경 후 web.xml 및 config 파일을 변경했다.
 * war로 build 한 후 ec2에 tomcat 설치 및 설정 하였다. `https://www.bsidesoft.com/7123` 참조하여 설정 하였다.
 * Wemo Project link [이동](http://52.79.214.36:8080/wemo/)
 
 -------------
 
 ## 2020-12-09
 **배포 후 몇가지 기능이 안되는 것을 확인했다**
 1. 사용자 지정 완성 품 안됨.
 2. 캘린더 저장안됨 (클릭시 html body 부분에 나오게 변경해야 할 듯하다.)<br>
 
 **기능 추가 할 것** (TODO에 입력하기)
 1. 브라우저의 크기가 작아질 시 순서 변경 후 저장 안됨.
 

 