/**
 * Created by tristandiaz on 11/4/17.
 */
$(document).ready(function () {

    $("#search-form").submit(function (event) {
        event.preventDefault();
        _submit();
    });

});

function _submit() {

    var fileName = $("#fileName").val();

    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "/summary/search",
        data: {"fileName": fileName},
        dataType: 'json',
        cache: false,
        success: function (data) {

            var summary = $("#summary");

            var table = "<table class='table' id='summary-table'>" +
            "<tr>" +
                "<th>File Name</th>" +
                "<th># of Valid Deals</th>" +
                "<th># of Invalid Deals</th>" +
                "<th>Timestamp</th>" +
                "<th>Process Duration</th>" +
            "</tr>"+
            "<tr>" +
                "<td>" + data.filename + "</td>"+
                "<td>" + data.validDeals + "</td>"+
                "<td>" + data.invalidDeals + "</td>"+
                "<td>" + data.datetime + "</td>"+
                "<td>" + data.processTime + "</td>"+
            "</tr></table>";

            summary.append(table);

            _clearSearchInput();
        },
        error: function (data) {

            $("#alertMsg").remove();
            $("#summary-table").remove();

            var alertDiv = $("#alert");
            alertDiv.append("<div id='alertMsg' class='alert alert-danger'><p><strong>" + data.responseText + "</strong></p></div>");
        }
    });
}

function _clearSearchInput() {
    $("#fileName").val("");
}


