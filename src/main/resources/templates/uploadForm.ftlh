<html>
<head>
    <title>Upload files</title>
    <meta charset="UTF-8">
</head>
<h5><a href="/">Back to start page</a></h5><br>
<body>
    <fieldset>
        <legend>Upload file</legend>
            <form enctype="multipart/form-data" method="post" action="/upload">
                <br><font color="red">Max file size = 256 MB</font>
                <br><input type="file" name="file" accept="application/json">
                <br><input type="submit" value="Upload">
            </form>
    </fieldset>
    <#if successMessage ??><font color="green" size="14">${successMessage}</font></#if>
</body>
</html>