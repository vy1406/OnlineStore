<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Add category</span><br/>
       Enter category name(that doesn't exist!)<br/>
    <form action="/Hw4/AddCategory" method="POST">
        <br/>
        Category name:<br/> <input type="text" name="category" required="required"/><br/>
        <input type="submit" class="button" value="Add">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />
