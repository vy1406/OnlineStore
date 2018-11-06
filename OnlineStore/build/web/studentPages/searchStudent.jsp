<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DB.CategoryDB"%>
<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Student search:</span><br/>
    <span class="font2">fill at least one of the fields</span>
    <form action="/Hw4/student/search" method="POST">
        <br/>
        Student name:<br/> <input type="text" name="studentName" /><br/>
        Student surname<br/> <input type="text" name="studentSurname" /><br/>
        ID:<br/> <input type="text" name="studentID" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>
        <input type="submit" class="button" value="search">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />