<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Advanced book search:</span><br/>
    <span class="font2">fill at least one of the fields</span>
    <form action="/Hw4/book/search" method="POST">
        <br/>
        Book name:<br/> <input type="text" name="bookName" /><br/>
        Author:<br/> <input type="text" name="authorName" /><br/>
        ISBN:<br/> <input type="text" name="bIsbn" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>
        Category:<br/>
        <%=util.TagUtil.checkBoxCategory() %>
        <input type="submit" class="button" value="search">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />