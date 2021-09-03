// 하이라이트
$(function () {
    var $divIpt = $('div.projectItem_input');
    var $divIpt2 = $('div.projectItem_input2');
    var $divIpt3 = $('div.projectItem_input3');
    var $divIpt4 = $('div.projectItem_input4');
    var $divIpt5 = $('div.projectItem_input5');

    $('html').click(function (e) {
        if ($(e.target).hasClass("inputInner")) {
            $divIpt.css("border", "1px solid black");
        } else {
            $divIpt.css("border", "1px solid #F0F0F0");
        }

        if ($(e.target).hasClass("inputInner2")) {
            $divIpt4.css("border", "1px solid black");
        } else {
            $divIpt4.css("border", "1px solid #F0F0F0");
        }

        if ($(e.target).hasClass("inputInner3")) {
            $divIpt5.css("border", "1px solid black");
        } else {
            $divIpt5.css("border", "1px solid #F0F0F0");
        }

        if ($(e.target).hasClass("textAreaInner")) {
            $divIpt2.css("border", "1px solid black");
        } else {
            $divIpt2.css("border", "1px solid #F0F0F0");
        }

        if ($(e.target).hasClass("textAreaInner2")) {
            $divIpt3.css("border", "1px solid black");
        } else {
            $divIpt3.css("border", "1px solid #F0F0F0");
        }
    });
});

// 설명 숨김 OR 보여줌
$(function () {
    $.contentShow = function (obj) {
        var $desc = obj;
        // alert('설명을 보여준다.');
        // obj.next().show(500);
        $desc.next().slideDown(400, 'linear');
    }

    $.contentShow2 = function (obj) {
        var $desc = obj;
        // alert('설명을 보여준다.');
        // obj.next().show(500);
        $desc.parent().parent().next().slideDown(400, 'swing');
    }

    $.contentHide = function (obj) {
        var $desc = obj;
        // alert('설명 사라짐.');
        // obj.parent().hide(500);
        $desc.parent().slideUp(400, 'linear');
    };


});

// 페이지 변경사항 발생시 저장 유도
$(document).ready(function () {
    var isChange = false;

    //input, select에 change event가 일어난 경우
    $('input, select').change(function () {
        isChange = true;
    });

    //페이지 이동이 있을 경우
    $(window).on("beforeunload", function () {
        // 데이터 변경이 있을 경우
        if (isChange) {
            return "이 페이지를 벗어나면 작성된 내용은 저장되지 않습니다.";
        }
    });

    //저장버튼 클릭시 변경값을 false로 바꿔준다.
    $("button.footer_save_btn").click(function () {
        isChange = false;
        //submit
    });
});

// 글자수 변경
$(document).ready(function () {
    $('#text1').keyup(function () {
        $('#text_cnt1').html("" + $(this).val().length + "/32");

        if ($(this).val().length > 32) {
            $(this).val($(this).val().substring(0, 32));
            $('#text_cnt1').html("32/32");
        }
    });

    $('#text2').keyup(function () {
        $('#text_cnt2').html("" + $(this).val().length + "/32");

        if ($(this).val().length > 32) {
            $(this).val($(this).val().substring(0, 32));
            $('#text_cnt2').html("32/32");
        }
    });

    $('#text3').keyup(function () {
        $('#text_cnt3').html("" + $(this).val().length + "/32");

        if ($(this).val().length > 32) {
            $(this).val($(this).val().substring(0, 32));
            $('#text_cnt3').html("32/32");
        }
    });

    $('#text4').keyup(function () {
        $('#text_cnt4').html("" + $(this).val().length + "/32");

        if ($(this).val().length > 32) {
            $(this).val($(this).val().substring(0, 32));
            $('#text_cnt4').html("32/32");
        }
    });

    $('#text5').keyup(function () {
        $('#text_cnt5').html("" + $(this).val().length + "/32");

        if ($(this).val().length > 32) {
            $(this).val($(this).val().substring(0, 32));
            $('#text_cnt5').html("32/32");
        }
    });
});

