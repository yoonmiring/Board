$(document).ready(function() {
    $('#postForm').submit(function(event) {
        event.preventDefault(); // 기본 폼 제출 동작 취소

        $.ajax({
            url: '/board/posts',
            method: 'POST',
            data: $('#postForm').serialize(),
            success: function() {
                window.location.href = '/board'; // 홈 화면으로 이동
            }
        });
    });
});


$.ajax({
    url: "/api/posts",
    method: "POST",
    data: {
        title: $("#title").val(),
        content: $("#content").val()
    },
    success: function(data) {
        // 성공적으로 데이터가 전송되었을 때 실행되는 코드
    },
    error: function(error) {
        // 데이터 전송 중 에러가 발생했을 때 실행되는 코드
    }
});