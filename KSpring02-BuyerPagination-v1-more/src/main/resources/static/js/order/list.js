$(function() {

    function tableClickHandler() {
        let seq = $(this).data("seq")

        location.href = `${rootPath}order/detail?seq=${seq}`
    }

    // $("table.order_list tr").on("click", tableClickHandler)

    $(document).on("click", "table.order_list tr", tableClickHandler)
})