<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Mouse 목록</title>
  <%@ taglib prefix="c" uri="jakarta.tags.core" %>
  <%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
</head>
<body>
  <table>
    <tr>
      <th>번호</th>
      <th>이름</th>
      <th>국적</th>
      <th>주소</th>
    </tr>
    <c:choose>
      <c:when test="${empty requestScope.mice || fn:length(mice) == 0}">
        <tr>
          <td colspan="4">등록된 Mouse 정보가 없습니다.</td>
        </tr>
      </c:when>
      <c:otherwise>
        <c:forEach items="${requestScope.mice}" var="mouse">
          <tr>
            <td>${mouse.id}</td>
            <td>${mouse.name}</td>
            <td>${mouse.country}</td>
            <td>${mouse.address}</td>
          </tr>
        </c:forEach>
      </c:otherwise>
    </c:choose>
  </table>
</body>
</html>
