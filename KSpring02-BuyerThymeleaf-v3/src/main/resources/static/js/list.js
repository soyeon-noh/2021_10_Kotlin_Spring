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
        location.href = "/buyer/detail?userid=" + id
    }

    $("table.buyer_list tr").on("click", tableClickHandler)

})