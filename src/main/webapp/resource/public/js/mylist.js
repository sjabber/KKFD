//남은일 계산
function dayBetween(startDay, endDay) {
    var termMiliSec = endDay.getTime() - startDay.getTime();
    var term = termMiliSec / (60 * 1000 * 60 * 24);
    return Math.ceil(term);
}

//[전체 테이블 페이지요청에 따라 교체]
function mylist(responseObj) {
    var $trDataObj = $("body > section > div.my > table.allProj tr.data"); //tr class가 data인 객체들
    $trDataObj.not(":first").remove(); //이전 화면 프로젝트 목록지우기(안보이는 tr원본객체는 남기고 삭제)
    var $trObj = $trDataObj.first(); //전체 테이블 tr원본객체
    var $btnObj = $("body > section > div.my > button.view"); //보기 버튼 원본객체
    $(responseObj.list).each(function (i, p) {
        var $trCopyObj = $trObj.clone(); //tr 복제본객체
        var totalFm = p.projFm * p.projQuantity;
        $trCopyObj.show();
        $trCopyObj.find("th.projNo").html(p.projNo);
        $trCopyObj
            .find("a.projTitle")
            .html(p.projTitle)
            .attr("href", "/project/info?no=" + p.projNo);
        $trCopyObj.find("td.term").html(p.projStart + "~" + p.projEnd);

        switch (p.projStatus) {
            case 0: //취소 : 빨강 기간에 취소선
                $trCopyObj
                    .find("div.status")
                    .addClass("bg-danger")
                    .addClass("text-white")
                    .html("취소");
                $trCopyObj
                    .find("td.term")
                    .addClass("text-decoration-line-through");
                break;
            case 10: //예정 : 회색
                $trCopyObj
                    .find("div.status")
                    .addClass("bg-secondary")
                    .addClass("text-white")
                    .html("예정");
                break;
            case 20: //실패 : 노랑 모집금액에 취소선
                $trCopyObj
                    .find("div.status")
                    .addClass("bg-warning")
                    .addClass("text dark")
                    .html("실패");
                $trCopyObj
                    .find("td.totalFm")
                    .addClass("text-decoration-line-through")
                    .html(numberWithCommas(totalFm + "원"));
                break;
            case 21: //성공 : 초록 모집금액 표시, 참여자 보기 버튼
                $trCopyObj
                    .find("div.status")
                    .addClass("bg-success")
                    .addClass("text-white")
                    .html("성공");
                $trCopyObj
                    .find("td.totalFm")
                    .html(numberWithCommas(totalFm + "원"));
                var btnCopyObj = $btnObj.clone();
                btnCopyObj
                    .show()
                    .val(p.projNo)
                    .attr(
                        "onClick",
                        "location.href='/project/joinlist?no=" + p.projNo + "'"
                    );
                $trCopyObj.find("td.view").append(btnCopyObj);
                break;
            default:
                //진행예정,진행 : 파랑 모집금액 표시
                $trCopyObj
                    .find("div.status")
                    .addClass("bg-primary")
                    .addClass("text-white")
                    .html("진행");
                $trCopyObj
                    .find("td.totalFm")
                    .html(numberWithCommas(totalFm + "원"));
                break;
        }
        $trObj.parent().append($trCopyObj);
    }); //end of each

    var cPage = responseObj.currentPage;
    var tPage = responseObj.totalPage;
    var sPage = responseObj.startPage;
    var ePage = responseObj.endPage;
    var url = responseObj.url;

    var $pageLi = $("div.pagination_container>ul.pagination").children(); //a태그 포함한 li
    var $prevLi = $pageLi.first();
    var $nextLi = $pageLi.last();
    if (cPage == 1) {
        $prevLi.addClass("disabled");
        $prevLi.find("a.page-link").attr("aria-disabled", true);
    } else {
        $prevLi.removeClass("disabled");
        $prevLi
            .find("a.page-link")
            .attr("aria-disabled", false)
            .attr("href", url + (cPage - 1));
    }
    if (cPage == tPage) {
        $nextLi.addClass("disabled");
        $nextLi.find("a.page-link").attr("aria-disabled", true);
    } else {
        $nextLi.removeClass("disabled");
        $nextLi
            .find("a.page-link")
            .attr("aria-disabled", false)
            .attr("href", url + (cPage + 1));
    }
    var $liObj = $pageLi.eq(1);
    $pageLi.parent().find("li.num").not(":first").remove(); //안보이는 원본li객체는 남기고 삭제
    for (let k = sPage; k <= ePage; k++) {
        $liCopyObj = $liObj.clone();
        $liCopyObj.show();
        $liCopyObj
            .find("a.page-link")
            .html(k)
            .attr("href", url + k);
        if (k == cPage) {
            $liCopyObj.addClass("active").attr("aria-current", "page");
        }
        $nextLi.before($liCopyObj);
    } //end of for
} //end of function

