<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
    <title>Ideas</title>
</head>

<body class="blue-grey-dark">
    <nav class="navbar teal shadow">
        <div class="container">
            <a href="/ideas" class="navbar-brand text-light">IDEAS</a>
            <ul class="navbar-nav">
                <li class="navbar-item"><a class="text-light" href="/logout">LOGOUT ${fn:toUpperCase(user.name)}</a></li>
            </ul>
        </div>
    </nav>
    <main>
        <div class="container">
            <div class="row">
                <div class="col-sm-12">
                    <h5 class="mt-3 text-light">Welcome, ${user.name}. Here are all the ideas.</h5>
                    <div class="d-flex justify-content-end">
                        <a href="/ideas/true" class="btn btn-sm btn-warning shadow">High Likes</a>
                        <a href="/ideas/false" class="btn btn-sm btn-warning shadow ms-3">Low Likes</a>
                    </div>
                    <table class="table table-striped table-hover text-light">
                        <thead>
                            <tr>
                                <th>Idea</th>
                                <th>Creator</th>
                                <th>Likes</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ideas}" var="idea">
                                <tr class="text-light">
                                    <td class="align-middle"><a class="teal-text" href="/ideas/${idea.id}">${idea.title}</a></td>
                                    <td class="align-middle">${idea.creator.name}</td>
                                    <td class="align-middle">${idea.likes.size()}</td>
                                    <td class="align-middle">
                                        <c:choose>
                                            <c:when test="${idea.likes.contains(user)}">
                                                <a href="/ideas/${idea.id}/unlike" class="btn btn-sm btn-warning text-light shadow">Unlike</a>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="/ideas/${idea.id}/like" class="btn btn-sm teal text-light shadow">Like</a>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="d-flex justify-content-end">
                        <a class="btn teal text-light shadow" href="/ideas/new">Add a New Idea</a>
                    </div>
                </div>
            </div>
        </div>
    </main>
</body>

</html>