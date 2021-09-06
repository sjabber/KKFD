$(function () {
    var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/creator/";
    $.ajax({
        method: "GET",
        url: backurl,
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
                alert("크리에이터로 등록하여 프로젝트를 등록해보세요");
            },
            200: function (responseObj) {
                var id = responseObj.crId;
                var nn = responseObj.crNn;
                var intro = responseObj.crIntro;
                var bank = responseObj.crBank;
                var acno = responseObj.crAcno;
                var acholder = responseObj.crAcholder;
                var path = responseObj.imgPath;

                if (id != null) {
                    $("img.cmypage_profile_img").attr("src", path);
                    $("img#prevImg").attr("src", path);
                    $("form.creatorIn input.crId").val(id);
                }
                if (nn != null) {
                    $("div.cmypage_nickname").text(nn);
                    $("form#nickname > input[name=crNn]").val(nn);
                    $("span#prevNn").text(nn);
                }
                if (intro != null) {
                    $("div.cmypage_intro").text(intro);
                    $("form#intro > textarea").val(intro);
                    $("div#prevIntro").text(intro);
                }
                if (bank != null) {
                    $("div.cmypage_bank").text(
                        bank + "  /  " + acno + "  /  " + acholder
                    );
                    $("form#bank select[name=crBank]").val(bank);
                    $("form#bank input[name=crAcno]").val(acno);
                    $("form#bank input[name=crAcholder]").val(acholder);
                }
            }, //end of 200
        },
    }); //end of Ajax

    $("form.creatorIn").submit(function () {
        var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/creator/";
        var formSerializeArray = $(this).serializeArray();
        var object = {};
        for (let i = 0; i < formSerializeArray.length; i++) {
            object[formSerializeArray[i]["name"]] =
                formSerializeArray[i]["value"];
        }
        var data = JSON.stringify(object);

        //크리에이터 : 크리에이터 닉네임,소개,은행계좌를 수정한 적 있거나 프로젝트를 한번이라도 등록한 경우(id!=null)
        var id = $(this).find("input[name=crId]").val();
        console.log(id);
        //DB에 등록한 정보로 kk_creator로 insert
        if (id == null || id == "") {
            console.log("insert");
            $.ajax({
                method: "POST",
                url: backurl,
                data: data,
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
                statusCode: {
                    401: function () {
                        alert("로그인후 사용해주세요");
                        location.replace("/member/login");
                    },
                    404: function () {
                        alert("등록하지 못하였습니다, 관리자에게 문의해 주세요. ");
                    },
                    500: function () {
                        alert("서버에러, 관리자에게 문의해 주세요.");
                    },
                    200: function (responseObj) {
                        location.reload();
                    },
                }//end of statusCode
            });//end of ajax
        } else {
            //update
            console.log("update");
            $.ajax({
                method: "PUT",
                url: backurl,
                data: data,
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
                statusCode: {
                    401: function () {
                        alert("로그인후 사용해주세요");
                        location.replace("/member/login");
                    },
                    404: function () {
                        alert("수정하지 못하였습니다, 관리자에게 문의해 주세요. ");
                    },
                    500: function () {
                        alert("서버에러, 관리자에게 문의해 주세요.");
                    },
                    200: function (responseObj) {
                        location.reload();
                    },
                }//end of statusCode
            });//end of ajax
        }//end of if
        return false;
    });
    $("button#upload").click(function () {
        var formData = new FormData($("form.imgIn")[0]);
        var backurl = "http://kkfd.eastus.cloudapp.azure.com:9999/kkfd/creator/profile";
        $.ajax({
            url: backurl,
            method: "POST",
            cache: false,
            xhrFields: {
                withCredentials: true,
            },
            processData: false,
            contentType: false,
            data: formData, //요청전달데이터
            enctype: "multipart/form-data",
            statusCode: {
                401: function () {
                    alert("로그인후 사용해주세요");
                    location.replace("/member/login");
                },
                500: function () {
                    alert("서버에러, 관리자에게 문의해 주세요.");
                },
                204: function (responseObj) {
                    alert("크리에이터 닉네임 부터 설정해주세요");
                },
                200: function (responseObj) {
                    location.reload();
                },
            }//end of statusCode
        });//end of ajax
        return false;
    });

    var $changeBtn = $(
        "div.cmypage_modify > div.cmypage_title_container > button.cmypage_change_btn"
    );
    $changeBtn.click(function () {
        var $before = $(this).parent().next(); //이전 정보
        var $after = $(this).parent().next().next(); //input_box
        if ($before.css("display") == "block") {
            $before.hide();
            $after.show();
        } else {
            $before.show();
            $after.hide();
        }
    });

    //미리보기
    $("input[type=file]").change(function () {
        if (this.files && this.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $("img.cmypage_profile_img").attr("src", e.target.result);
            };
            reader.readAsDataURL(this.files[0]);
        }
    });
});