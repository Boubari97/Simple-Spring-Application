<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Start page</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1 align="center">Welcome to start page, ${username ! "Guest"}!</h1>
    <p><#if username ??><a href="/logout">Log Out</a>
        <#else><a href="/login">Log In</a> OR <a href="/registration">Registration</a>
    </#if>
    <p>Show all users in PDF file: <a href="/users/pdf">Go</a>
    <p>Show all users: <a href="/users">Go</a>
    <p>Open load page: <a href="/upload">Go</a>
</body>
</html>