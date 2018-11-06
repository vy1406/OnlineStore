<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Pay fine</div>
    <div style="text-align: center; margin-top: 15px;">
        <jsp:useBean id="student" type="model.Student" scope="request" />
        <form action="/Hw4/student/updateFine">
            <TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR="#FFAD00">
                    <TH>Parameter Name<TH>Parameter Value(s)
                </TR>
                <TR>
                    <TD>Student id</TD>                
                    <TD><jsp:getProperty name="student" property="studetId" /></TD>
                </TR>
                <TR>
                    <TD>Student name</TD>
                    <TD><jsp:getProperty name="student" property="stdName" /></TD>
                </TR>
                <TR>
                    <TD>Student surname</TD>
                    <TD><jsp:getProperty name="student" property="stdSurName" /></TD>
                </TR>
                <TR>
                    <TD>Current fine</TD>
                    <TD><jsp:getProperty name="student" property="fine" /></TD>
                </TR>
                <TR>
                    <TD>Willing to pay</TD>
                    <TD><input type="number" name="sumToPay" required="required" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="width: 40px;" min="1" max="<jsp:getProperty name="student" property="fine" />"/></TD>
                </TR>
            </TABLE>
            <input type="hidden" name="studentID" value="<%=student.getStudetId()%>">
            <input type="submit" value="submit" style="float: left; margin-left: 339px;" />
        </form>   
        <a href="/Hw4/welcomePage.jsp"><input type="submit" value="cancel" style="float: left"/></a>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />