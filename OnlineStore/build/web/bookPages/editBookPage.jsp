<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Edit book</span><br/>
       Enter Isbn number of the book<br/>
    <form action="/Hw4/book/edit" method="POST">
        <br/>
        ISBN:<br/> <input type="text" name="bookIsbn" /><br/>
        <input type="submit" class="button" value="Edit">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />