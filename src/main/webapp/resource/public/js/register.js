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
