var idCheck = null;
var pwdRegexCheck;
var pwdEqualCheck;

/*
function DaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            $('#zipNo').val(data.zonecode);

            var extraAddr = '';

            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }

                $('#roadAddrPart').val(data.roadAddress + extraAddr);
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                $('#roadAddrPart').val(data.jibunAddress);
            }

            $('#addrDetail').focus();
        }
    }).open();
}
*/

function clientSignUp() {
    var backurl = 'http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/member/signup';
    var name = $('div.join_form > ul > li > div.renew_input > input[name=mem_userName]').val();
    var phone = $('div.join_form > ul > li > div.renew_input > input[name=mem_userPhone]').val();
    var email1 = $('div.join_form > ul > li > div.input_box_m > input[name=mem_userEmail]').val();
    var email2 = $('div.join_form > ul > li >  div.input_box_m > input[name=mem_userEmail2]').val();
    if (idCheck === true && pwdEqualCheck === true && pwdRegexCheck === true && name !== "" && phone !== ""
        && email1 !== "" && email2 !== "") {
        $.ajax({
            url: backurl,
            method: 'post',
            accept: 'application/json',
            data: JSON.stringify({
                'id': $('div.join_form > ul > li > div.renew_input > input[name=mem_userid]').val(),
                'pwd': $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw]').val(),
                'name': name,
                'phone': phone,
                'email': email1 + "@" + email2
            }),
            contentType: 'application/json; charset=utf-8',
            statusCode: {
                400:function () {
                    alert('사용할 수 없는 아이디 입니다.');
                },
                500:function () {
                    alert('서버에러, 관리자에게 문의해 주세요.');
                }
            },
            success: function (result) {
                alert("회원가입 되었습니다.");
                document.location.href = '/member/login';
            },
        });
    } else if (idCheck === null) {
        // $('button.id_search').focus();
        $('input[name=mem_userid]').focus();
        alert("아이디 중복확인을 해주세요.");
    } else if (idCheck === false) {
        $('div.join_form > ul > li > div.renew_input > input[name=mem_userid]').focus();
        alert("사용할 수 없는 아이디 입니다.");
    } else if (pwdRegexCheck == null) {
        $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw]').focus();
        alert("비밀번호를 입력해 주세요.");
    } else if (pwdEqualCheck === false) {
        $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw2]').focus();
        alert("비밀번호가 일치하지 않습니다.");
    } else if (pwdRegexCheck === false) {
        $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw]').focus();
        alert("비밀번호 형식이 올바르지 않습니다.");
    } else if (name === "") {
        alert("이름을 입력해 주세요.");
        $('div.join_form > ul > li > div.renew_input > input[name=mem_userName]').focus();
    } else if (phone === "") {
        $('div.join_form > ul > li > div.renew_input > input[name=mem_userPhone]').focus();
        alert("휴대전화 번호를 입력해 주세요.");
    } else if (email1 === "") {
        $('div.join_form > ul > li > div.input_box_m > input[name=mem_userEmail]').focus();
        alert("이메일을 입력해 주세요");
    } else if (email2 === "") {
        $('div.join_form > ul > li >  div.input_box_m > input[name=mem_userEmail2]').focus();
        alert("이메일을 입력해 주세요");
    } else {
        alert("에러발생");
    }
}

function duplicateCheck() {
    var id = $('div.join_form > ul > li > div.renew_input > input[name=mem_userid]').val();
    if (id == "") {
        alert("아이디를 입력하세요.");
    } else {
        var backurl = 'http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/member/' + id;
        $.ajax({
            url: backurl,
            method: 'get',
            statusCode: {
                400:function () {
                    alert('사용할 수 없는 아이디 입니다.');
                    $('#message1').show();
                    $('#message1').css("color", "red");
                    $('#message1').text('사용할 수 없는 아이디 입니다.');
                    idCheck = false;
                },
                500:function () {
                    alert('서버에러, 관리자에게 문의해 주세요.');
                    $('#message1').show();
                    $('#message1').css("color", "red");
                    $('#message1').text('서버에러 발생!');
                    idCheck = false;
                }
            },
            success: function (result) {
                //중복 아이디 없는 경우
                alert('사용가능한 아이디 입니다.');
                $('#message1').show();
                $('#message1').css("color", "green");
                $('#message1').text('사용가능한 아이디 입니다.');
                idCheck = true;
            },
            /*            error: function (xhr) {
                            var data = $.trim(xhr);
                            alert(data.status);
                            alert(data);
                            console.log(data);
                            if (data.status === 400) { // 중복 아이디 있는 경우
                                alert('사용할 수 없는 아이디 입니다.');

                            } else if (data.status === 500) {
                                alert('서버에러');
                            }
                        }*/
        });
    }
}

$(function () {
    $('#message2').hide();
    var regex = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!^%*#?&]{8,16}$/;
    $('input[name=mem_userPw]').keyup(function () {
        $('#message2').show();
        var pwd = $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw]').val();
        if (false === regex.test(pwd)) {
            $('#message2').text('8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.');
            pwdRegexCheck = false;
        } else {
            $('#message2').css("color", "green");
            $('#message2').text('사용 가능');
            pwdRegexCheck = true;
        }
    });
});
// 패스워드 일치 검사
$(function () {
    $('#message3').hide();
    $('input').keyup(function () {
        var pwd1 = $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw]').val();
        var pwd2 = $('div.join_form > ul > li > div.renew_input > input[name=mem_userPw2]').val();
        if (pwd1 != "" || pwd2 != "") {
            if (pwd1 !== pwd2) {
                $('#message3').show();
                $('#message3').text('패스워드가 일치하지 않습니다.');
                pwdEqualCheck = false;
            } else {
                $('#message3').hide();
                pwdEqualCheck = true;
            }
        }
    });
});

// 이메일 선택박스
function selectEmail(ele) {
    var $ele = $(ele);
    var $email2 = $('input[name=mem_userEmail2]');
    if ($ele.val() == "1") {
        $email2.attr('readonly', false);
        $email2.val('');
    } else {
        $email2.attr('readonly', true);
        $email2.val($ele.val());
    }
}