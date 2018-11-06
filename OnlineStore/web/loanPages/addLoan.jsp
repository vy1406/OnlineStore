<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Add loan</span><br/>
    Enter student ID<br/>
    <form action="/Hw4/loan/addLoan" method="POST">
        <br/>
        ID:<br/> <input type="text" name="studentID" required="required"/><br/>
        <input type="submit" class="button" value="submit">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />