function DaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      $("#input_zipcode").val(data.zonecode);

      var extraAddr = "";

      if (data.userSelectedType === "R") {
        // 사용자가 도로명 주소를 선택했을 경우
        // 법정동명이 있을 경우 추가한다. (법정리는 제외)
        // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
        if (data.bname !== "" && /[동|로|가]$/g.test(data.bname)) {
          extraAddr += data.bname;
        }
        // 건물명이 있고, 공동주택일 경우 추가한다.
        if (data.buildingName !== "" && data.apartment === "Y") {
          extraAddr +=
            extraAddr !== "" ? ", " + data.buildingName : data.buildingName;
        }
        // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
        if (extraAddr !== "") {
          extraAddr = " (" + extraAddr + ")";
        }

        $("#input_addr").val(data.roadAddress + extraAddr);
      } else {
        //사용자가 지번 주소를 선택했을 경우(J)
        $("#input_addr").val(data.roadAddress + extraAddr);
        //$("#input_addr").val(data.jibunAddress);
      }

      $("#input_detail").focus();
    },
  }).open();
}

function goBack() {
  window.history.back();
}

function searchParam(key) {
  return new URLSearchParams(location.search).get(key);
}

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

var projFm = 0;
var dt = new Date();
$(function () {
  const URLSearch = new URLSearchParams(location.search);
  var no = searchParam("no");
  console.log(no);
  var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + no;
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    xhrFields: { withCredentials: true },
    url: backurl,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    timeout: {},
    success: function (responseData) {
      $(responseData).each(function (i, e) {
        var projNo = e.projNo;
        var projCategory = e.projCategory;
        var projTitle = e.projTitle;
        var crId = e.creator.crId;
        var crNn = e.creator.crNn;
        var crIntro = e.creator.crIntro;
        var projIntro = e.projIntro;
        projFm = e.projFm;
        var projTargetcnt = e.projTargetcnt;
        var projLimitcnt = e.projLimitcnt;
        var projQuantity = e.projQuantity;
        var projGoals = e.projGoals;
        var projStart = e.projStart;
        var projEnd = e.projEnd;
        var projDelivery = e.projDelivery;
        var projBm = e.projBm;
        var sum = projFm * projQuantity;
        console.log(funding_quantity);
        $("div.category_nickname>span.category").html(
          categoryNoChange[projCategory]
        );
        $("div.category_nickname>span.cr_nn").html(crNn);
        $("div.proj_title").html(projTitle);
        $("div.represent_img>img.thumbnail_img")
          .attr("src", "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/project/" + no + "/" + no + "_t.png")
          .attr(
            "onerror",
            "this.src='/img/project/" + no + "/" + no + "_t.jpg';"
          );
        $("span.fm").html(numberWithCommas(sum) + "원");
        $("span.goal_pct").html(numberWithCommas(projGoals) + "%");
        $("div.box_row>span.fm").html(numberWithCommas(projFm) + "원");
        $("div.box_row>span.delivery").html(projDelivery);
        $("div.box_row>span.final_fm").html(
          numberWithCommas(projFm * 1) + "원"
        );
        $("div.pay_info>span.funding_paydate").html(projEnd);
        console.log(dt);

        $("#apply_btn").click(function () {
          if ($("input#input_receiver").val() == "") {
            alert("받는 사람을 입력해주세요");
            return false;
          } else if ($("input#input_addr").val() == "") {
            alert("주소를 입력해주세요");
            return false;
          } else if ($("input#input_detail").val() == "") {
            alert("상세주소를 입력해주세요");
            return false;
          } else if ($("select#cmpypage_bankname option:selected").val() == 0) {
            alert("은행을 선택해주세요");
            return false;
          } else if ($("input#input_acno").val() == "") {
            alert("계좌번호를 입력해주세요");
            return false;
          }

          $.ajax({
            method: "POST",
            transformRequest: [null],
            transformResponse: [null],
            jsonpCallbackParam: "callback",
            xhrFields: { withCredentials: true },
            url: "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/funding",
            headers: {
              Accept: "application/json, text/plain, */*",
              "Content-Type": "application/json;charset=utf-8",
            },
            data:
              '{"project":{"projNo":"' +
              projNo +
              '"},"funQuantity":' +
              $("input#funding_quantity").val() +
              ',"funFm":' +
              projFm * $("input#funding_quantity").val() +
              ',"funName":"' +
              $("input#input_receiver").val() +
              '","funAddress":"' +
              $("input#input_addr").val() +
              '","funDetail":"' +
              $("input#input_detail").val() +
              '","funBank":"' +
              $("select#cmpypage_bankname option:selected").val() +
              '","funAcno":"' +
              $("input#input_acno").val() +
              '"}',
            timeout: {},
            statusCode: {
              300: function () {
                alert(
                  "주문수량이 재고보다 많거나 이미 제한수량에 도달한 프로젝트입니다"
                );
                location.replace("/project/info?no=" + projNo);
              },
              401: function () {
                alert("로그인하세요");
                goBack();
              },
              500: function () {
                alert("서버 에러, 관리자에게 문의해주세요");
                goBack();
              },
            },
            success: function (responseObj) {
              alert("신청 완료");
              location.replace("/member/funlist");
            },
          });
        });
      });
    },
  });

  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    xhrFields: { withCredentials: true },
    url: "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/member/",
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    success: function (responseData) {
      $(responseData).each(function (i, e) {
        var email = e.email;
        var phone = e.phone;
        $("span.mem_email").html(email);
        $("span.mem_phone").html(phone);
      });
    },
    error: function (jqXHR) {
      alert("로그인하세요");
      goBack();
    },
  });

  $("#funding_quantity").change(function () {
    console.log($(this).val());
    console.log(projFm);
    var totalfm = $(this).val() * projFm;
    $("div.box_row>span.final_fm").html(numberWithCommas(totalfm) + "원");
  });

  $("input.ckbox").click(function (e) {
    if ($("#ckbox1").is(":checked") && $("#ckbox2").is(":checked")) {
      $("#apply_btn").attr("disabled", false);
    } else {
      $("#apply_btn").attr("disabled", true);
    }
  });
});
