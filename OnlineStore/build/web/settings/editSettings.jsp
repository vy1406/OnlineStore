<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Settings Edit</div>
    <div style="text-align: center; margin-top: 15px;">
        <jsp:useBean id="librarySettings" type="model.Library" scope="request" />
        
        <form action="/Hw4/admin/update">
            <TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR="#FFAD00">
                    <TH>Parameter Name<TH>Parameter Value(s)
                </TR>
                <TR>
                    <TD>Max num of loans</TD>                
                    <TD><input type="text" name="numOfLoans" value="<jsp:getProperty name="librarySettings" property="numOfLoans" />" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>
                </TR>
                <TR>
                    <TD>Damage fine</TD>                
                    <TD><input type="text" name="damageFine" value="<jsp:getProperty name="librarySettings" property="damageFine" />" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>
                </TR>
                <TR>
                    <TD>Late fine</TD>                
                    <TD><input type="text" name="lateFine" value="<jsp:getProperty name="librarySettings" property="lateFine" />" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>
                </TR>
                <TR>
                    <TD>Max fine</TD>                
                    <TD><input type="text" name="maxFine" value="<jsp:getProperty name="librarySettings" property="maxFine" />" onkeypress='return event.charCode >= 48 && event.charCode <= 57'/></TD>
                </TR>
            </TABLE>
            <input type="submit" name="submitValue" value="update" style="float: left; margin-left: 310px;" />
        </form>      
        <a href="/Hw4/welcomePage.jsp"><input type="submit" value="cancel" style="float: left"/></a>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />