<jsp:include page="/includes/includeUpperPart.jsp" />

<div id="content">
    <div style="text-align: center; font-weight: bold; text-decoration: underline; font-size: x-large; margin-top: 10px;">
        <%=(String)request.getSession().getAttribute("messageType")%>
    </div>
        <div style="text-align: center; margin-top: 15px;">
            <p><%=(String)request.getSession().getAttribute("messageContent")%></p>
        <form action="/Hw4/welcomePage.jsp">
                <input type="submit" value="Home" />
            </form>
        </div>

<jsp:include page="/includes/includeBottomPart.jsp" />