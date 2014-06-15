$(document).ready(function () {
    loadRecipe();

    function loadRecipe() {

        $.ajax({
            url: '/getUserRecipes',
            type: 'get',
            dataType: "json",
            success: function (data) {
                viewUserRecipes(data);
            }
        });
    }


    function viewUserRecipes(json) {
        $('.list-recipes').empty();

        var listRecipes = "";
        $.each(json, function (i, item) {
            listRecipes += '<div class="panel panel-default">';
            listRecipes += '<div class="panel-body"><h1>';

            listRecipes += generateHeader(item);

            listRecipes += '<button type="button" class="btn btn-default pull-right delete-recipe" id="' + item.id + '">Delete</button>';
            listRecipes += '</h1></div>';
            listRecipes += '<div class="panel-footer">';

            listRecipes += generateFooter(item);


            listRecipes += '</div>';
            listRecipes += '</div>';
        });

        $('.list-recipes').html(listRecipes);

    }

    function generateHeader(recipe) {
        var header = 'if ';
        header += '<img src="images/'+recipe.channelThis.name+'.png">';
        header += ' then ';
        header += '<img src="images/'+recipe.channelThat.name+'.png">';
        return header;
    }

    function generateFooter(recipe){
        var footer='If ';
        footer += recipe.channelActionThis.name;
        footer += ', then ';
        footer += recipe.channelActionThat.name;
        return footer;
    }

});