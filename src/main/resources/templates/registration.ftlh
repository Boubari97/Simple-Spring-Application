<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Registration</title>
    <meta charset="UTF-8">
</head>
<h5><a href="/">Back to start page</a></h5><br>
<body>
    <form method="post" action="/registration">
        <br><input name="username" type="text" required placeholder="Username">
        <br><input name="password" type="password" required placeholder="Password">
        <br><input type="submit" value="Create Account">
    </form>
</body>
</html>