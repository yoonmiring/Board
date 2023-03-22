console.log(PostDto);

function openWriteForm() {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '/board/posts');
    xhr.onreadystatechange = function() {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                var response = xhr.responseText;
                // response를 이용해 글쓰기 창을 구현한다.

                var formWrapper =document.createElement('div');
                formWrapper.innerHTML = response;
                var WriteForm = formWrapper.querySelector('.write-form');
                // 폼 삽입하기
                var postList = document.querySelector('.post-list');
                postList.insertAdjacentElement('afterend', writeForm);
            }
        }
    };
    xhr.send();
}
