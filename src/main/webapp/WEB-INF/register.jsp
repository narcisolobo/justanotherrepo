<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/style.css">
    <title>Register</title>
</head>

<body class="flexed vh-100 blue-grey-darker">
    <div class="card blue-grey-dark w-24 shadow">
        <div class="card-body">
            <h2 class="card-title text-light">REGISTER</h2>
            <form:form action="/register" method="post" modelAttribute="user">
                <div class="mb-3">
                    <form:input placeholder="Name" class="form-control" path="name" />
                    <small><form:errors path="name" class="orange-text" /></small>
                </div>
                <div class="mb-3">
                    <form:input placeholder="Email Address" class="form-control" path="email" type="email" />
                    <small><form:errors path="email" class="orange-text" /></small>
                </div>
                <div class="mb-3">
                    <form:input placeholder="Password" class="form-control" path="password" type="password" />
                    <small><form:errors path="password" class="orange-text" /></small>
                </div>
                <div class="mb-3">
                    <form:input placeholder="Confirm Password" class="form-control" path="passwordConfirm" type="password" />
                    <small><form:errors path="passwordConfirm" class="orange-text" /></small>
                </div>
                <div class="flexed">
                    <button type="submit" class="btn teal flex-grow text-light shadow">REGISTER</button>
                </div>
            </form:form>
        </div>
        <div class="card-link text-center">
            <p><a class="teal-text" href="/login">Click here to login.</a></p>
        </div>
    </div>
</body>
</html>