<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="/css/style.css">
    <title>Login</title>
</head>

<body class="flexed vh-100 blue-grey-darker">
    <div class="card blue-grey-dark w-24 shadow">
        <div class="card-body">
            <h2 class="card-title text-light">LOGIN</h2>
            <form action="/login" method="post">
                <div class="mb-3">
                    <input class="form-control" id="email" type="email" name="email" placeholder="Email Address">
                    <small class="orange-text"><c:out value="${error}" /></small>
                </div>
                <div class="mb-3">
                    <input class="form-control" id="password" type="password" name="password" placeholder="Password">
                    <small class="orange-text"><c:out value="${error}" /></small>
                </div>
                <div class="flexed">
                    <button type="submit" class="btn teal flex-grow text-light shadow">LOGIN</button>
                </div>
            </form>
        </div>
        <div class="card-link text-center">
            <p><a class="teal-text" href="/register">Click here to register</a></p>
        </div>
    </div>
</body>
</html>