<#import "/spring.ftl" as spring/>
<html>
<head>
    <title>Number</title>
    <meta charset="UTF-8">
</head>
<body>
    <h5><a href="#" onclick="history.go(-1)">Go Back</a>
    OR <a href="/">Go to start page</a></h5>

    <form action=${formAction} method="post">
        <br/><input placeholder="Uid" type="number" name="uid"
                    <#if number ??>value=${number.uid}</#if> readonly>
        <br/><input placeholder="Number" name="number" required
                    <#if number ??>type="text" value="${number.number}" readonly<#else>type="number"</#if>>
        <br/><input placeholder="User" type="text" name="user"
                    value="${username}" required readonly>
        <br/><select name="companyUid" required>
            <option <#if number ?? && number.phoneCompany.uid=1>selected</#if>
                    value="1">MTS</option>
            <option <#if number ?? && number.phoneCompany.uid=2>selected</#if>
                    value="2">Beeline</option>
            <option <#if number ?? && number.phoneCompany.uid=3>selected</#if>
                    value="3">MegaFon</option>
        </select>
    <br/><input type="submit" value="Save">
    </form>
</body>
</html>