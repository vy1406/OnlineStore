<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Pay fine</span><br/>
    Enter student id<br/>
    <form action="/Hw4/student/payFine" method="POST">
        <br/>
        ID:<br/> <input type="text" name="studentID" /><br/>
        <input type="submit" class="button" value="submit">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />