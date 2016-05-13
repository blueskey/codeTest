<form method="post" action="${postUrl}">
<#list postParams?keys as keyName>
${keyName}：<input name="${keyName}" value="${postParams[keyName]}"><br/>

</#list>
<input type="submit" value="提交">
</form>