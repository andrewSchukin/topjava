const userAjaxUrl = "ajax/profile/meals/"

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: userAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(userAjaxUrl, updateTableByData);
}

$(function () {
    makeEditable({
        ajaxUrl: userAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": userAjaxUrl,
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime",
                    "render":function (data, type, row) {
                        if (type === "display") {
                            return row.dateTime.replace('T', ' ').substr(0,16);
                        }
                        return data;
                    }
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
                    "desc"
                ]
            ],
            "createdRow": function (row, data, dataIndex) {
                if (!data.enabled) {
                    $(row).attr("data-mealExcess", data.excess);
                }
            }
        }),
        updateTable: updateFilteredTable
    });
    startDate = $('#startDate');
    startDate.datetimepicker({format:'Y-m-d', timepicker:false});

    endDate = $('#endDate');
    endDate.datetimepicker({format:'Y-m-d',timepicker:false});

    startTime = $('#startTime');
    startTime.datetimepicker({format:'H:i',datepicker:false});

    endTime = $('#endTime');
    endTime.datetimepicker({format:'H:i',datepicker:false});

});