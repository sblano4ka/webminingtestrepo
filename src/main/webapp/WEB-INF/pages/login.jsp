<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!DOCTYPE html >
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<div class="row" id="content">
    <c:if test="${not empty message}">
        <div id="error_message" class="alert alert-warning alert-dismissable">
            <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
            <p>${message}</p>
        </div>
    </c:if>
    <div class="col-xs-6 col-xs-offset-3" style="margin-top: 100px">
        <div class="well">
            <form class="bs-example form-horizontal" method="post" action="<c:url value="/login"/> ">
                <fieldset>
                    <legend>Login please:</legend>
                    <div class="form-group">
                        <label for="email" class="col-xs-2 control-label">Login</label>

                        <div class="col-xs-10">
                            <input type="text" class="form-control" name="email" id="email" placeholder="Email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-xs-2 control-label">Password</label>

                        <div class="col-xs-10">
                            <input type="password" class="form-control" name="password" id="password"
                                   placeholder="Password">
                        </div>
                    </div>
                </fieldset>
                <input type="submit" class="btn btn-primary center-block" value="Login"/>
            </form>
        </div>
    </div>
</div>
<!--/row-->

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>

