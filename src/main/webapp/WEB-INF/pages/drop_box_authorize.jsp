<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>


<div class="starter-template">
    <h1>Input code</h1>

    <a href="/dropbox/usercode"> Get DropBox code</a>

    <form class="form-signin" action="/dropbox/code" method="post">
        <input type="text" name="code" class="form-control" placeholder="Enter code"/>

        <input type="submit" class="btn btn-sm btn-primary">
    </form>

    <hr/>

    <a href="/instagram/usercode"> Get Instagram code</a>
    <p> Token: ${inst}</p>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>