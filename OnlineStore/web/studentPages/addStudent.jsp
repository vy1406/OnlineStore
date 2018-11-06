<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <span class="font1">Add new student:</span>
    <form action="/Hw4/student/add" method="POST">
        <br/>
        Name:<br/> <input type="text" name="studentName" required="required"/><br/>
        Surname:<br/> <input type="text" name="studentSurname" required="required"/><br/>
        ID:<br/> <input type="text" name="studentID" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>
        Gender:<br/>
        <input type="radio" name="gender" value="male" checked="checked"/>male<br/>
        <input type="radio" name="gender" value="female"/>female<br/>
        <br/>
        Email:<br/> <input type="email" name="sEmail" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}$" title="email@example.com"/><br/>
        Phone number:<br/> <input type="tel" name="phoneNumber" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/><br/>
        Create user: <input type="checkbox" name="user" value="yes"><br/>
        <input type="submit" class="button" value="Add">
    </form>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />