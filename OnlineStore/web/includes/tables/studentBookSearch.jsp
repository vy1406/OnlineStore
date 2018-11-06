<TABLE BORDER=1 ALIGN=CENTER>
    <TR BGCOLOR="#FFAD00">
        <TH>Book Picture<TH>Book name<TH>Author<TH>ISBN<TH>Category<TH>Release date<TH>Copies available<TH>Loan
    </TR>
    <jsp:useBean id="bookList" type="java.util.ArrayList<model.Book>" scope="request" />
    <%for(model.Book b : bookList) {%>
    <TR>
        <TD><img src="<%=b.getBookPic()%>" alt="" height="100" width="100"></TD>
        <TD><%=b.getBookName()%></TD>
        <TD><%=b.getAuthor()%></TD>
        <TD><%=b.getIsbn()%></TD>
        <TD><%=b.getCategory()%></TD>
        <TD><%=b.getReleaseDate()%></TD>
        <TD><%=b.getNumOfCopiesAvailable()%></TD>
        <TD>
            <%if(b.getNumOfCopiesAvailable()>0){%>
            <form action="/Hw4/loan/addBookToCart">
                <input type="hidden" name="bookIsbn" value="<%=b.getIsbn()%>">
                <input type="submit" value="loan" />
            </form>
            <%}%>
        </TD>
    </TR>
    <%}%>
</TABLE>
<form action="/Hw4/welcomePage.jsp">
       <input type="submit" value="HOME" />
</form>