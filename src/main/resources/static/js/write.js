$.ajax({
    url: "/board",
    contentType: 'application/json',
    method: "POST",
    data: JSON.stringify({
        title: $("#title").val(),
        content: $("#content").val()
    }),
    success: function(data) {
        console.log("success")
        // 성공적으로 데이터가 전송되었을 때 실행되는 코드
    },
    error: function(error) {
        console.log("error")
        // 데이터 전송 중 에러가 발생했을 때 실행되는 코드
    }
});