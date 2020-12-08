<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>WeMo 캘린더</title>

    <link rel="stylesheet" href="resources/css/fullcalendar.min.css" />
    <link rel="stylesheet" href="resources/css/bootstrap.min2.css" />
    <link rel="stylesheet" href='resources/css/select2.min.css' />
    <link rel="stylesheet" href='resources/css/bootstrap-datetimepicker.min.css' />
    <link rel="stylesheet" href="resources/css/main.css"/>
    <link rel="stylesheet" href="resources/css/WeMo_Main_CSS.css"/>
    <style>
    .Cal-div {
    	width : 80%;    
    }
    .btn-back{
    	margin-left : 100px;
    }
    </style>
</head>

<body>
	<span style="display:none" id="USER_EMAIL">${USER_EMAIL }</span>
 	<button type = "button" class = "btn btn-outline-dark btn-back" onClick="history.back()">메모장으로 돌아가기</button>
    <div class="container Cal-div">
   

        <!-- 일자 클릭시 메뉴오픈 -->
        <div id="contextMenu" class="dropdown clearfix">
            <ul class="dropdown-menu dropNewEvent" role="menu" aria-labelledby="dropdownMenu"
                style="display:block;position:static;margin-bottom:5px;">
                <li><a tabindex="-1" href="#">공부</a></li>
                <li><a tabindex="-1" href="#">운동</a></li>
                <li><a tabindex="-1" href="#">가계부</a></li>
                <li class="divider"></li>
                <li><a tabindex="-1" href="#" data-role="close">Close</a></li>
            </ul>
        </div>

        <div id="wrapper">
            <div id="loading"></div>
            <div id="calendar"></div>
        </div>


        <!-- 일정 추가 MODAL -->
        <div class="modal" tabindex="-1" role="dialog" id="eventModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"></h4>
                    </div>
                    <div class="modal-body">

                        <div class="row">
                            <div class="col-md-8">
                                <label class="col-xs-4" for="edit-allDay">하루종일</label>
                                <input class='allDayNewEvent' id="edit-allDay" type="checkbox" />
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-md-8">
                                <label class="col-xs-4" for="edit-title">일정명</label>
                                <input class="inputModal" type="text" name="edit-title" id="edit-title"
                                    required="required" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <label class="col-xs-4" for="edit-start">시작 시간</label>
                                <input class="inputModal" type="text" name="edit-start" id="edit-start" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <label class="col-xs-4" for="edit-end">종료 시간</label>
                                <input class="inputModal" type="text" name="edit-end" id="edit-end" />
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <label class="col-xs-4" for="MEMO_SUB">구분</label>
                                <select class="inputModal" id="MEMO_SUB">
                                    <option value="STUDY">공부</option>
                                    <option value="HEALTH">운동</option>
                                    <option value="MONEY">가계부</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8">
                                <label class="col-xs-4" for="edit-color">색상</label>
                                <select class="inputModal" name="edit-color" id="MEMO_COLOR">
                                    <option value="#B6F2CB" style="color:#B6F2CB; font-weight:700">메모색1</option>
                                    <option value="#C3F2B6" style="color:#C3F2B6; font-weight:700">메모색2</option>
                                    <option value="#EBF2B6" style="color:#EBF2B6; font-weight:700">메모색3</option>
                                    <option value="#F2D7B6" style="color:#F2D7B6; font-weight:700">메모색4</option>
                                    <option value="#F2B6B6" style="color:#F2B6B6; font-weight:700">메모색5</option>
                                </select>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-8	">
                                <label class="col-xs-4" for="edit-desc">설명</label>
                                <textarea rows="4" cols="50" class="inputModal" name="edit-desc"
                                    id="MEMO_TEX"></textarea>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer modalBtnContainer-addEvent">
                        <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
                        <button type="button" class="btn btn-primary" id="save-event">저장</button>
                    </div>
                    <div class="modal-footer modalBtnContainer-modifyEvent">
                        <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                        <button type="button" class="btn btn-danger" id="deleteEvent">삭제</button>
                        <button type="button" class="btn btn-primary" id="updateEvent">저장</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

        <div class="panel panel-default">

            <div class="panel-heading">
                <h3 class="panel-title">필터</h3>
            </div>

            <div class="panel-body">

                <div class="col-lg-6">
                    <label for="calendar_view">구분별</label>
                    <div class="input-group">
                        <select class="filter" id="type_filter" multiple="multiple">
                            <option value="공부">공부</option>
                            <option value="운동">운동</option>
                            <option value="가계부">가계부</option>
                        </select>
                    </div>
                </div>

                <div class="col-lg-6">
                    <label for="calendar_view">등록자별</label>
                    <div class="input-group">
                        <label class="checkbox-inline"><input class='filter' type="checkbox" value="${USER_EMAIL }"
                                checked>${USER_EMAIL }</label>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.filter panel -->
    </div>
    <!-- /.container -->
    <script src="resources/js/jquery.min.js"></script>
    <script src="resources/js/bootstrap.min2.js"></script>
    <script src="resources/js/moment.min.js"></script>
    <script src="resources/js/fullcalendar.min.js"></script>
    <script src="resources/js/ko.js"></script>
    <script src="resources/js/select2.min.js"></script>
    <script src="resources/js/bootstrap-datetimepicker.min.js"></script>
    <script src="resources/js/main.js"></script>
    <script src="resources/js/addEvent.js"></script>
    <script src="resources/js/editEvent.js"></script>
    <script src="resources/js/etcSetting.js"></script>
    <script src="resources/js/WeMo_Main_Functions.js"></script>
    
</body>

</html>