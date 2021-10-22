$(function() {

    function tableClickHandler() {
        const seq = $(this).data("seq")
        location.href = "/order/detail/" + seq
    }

    $("table.order_list tr").on("click", tableClickHandler)

})