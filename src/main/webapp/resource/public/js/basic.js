var backContextPath = 'http://kkfd.eastus.cloudapp.azure.com:9999/kkfd';
var frontContextPath = 'http://localhost';
//천단위 쉼표
function numberWithCommas(x) {
    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
};
//전역변수로 선언, 페이지 콘솔 로그 사용을 막는다.
/*  console = {};
  console.log = function(){};
  console.warn = function(){};
  console.error = function(){};
  console.warn = console.error = () => {};
  (() => { console.warn = console.error = () => {}} )();*/

function logined() {
    $('span.login_signup_name > a.admin-no').hide();
    $('span.login_signup_name > a.admin-yes').show();
}

function logouted() {
    $('span.login_signup_name > a.admin-no').show();
    $('span.login_signup_name > a.admin-yes').hide();
}

function Logout() {
    var backurl = 'http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/member/logout';
    $.ajax({
        url: backurl,
        method: 'get',
        xhrFields: {
            withCredentials: true
        },
        success: function () {
            alert("로그아웃 되었습니다.");
            logouted();
            document.location.reload();
        },
        error: function (xhr) {
            alert("로그아웃 에러");
        },
    });
}

function checkLogined() {
    var backurl = 'http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/member/logincheck';
    $.ajax({
        url: backurl,
        method: 'get',
        dataType: "json",
        xhrFields: {
            withCredentials: true
        },
        statusCode: {
            204: function () {
                logouted();
            },
        },
        success: function (result) {
            logined();
        },
        error: function (result) {
            logouted();
        }
    });
}

// 카카오 간편 로그인 실패시
/*  $.urlParam = function (name) {
    var results = new RegExp('[\?&]' + name + '=([^&#]*)')
            .exec(window.location.href);
    if (results == null) {
      return 0;
    }
    return results[1] || 0;
  }
  $(function () {
    if ($.urlParam('login') === "fail") {
      alert("카카오 간편로그인 실패.");
    }
  });*/