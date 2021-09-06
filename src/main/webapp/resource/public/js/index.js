$(function () {
  var url = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/main";
  var projArea;
  var $divObj1 = $("div#area1");
  var $divObj2 = $("div#area2");
  var $divObj3 = $("div#area3");
  var $divObj4 = $("div#area4");
  var divHtml = "";
  $.ajax({
    method: "GET",
    transformRequest: [null],
    transformResponse: [null],
    jsonpCallbackParam: "callback",
    url: url,
    headers: {
      Accept: "application/json, text/plain, */*",
    },
    xhrFields: { withCredentials: true },
    statusCode: {
      204: function () {
        divHtml +=
          '<div id="no_content_img"><img src="/img/no_project.png" /></div>';
        $divObj1.html(divHtml);
        $divObj2.html(divHtml);
        $divObj3.html(divHtml);
        $divObj4.html(divHtml);
      },
    },
    success: function (responseData) {
      $(responseData).each(function (i, e) {
        projArea = e.projArea;
        var projNo = e.project.projNo;
        var projCategory = e.project.projCategory;
        var crNn = e.project.creator.crNn;
        var projBm = e.project.projBm;
        var projTitle = e.project.projTitle;
        var projGoals = e.project.projGoals;
        var projStart = e.project.projStart;
        var projEnd = e.project.projEnd;
        var ext = e.project.ext;

        divHtml = "";
        divHtml += '<div class="focus_item">';
        divHtml +=
          '<a class="info_btn" href="/project/info?no=' + projNo + '">';
        divHtml += '<div class="focus_item_img">';
        divHtml +=
          '<img class="focus_item_img_item" src="/img/project/' +
          projNo +
          "/" +
          projNo +
          "_t." +
          ext +
          '" />';
        divHtml += "</div>";
        divHtml += "</a>";
        divHtml += '<div class="focus_item_category">';
        divHtml += '<div class="category_warp">';
        divHtml +=
          '<span class="category_item1">' +
          categoryNoChange[projCategory] +
          "</span>";
        divHtml += '<span class="category_between"> | </span>';
        divHtml += '<span class="category_item2">' + crNn + "</span>";
        divHtml += "</div>";
        divHtml += '<div class="category_bookmark">';
        if (projBm == 1) {
          divHtml +=
            '<button class="category_bookmark_btn" value="' + projNo + '">';
        } else {
          divHtml +=
            '<button class="category_bookmark_btn off" value="' + projNo + '">';
        }
        if (projBm == 1) {
          divHtml += '<i class="fas fa-bookmark" id="bm_icon"></i>';
        } else {
          divHtml += '<i class="far fa-bookmark" id="bm_icon"></i>';
        }
        divHtml += "</button>";
        divHtml += "</div>";
        divHtml += "</div>";
        divHtml +=
          '<a class="info_btn" href="/project/info?no=' + projNo + '">';
        divHtml += '<div class="focus_item_title">';
        divHtml +=
          '<span class="focus_item_title_value">' + projTitle + "</span>";
        divHtml += "</div>";
        divHtml += "</a>";
        divHtml +=
          '<br /><div class="progress" style="height: 2px;"><div class="progress-bar bg-danger" role="progressbar" style="width: ' +
          projGoals +
          '%;" aria-valuenow="' +
          projGoals +
          '" aria-valuemin="0" aria-valuemax="100"></div></div>';
        divHtml += '<div class="focus_item_purpose">';
        divHtml += '<span class="purpose_value">' + projGoals + "% 달성</span>";
        divHtml += "</div>";
        divHtml += '<div class="focus_item_term">';
        divHtml +=
          '<span class="term">' + projStart + " ~ " + projEnd + "</span>";
        divHtml += "</div>";
        divHtml += "</div>";

        if (projArea == 1) {
          $divObj1.append(divHtml);
        } else if (projArea == 2) {
          $divObj2.append(divHtml);
        } else if (projArea == 3) {
          $divObj3.append(divHtml);
        } else if (projArea == 4) {
          $divObj4.append(divHtml);
        }
      });
    },
  });

  //북마크 버튼
  $("div.focus_item_warp").on(
    "click",
    "button.category_bookmark_btn",
    function () {
      var $iconObj = $(this).children();
      if ($(this).hasClass("off")) {
        $.ajax({
          url:
            "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + $(this).val() + "/bookmark",
          method: "POST",
          transformRequest: [null],
          transformResponse: [null],
          jsonpCallbackParam: "callback",
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json;charset=utf-8",
          },
          data: '{ "projNo":"' + $(this).val() + '" }',
          timeout: {},
          xhrFields: { withCredentials: true },
          success: function (responseData) {
            alert("북마크에 추가되었습니다");
            $iconObj.removeClass("far fa-bookmark");
            $iconObj.addClass("fas fa-bookmark");
          },
          error: function (jqXHR) {
            alert("로그인하세요");
          },
        });
      } else {
        $.ajax({
          url:
            "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + $(this).val() + "/bookmark",
          method: "DELETE",
          transformRequest: [null],
          transformResponse: [null],
          jsonpCallbackParam: "callback",
          headers: {
            Accept: "application/json, text/plain, */*",
            "Content-Type": "application/json;charset=utf-8",
          },
          data: '{ "projNo":"' + $(this).val() + '" }',
          xhrFields: { withCredentials: true },
          timeout: {},
          success: function (responseData) {
            alert("북마크에서 삭제되었습니다");
            $iconObj.removeClass("fas fa-bookmark");
            $iconObj.addClass("far fa-bookmark");
          },
          error: function (jqXHR) {
            alert("로그인하세요");
          },
        });
      }
      $(this).toggleClass("off");
    }
  );
});
