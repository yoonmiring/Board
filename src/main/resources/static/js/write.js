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