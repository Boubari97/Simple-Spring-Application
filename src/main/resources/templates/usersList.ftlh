<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
</head>
    <h5><a href="/">Back to start page</a></h5>

    <table border=1>
        <tr><td>User Uid</td><td>Username</td></tr>
        <#list users as user>
        <tr><td>${user.uid}</td><td><a href="/users/${user.uid}">${user.username}</a></td></tr>
        </#list>
    </table>
</body>
</html>