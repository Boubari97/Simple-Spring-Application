<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Users</title>
    <meta charset="UTF-8">
</head>
<body>
    <h5><a href="/">Back to start page</a></h5><br>
    <table border=1>
        <tr><td>User Uid</td><td>Username</td></tr>
        <#list users as user>
            <tr><form method="post" action="/users/${user.uid}/delete">
                <td>${user.uid}</td><td><a href="/users/${user.uid}">${user.username}</a></td>
                <td><input type="submit" value="Delete user"/></td>
            </form></tr>
        </#list>
    </table>
</body>
</html>