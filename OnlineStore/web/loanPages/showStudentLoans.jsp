<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <jsp:useBean id="loanList" type="java.util.ArrayList<model.Loan>" scope="request" />
    <jsp:useBean id="currStudent" type="model.Student" scope="request" />
    <div style=" text-align: center; font-weight: bold; text-decoration: underline; font-size: xx-large; margin-top: 10px;">Student loans</div>
    <div style=" margin-top: 15px; margin-left: 185px">
        <span style="font-weight: bold;">Student id:</span><jsp:getProperty name="currStudent" property="studetId"/><br/>
        <span style="font-weight: bold;">Student name:</span><jsp:getProperty name="currStudent" property="stdName"/><br/>
        <span style="font-weight: bold;">Fine:</span><jsp:getProperty name="currStudent" property="fine"/><br/>
    </div>
    <hr>
    <div style="text-align: center; margin-top: 15px;">
        <TABLE BORDER=1 ALIGN=CENTER>
            <TR BGCOLOR="#FFAD00">
                <TH>Book Picture<TH>Book name<TH>Author<TH>ISBN<TH>Copy id<TH>Category<TH>Loan StartDate<TH>Loan endDate<TH>In Loan
            </tr>
            <%for(model.Loan l : loanList){%>
                <%for(model.BookInLoan bil : l.getBil()){%>
                <tr>
                    <td><img src="<%=bil.getBook().getBookPic()%>" alt="" height="100" width="100"></td>
                    <td><%=bil.getBook().getBookName()%></td>
                    <td><%=bil.getBook().getAuthor()%></td>
                    <td><%=bil.getBook().getIsbn()%></td>
                    <td><%=bil.getCopy().getCopyID()%></td>
                    <td><%=bil.getBook().getCategory()%></td>
                    <td><%=util.DateUtil.returnSimpleDateFormat(l.getStartDate())%></td>
                    <td><%=util.DateUtil.returnSimpleDateFormat(l.getEndDate())%></td>
                    <td><%=bil.getInLoan()%></td>
                </tr>
                <%}%>
            <%}%>
        </table> 
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />