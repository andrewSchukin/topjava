const mealAjaxUrl = "ajax/profile/meals/";

function updateByFilter() {
    $.ajax({
        type: "GET",
        url: mealAjaxUrl + "filter",
        data: $("#filter").serialize(),
        success: function(data) {
            context.datatableApi.clear().rows.add(data).draw();
        }
    });
}

function clearTable() {
    $("#filter")[0].reset();
    $.get(mealAjaxUrl, updateByFilter);
}

$(function () {
    makeEditable({
            ajaxUrl: mealAjaxUrl,
            datatableApi: $("#datatable").DataTable({
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "dateTime"
                    },
                    {
                        "data": "description"
                    },
                    {
                        "data": "calories"
                    },
                    {
                        "defaultContent": "Edit",
                        "orderable": false
                    },
                    {
                        "defaultContent": "Delete",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ],
                "createdRow": function (row, data) {
                    $(row).attr("data-mealExcess", data.excess);
                },
                updateTable: updateByFilter
            })
        }
    );
});