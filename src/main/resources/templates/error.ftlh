<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h1>${message ! "Something went wrong!!!"}</h1>
    <#if error ??><h5>${error}</h5></#if>
</body>
</html>