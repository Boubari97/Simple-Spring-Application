<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>User</title>
    <meta charset="UTF-8">
</head>
<body>
<h5><a href="#" onclick="history.go(-1)">Go Back</a></h5>
<fieldset>
    <legend>User</legend>
    <form name="user">
        Uid : <a>${uid}</a><br/>
        Username : <a>${username}</a><br/>
        Numbers :
        <#if numbers ??><#list numbers as number>
            <a href="/number/${number.uid}">${number.number}</a><br/>
        </#list></#if>
        <#if username == principal.name><a href="/number">Add number</a></#if><br/>
        Roles :
        <#if roles ??><#list roles as role>
            <a>${role.name}</a><br/>
        </#list></#if>
    </form>
</fieldset>
</body>
</html>