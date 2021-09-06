$(function () {
  var page = 1;
  var url = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/bmlist/" + page;
  var $divObj = $("div.focus_item_warp");
  var divHtml = "";
  $.ajax({
    url: url,
    method: "GET",
    xhrFields: { withCredentials: true },
    statusCode: {
      204: function () {
        divHtml +=
          '<div id="no_content_img"><img src="http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/no_bookmark.png" /></div>';
        $divObj.html(divHtml);
      },
      502: function () {
        alert("502");
      },
    },
    success: function (responseData) {
      $(responseData).each(function (i, e) {
        var projNo = e.projNo;
        var projCategory = e.projCategory;
        var crNn = e.creator.crNn;
        var projBm = e.projBm;
        var projTitle = e.projTitle;
        var projGoals = e.projGoals;
        var projStart = e.projStart;
        var projEnd = e.projEnd;
        var ext = e.ext;

        divHtml += '<div class="focus_item">';
        divHtml +=
          '<a class="info_btn" href="/project/info?no=' + projNo + '">';
        divHtml += '<div class="focus_item_img">';
        divHtml +=
          '<img class="focus_item_img_item" src="http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/project/' +
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
      });
      $divObj.html(divHtml);
    },
  });

  //무한 스크롤
  $(window).scroll(function () {
    var scrolltop = parseInt($(window).scrollTop());
    if ($(window).scrollTop() == $(document).height() - $(window).height()) {
      page++;
      var url = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/bmlist/" + page;
      var method = "GET";
      $.ajax({
        url: url,
        method: method,
        xhrFields: { withCredentials: true },
        success: function (responseData) {
          $(responseData).each(function (i, e) {
            var projNo = e.projNo;
            console.log(projNo);
            var projCategory = e.projCategory;
            var crNn = e.creator.crNn;
            var projBm = e.projBm;
            var projTitle = e.projTitle;
            var projGoals = e.projGoals;
            var projStart = e.projStart;
            var projEnd = e.projEnd;
            var ext = e.ext;

            divHtml += '<div class="focus_item">';
            divHtml +=
              '<a class="info_btn" href="/project/info?no=' + projNo + '">';
            divHtml += '<div class="focus_item_img">';
            divHtml +=
              '<img class="focus_item_img_item" src="http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/img/project/' +
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
                '<button class="category_bookmark_btn off" value="' +
                projNo +
                '">';
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
            divHtml +=
              '<span class="purpose_value">' + projGoals + "% 달성</span>";
            divHtml += "</div>";
            divHtml += '<div class="focus_item_term">';
            divHtml +=
              '<span class="term">' + projStart + " ~ " + projEnd + "</span>";
            divHtml += "</div>";
            divHtml += "</div>";
          });
          $divObj.html(divHtml);
        },
        error: function (jqXHR) {
          alert(jqXHR.status);
        },
      });
    }
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
          xhrFields: { withCredentials: true },
          timeout: {},
          success: function (responseData) {
            alert("북마크에 추가되었습니다");
            $iconObj.removeClass("far fa-bookmark");
            $iconObj.addClass("fas fa-bookmark");
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
        });
      }
      $(this).toggleClass("off");
    }
  );
});
