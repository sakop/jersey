<html>
   ${user}
   <#assign a="1"/>
   ${a}
   <@print obj="${latestProduct.url}"/>
</html>

<#macro print obj>
        hello ${obj}
</#macro>