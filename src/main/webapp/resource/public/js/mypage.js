//숫자만 기입하면 -씌여지게 하는 함수
function inputPhoneNumber(obj) {
    let number = obj.value.replace(/[^0-9]/g, "");
    let phone = "";
    if (number.length < 4) {
        return number;
    } else if (number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if (number.substr(1, 1) === "2" && number.length < 10) {
        //case 02-xxx-xxxx
        phone += number.substr(0, 2);
        phone += "-";
        phone += number.substr(2, 3);
        phone += "-";
        phone += number.substr(5, 4);
    } else if (number.substr(1, 1) === "2" && number.length < 11) {
        phone += number.substr(0, 2);
        phone += "-";
        phone += number.substr(2, 4);
        phone += "-";
        phone += number.substr(6, 4);
    } else if (number.length < 11) {
        //case 010-xxx-xxxx, 031-xxx-xxxx
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else { //010-xxxx-xxxx, 031-xxxx-xxxx
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
    }
    obj.value = phone;
}
function divPhoneNumber(num) {
    var phone = "";
    if (num.length < 10) {
        //case : 02-xxx-xxxx
        phone += num.substr(0, 2);
        phone += "-"
        phone += num.substr(2, 3);
        phone += "-"
        phone += str.substr(5, 4);
    } else if (num.substr(1, 1) === "2" && num.length === 10) {
        //case : 02-xxxx-xxxx
        phone += num.substr(0, 2);
        phone += "-"
        phone += num.substr(2, 4);
        phone += "-"
        phone += num.substr(6, 4);
    } else if (num.length === 10) {
        phone += num.substr(0, 3);
        phone += "-"
        phone += num.substr(3, 3);
        phone += "-"
        phone += num.substr(6, 4);
    } else if (num.length === 11) {
        phone += num.substr(0, 3);
        phone += "-"
        phone += num.substr(3, 4);
        phone += "-"
        phone += num.substr(7, 4);
    }
    return phone;
}
function update(jsonData) {
    var backurl = "http://localhost:9999/kkfd/member/";
    $.ajax({
        "method": "PUT",
        "url": backurl,
        "data": jsonData,
        "transformRequest": [null],
        "transformResponse": [null],
        "jsonpCallbackParam": "callback",
        "headers": {
            "Accept": "application/json, text/plain, */*",
            "Content-Type": "application/json;charset=utf-8"
        }, "xhrFields": {
            withCredentials: true
        }, "statusCode": {
            401: function () {
                alert('로그인후 사용해주세요');
                location.replace("/member/login");
            },
            404: function () {
                alert('수정하지 못했습니다. 관리자에게 문의해 주세요.');
            },
            500: function () {
                alert('서버에러, 관리자에게 문의해 주세요.');
            },
            200: function (responseObj) {
                alert("회원정보 수정성공");
                location.reload();
                $('div.mypage_name').text(responseObj.name);
                $('div.mypage_phone').text(responseObj.phone);
                $('input#phone').val(responseObj.phone);
                $('div.mypage_email').text(responseObj.email);
            },//end of 200
        }//end of statusCode
    });//end of Ajax
}//end of function

$(function () {
    var backurl = "http://localhost:9999/kkfd/member/";
    $.ajax({
        "method": "GET",
        "url": backurl,
        "transformRequest": [null],
        "transformResponse": [null],
        "jsonpCallbackParam": "callback",
        "headers": {
            "Accept": "application/json, text/plain, */*"
        }, "xhrFields": {
            withCredentials: true
        },
        "timeout": {},
        "statusCode": {
            401: function () {
                alert('로그인후 사용해주세요');
                location.replace("/member/login");
            },
            500: function () {
                alert('서버에러, 관리자에게 문의해 주세요.');
            },
            200: function (responseObj) {
                var phone = divPhoneNumber(responseObj.phone);
                $('div.mypage_name').text(responseObj.name);
                $('div.mypage_phone').text(phone);
                $('input#phone').val(responseObj.phone);
                $('div.mypage_email').text(responseObj.email);
            },//end of 200
        }//end of statusCode
    });//end of Ajax

    //비밀번호 유효성검사
    var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!^%*#?&]{8,16}$/;
    $('input#pwd1').keyup(function () {
        $('#message2').show();
        if (false === regex.test($(this).val())) {
            $('#message2').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.');
        } else {
            $('#message2').css("color", "green");
            $('#message2').text('사용 가능');
        }
    });

    //핸드폰번호 -빼고 db에전송
    $('button#phoneUp').click(function () {
        var phoneDate = "";
        var $phone = $('input#phone');
        if ($phone.val().length < 12) {
            //case : 02-xxx-xxxx
            phoneDate += $phone.val().substr(0, 2);
            phoneDate += $phone.val().substr(3, 3);
            phoneDate += $phone.val().substr(7, 4);
        } else if ($phone.val().substr(1, 1) === "2" && $phone.val().length === 12) {
            //case : 02-xxxx-xxxx
            phoneDate += $phone.val().substr(0, 2);
            phoneDate += $phone.val().substr(3, 4);
            phoneDate += $phone.val().substr(8, 4);
        } else if ($phone.val().length === 12) {
            phoneDate += $phone.val().substr(0, 3);
            phoneDate += $phone.val().substr(4, 3);
            phoneDate += $phone.val().substr(8, 4);
        } else if ($phone.val().length === 13) {
            phoneDate += $phone.val().substr(0, 3);
            phoneDate += $phone.val().substr(4, 4);
            phoneDate += $phone.val().substr(9, 4);
        }
        var data = '{"phone" : "' + phoneDate + '"}';
        update(data);
        return false;
    });
    /*
          //핸드폰번호 유효성검사 후 수정 요청
          $('button#phoneUp').click(function () {
            var phone = $('input#phone').val()
            var patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
            if (!patternPhone.test(phone)) {
              alert('핸드폰 번호를 확인 해주세요');
              $('input#phone').focus();
              return false;
            }
            var data = '{"phone" : "' + phone + '"}'
            update(data);
            return false;
          });
    */
    //비밀번호 유효성 검사 후 수정 요청
    $('form#pwdUp').submit(function () {
        if ($('input#pwd1').val() != $('input#pwd2').val()) {
            alert("비밀번호 불일치");
            ($('input#pwd2').focus());
            return false;
        }
        if ($('#message2').text() != '사용 가능') {
            alert("비밀번호 형식이 맞지 않습니다.")
                ($('input#pwd1').focus());
            return false;
        }
        var data = '{"pwd" : "' + $('input#pwd1').val() + '"}';
        console.log(data);
        update(data);
        return false;
    });

    //이메일 수정 요청
    $('button#emailUp').click(function () {
        var email = $('input#email1').val() + '@';
        if ($('select#email3').val() != 1) {
            email += $('select#email3').val();
        } else {
            email += $('input#email2').val();
        }
        console.log(email);
        var data = '{"email" : "' + email + '"}'
        console.log(data);
        update(data);
        return false;
    });

    $('select#email3').change(function () {
        var $email2 = $('input#email2');
        if ($(this).val() == "1") {
            $email2.show();
            $(this).width(20);
        } else {
            $email2.hide();
            $(this).width(210);
        }
    });

    var $changeBtn = $('div.mypage_modify > div.mypage_title_container > button.mypage_change_btn');
    $changeBtn.click(function () {
        var $before = $(this).parent().next();//이전 정보
        var $after = $(this).parent().next().next();//input_box
        if ($before.css("display") == "block") {
            $before.hide();
            $after.show();
        } else {
            $before.show();
            $after.hide();
        }
    });
});