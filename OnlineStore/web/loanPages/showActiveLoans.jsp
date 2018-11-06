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
        <form action="/Hw4/loan/returnBook">
            <TABLE BORDER=1 ALIGN=CENTER>
                <TR BGCOLOR="#FFAD00">
                    <TH>Book Picture<TH>Book name<TH>ISBN<TH>Loan id<TH>Loan StartDate<TH>Loan endDate<TH>Late<TH>Curr condition<TH>New Condition<TH>Return
                </tr>
                <%for(model.Loan l : loanList){%>
                    <%for(model.BookInLoan bil : l.getBil()){%>
                    <tr>

                        <td><img src="<%=bil.getBook().getBookPic()%>" alt="" height="100" width="100"></td>
                        <td><%=bil.getBook().getBookName()%></td>
                        <td><%=bil.getBook().getIsbn()%></td>
                        <td><%=l.getLoanID()%></td>
                        <td><%=util.DateUtil.returnSimpleDateFormat(l.getStartDate())%></td>
                        <td><%=util.DateUtil.returnSimpleDateFormat(l.getEndDate())%></td>
                        <td><%=util.DateUtil.checkIfLate(l.getEndDate())%></td>
                        <td><%=bil.getCopy().getCopyCondition()%></td>
                        <td>
                            <input type="hidden" name="allConditionsID" value="<%=bil.getCopy().getCopyID()%>">
                            <input type="number" name="condition" required="required" value="<%=bil.getCopy().getCopyCondition()%>" onkeypress='return event.charCode >= 48 && event.charCode <= 57' style="width: 40px;" min="<%=bil.getCopy().getCopyCondition()%>" max="5"/>
                        </td>
                        <td>
                            <input type="checkbox" name="copyID" value="<%=l.getLoanID()%>:<%=bil.getCopy().getCopyID()%>">
                        </td>
                    </tr>
                    <%}%>
                <%}%>
            </table> 
            <input type="hidden" name="studentID" value="<%=currStudent.getStudetId()%>">
            <input type="submit" value="return" />
        </form>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />