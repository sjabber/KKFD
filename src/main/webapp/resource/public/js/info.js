function goBack() {
  window.history.back();
}

function searchParam(key) {
  return new URLSearchParams(location.search).get(key);
}

function numberWithCommas(x) {
  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

function dayBetween(startDay, endDay) {
  var termMiliSec = endDay.getTime() - startDay.getTime();
  var term = termMiliSec / (60 * 1000 * 60 * 24);
  return Math.ceil(term);
}

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
    statusCode: {
      204: function () {
        alert("없는 프로젝트입니다");
        goBack();
      },
      500: function () {
        alert("서버 에러, 관리자에게 문의해주세요");
        goBack();
      },
    },
    success: function (responseData) {
      $(responseData).each(function (i, e) {
        var projNo = e.projNo;
        var projTitle = e.projTitle;
        var crId = e.creator.crId;
        var crNn = e.creator.crNn;
        var crIntro = e.creator.crIntro;
        var projIntro = e.projIntro;
        var projFm = e.projFm;
        var projTargetcnt = e.projTargetcnt;
        var projLimitcnt = e.projLimitcnt;
        var projQuantity = e.projQuantity;
        var projGoals = e.projGoals;
        var projStart = e.projStart;
        var projEnd = e.projEnd;
        var projBm = e.projBm;
        var projStatus = e.projStatus;
        var sum = projFm * projQuantity;
        $("div.proj_title").html(projTitle);
        $("div.creator_profile_img>img")
          .attr("src", "http://kkfd.eastus.cloudapp.azure.com:9999/kkfdhttp://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/profile/" + crId + "/" + crId + ".png")
          .attr(
            "onerror",
            "this.src='/img/profile/" + crId + "/" + crId + ".jpg';"
          );
        $("div.creator_name>span").html(crNn);
        $("span.sum").html(numberWithCommas(sum));
        $("span.pct").html(projGoals + "%");
        $("span.start").html(projStart);
        $("span.end").html(projEnd);
        $("span.quantity").html(projQuantity);
        $("span.limit").html(projLimitcnt);
        $("span.fm").html(numberWithCommas(projFm));
        $("span.target_cnt_fm").html(numberWithCommas(projTargetcnt * projFm));
        $("div.represent_img>img.represent_img")
          .attr("src", "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/project/" + no + "/" + no + "_t.png")
          .attr(
            "onerror",
            "this.src='/img/project/" + no + "/" + no + "_t.jpg';"
          );
        for (var i = 1; i < 5; i++) {
          $("div.mid_img_" + i + ">img.mid_imgss_" + i)
            .attr("src", "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/project/" + no + "/" + no + "_" + i + ".png")
            .attr(
              "onerror",
              "this.src='/img/project/" + no + "/" + no + "_" + i + ".jpg';"
            );
        }
        $("textarea.content").html(projIntro);
        $("div.mid_creator_profile_img>img.mid_creator_profile_img")
          .attr("src", "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/profile/" + crId + "/" + crId + ".png")
          .attr(
            "onerror",
            "this.src='/img/profile/" + crId + "/" + crId + ".jpg';"
          );
        $("div.mid_creator_info span").html(crNn);
        $("div.mid_creator_intro").html(crIntro);

        var now = new Date();
        var start = new Date(projStart);
        var end = new Date(projEnd);
        if (
          end.getTime() > now.getTime() &&
          now.getTime() > start.getTime() &&
          projQuantity < projLimitcnt &&
          projStatus == 1
        ) {
          $("#go_to_apply_btn")
            .html("펀딩 신청")
            .attr("onclick", "location.href='/project/apply?no=" + no + "'");
          $("#go_to_apply_btn").attr("disabled", false).show();
        } else {
          $("#go_to_apply_btn").html("신청 불가").attr("disabled", true).show();
        }

        if (projBm == 1) {
          $("#bm_btn").removeClass("off");
          $("#bm_icon").removeClass("far fa-bookmark");
          $("#bm_icon").addClass("fas fa-bookmark");
        }

        $("#bm_btn").click(function () {
          if ($(this).hasClass("off")) {
            console.log("if");
            $.ajax({
              url: "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + projNo + "/bookmark",
              method: "POST",
              transformRequest: [null],
              transformResponse: [null],
              jsonpCallbackParam: "callback",
              xhrFields: { withCredentials: true },
              headers: {
                Accept: "application/json, text/plain, */*",
                "Content-Type": "application/json;charset=utf-8",
              },
              data: '{ "projNo":"' + projNo + '" }',
              timeout: {},
              success: function (responseData) {
                alert("북마크에 추가되었습니다");
                $("#bm_icon").removeClass("far fa-bookmark");
                $("#bm_icon").addClass("fas fa-bookmark");
              },
              error: function (jqXHR) {
                alert("로그인하세요");
              },
            });
          } else {
            console.log("else");
            $.ajax({
              url: "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + projNo + "/bookmark",
              method: "DELETE",
              transformRequest: [null],
              transformResponse: [null],
              jsonpCallbackParam: "callback",
              xhrFields: { withCredentials: true },
              headers: {
                Accept: "application/json, text/plain, */*",
                "Content-Type": "application/json;charset=utf-8",
              },
              data: '{ "projNo":"' + projNo + '" }',
              timeout: {},
              success: function (responseData) {
                alert("북마크에서 삭제되었습니다");
                $("#bm_icon").removeClass("fas fa-bookmark");
                $("#bm_icon").addClass("far fa-bookmark");
              },
              error: function (jqXHR) {
                alert("로그인하세요");
              },
            });
          }
          $(this).toggleClass("off");
        });
      });
    },
    error: function (jqXHR, textStatus) {},
  });

  var $divObj = $("div.prev_proj_wrap");
  var $divObj2 = $("div.test");
  var divHtml = "";
  var backurl2 = "";
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + no + "/history",
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    data: "",
    timeout: {},
    success: function (responseData) {
      if (responseData != "") {
        divHtml +=
          '<div class="mid_creator_top">∴ 크리에이터의 다른 프로젝트</div>';
        divHtml += '<div class="prev_proj_wrap">';
        $(responseData).each(function (i, e) {
          var prevProjNo = e.projNo;
          var prevProjTitle = e.projTitle;
          var prevProjSummary = e.projSummary;

          divHtml += '<div class="prev_proj">';
          divHtml +=
            '<a class="prev_proj_btn" href="http://localhost/project/info?no=' +
            prevProjNo +
            '">';
          divHtml += '<div class="prev_proj_img">';
          divHtml +=
            '<img class="thumbnail_img" src="/img/project/' +
            prevProjNo +
            "/" +
            prevProjNo +
            '_t.png" onerror="this.src=\'/img/project/' +
            prevProjNo +
            "/" +
            prevProjNo +
            "_t.jpg';\" />";
          divHtml += "</div>";
          divHtml += '<div class="prev_proj_info_wrap">';
          divHtml += '<div class="prev_proj_title">';
          divHtml += prevProjTitle;
          divHtml += "</div>";
          divHtml += '<div class="prev_proj_summary">';
          divHtml += prevProjSummary;
          divHtml += "</div>";
          divHtml += "</div>";
          divHtml += "</a>";
          divHtml += "</div>";
        });
        divHtml += "</div>";
        $divObj2.html(divHtml);
      }
    },
  });
});