$(function () {
    var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/creator/projects/1";
    $.ajax({
        url: backurl,
        method: "GET",
        transformRequest: [null],
        transformResponse: [null],
        jsonpCallbackParam: "callback",
        headers: {
            Accept: "application/json, text/plain, */*",
        },
        xhrFields: {
            withCredentials: true,
        },
        timeout: {},
        statusCode: {
            401: function () {
                alert("로그인후 사용해주세요");
                location.replace("/member/login");
            },
            500: function () {
                alert("서버에러, 관리자에게 문의해 주세요.");
            },
            204: function () {
                var trHtml = "";
                trHtml +=
                    '<tr><td colspan="6" class="text-center">등록한 프로젝트가 없습니다.</td><tr>';
                $("body > section > div.my > table.allProj>tbody").html(trHtml);
                trHtml = "";
                trHtml +=
                    '<tr><td colspan="6" class="text-center">진행중인 프로젝트가 없습니다.</td><tr>';
                $("body > section > div.my > table.ongoing>tbody").html(trHtml);
            },
            200: function (responseObj) {
                //0:취소
                //1:정상(10:진행예정 / 11:달성률<25 / 12: 25<=달성률<75 / 13: 75<=달성률<100 / 15:100<=달성률<제한수량 /  19:제한수량도달)
                //2:마감(20:실패, 21:성공)
                var $trObj = $("body > section > div.my > table.ongoing tr.data"); //진행예정&진행중 테이블 tr원본객체
                var $btnObj = $("body > section > div.my > button.cancle"); //취소버튼 원본객체
                var now = new Date();
                var ongoingCnt = 0;
                $(responseObj.list).each(function (i, p) {
                    if (p.projStatus > 9 && p.projStatus < 20) {
                        ongoingCnt++;
                        var totalFm = p.projFm * p.projQuantity;
                        var restDay = dayBetween(now, new Date(p.projEnd));
                        var $trCopyObj = $trObj.clone(); //tr 복제본객체
                        $trCopyObj.show();
                        $trCopyObj.find("th.projNo").html(p.projNo);
                        $trCopyObj
                            .find("a.projTitle")
                            .html(p.projTitle)
                            .attr("href", "/project/info?no=" + p.projNo);
                        $trCopyObj
                            .find("td.totalFm")
                            .html(numberWithCommas(totalFm) + "원");
                        $trCopyObj.find("td.restDay").html(restDay + "일");
                        var pst = p.projGoals;
                        var $progressBar = $trCopyObj.find("div.progress-bar");
                        console.log(p.projNo + ":" + p.projStatus);
                        switch (p.projStatus) {
                            case 10: //진행예정
                                $trCopyObj.find("div.progress").append("진행예정");
                                break;
                            case 11: //25%이하 : 노랑
                                $progressBar
                                    .css("width", pst * 2)
                                    .attr("aria-valuenow", pst)
                                    .html(pst + "%");
                                $progressBar.addClass("bg-warning");
                                break;
                            case 12: //25%~75% : 파랑
                                $progressBar
                                    .css("width", pst * 2)
                                    .attr("aria-valuenow", pst)
                                    .html(pst + "%");
                                $progressBar.addClass("bg-primary");
                                break;
                            case 13: //75%~100% : 청록
                                $progressBar
                                    .css("width", pst * 2)
                                    .attr("aria-valuenow", pst)
                                    .html(pst + "%");
                                $progressBar.addClass("bg-info");
                                break;
                            case 15: //100%~제한수량 : 초록
                                $progressBar
                                    .css("width", 100 * 2)
                                    .attr("aria-valuenow", 100)
                                    .html(pst + "%");
                                $progressBar.addClass("bg-success");
                                break;
                            default:
                                //제한수량 도달
                                console.log("제한");
                                $progressBar
                                    .css("width", 100 * 2)
                                    .attr("aria-valuenow", 100)
                                    .html("제한수량도달");
                                $progressBar.addClass("bg-secondary");
                                break;
                        }
                        /*[취소가능 버튼 복제 후 추가] 10:진행예정 / 11:달성률<25 / 12: 25<=달성률<75 / 13: 75<=달성률<100 */
                        if (
                            p.projStatus == 12 ||
                            p.projStatus == 11 ||
                            p.projStatus == 13 ||
                            p.projStatus == 10
                        ) {
                            var $btnCopyObj = $btnObj.clone(); //취소버튼복제본객체
                            $btnCopyObj.show();
                            $btnCopyObj.val(p.projNo);
                            $trCopyObj.find("td.cancle").append($btnCopyObj);
                        }
                    }
                    $trObj.parent().append($trCopyObj);
                }); //end of each
                //진행중인 프로젝트 없으면
                console.log(ongoingCnt);
                if (ongoingCnt == 0) {
                    console.log("if");
                    var trHtml = "";
                    trHtml +=
                        '<tr><td colspan="6" class="text-center">진행 중인 프로젝트가 없습니다.</td><tr>';
                    $("body > section > div.my > table.ongoing > tbody").html(
                        trHtml
                    );
                }

                //전체리스트 출력
                mylist(responseObj);
            }, //end of 200
        }, //end of statusCode
    }); //end of Ajax

    //페이지이동
    $("div.pagination_container>ul.pagination").on(
        "click",
        "li.page-item > a.page-link",
        function () {
            var backurl = $(this).attr("href");
            $.ajax({
                url: backurl,
                method: "GET",
                transformRequest: [null],
                transformResponse: [null],
                jsonpCallbackParam: "callback",
                headers: {
                    Accept: "application/json, text/plain, */*",
                },
                xhrFields: {
                    withCredentials: true,
                },
                timeout: {},
                statusCode: {
                    401: function () {
                        alert("로그인후 사용해주세요");
                        location.replace("/member/login");
                    },
                    500: function () {
                        alert("서버에러, 관리자에게 문의해 주세요.");
                    },
                    200: function (responseObj) {
                        mylist(responseObj);
                    }, //end of 200
                }, // end of statusCode
            }); //end of Ajax
            return false;
        }
    ); //end of function;

    //취소 버튼
    $("table.ongoing").on("click", "button.cancle", function () {
        var result = confirm(
            "취소확인) 약관에 따라 위약금 모집금액의 10%를 지불해야 합니다.그래도 취소하시겠습니다."
        );
        if (result) {
            var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/project/" + $(this).val();
            $.ajax({
                method: "PUT",
                url: backurl,
                transformRequest: [null],
                transformResponse: [null],
                jsonpCallbackParam: "callback",
                headers: {
                    Accept: "application/json, text/plain, */*",
                    "Content-Type": "application/json;charset=utf-8",
                },
                xhrFields: {
                    withCredentials: true,
                },
                data: "",
                timeout: {},
                statusCode: {
                    401: function () {
                        alert("로그인후 사용해주세요");
                        location.replace("/member/login");
                    },
                    403: function () {
                        alert("취소 권한이 없습니다.");
                        location.replace("/");
                    },
                    500: function () {
                        alert("서버에러, 관리자에게 문의해 주세요.");
                    },
                    200: function (responseObj) {
                        location.reload();
                    }, //end of 200
                }, //end of statusCode
            }); //end of Ajax;
        } //end of if;
    }); //end of function;
}); //end of load;