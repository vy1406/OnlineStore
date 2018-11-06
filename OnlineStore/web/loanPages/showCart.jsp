<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <jsp:useBean id="sessionLoan" type="model.Loan" scope="session" />
    <jsp:useBean id="loanCart" type="java.util.ArrayList<model.BookInLoan>" scope="session" />
    <jsp:useBean id="loanStudent" type="model.Student" scope="session" />
    <div style=" text-align: center; font-weight: bold; text-decoration: underline; font-size: xx-large; margin-top: 10px;">Loan cart</div>
    <div style=" margin-top: 15px; margin-left: 185px">
        <span style="font-weight: bold;">Loan id:</span><jsp:getProperty name="sessionLoan" property="loanID"/><br/>
        <span style="font-weight: bold;">Student id:</span><jsp:getProperty name="loanStudent" property="studetId"/><br/>
        <span style="font-weight: bold;">Student name:</span><jsp:getProperty name="loanStudent" property="stdName"/><br/>
        <span style="font-weight: bold;">Fine:</span><jsp:getProperty name="loanStudent" property="fine"/><br/>
        <span style="font-weight: bold;">Number of books allowed:</span><jsp:getProperty name="loanStudent" property="numOfBooksAllowed"/><br/>
        <%if(loanCart.size()<=loanStudent.getNumOfBooksAllowed()){%>
            <span style="font-weight: bold;">Number of books in cart:</span><%=loanCart.size()%><br/>
        <%}else{%>
            <span style="font-weight: bold;">Number of books in cart:</span><span style="color: red;"><%=loanCart.size()%></span><br/>
            <span style="color: red;">The amount of books in cart exceed your</span><br/>
            <span style="color: red;">allowed number of books you can loan.</span><br/>
        <%}%>
    </div>
    <hr>
    <div style="text-align: center; margin-top: 15px;">
        <TABLE BORDER=1 ALIGN=CENTER>
            <TR BGCOLOR="#FFAD00">
                <TH>Book Picture<TH>Book name<TH>Author<TH>ISBN<TH>Copy id<TH>Category<TH>Remove
            </tr>
            <%for(model.BookInLoan bil : loanCart){%>
            <tr>
                <td><img src="<%=bil.getBook().getBookPic()%>" alt="" height="100" width="100"></td>
                <td><%=bil.getBook().getBookName()%></td>
                <td><%=bil.getBook().getAuthor()%></td>
                <td><%=bil.getBook().getIsbn()%></td>
                <td><%=bil.getCopy().getCopyID()%></td>
                <td><%=bil.getBook().getCategory()%></td>
                <td>
                    <form action="/Hw4/loan/removeBookFromCart">
                        <input type="hidden" name="bookIsbn" value="<%=bil.getCopy().getBookIsbn()%>">
                        <input type="hidden" name="copyID" value="<%=bil.getCopy().getCopyID()%>">
                        <input type="submit" value="remove" />
                    </form>
                </td>
            </tr>
            <%}%>
        </table> 
        <form action="/Hw4/loan/finishLoan" style="float: left; margin-left: 320px;">
            <input type="submit" value="Finish Loan" style="width: 110px;"/>
        </form>
        <form action="/Hw4/loan/cancelLoan" style="float: left; margin-left: 20px;">
            <input type="submit" value="Cancel Loan" style="width: 110px;"/>
        </form>
        <br/>
        <form action="/Hw4/bookPages/searchBook.jsp">
            <input type="submit" value="Search" style="float: left; margin-left: 380px; width: 110px;"/>
        </form>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />