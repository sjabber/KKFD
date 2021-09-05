function searchParam(key) {
    return new URLSearchParams(location.search).get(key);
}

//운송장번호 자릿수제한(input type number)
function numberMaxLength(e) {
    if (e.value.length > e.maxLength) {
        e.value = e.value.slice(0, e.maxLength);
    }
};
//var optionHtml = '<option value="">--선택--</option>' + '<option value="1">대한통운</option>' + '<option value="2">우체국</option>' + '<option value="3">한진택배</option>' + '<option value="4">현대택배</option>';
$(function () {
    const URLSearch = new URLSearchParams(location.search);
    var projNo = searchParam("no");
    var trHtml = '';
    var backurl = "http://localhost:9999/kkfd/project/" + projNo + "/fundings";
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
                alert('로그인 후 사용해주세요');
                location.replace("/member/login");
            },
            403: function () {
                alert('참여자 정보 접근 권한이 없습니다.');
                location.replace("/");
            },
            500: function () {
                alert('서버에러, 관리자에게 문의해 주세요.');
            },
            200: function (responseObj) {
                //"no":357,"project":{"projNo":357,"projTitle":"테스트321",,...},"fundingList":[{"funNo":113,"member":{"id":null,....},"funQuantity":9,...},{"funNo":114,"member":{"id":null,....},
                //프로젝트 정보 제목, 모집금액, 달성률, 종료일, 배송예정일
                var p = responseObj.project;
                var totalFm = p.projFm * p.projQuantity;
                var end = p.projEnd;
                var delivery = p.projDelivery; //2021-09-22T15:00:00.000+00:00
                var path = "/img/project/" + p.projNo + "/" + p.projNo + "_t." + p.ext;
                console.log(path);
                $('div.proj_title').html(p.projTitle);
                $('div.pct > span.fm').html(numberWithCommas(totalFm) + '원');
                $('div.pct > span.goal_pct').html(p.projGoals + '%');
                $('div.day > span.end').html(end.substring(0, 10));
                $('div.day > span.delivery').html(delivery.substring(0, 10));
                $('div.represent_img > img.thumbnail_img').attr('src', path);


                $(responseObj.fundingList).each(function (i, f) {
                    var track = f.funTrack;
                    trHtml += '<tr>'
                    trHtml += '<th scope="row">';
                    if (track == 0) {
                        trHtml += '<input type="checkbox" name="funNo" class="funNo form-check-input"  value="';
                        trHtml += f.funNo;
                        trHtml += '">'
                    }
                    trHtml += '</th>';
                    trHtml += '<td class="text-center">';
                    trHtml += f.funNo;
                    trHtml += '</td>';
                    trHtml += '<td>';
                    trHtml += f.funDate;
                    trHtml += '</td>';
                    trHtml += '<td class="text-center">';
                    trHtml += f.member.name;
                    trHtml += '</td>';
                    trHtml += '<td>';
                    trHtml += f.member.phone;
                    trHtml += '</td>';
                    trHtml += '<td class="addr">'
                    trHtml += f.funAddress + " ";
                    trHtml += f.funDetail;
                    trHtml += '</td>';
                    trHtml += '<td class="text-center">';
                    trHtml += f.funQuantity;
                    trHtml += '</td>';
                    /*택배사 추가시 살리기
                    trHtml += '<td class="text-center">'
        
                    if (track == 0) {
                      trHtml += '<select class="trackIn form-control form-control-sm" name ="courier" disabled required>';
                      trHtml += optionHtml;
                      trHtml += '</select>';
                    } else {
                      trHtml += "택배사";
                    }
                    trHtml += '</td>';
                    */
                    trHtml += '<td class="text-center">';
                    if (track == 0) {
                        trHtml += '<input class="trackIn form-control form-control-sm" type="number" name=trackNum" disabled required placeholder="8자리" maxlength="8" oninput="numberMaxLength(this);"/>';
                    } else {
                        trHtml += track;
                    }
                    trHtml += '</td>';
                    trHtml += '</tr>';
                });//end of each
                $('#tableBody').html(trHtml);

            },//end of 200
        },//end of statusCode
    });//end of Ajax

    $('button.track').click(function () {
        //체크박스 선택된 행의 펀딩번호,운송장 번호 JSON으로 변환
        var jsonArr = new Array();
        var pass = true;
        $('input[name=funNo]:checked').each(function () {
            var $trObj = $(this).parent().parent();
            var $inputObj = $trObj.children().eq(7).find('input.trackIn');
            var value = $inputObj.val();
            if (value.length != 8) {
                focus($inputObj);
                alert("8자리");
                pass = false;
                return false;
            }
            var funObj = new Object();//초기화
            funObj["funNo"] = $(this).val();
            funObj["funTrack"] = $inputObj.val();
            /*배송 테이블,DTO만들어지면 변수명 바꾸기
            trackObj["delFun"] = $(this).val();
            trackObj["delCourier"] = $(this).val();
            trackObj["delTrack"] = $(this).val();*/
            jsonArr.push(funObj);
        });
        if (pass) {
            var data = JSON.stringify(jsonArr);
            console.log(data);
            var backurl = "http://localhost:9999/kkfd/fundings";
            $.ajax({
                "method": "PUT",
                "url": backurl,
                "transformRequest": [null],
                "transformResponse": [null],
                "jsonpCallbackParam": "callback",
                "headers": {
                    "Accept": "application/json, text/plain, */*",
                    "Content-Type": "application/json;charset=utf-8"
                }, "xhrFields": {
                    withCredentials: true
                },
                "data": data,
                "timeout": {},
                "statusCode": {
                    401: function () {
                        alert('로그인후 사용해주세요');
                        location.replace("/member/login");
                    },
                    500: function () {
                        alert('서버에러, 관리자에게 문의해 주세요.');
                    },
                    200: function (responseData) {
                        alert(responseData + "건 운송장 입력 완료");
                        location.reload();
                    },//end of 200
                }//end of statusCode
            });//end of ajax
        }//end of if
    });//end of function

    //검색
    $("#mykeyword").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#tableBody tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
    });


    //체크박스 클릭시 tr테두리
    $('#tableBody').on('change', 'input.funNo', function () {
        var $trObj = $(this).parent().parent();
        //$selectObj = $trObj.children().eq(7).find('select.trackIn');
        $inputObj = $trObj.children().eq(7).find('input.trackIn');
        $trObj.toggleClass("border").toggleClass("border-danger");
        if ($(this).is(":checked") == true) {
            //$selectObj.attr("disabled", false);
            $inputObj.attr("disabled", false);
        } else {
            //$selectObj.attr("disabled", true);
            $inputObj.attr("disabled", true);
        }
    });

    //운송장 번호 입력시 파란색으로 표시
    $('#tableBody').on('keyup', 'input.trackIn', function () {
        var $trObj = $(this).parent().parent();
        if ($(this).val().length == 8) {
            console.log(1);
            $trObj.removeClass("border-danger");
            $trObj.addClass("border-info");
        } else {
            console.log(2);
            $trObj.addClass("border-danger");
            $trObj.removeClass("border-info");
        }
    });

});