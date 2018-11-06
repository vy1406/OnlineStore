<%@page import="java.util.ArrayList"%>
<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Book Edit</div>
    <div style="text-align: center; margin-top: 15px;">
        <jsp:useBean id="bookToEdit" type="model.Book" scope="request" />
        <form action="/Hw4/book/updateBook">
            <TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR="#FFAD00">
                    <TH>Parameter Name<TH>Parameter Value(s)
                </TR>
                <TR>
                    <TD>Book isbn</TD>                
                    <TD><input type="text" name="bIsbn" value="<jsp:getProperty name="bookToEdit" property="isbn" />" onkeypress='return event.charCode >= 48 && event.charCode <= 57' readonly="readonly"/></TD>
                </TR>
                <TR>
                    <TD>Book name</TD>
                    <TD><input type="text" name="bookName" required="required" value="<jsp:getProperty name="bookToEdit" property="bookName" />" /></TD>
                </TR>
                <TR>
                    <TD>Book author</TD>
                    <TD><input type="text" name="authorName" required="required" value="<jsp:getProperty name="bookToEdit" property="author" />" /></TD>
                </TR>
                <TR>
                    <TD>Category</TD>
                    <TD><%=util.TagUtil.selectCategory(bookToEdit.getCategory()) %></TD>
                </TR>
                <TR>
                    <TD>Book release year</TD>
                    <TD><input type="number" name="releaseDate" required="required" value="<jsp:getProperty name="bookToEdit" property="releaseDate" />" onkeypress='return event.charCode >= 48 && event.charCode <= 57' min="1900" max="2016"/></TD>
                </TR>
                <TR>
                    <TD>Pic location</TD>
                    <TD><input type="text" name="pic" value="<jsp:getProperty name="bookToEdit" property="bookPic" />" /></TD>
                </TR>
                <TR>
                    <TD>Add copies</TD>
                    <TD>
                        <input type="number" name="numOfCopies" required="required" value="1" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="width: 40px;" min="1" max="50"/>
                        <input type="submit" name="submitValue" value="add" />
                    </TD>
                </TR>
            </TABLE>
            <input type="submit" name="submitValue" value="update" style="float: left; margin-left: 310px;" />
        </form>
        <form action="/Hw4/book/remove" style="float: left">
            <input type="hidden" name="bIsbn" value="<%=bookToEdit.getIsbn() %>">
            <input type="submit" value="delete" />
        </form>        
        <a href="/Hw4/welcomePage.jsp"><input type="submit" value="cancel" style="float: left"/></a>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />