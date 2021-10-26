$(function () {

// 방법 1
// 이런반복되는 코드말고 아래에 있는 코드로 썼다.
// jQuery가 간단한데 유지보수가 힘들 수 있다. 오타도 조심.
//    $("li.login").on("click", function(){
//        alert("로그인 메뉴 클릭")
//        location.href = "/login"
//    })
//    $("li.pay").on("click", function(){
//            alert("결제관리 메뉴 클릭")
//        location.href = "/pay"
//    })
//    $("li.home").on("click", function(){
//            alert("Home 클릭")
//        location.href = "/"
//    })

    // li 전체에 click event 설정
    $("nav li").on("click", function () {
        // 현재 클릭된 것에서 무언가를 추출할 때
        // this : js 에서 e.currentTarget
        const className = $(this).attr("class") // 만약 class가 붙어있다면
        const tagId = $(this).attr("id") // 만약 id가 붙어있다면
        const tagName = $(this).attr("name")
        const text = $(this).text()

        /*
        JS Debuging
        여러개의 변수 데이터를 alert() 으로 보고 싶을 때
        데이터들을 JSON 객체로 만들고
        JSON.stringify()를 이용해 문자열로 바꾼 후 alert()로 표시하자
        */
//        const tagInfo = {
//            tagName, tagId, className, text
//        }
//
//        alert(JSON.stringify(tagInfo))

        // 방법 2
        let href = `${rootPath}`

        // if (text==="Home"){
        //     href
        // } else
        if (text === "주문 관리") {
            href += "order/"
        } else if (text === "결제 관리") {
            href += "pay"
        } else if (text === "로그인") {
            href += "login"
        }

        location.href = `${href}`
    })

})