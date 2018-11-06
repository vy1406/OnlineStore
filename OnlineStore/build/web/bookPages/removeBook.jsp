<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Remove book</span><br/>
       Enter Isbn number of the book<br/>
    <form action="/Hw4/book/remove" method="POST">
        <br/>
        ISBN:<br/> <input type="text" name="bIsbn" /><br/>
        <input type="submit" class="button" value="Remove">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />