<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h1>${errorMessage ! "Something went wrong!!!"}</h1>
    <#if errorDetails ??><h5>${errorDetails}</h5></#if>
</body>
</html>