function DaumPostcode() {
  new daum.Postcode({
    oncomplete: function (data) {
      $("#addr_search").val(data.zonecode);

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

        $("#road_addr").val(data.roadAddress + extraAddr);
      } else {
        // 사용자가 지번 주소를 선택했을 경우(J)
        $("#road_addr").val(data.jibunAddress);
      }

      $("#detail_addr").focus();
    },
  }).open();
}
