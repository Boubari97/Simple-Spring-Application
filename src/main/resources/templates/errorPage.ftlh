<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Error page</title>
</head>
<body>
    <h5><a href="#" onclick="history.go(-1)">Go Back</a>
        OR <a href="/">Go to start page</a></h5>
    <#if statusCode ??><h1>Status code: ${statusCode}</h1></#if>
    <h2>${errorMessage ! "Something went wrong!!!"}</h2>
    <#if errorDetails ??><h5>${errorDetails}</h5></#if>
</body>
</html>