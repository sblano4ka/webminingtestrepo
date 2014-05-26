$(document).ready(function () {
    var recipe = {};
    var triggerChannel;

    // THIS
    $('#selectThis').click(function () {
        $.ajax({
            url: '/getTriggerChanel',
            type: 'get',
            dataType: "json",
            data: { 'type': 'this'},
            success: function (data) {
                viewTriggerChannelThis(data);
            }
        });

        $('#triggerChannel').modal('show');
    });

    function viewTriggerChannelThis(json) {
        $('.modal-trigger-channel').empty();
        var k = 0;
        var popup = '';
        $.each(json, function (i, item) {
            if (i == 0 || (i + 1) % 3 == 0) {
                popup += '<div class="row">';
                k = i;
            }
            popup += '<div class="col-md-4">';
            popup += '<a  class="link-trigger-channel-this" href="#" id="' + item.id + '">';
            popup += item.name;
            popup += '</a>';
            popup += '</div>';

            if (k + 3 == i) {
                popup += '</div>';
            }

        });

        if (k % 3 != 0) {
            popup += '</div>';
        }
        $('.modal-trigger-channel').html(popup);

        $(document).on('click', "a.link-trigger-channel-this", function () {
            var id = $(this).attr('id');
            triggerChannel = $(this).text();
            recipe.serviceThisId = id;
            viewPopupTriggerThis(id);
            alert(triggerChannel);
        });
    }

    function viewPopupTriggerThis(id) {
        $.ajax({
            url: '/getTrigger',
            type: 'get',
            dataType: "json",
            data: { 'id': id, 'type': 'this' },
            success: function (data) {
                viewTriggerThis(data);
            }
        });

        $('#trigger').modal('show');

    }

    function viewTriggerThis(json) {
        $('.modal-trigger').empty();
        var k = 0;
        var popup = '';
        $.each(json, function (i, item) {
            if (i == 0 || (i + 1) % 3 == 0) {
                popup += '<div class="row">';
                k = i;
            }
            popup += '<div class="col-md-4">';
            popup += '<div class="alert alert-success">';
            popup += '<a  class="link-trigger-this" href="#" id="' + item.id + '">';
            popup += item.name;
            popup += '</a>';
            popup += '</div>';
            popup += '</div>';

            if (k + 3 == i) {
                popup += '</div>';
            }

        });

        if (k % 3 != 0) {
            popup += '</div>';
        }
        $('.modal-trigger').html(popup);

        $(document).on('click', "a.link-trigger-this", function () {
            var id = $(this).attr('id');
            recipe.serviceActionThisId = id;
            $('#selectThis').text(triggerChannel);
            $('#trigger').modal('hide');
            $('#triggerChannel').modal('hide');
        });
    }


    // THAT

    $('#selectThat').click(function () {
        $.ajax({
            url: '/getTriggerChanel',
            type: 'get',
            dataType: "json",
            data: { 'type': 'that'},
            success: function (data) {
                viewTriggerChannelThat(data);
            }
        });

        $('#triggerChannel').modal('show');
    });


    function viewTriggerChannelThat(json) {
        $('.modal-trigger-channel').empty();
        var k = 0;
        var popup = '';
        $.each(json, function (i, item) {
            if (i == 0 || (i + 1) % 3 == 0) {
                popup += '<div class="row">';
                k = i;
            }
            popup += '<div class="col-md-4">';
            popup += '<a  class="link-trigger-channel-that" href="#" id="' + item.id + '">';
            popup += item.name;
            popup += '</a>';
            popup += '</div>';

            if (k + 3 == i) {
                popup += '</div>';
            }

        });

        if (k % 3 != 0) {
            popup += '</div>';
        }
        $('.modal-trigger-channel').html(popup);

        $(document).on('click', "a.link-trigger-channel-that", function () {
            var id = $(this).attr('id');
            triggerChannel = $(this).text();
            recipe.serviceThatId = id;
            viewPopupTriggerThat(id);
            alert(triggerChannel);
        });
    }

    function viewPopupTriggerThat(id) {
        $.ajax({
            url: '/getTrigger',
            type: 'get',
            dataType: "json",
            data: { 'id': id, 'type': 'that'},
            success: function (data) {
                viewTriggerThat(data);
            }
        });

        $('#trigger').modal('show');

    }

    function viewTriggerThat(json) {
        $('.modal-trigger').empty();
        var k = 0;
        var popup = '';
        $.each(json, function (i, item) {
            if (i == 0 || (i + 1) % 3 == 0) {
                popup += '<div class="row">';
                k = i;
            }
            popup += '<div class="col-md-4">';
            popup += '<div class="alert alert-success">';
            popup += '<a  class="link-trigger-that" href="#" id="' + item.id + '">';
            popup += item.name;
            popup += '</a>';
            popup += '</div>';
            popup += '</div>';

            if (k + 3 == i) {
                popup += '</div>';
            }

        });

        if (k % 3 != 0) {
            popup += '</div>';
        }
        $('.modal-trigger').html(popup);

        $(document).on('click', "a.link-trigger-that", function () {
            var id = $(this).attr('id');
            recipe.serviceActionThatId = id;
            $('#selectThat').text(triggerChannel);
            $('#trigger').modal('hide');
            $('#triggerChannel').modal('hide');
        });
    }
});