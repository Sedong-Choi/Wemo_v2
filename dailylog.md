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
--------------
### 2020-12-03 END!

 
 