<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!DOCTYPE html >
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<script type="text/javascript" src="<c:url value='/js/my_recipe.js' />"></script>
<div class="row" id="content">

    <div class="row">
        <div class="jumbotron">
            <h1>My recipes</h1>
        </div>

        <div class="list-recipes">

        </div>
    </div>
</div>

</body>
</html>

