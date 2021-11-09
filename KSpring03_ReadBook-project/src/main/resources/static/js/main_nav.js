$(function (){
    $("nav.main_nav li").on("click", function() {
        const text = $(this).text()

        if(text === "내 독서록"){
            alert("내 독서록")
            location.href = `${rootPath}member/mypage`
        } else if(text === "독서록 작성" ){
            location.href = `${rootPath}insert`
        } else if(text === "로그인"){
            location.href = `${rootPath}member/login`
        }
        console.log(text)
    })
})