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
                    <h2 class="text-light mt-3">Add a New Idea</h2>
                    <form:form method="POST" action="/ideas/new" modelAttribute="idea">
                        <form:hidden path="creator" value="${user.id}" />
                        <div class="mb-3">
                            <form:label class="form-label text-light" path="title">Title:</form:label>
                            <form:input placeholder="Title" class="form-control" path="title" />
                            <small><form:errors path="title" class="orange-text" /></small>
                        </div>
                        <div class="d-flex justify-content-end">
                            <input type="submit" class="btn teal text-light shadow" value="ADD IDEA" />
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </main>
</body>

</html>