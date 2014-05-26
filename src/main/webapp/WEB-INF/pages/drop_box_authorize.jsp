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
            <form class="bs-example form-horizontal" method="post" action="<c:url value="/dropbox/code"/> ">
                <fieldset>
                    <legend>DropBox code:</legend>
                    <div class="form-group">
                        <label for="code" class="col-xs-2 control-label">Code</label>

                        <div class="col-xs-10">
                            <input type="text" class="form-control" name="code" id="code" placeholder="Code">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="code" class="col-xs-2 control-label"></label>
                        <div class="col-xs-10">
                            <a href="<c:url value="/dropbox/usercode"/>">Get code from DropBox</a>
                        </div>
                    </div>

                </fieldset>
                <input type="submit" class="btn btn-primary center-block" value="Submit"/>
            </form>
        </div>
    </div>
</div>
<!--/row-->

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>

