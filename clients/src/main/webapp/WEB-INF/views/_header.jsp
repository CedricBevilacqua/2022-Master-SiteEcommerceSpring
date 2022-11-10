<!DOCTYPE html>
<%@page import="sra1.jee.projet.drive.model.Client"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<% 
String ctxPath = request.getContextPath(); 

%>

<html lang="en">

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<title>Web Drive</title>	
	
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
    <link href="<%= ctxPath %>/css/reclamation.css" rel="stylesheet"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.0/css/all.min.css">
</head>

<body>

<div class="container-fluid">

<!-- navbar -->

<nav class="navbar navbar-expand-lg bg-light">
    <div class="container-fluid">
      <a class="navbar-brand" href="<%=ctxPath%>/home/page.html">Web Drive</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="<%=ctxPath%>/catalog/categories.html">Catégories</a>
          </li>
        </ul>
        <div class="dropdown">
            <a class="btn dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Mon compte
            </a>
			<ul class="dropdown-menu">
			<c:choose>
				<c:when test="${not sessionScope.authentication eq true}">
					<li><a class="dropdown-item" style="text-align:center"  href="<%= ctxPath %>/auth/login.html">Se connecter</a></li>	
				</c:when>
				<c:otherwise>
					<li><a class="dropdown-item" href="<%= ctxPath %>/account/edit.html" >Modifier mon compte</a></li>	
          			<li><a class="dropdown-item" href="<%= ctxPath %>/orders/history.html" >Historique des commandes</a></li>
          			<li><a class="dropdown-item" href="<%= ctxPath %>/reclamation/page.html" >Réclamations</a></li>
          			<c:if test="${sessionScope.role_client eq 'ADMIN'}">
          				<li><a class="dropdown-item" href="<%= ctxPath %>/admin/page.html" >Page d'administration</a></li>
          			</c:if>
					<li><a class="dropdown-item" href="<%= ctxPath %>/auth/logout.html">Se déconnecter</a></li>	
				</c:otherwise>
			</c:choose>
			</ul>
        </div>
        
        <a class="btn" href="<%=ctxPath%>/cart/content.html"><i class="fa fa-shopping-cart" aria-hidden="true"></i></a>
        <div class="dropdown">
            <a class="btn dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            Panier
            </a>
          
            <ul class="dropdown-menu dropdown-menu-end" id="cartSnippet">
         		<!-- cart content -->
            </ul>
        </div>

      </div>
    </div>
  </nav>
 </div>