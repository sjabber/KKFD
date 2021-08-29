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

//이미지 미리보기
$(function () {
    $('div.profile_upload > input[type=file]').change(function () {
        /*if ($('img.profile_circle_img').css("display") === "none") {
            $('div.profile_circle_inner').hide();
            $('img.profile_circle_img').show();
        }*/

        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('img.profile_circle_img').attr("src", e.target.result);
            }
            reader.readAsDataURL(this.files[0]);
        }
    });
});

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
