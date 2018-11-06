<%@page import="java.util.ArrayList"%>
<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Show all books</div>
    <div style="text-align: center; margin-top: 15px;">
        <TABLE BORDER=1 ALIGN=CENTER>
            <TR BGCOLOR="#FFAD00">
                <TH>Book Picture<TH>Book name<TH>Author<TH>ISBN<TH>Category<TH>Release date<TH>Loan
            </TR>
            <jsp:useBean id="bookList" type="ArrayList<model.Book>" scope="request" />
            <%for(model.Book b : bookList) {%>
            <TR>
                <TD><img src="<%=b.getBookPic()%>" alt="" height="100" width="100"></TD>
                <TD><%=b.getBookName()%></TD>
                <TD><%=b.getAuthor()%></TD>
                <TD><%=b.getIsbn()%></TD>
                <TD><%=b.getCategory()%></TD>
                <TD><%=b.getReleaseDate()%></TD>
                <%if(true){%>
                    <TD>
                        <form action="">
                            <input type="hidden" name="isbnFromSearch" value="">
                            <input type="submit" value="loan" />
                        </form>
                    </TD>
                <%}%>
            </TR>
            <%}%>
        </TABLE>
        <form action="/Hw4/welcomePage.jsp">
               <input type="submit" value="HOME" />
        </form>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />