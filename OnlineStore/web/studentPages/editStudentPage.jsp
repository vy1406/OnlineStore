<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Edit Student</span><br/>
       Enter ID number of the Student<br/>
    <form action="/Hw4/student/edit" method="POST">
        <br/>
        Student id:<br/> <input type="text" name="studentID" /><br/>
        <input type="submit" class="button" value="Edit">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />