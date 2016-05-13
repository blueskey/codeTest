<html>

<head>
    <style>

        ul.dh{list-style-type: none;}
        ul.dh a{text-decoration:none;}
        ul.dh li{
            float: left;　display: inline;
            width:60px;
            height: 30px;background-color:deepskyblue;font-size: 12px;color:#fff;line-height: 2em;vertical-align:middle;padding:4px 4px;border-right: 1px solid #fff;text-align: center}
        ul.dh li:hover{background-color: aqua;cursor: pointer;}
    </style>
</head>


<body>
<div>
<ul class="dh">
<li name="sx"><a href="#sx">数学</a></li>
<li name="tw"><a href="#tw">天文</a></li>
<li name="yx"><a href="#yx">医学</a></li>
<li name="yyx"><a href="#yyx">语言学</a></li>
<li name="kg"><a href="#kg">考古学</a></li>
<li name="xl"><a href="#xl">心理学</a></li>
</ul>
</div>
<div>
    <div id="sx" ><p>数学</p></div>
    <div id="tw" hidden="hidden"><p>天文</p></div>
    <div id="yyx" hidden="hidden"><p>天文</p></div>
    <div id="kg" hidden="hidden"><p>天文</p></div>
    <div id="xl" hidden="hidden"><p>天文</p></div>
</div>
<br/>
<br/>
<br/>
<hr/>
<h1>${message}</h1>
天天

${request.getContextPath()}

<mark>helo</mark>
<figure>
    <img src="img/1.jpg" alt="test">
    <figcaption><p>我是这张图片的文字</p></figcaption>
</figure>

<table>
    <th>
        姓名
    </th>
    <th>
        年龄
    </th>
    <th>
        生日
    </th>


<#list userDos as userDo>
    <#if userDo.name!="lili">
        <tr>
            <td>${(userDo.name) !"visitor"}</td>
            <td>${userDo.age}</td>
            <td>${userDo.birthday?string("yyyy-MM-dd HH:mm:ss")}</td>
        </tr>
    </#if>

</#list>
</table>

<br/>
<br/>

<table>
    <th>
        姓名
    </th>
    <th>
        年龄
    </th>
    <th>
        生日
    </th>

    <!-- 遍历map 法1 -->
<#list userDoMap?keys as userName>
    <tr>
        <td>${(userName) !"visitor"}</td>
        <td>${userDoMap[userName].age} </td>
        <td>${userDoMap[userName].birthday?string("yyyy-MM-dd HH:mm:ss")}</td>
    </tr>

</#list>

    <!-- 遍历map 法2 选定？-->
<#--<#list userDoMap.keySet() as userName>-->
    <#--<tr>-->
        <#--<td>${(userName) !"visitor"}</td>-->
        <#--<td>${userDoMap.get(userName).age} </td>-->
        <#--<td>${userDoMap.get(userName).birthday?string("yyyy-MM-dd HH:mm:ss")}</td>-->
    <#--</tr>-->
<#--</#list>-->



</table>

<#list ["foo", "bar", "baz"] + ["Julia", "Kate"] as x>
${x}
</#list>
<br>

<!-- 1-10 -->
<#list 1 .. 10 as x>
${x}
</#list>
<br>

<!-- 1-4 -->
<#list 1 ..< 5 as x>
${x}
</#list>

<br>
<!-- 1-5 -->
<#list 1 ..* 5 as x>
${x}
</#list>

<br>

<#assign s = "ABCDEF">
${s}
<br>
${s[2..3]}
<br>
${s[2..<4]}
<br>
${s[2..*3]}
<br>
${s[2..*100]}
<br>
${s[2..]}
<br>

<#assign ages = {"Joe":23, "Fred":25} + {"Joe":30, "Julia":18}>
- Joe is ${ages.Joe}
- Fred is ${ages.Fred}
- Julia is ${ages.Julia}

<form action="" method="post">
    <label for="username">Create a Username: </label>
    <input type="text"
           name="username"
           id="username"
           placeholder="4 <> 10"
           pattern="[A-Za-z]{4,10}"
           autofocus
           required>
    <button type="submit">Go </button>
</form>


<small>我是底部小字</small>
</body>
</html>