//프로필 이미지 미리보기
$(function () {
    $('div.profile_upload > input[type=file]').change(function () {
        /*if ($('img.profile_circle_img').css("display") === "none") {
            $('div.profile_circle_inner').hide();
            $('img.profile_circle_img').show();
        }*/

        if (this.files && this.files[0]) {
            //이미지 파일 타입인지 검증한다.
            var f = Array.prototype.slice.call(this.files);
            if (!f[0].type.match("image.*")) {
                alert("이미지 파일만 업로드 가능합니다.");
                return;
            }

            var reader = new FileReader();
            reader.onload = function (e) {
                $('img.profile_circle_img').attr("src", e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
});

//프로젝트 대표 이미지 미리보기
var titleImg = false;

$(function () {
    $('#ImageUploader1 > input[type=file]').change(function () {

        if (this.files && this.files[0]) {
            //이미지 파일 타입인지 검증한다.
            var f = Array.prototype.slice.call(this.files);
            if (!f[0].type.match("image.*")) {
                alert("이미지 파일만 업로드 가능합니다.");
                return;
            }

            $('#titleImg').show();
            var reader = new FileReader();
            reader.onload = function (e) {
                $('img.project_title_img_box_img').attr("src", e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
            titleImg = true;
        }
    });
});

// 프로젝트 보조 이미지 미리보기 (다중 이미지 미리보기)
var sel_files = [];

$(document).ready(function () {
    $('#ImageUploader2 > input[type=file]').on("change", handleImgsFileSelect);
});

function handleImgsFileSelect(e) {
    // 이미지 정보들 초기화
    sel_files = [];
    $('#subImg').empty();

    $('#subImg').show();
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);

    filesArr.forEach(function (f) {
        if (!f.type.match("image.*")) {
            alert("이미지 파일만 업로드 가능합니다.");
            return;
        }
        sel_files.push(f);

        var reader = new FileReader();
        reader.onload = function (e) {
            var img_html = "<div class=\"project_detail_img_box\">\n" +
                "                    <img src=\"" + e.target.result + "\" + class=\"project_detail_img_box_img\" id=\"detail_img1\">\n" +
                "                </div>";
            $('#subImg').append(img_html);
        }
        reader.readAsDataURL(f);
    });
}

// 버튼 색상 변경 및 섹션 보이기&숨기기
function section1() {
    var section1 = $('#section1');

    $('button.btn-head1').css('background-color', '#FF5757');
    $('button.btn-head1').css('color', "#FFFFFF");

    $('button.btn-head2').css('background-color', '#FFFFFF');
    $('button.btn-head2').css('color', '#000000');

    $('button.btn-head3').css('background-color', '#FFFFFF');
    $('button.btn-head3').css('color', '#000000');

    section1.show();
    $('#section2').hide();
    $('#section3').hide();
    $('#footer').hide();
}

function section2() {
    checkCreator();

    var section2 = $('#section2');

    $('button.btn-head1').css('background-color', '#FFFFFF');
    $('button.btn-head1').css('color', '#000000');

    $('button.btn-head2').css('background-color', '#FF5757');
    $('button.btn-head2').css('color', "#FFFFFF");

    $('button.btn-head3').css('background-color', '#FFFFFF');
    $('button.btn-head3').css('color', '#000000');

    $('#section1').hide();
    section2.show();
    $('#section3').hide();
    $('#footer').hide();
}

function section3() {
    var section3 = $('#section3');

    $('button.btn-head1').css('background-color', '#FFFFFF');
    $('button.btn-head1').css('color', '#000000');

    $('button.btn-head2').css('background-color', '#FFFFFF');
    $('button.btn-head2').css('color', '#000000');

    $('button.btn-head3').css('background-color', '#FF5757');
    $('button.btn-head3').css('color', "#FFFFFF");

    $('#section1').hide();
    $('#section2').hide();
    section3.show();
    $('#footer').show();
}

// 날짜 계산 로직
$('#end_dates').datepicker({
    minDate: new Date(),
});

datePickerSet($("#start_dates"), $("#end_dates"), true);

function datePickerSet(sDate, eDate, flag) {

    //시작 ~ 종료 2개 짜리 달력 datepicker
    if (!isValidStr(sDate) && !isValidStr(eDate) && sDate.length > 0 && eDate.length > 0) {
        var sDay = sDate.val();
        var eDay = eDate.val();

        if (flag && !isValidStr(sDay) && !isValidStr(eDay)) { //처음 입력 날짜 설정, update...
            var sdp = sDate.datepicker().data("datepicker");
            sdp.selectDate(new Date(sDay.replace(/-/g, "/")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요

            var edp = eDate.datepicker().data("datepicker");
            edp.selectDate(new Date(eDay.replace(/-/g, "/")));  //익스에서는 그냥 new Date하면 -을 인식못함 replace필요
        }

        if (!isValidStr(eDay)) {
            //isValidStr -> true, 값이 없는것 -> false 처리됨
            //isValidStr -> false, 값이 있음 -> true 처리됨

            eDay = new Date(eDay);

            // 종료일을 먼저 고른경우 시작일은 종료일 하루 전까지만 가능하도록
            eDay.setDate(eDay.getDate() - 1);
            let dd = eDay.getDate();

            let mm = eDay.getMonth();
            mm += 1;

            let yy = eDay.getFullYear();


            const FormattedDate = yy + '-' + mm + '-' + dd;
            // console.log(FormattedDate);

            sDate.datepicker({
                maxDate: new Date(FormattedDate)
                //maxDate: new Date(eDay.replace(/-/g, "/")),
            });
        }
        //시작일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
        sDate.datepicker({
            language: 'ko',
            autoClose: true,
            minDate: new Date(),
            onSelect: function () {
                datePickerSet(sDate, eDate);
            }
        });


        if (!isValidStr(sDay)) {
            //isValidStr -> true, 값이 없는것 -> false 처리됨
            //isValidStr -> false, 값이 있음 -> true 처리됨

            sDay = new Date(sDay);

            // 시작일을 먼저 고른경우 종료일은 시작일 하루 뒤뒤부터 가능하도록
            sDay.setDate(sDay.getDate() + 1);
            let dd = sDay.getDate();

            let mm = sDay.getMonth();
            mm += 1;

            let yy = sDay.getFullYear();


            const FormattedDate2 = yy + '-' + mm + '-' + dd;
            // console.log(FormattedDate2);

            eDate.datepicker({
                minDate: new Date(FormattedDate2)
                // minDate: new Date(sDay.replace(/-/g, "/"))
            });
        }
        //종료일자 세팅하기 날짜가 없는경우엔 제한을 걸지 않음
        eDate.datepicker({
            language: 'ko',
            autoClose: true,
            onSelect: function () {
                datePickerSet(sDate, eDate);
            }
        });
    }

    function isValidStr(str) {
        if (str === null || str === undefined || str === "")
            return true;
        else
            return false;
    }
}

function checkCreator() {
    var backurl = 'http://localhost:9999/kkfd/creator/';
    $.ajax({
        url: backurl,
        method: 'get',
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        dataType: "json",
        header: {
            Accept: "application/json, text/plain, */*"
        },
        statusCode: {
            204: function () {
                alert('정보 없음');
            },
            401: function () {
                alert('로그인 안되어있음');
            },
            500: function () {
                alert('서버오류, 관리자에게 문의해주세요.');
            }
        },
        success: function (result) {
            var nickName = result.crId;
            var intro = result.crIntro;
            var bank = result.crBank;
            var acno = result.crAcno;
            var acholder = result.crAcholder;

            if (nickName != null) {
                $('#text4').val(nickName);
            }
            if (intro != null) {
                $('#text5').val(intro);
            }
            if (bank != null) {
                $('#bank').val(bank);
            }
            if (acno != null) {
                $('#acno').val(acno);
            }
            if (acholder != null) {
                $('#acholder').val(acholder);
            }

        },
        /*error: function (result) {
            alert('서버오류, 관리자에게 문의해주세요.');
        }*/
    });
}

/*function RegistProject() {
    // 1. 프로젝트 기본정보
    /!*    var p_category = $('select.selectBox option:selected').val();
        var p_title = $('input#text1').val();
        var p_summary = $('textarea#text2').val();
        var p_intro = $('textarea#text3').val();*!/
    var p_category = $('select.selectBox option:selected');
    var p_title = $('input#text1');
    var p_summary = $('textarea#text2');
    var p_intro = $('textarea#text3');
    var p_image = $('input#fileUpload1');

    var projectInfo = [p_category, p_title, p_summary, p_intro];

    // 2. 창작자 정보
    /!*    var c_name = $('input#text4').val();
        var c_intro = $('textarea#text5').val();
        var c_bank = $('select#bank option:selected').val();
        var c_acno = $('input#acno').val();
        var c_acholder = $('input#acholder').val();*!/
    var c_name = $('input#text4');
    var c_intro = $('textarea#text5');
    var c_bank = $('select#bank option:selected');
    var c_acno = $('input#acno');
    var c_acholder = $('input#acholder');

    var creatorInfo = [c_name, c_intro, c_bank, c_acno];

    // 3. 펀딩계획
    /!*    var f_fm = $('input#fm').val();
        var f_limitcnt = $('input#limitcnt').val();
        var f_targetcnt = $('input#targetcnt').val();
        var f_start = $('input#start_dates').val();
        var f_end = $('input#end_dates').val();*!/
    var f_fm = $('input#fm');
    var f_limitcnt = $('input#limitcnt');
    var f_targetcnt = $('input#targetcnt');
    var f_start = $('input#start_dates');
    var f_end = $('input#end_dates');

    var fundingInfo = [f_fm, f_limitcnt, f_targetcnt, f_start, f_end];

    // 필수 입력이 되어있지 않은 경우 포커싱
    // 프로젝트 기본정보 포커싱
    for (var i = 0; i < projectInfo.length; i++) {
        if (projectInfo[i].val() === "") {
            alert('기본 정보의 필수항목을 입력해주세요.');
            section1();
            projectInfo[i].focus();
            return;
        }
    }
    if (titleImg === false) {
        alert('대표 이미지를 업로드해 주세요.')
        section1();
        p_image.focus()
        return;
    }

    // 프로젝트 창작자 정보 포커싱
    for (var i = 0; i < creatorInfo.length; i++) {
        if (creatorInfo[i].val() === "") {
            alert('창작자 정보의 필수항목을 입력해주세요.');
            section2();
            creatorInfo[i].focus();
            return;
        }
    }

    // 프로젝트 펀딩계획 정보 포커싱
    for (var i = 0; i < fundingInfo.length; i++) {
        if (fundingInfo[i].val() === "") {
            alert('펀딩계획의 필수항목을 입력해주세요.');
            section3();
            fundingInfo[i].focus();
            return;
        }
    }

    // todo projDelivery (예상 수령일) 날짜 계산되어서 들어가야한다.
    $.ajax({
        url: 'http://localhost:9999/kkfd/project/register',
        method: 'post',
        // accept: 'application/json', // 멀티파트로 변경한다.
        xhrFields: {
            withCredentials: true
        },
        //form데이터 객체가 필요하다.
        //form input tag의 name 속성 => creator.crId
        //form.serialize();
        data: JSON.stringify({
            'projNo': "",
            "creator":
                {
                    "crId": "",
                    "crNn": c_name.val(),
                    "crIntro": c_intro.val(),
                    "crAcholder": c_acholder.val(),
                    "crBank": c_bank.val(),
                    "crAcno": c_acno.val()
                },
            "projCategory": p_category.val(),
            "projTitle": p_title.val(),
            "projSummary": p_summary.val(),
            "projIntro": p_intro.val(),
            "projFm": f_fm.val(),
            "projTargetcnt": f_targetcnt.val(),
            "projLimitcnt": f_limitcnt.val(),
            "projQuantity": "",
            "projGoals": "",
            "projStart": f_start.val(),
            "projEnd": f_end.val(),
            "projDelivery": "",
            "projBmcnt": "",
            "projStatus": "",
            "projBm": ""
        }),
        contentType: 'application/json; charset=utf-8',
        statusCode: {
            401: function () {
                alert('로그인이 안되어있는 경우 등록 불가!!');
            },
            500: function () {
                alert('서버오류 관리자에게 문의해 주세요.');
            }
        },
        success: function (result) {
            alert('성공적으로 대입됨');
        },
        error: function (result) {
            alert('서버에러 발생, 관리자에게 문의해주세요');
        }
    });
}*/

function RegistProject() {
/*    // 1. 프로젝트 기본정보
    /!*    var p_category = $('select.selectBox option:selected').val();
        var p_title = $('input#text1').val();
        var p_summary = $('textarea#text2').val();
        var p_intro = $('textarea#text3').val();*!/
    var p_category = $('select.selectBox option:selected');
    var p_title = $('input#text1');
    var p_summary = $('textarea#text2');
    var p_intro = $('textarea#text3');
    var p_image = $('input#fileUpload1');

    var projectInfo = [p_category, p_title, p_summary, p_intro];

    // 2. 창작자 정보
    /!*    var c_name = $('input#text4').val();
        var c_intro = $('textarea#text5').val();
        var c_bank = $('select#bank option:selected').val();
        var c_acno = $('input#acno').val();
        var c_acholder = $('input#acholder').val();*!/
    var c_name = $('input#text4');
    var c_intro = $('textarea#text5');
    var c_bank = $('select#bank option:selected');
    var c_acno = $('input#acno');
    var c_acholder = $('input#acholder');

    var creatorInfo = [c_name, c_intro, c_bank, c_acno];

    // 3. 펀딩계획
    /!*    var f_fm = $('input#fm').val();
        var f_limitcnt = $('input#limitcnt').val();
        var f_targetcnt = $('input#targetcnt').val();
        var f_start = $('input#start_dates').val();
        var f_end = $('input#end_dates').val();*!/
    var f_fm = $('input#fm');
    var f_limitcnt = $('input#limitcnt');
    var f_targetcnt = $('input#targetcnt');
    var f_start = $('input#start_dates');
    var f_end = $('input#end_dates');

    var fundingInfo = [f_fm, f_limitcnt, f_targetcnt, f_start, f_end];

    // 필수 입력이 되어있지 않은 경우 포커싱
    // 프로젝트 기본정보 포커싱
    for (var i = 0; i < projectInfo.length; i++) {
        if (projectInfo[i].val() === "") {
            alert('기본 정보의 필수항목을 입력해주세요.');
            section1();
            projectInfo[i].focus();
            return;
        }
    }
    if (titleImg === false) {
        alert('대표 이미지를 업로드해 주세요.')
        section1();
        p_image.focus()
        return;
    }

    // 프로젝트 창작자 정보 포커싱
    for (var i = 0; i < creatorInfo.length; i++) {
        if (creatorInfo[i].val() === "") {
            alert('창작자 정보의 필수항목을 입력해주세요.');
            section2();
            creatorInfo[i].focus();
            return;
        }
    }

    // 프로젝트 펀딩계획 정보 포커싱
    for (var i = 0; i < fundingInfo.length; i++) {
        if (fundingInfo[i].val() === "") {
            alert('펀딩계획의 필수항목을 입력해주세요.');
            section3();
            fundingInfo[i].focus();
            return;
        }
    }*/

    // formData, 파일 여러개 업로드
    var formData = new FormData();
    var b = [];
    for(var i = 0; i < document.forms.length; i++) {
        var form = document.forms[i];
        var data = new FormData(form);
        var formValues = data.entries();

        while (!(ent = formValues.next()).done) {
            formData.append(`${ent.value[0]}[]`, ent.value[1]);
        }
    }

    // form data 항목 출력.
    formData.forEach(function (value, key) {
        console.log(key + ":" + value);
    });

    // todo projDelivery (예상 수령일) 날짜 계산되어서 들어가야한다.
    $.ajax({
        url: 'http://localhost:9999/kkfd/project/register',
        method: 'post',
        // accept: 'application/json', // 멀티파트로 변경한다.
        xhrFields: {
            withCredentials: true
        },
        contentType : false,
        processData: false,
        //form데이터 객체가 필요하다.
        //form input tag의 name 속성 => creator.crId
        //form.serialize();
        data: formData,
        statusCode: {
            401: function () {
                alert('로그인이 안되어있는 경우 등록 불가!!');
            },
            500: function () {
                alert('서버오류, 관리자에게 문의해 주세요.');
            }
        },
        success: function (result) {
            alert('성공적으로 대입됨');
        },
        error: function (result) {
            alert('서버오류2, 관리자에게 문의해 주세요.');
        }
    });
}

function fileUpload() {
    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "http://localhost:9999/kkfd/importTargets",
        data: data,
        processData: false,
        contentType: false,
        async: false,
        cache: false,
        timeout: 600000,
        xhrFields: {
            withCredentials: true
        },
        success: function (result) {
            // console.log("SUCCESS : ", result.data);
            alert('전송이 완료되었습니다.');
            location.reload();
        },
        error: function (result) {
            if (result.status === 500) {
                alert('서버에러');
            } else if (r.status === 403) {
                Refresh();
                alert("세션 갱신, 다시 클릭해 주세요.");
            } else if (result.status === 405) {
                alert("훈련대상은 최대 300명 까지만 등록이 가능합니다. ");
            } else {
                alert('파일을 확인해주세요');
            }
            // console.log("ERROR : ", result.status);
        }
    });
}


// 섹션 교체 기능
/*$(function () {
    //checkLogined();
    // 메뉴 버튼 객체들
    var $menuObj = $('div.main-btn > div.main-btn_inner > button');

    // 글쓰기 버튼
    var $writeBtn = $('button.writeBtn.btn.btn-primary');

    // 세션 객체
    var $section = $('#section');

    $menuObj.click(function () {
        // 클릭된 객체 href 속성값
        var href = $(this).attr('href');
        switch (href) {
            case '/register/regist/basicInfo':
            case '/register/regist/creatorInfo':
            case '/register/regist/fundingInfo':
                $menuObj.css({background: "#ffffff", color: "#000000"})
                $(this).css({background: "#FF5757", color: "#ffffff"});
                $section.load(href, function (responseTxt, statusTxt, xhr) {
                    if (statusTxt == 'error') alert('Error: ' + xhr.status + ': ' + xhr.statusText);
                });
                break;
        }
        // return false를 하지 않을 경우 페이지 자체가 이동된다.
        return false;
    });
});*/
