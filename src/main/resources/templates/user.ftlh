<html>
<head>
    <title>User</title>
    <meta charset="UTF-8">
</head>
<body>
<h5><a href="/users">Back to list</a></h5>
<fieldset>
    <legend>User</legend>
    <form name="user">
        Uid : <a>${uid}</a><br/>
        Username : <a>${username}</a><br/>
        <#list numbers as number>
            Numbers: <a>${number.number}</a><br/>
        </#list>
    </form>
</fieldset>
</body>
</html>