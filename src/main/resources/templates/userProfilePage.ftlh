<#import "/spring.ftl" as spring/>
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
        Numbers:
        <#if numbers ??><#list numbers as number>
            <a>${number.number}</a><br/>
        </#list></#if>
        ROLES:
        <#if roles ??><#list roles as role>
            <a>${role.name}</a><br/>
        </#list></#if>
    </form>
</fieldset>
</body>
</html>