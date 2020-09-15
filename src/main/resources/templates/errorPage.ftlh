<#import "/spring.ftl" as spring/>

<html>
<head>
    <title>Error page</title>
</head>
<h5><a href="/">Back to start page</a></h5><br>
<body>
    <h1>${errorMessage ! "Something went wrong!!!"}</h1>
    <#if errorDetails ??><h5>${errorDetails}</h5></#if>
    <#if exceptionCause ??><h5>${exceptionCause}</h5></#if>
    <#if statusCode ??><h5>Code: ${statusCode}</h5></#if>
</body>
</html>