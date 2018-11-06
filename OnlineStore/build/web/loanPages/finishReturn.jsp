<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <jsp:useBean id="sessionLoan" type="model.Loan" scope="request" />
    <jsp:useBean id="loanStudent" type="model.Student" scope="request" />
    <jsp:useBean id="damageFine" type="Integer" scope="request" />
    <jsp:useBean id="lateFine" type="Integer" scope="request" />
    
    <div style=" text-align: center; font-weight: bold; text-decoration: underline; font-size: xx-large; margin-top: 10px;">Return finished</div>
    <div style=" margin-top: 15px; margin-left: 185px">
        <span style="font-weight: bold;">Loan id:</span><jsp:getProperty name="sessionLoan" property="loanID"/><br/>
        <span style="font-weight: bold;">Student id:</span><jsp:getProperty name="loanStudent" property="studetId"/><br/>
        <span style="font-weight: bold;">Student name:</span><jsp:getProperty name="loanStudent" property="stdName"/><br/>
        <span style="font-weight: bold;">Fine for book damage:</span><span style="color: red;"><%=damageFine%></span><br/>
        <span style="font-weight: bold;">Fine for late return:</span><span style="color: red;"><%=lateFine%></span><br/>
        <br>
        <span style="font-weight: bold;">book return finished successfully!</span><br/>
    </div>
    <center>
        <form action="/Hw4/welcomePage.jsp">
                <input type="submit" value="Home" />
            </form>
        </div>
    </center>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />