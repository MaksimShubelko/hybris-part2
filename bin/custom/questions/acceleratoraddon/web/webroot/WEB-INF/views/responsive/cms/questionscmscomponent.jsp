<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>PDP Questions component</title>
</head>
<body>
<form content="${product}">
    <div style="font-size: ${fontSize}px">
        <div class="row">
            <div class="col-xs-offset-1 text-success">
                <br>Font size: ${fontSize}
                <br>Max questions to show: ${numberOfQuestionsToShow}
                <br>
                <br>

                <c:forEach var="question" items="${questions}" varStatus="loop">
                    <c:if test="${loop.count <= numberOfQuestionsToShow}">
                        <br>Q: ${question.getQuestion()}
                        <br>A: ${question.getAnswer()}
                        <br>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </div>
</form>
</body>
</html>
