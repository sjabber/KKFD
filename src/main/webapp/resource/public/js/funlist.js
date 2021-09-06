$(function () {
    var $form = $("form#criteria");
    $form.submit(function () {
        var data = $form.serialize();
        var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/fundings";
        $.ajax({
            url: backurl,
            method: "GET",
            data: data,
            xhrFields: {
                withCredentials: true,
            },
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
                        '<tr><td colspan="8" class="text-center">펀딩한 프로젝트가 없습니다.</td><tr>';
                    $("body > section > div.my > table.allFun>tbody").html(trHtml);
                },
                200: function (responseObj) {
                    var $trDataObj = $(
                        "body > section > div.my > table.allFun tr.data"
                    ); //tr class가 data인 객체들
                    $trDataObj.not(":first").remove(); //이전 화면 프로젝트 목록지우기(안보이는 tr원본객체는 남기고 삭제)
                    var $trObj = $trDataObj.first();
                    $(responseObj.list).each(function (i, f) {
                        var $trCopyObj = $trObj.clone(); //tr 복제본객체
                        console.log(f.project.projNo);
                        $trCopyObj.show();
                        $trCopyObj.find("th.funNo").html(f.funNo);
                        $trCopyObj
                            .find("a.projTitle")
                            .html(f.project.projTitle)
                            .attr("href", "/project/info?no=" + f.project.projNo);
                        $trCopyObj.find("td.funDate").html(f.funDate);
                        $trCopyObj
                            .find("td.funFm")
                            .html(numberWithCommas(f.funFm) + "원");
                        $trCopyObj.find("td.projEnd").html(f.project.projEnd);
                        $trCopyObj
                            .find("td.projDelivery")
                            .html(f.project.projDelivery);
                        if (f.funTrack != 0) {
                            $trCopyObj.find("td.funTrack").html(f.funTrack);
                        }

                        //0:결제취소(0무산(크리에이터가 취소) /  20실패)
                        //1:진행중(10:진행중 / 11:임박(90%이상) / 12:결제예정(달성:마감전 100%))
                        //2:결제완료(성공) (21: 성공 / 22 : 배송 중(성공+배송예정 일 이후)-결제
                        switch (f.project.projStatus) {
                            case 0: // 무산 펀딩금액,배송예정일 취소선
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-danger")
                                    .addClass("text-white")
                                    .html("무산");
                                $trCopyObj
                                    .find("td.funFm")
                                    .addClass("text-decoration-line-through");
                                $trCopyObj
                                    .find("td.projDelivery")
                                    .addClass("text-decoration-line-through");
                                break;
                            case 10: //진행중 : 회색
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-secondary")
                                    .addClass("text-white")
                                    .html("진행중");
                                break;
                            case 11: //성공임박 : 검정
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-dark")
                                    .addClass("text-white")
                                    .html("성공임박");
                                break;
                            case 12: //결제예정 : 청록 , 종료일(결제일) 강조
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-info")
                                    .addClass("text-dark")
                                    .html("결제예정");
                                $trCopyObj.find("td.projEnd").addClass("fw-bold");
                                break;
                            case 20: //실패 : 노랑, 펀딩금액,배송예정일 취소선
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-warning")
                                    .addClass("text dark")
                                    .html("실패");
                                $trCopyObj
                                    .find("td.funFm")
                                    .addClass("text-decoration-line-through");
                                $trCopyObj
                                    .find("td.projDelivery")
                                    .addClass("text-decoration-line-through");
                                break;
                            case 21: //결제성공 : 초록 배송예정일 강조
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-success")
                                    .addClass("text-white")
                                    .html("결제성공");
                                $trCopyObj.find("td.projDelivery").addClass("fw-bold");
                                break;
                            case 22: //배송중 :
                                $trCopyObj
                                    .find("div.status")
                                    .addClass("bg-primary")
                                    .addClass("text-white")
                                    .html("배송중");
                                $trCopyObj.find("td.projDelivery").addClass("fw-bold");
                                /*배송조회버튼
                              var btnCopyObj = $btnObj.clone();
                              btnCopyObj.show().val(p.projNo).attr('onClick', "");
                              $trCopyObj.find('td.view').append(btnCopyObj);
                              */
                                break;
                        }
                        $trObj.parent().append($trCopyObj);
                    }); //end of each

                    var cPage = responseObj.currentPage;
                    var tPage = responseObj.totalPage;
                    var sPage = responseObj.startPage;
                    var ePage = responseObj.endPage;

                    var $pageLi = $(
                        "div.pagination_container>ul.pagination"
                    ).children(); //a태그 포함한 li
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
                            .attr("href", cPage - 1);
                    }
                    if (cPage == tPage) {
                        $nextLi.addClass("disabled");
                        $nextLi.find("a.page-link").attr("aria-disabled", true);
                    } else {
                        $nextLi.removeClass("disabled");
                        $nextLi
                            .find("a.page-link")
                            .attr("aria-disabled", false)
                            .attr("href", cPage + 1);
                    }
                    var $liObj = $pageLi.eq(1);
                    $pageLi.parent().find("li.num").not(":first").remove(); //안보이는 원본li객체는 남기고 삭제
                    for (let k = sPage; k <= ePage; k++) {
                        $liCopyObj = $liObj.clone();
                        $liCopyObj.show();
                        $liCopyObj.find("a.page-link").html(k).attr("href", k);
                        if (k == cPage) {
                            $liCopyObj.addClass("active").attr("aria-current", "page");
                        }
                        $nextLi.before($liCopyObj);
                    } //end of for
                }, //end of 200
            }, //end of statuscod
        }); //end of Ajax
        return false;
    });

    //초기 화면 로드
    $form.submit();

    $form.find("input[name=term]").change(function () {
        $form.find("input[name=page]").val(1); //상태값 바뀌면 페이지 1로 초기화
        $form.submit();
    });

    $form.find("select[name=state]").change(function () {
        $form.find("input[name=page]").val(1); //상태값 바뀌면 페이지 1로 초기화
        $form.submit();
    });
    $("div.pagination_container>ul.pagination").on(
        "click",
        "li.page-item > a.page-link",
        function () {
            if ($(this).parent().hasClass("move")) {
                //<<버튼이거나 >>이고 비활성화시
                if ($(this).attr("aria-disabled") == true) {
                    return false;
                }
            }
            var page = $(this).attr("href");
            console.log(page);
            $form.find("input[name=page]").val(page);
            $form.submit();
            return false; //버블링방지
        }
    );
}); //end of load;