<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<jsp:include page="include/head.jsp" />

<body>
	<jsp:include page="include/header.jsp" />
	
	<div id=pageHeader class="container-fluid">
		<h2>Home</h2>
	</div>


	<div id=main class="container-fluid" >
		<div id=welcome class="container-fluid">
			<h2>Welcome, <c:out value="${user.userName}"></c:out>!</h2>
			<h3>Thanks for being part of our growing community.  Let's talk more soon.</h3>
		</div>
<%-- 		
		<div id=productList class="container-fluid">
			<h3>Product List</h3>
			<!-- <a href= "/store/product/new"><button class="btn btn-primary">Add Product</button></a> -->
			<a href= "/product/new"><button class="btn btn-primary">Add Product</button></a>
			<table class="table table-striped table-dark table-hover">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">productName</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${productList}">
						<tr>
							<td>${record.id}</td>
							<td><a href="/product/${record.id}">${record.productName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div id=categoryList class="container-fluid">
			<h3>Category List</h3>
			<!-- <a href= "/store/category/new"><button class="btn btn-primary">Add Category</button></a> -->
			<a href= "/category/new"><button class="btn btn-primary">Add Category</button></a>
			<table class="table table-striped table-dark table-hover">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">categoryName</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${categoryList}">
						<tr>
							<td>${record.id}</td>
							<td><a href="/category/${record.id}">${record.categoryName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

 --%>

		<div id=List class="container-fluid">
			<h3>Twinone List</h3>
			<!-- <a href= "/store//new"><button class="btn btn-primary">Add Twinone</button></a> -->
			<a href= "/twinone/new"><button class="btn btn-primary">Add Twinone</button></a>
<%-- 			
			<c:choose>
				<c:when test="${mgmtPermissionErrorMsg != null}">
					<p class="errorText">${mgmtPermissionErrorMsg}</p>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose> 
--%>
			<table class="table table-striped table-dark table-hover">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">Name</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${twinoneList}">
						<tr>
							<td>${record.id}</td>
							<%-- <td><a href="/store//${record.id}">${record.Name}</a></td> --%>
							<td><a href="/twinone/${record.id}">${record.twinoneName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		
		<div id=twintwoList class="container-fluid">
			<h3>Twintwo List</h3>
			<!-- <a href= "/store/twintwo/new"><button class="btn btn-primary">Add Twintwo</button></a> -->
			<a href= "/twintwo/new"><button class="btn btn-primary">Add Twintwo</button></a>
<%-- 			
			<c:choose>
				<c:when test="${mgmtPermissionErrorMsg != null}">
					<p class="errorText">${mgmtPermissionErrorMsg}</p>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose> 
--%>
			<table class="table table-striped table-dark table-hover">
				<thead>
					<tr>
						<th scope="col">id</th>
						<th scope="col">twintwoName</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="record" items="${twintwoList}">
						<tr>
							<td>${record.id}</td>
							<%-- <td><a href="/store/twintwo/${record.id}">${record.twintwoName}</a></td> --%>
							<td><a href="/twintwo/${record.id}">${record.twintwoName}</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	
	</div>
	
	<jsp:include page="include/footer.jsp"/>
			
			
</body>
</html>