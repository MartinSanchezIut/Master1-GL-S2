<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="fr">
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="Login Page">
        <title>Sign In</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <meta name="theme-color" content="#563d7c">
        <style>
            .errorblock {
                color: #000;
                background-color: #ffEEEE;
                border: 3px solid #ff0000;
                padding: 8px;
                margin: 16px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <h1>Log in</h1>
            <% if(request.getParameter("error") != null) { %>
                <div class="errorblock">Invalid Username and Password</div>
            <% } %>
            <% if(request.getParameter("logout") != null) { %>
            <div class="alert alert-success" role="alert">Logout was successful</div>
            <% } %>
            <% if(request.getParameter("confirm") != null) { %>
            <div class="alert alert-success" role="alert">User creation is confirmed. Please log in</div>
            <% } %>
            <form:form action="/doLogin" method="POST">
                <label for="username">User name: </label>
                <input type="text" id="username" name="username"/>
                <br/>
                <label for="password">Password: </label>
                <input type="password" id="password" name="password"/>
                <br/>
                <label>Remember me: <input type="checkbox" name="remember-me"></label>
                <br/>
                <input type="submit" value="Login" role="button" class="btn btn-lg btn-primary" />
            </form:form>
            If you don't have an account yet, sign up <a href="/register">here</a>
        </div>
    </body>
</html>