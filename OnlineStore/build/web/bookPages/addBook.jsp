<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.CategoryDB"%>
<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Add new book to the library</span>
    <form action="/Hw4/book/add" method="POST">
        <br/>
        Book name:<br/> <input type="text" name="bookName" required="required" /><br/>
        Author:<br/> <input type="text" name="authorName" required="required"/><br/>
        Category:<br/>
        <%=util.TagUtil.selectCategory("null") %>
        ISBN:<br/> <input type="text" name="bIsbn" onkeypress='return event.charCode >= 48 && event.charCode <= 57' required="required"/><br/>
        Release date:<br/> <input type="number" name="releaseDate" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' min="1900" max="2016"/><br/>
        Number of copies:<br/> <input type="number" name="numOfCopies" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="width: 40px;" min="1" max="50"/><br/>
        Book url:<br/> <input type="text" name="pic" pattern="https?://.+" title="Include http://"><br/>
        <input type="submit" class="button" value="Add">
    </form>
</div>
        
<jsp:include page="/includes/includeBottomPart.jsp" />
