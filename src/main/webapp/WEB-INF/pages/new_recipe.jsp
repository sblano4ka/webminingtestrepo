<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jspf/taglibs.jspf" %>
<!DOCTYPE html >
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/navbar.jspf" %>
<script type="text/javascript" src="<c:url value='/js/recipe.js' />"></script>

<div class="row" id="content">
    <div class="well">
        <h1>Create recipe</h1>
    </div>
    <div class="col-md-8">
        <h1> if <a href="#" id="selectThis" class="btn btn-success" style="font-size: 35px;">this</a> then
            <a href="#" id="selectThat" class="btn btn-success" style="font-size: 35px;">that</a></h1>
        <p> <a href="#" id="createRecipe" class="btn btn-primary" >Create</a> </p>
    </div>

</div>
<!--/row-->
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>

<!-- Modal Choose Trigger Channel -->
<div class="modal fade" id="triggerChannel" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Choose Trigger Channel</h4>
            </div>
            <div class="modal-body modal-trigger-channel">

            </div>
        </div>
    </div>
</div>


<!-- Modal Choose Trigger -->
<div class="modal fade" id="trigger" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title">Choose Trigger</h4>
            </div>
            <div class="modal-body modal-trigger">

            </div>
        </div>
    </div>
</div>

</html>

