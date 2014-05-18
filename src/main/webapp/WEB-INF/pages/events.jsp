<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap 101 Template</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" href="<c:url value='/css/datepicker.css' />"/>
    <link rel="stylesheet" href="<c:url value='/css/bootstrap.css' />"/>


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script type="text/javascript" src="<c:url value='/js/bootstrap.min.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/bootstrap-datepicker.js' />"></script>
    <script type="text/javascript" src="<c:url value='/js/events.js' />"></script>


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>

    <![endif]-->
</head>
<body>


<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Your events</a>
        </div>

        <!--/.nav-collapse -->
    </div>
</div>

<div class="container">

    <div class="starter-template">
        <h1>Please select date.</h1>

        <form class="form-signin" id="events">
            <div class="form-group">
                <input type="text" id="date" name="date" value="02/16/2014"/>
            </div>
            <div class="form-group">
                <textarea class="form-control" id="message" rows="6"> </textarea>
            </div>

            <div id="buttons">
                <button type="button" id="add" class="btn btn-success">Add event</button>
                <button type="button" id="update" class="btn btn-success">Update event</button>
            </div>

        </form>


        <script>
            $('#date').datepicker();
        </script>


    </div>

</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

</body>
</html>