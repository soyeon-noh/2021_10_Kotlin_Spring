$(function(){

    function submit(){
        const id = $(this).data("id")
        location.href = "/buyer/insert?userid=" + id
    }
    $("button.btn_write").on("click", submit)
})