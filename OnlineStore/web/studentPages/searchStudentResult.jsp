<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">Student search result</div>
    <div style="text-align: center; margin-top: 15px;">
        <jsp:useBean id="studentsTable" type="model.Table" scope="request" />
        <%=studentsTable.getTable()%>
        <form action="/Hw4/welcomePage.jsp">
               <input type="submit" value="HOME" />
        </form>
    </div>
</div>

<jsp:include page="/includes/includeBottomPart.jsp" />