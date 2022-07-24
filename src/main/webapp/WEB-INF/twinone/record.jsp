<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="/WEB-INF/include/head.jsp" />

<body>
	<jsp:include page="/WEB-INF/include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>Twinone Management</h2>
	</div>
	
	<div id=main class="card">
		<div id="recordHeader"> 
			<h3><c:out value="${twinone.twinoneName}"></c:out></h3>
			<a href= "/twinone/${twinone.id}/edit"><button class="btn btn-primary">Edit</button></a>
		</div>
		<div id=about class="card">
			<%-- <h4>id: <c:out value="${publication.id}"></c:out></h4> --%>
			
			
			<h4>twinoneDesc:</h4>
			<pre class="textAreaReadOut"><c:out value="${twinone.twinoneDesc}"></c:out></pre>

			<h4>price: $<c:out value="${twinone.price}"></c:out></h4>
<%-- 			
			<p>Here are
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id}">your</c:when>
				<c:otherwise>
				${publication.userMdl.userName}'s
				</c:otherwise>
			</c:choose> 
			thoughtsOnPub: </p>
--%>


			<%-- 
			<h4>publication.dojoMdl.dojoName: <c:out value="${publication.dojoMdl.dojoName}"></c:out></h4> 
			--%> 
			<%-- <a href= "/publication/${publication.id}/edit">OrigEdit</a>  --%>
			
<%-- 
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id }">
					<a href= "/publication/${publication.id}/edit"><button class="btn btn-secondary">Edit</button></a>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose> 
			
			<c:choose>
				<c:when test="${user.id == publication.userMdl.id }">
					<form action="/publication/${publication.id}" method="post">
					    <input type="hidden" name="_method" value="delete">
					    <button class="btn btn-danger">Delete this publication</button>
					</form>
				</c:when>
				<c:otherwise>
				</c:otherwise>
			</c:choose>
						 --%>
		</div>
		
		
		
		<div id=list class="card">
			<h3>Twintwo List</h3>
<%-- 			
			<c:choose>
				<c:when test="${mgmtPermissionErrorMsg != null}">
					<p class="errorText">${mgmtPermissionErrorMsg}</p>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose> 
--%>
			<table class="table table-striped table-dark">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">twintwoName</th>
						<th scope="col">actions</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${assignedCategories}">
						<tr>
							<td>${record.id}</td>
							<%-- <td><a href="/store/twintwo/${record.id}">${record.twintwoName}</a></td> --%>
							<td><a href="/twintwo/${record.id}">${record.twintwoName}</a></td>
							<td>
								<%-- <form action="/store/removeTwinoneTwintwoJoin" method="post"> --%>
								<form action="/removeTwinoneTwintwoJoin" method="post">
								    
								    <input type="hidden" name="_method" value="delete">
								    <input type="hidden" name="twintwoId" value="${record.id}"/>
								    <input type="hidden" name="twinoneId" value="${twinone.id}"/>
								    <input type="hidden" name="origin" value="1"/>
								    
								    <button class="btn btn-danger">Delete</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

		<div id=form class="card">
			<h2>Add a Twintwo:</h2>

			<%-- <form action='/store/twinone/${twinone.id}' method='post' > --%>
			<form action='/twinone/${twinone.id}' method='post' >
			<%-- modelAttribute='twinone' --%>
			
				<!-- JRF note to self: I don't think next line is necessry, and cam says yep! -->
				<%-- <input type="hidden" value="${twinone.id}" path="id" /> --%>

				<div class="form-group">
					<select name="twintwoId"> 
					<!-- above name needs to match the value in the paramvalue in the ctl file -->
					<!-- path="twintwoMdl" -->
					<!-- JRF: above left out... this is a JSTL form thing -->
					
						<c:forEach var="record" items="${unassignedCategories}">
							<!--- Each option VALUE is the id of the dojo --->
							<option value="${record.id}" >
							<!-- path="twintwoMdl"> -->
								<!--- This is what shows to the user as the option --->
								<c:out value="${record.twintwoName}" />
							</option>
						</c:forEach>
					</select>
				</div>
 				
 				<button type="submit" class="btn btn-primary">Add</button>
			</form>
		</div>
	
	</div>
 
 	<jsp:include page="/WEB-INF/include/footer.jsp"/>
</body>
</html>