<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Student Edit</div>
    <div style="text-align: center; margin-top: 15px;">
        <jsp:useBean id="studentToEdit" type="model.Student" scope="request" />
        <form action="/Hw4/student/updateStudent">
            <TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR="#FFAD00">
                    <TH>Parameter Name<TH>Parameter Value(s)
                </TR>
                <TR>
                    <TD>Student id</TD>                
                    <TD><input type="text" name="studentID" required="required" value="<jsp:getProperty name="studentToEdit" property="studetId" />" readonly="readonly" /></TD>
                </TR>
                <TR>
                    <TD>Student name</TD>
                    <TD><input type="text" name="studentName" required="required" value="<jsp:getProperty name="studentToEdit" property="stdName" />" /></TD>
                </TR>
                <TR>
                    <TD>Student surname</TD>
                    <TD><input type="text" name="studentSurname" required="required" value="<jsp:getProperty name="studentToEdit" property="stdSurName" />" /></TD>
                </TR>
                <TR>
                    <TD>Email</TD>
                    <TD><input type="email" name="sEmail" required="required" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,3}$" title="email@example.com" value="<jsp:getProperty name="studentToEdit" property="stdEmail" />"/></TD>
                </TR>
                <TR>
                    <TD>Gender</TD>
                    <TD><%= util.TagUtil.checkBoxGender(studentToEdit.getGender()) %></TD>
                </TR>
                <TR>
                    <TD>Phone number</TD>
                    <TD><input type="tel" name="phoneNumber" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' value="<jsp:getProperty name="studentToEdit" property="phoneNumber" />"/></TD>
                </TR>
            </TABLE>
            <input type="submit" value="submit" style="float: left; margin-left: 270px;" />
        </form>
        <form action="/Hw4/student/remove" style="float: left">
            <input type="hidden" name="studentID" value="<%=studentToEdit.getStudetId() %>">
            <input type="submit" value="delete" />
        </form> 
        <form action="/Hw4/student/addUser" style="float: left">
            <input type="hidden" name="studentID" value="<%=studentToEdit.getStudetId() %>">
            <input type="submit" value="Create user" />
        </form>      
        <a href="/Hw4/welcomePage.jsp"><input type="submit" value="cancel" style="float: left"/></a>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />