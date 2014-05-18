$(document).ready(function () {

    $('#date').on('changeDate', function (ev) {
        var json = getJson();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/getevent',
            type: 'post',
            dataType: "json",
            data: JSON.stringify(json),
            success: function (data) {
                $('#message').val(data.message);
                changeButton();
            }
        });
    });

    $('#add').click(function(){
        var json = getJson();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/addevent',
            type: 'post',
            dataType: "json",
            data: JSON.stringify(json),
            success: function (data) {
                changeButton();
                alert("Note is save.")
            }
        });
    });

    $('#update').click(function(){
        var json = getJson();
        $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            url: '/updateevent',
            type: 'post',
            dataType: "json",
            data: JSON.stringify(json),
            success: function (data) {
                changeButton();
                alert("Note is update.")
            }
        });
    });

    function changeButton() {
        var message = $('#message').val();
        if (message.length == 0) {
            $('#add').show();
            $('#update').hide();
        }
        else {
            $('#add').hide();
            $('#update').show();
        }
    }

    function getJson() {
        var json = {
            date: $("#date").val(),
            message: $("#message").val()
        };

        return json;
    }


});