$(function(){
    const button = $("section.order_detail button")
    if(button) {
        $(button).on("click", function(){
            const className = $(this).attr("class")
//             alert(className)

            let href = `${rootPath}order`

            if(className.includes("btn_list")){
//                alert("리스트로 이동")
            } else if(className.includes("btn_update")) {
//                 alert("수정하기")
                 href = `${href}/update/${seq}`
            } else if(className.includes("btn_delete")) {
                 href = `${href}/delete/${seq}`
                 if(!confirm("삭제할까요?")){
                     return false
                 }
            }
             location.href = `${href}`
        })
    }
})