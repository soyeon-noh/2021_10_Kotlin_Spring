//<!--
//document.addEventListener("DOMContentLoaded", function() {
//    querySelector()
//    getElementById()
//})
//-->

// 바닐라 JS
// document.addEventListener("DOMContentLoaded", function() {
//      const table = querySelector("table.buyer_list)
//      table.addEventListener("click", ()=>{
//          const id = table.target.closest("TR").dataset.id
//      });
// })

// jQuery를 사용할 때
// $(document.ready(function() {   }))
// $(function() {
//})

$(function() {
//    jQuery 코드 영역
//    $("table.buyer_list tr") -> 자체가 Selector()
//    $("table.buyer_list tr").click(function() {   })
/*
    class buyer_list 인 table 내부(>)의
    tr 중 한개가 클릭되면 함수를 실행하라

    data-id 값을 getter 하여
    alert 창에 표시하라
*/

// 여기는 화살표함수가 잘 안된대. 혹시 모르니 function 사용

// inline callback으로 바로 붙이는 방법
//    $("table.buyer_list tr").on("click", function() {
//        const id = $(this).data("id") // data-id 를 getter 하기
//        alert(id)
//        location.href ="/detail?userid=" + id
//    })


// const tableClickHandler = () => {} // 이거말고
// const tableClickHandler = function(){} // 이거나

// 정통적인 방법..?
    function tableClickHandler() { // 이걸 쓰래
        const id = $(this).data("id")
        // location.href = "/buyer/detail?userid=" + id
       location.href = `${rootPath}buyer/detail?userid=${id}`
    }

    // 이미 생성된 DOM에는 정상적으로 event 가 적용된다.
    // 동적으로 추가된 append() DOM 에는 event 를 적용할 수 없다.
    // $("table.buyer_list tr").on("click", tableClickHandler)

    // 동적으로 추가된 table tr 요소에 event 를 설정할 때 사용
    // 그러나 메모리 및 자원 관리에 비효율적이기 때문에 꼭 필요할 때 사용하도록 한다.
    // ( document야 내가 click한게 저 table.buyer_list tr 이 맞니? )
    $(document).on("click", "table.buyer_list tbody tr", tableClickHandler)

})