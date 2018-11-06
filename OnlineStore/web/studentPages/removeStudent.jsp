<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Remove Student</span><br/>
    Enter student id<br/>
    <form action="/Hw4/student/remove" method="POST">
        <br/>
        ID:<br/> <input type="text" name="studentID" /><br/>
        <input type="submit" class="button" value="Remove">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />