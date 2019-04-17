const mealsAjaxUrl = "ajax/profile/meals/"

function updateFilteredTable() {
    $.ajax({
        type: "GET",
        url: mealsAjaxUrl + "filter",
        data: $("#filter").serialize()
    }).done(updateTableByData);
}

function clearFilter() {
    $("#filter")[0].reset();
    $.get(mealsAjaxUrl, updateTableByData);
}

$.ajaxSetup({
    converters: {
        "text json": function (stringData) {
            const json = JSON.parse(stringData);
            $(json).each(function () {
                this.dateTime = this.dateTime.replace('T', ' ').substr(0, 16);
            });
            return json;
        }
    }
});

$(function () {
    makeEditable({
        ajaxUrl: mealsAjaxUrl,
        datatableApi: $("#datatable").DataTable({
            "ajax": {
                "url": mealsAjaxUrl,
                "dataSrc": ""
            },
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
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderEditBtn
                },
                {
                    "defaultContent": "",
                    "orderable": false,
                    "render": renderDeleteBtn
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

    dateTime = $('#dateTime');
    dateTime.datetimepicker({format: 'Y-m-d H:i'});

});