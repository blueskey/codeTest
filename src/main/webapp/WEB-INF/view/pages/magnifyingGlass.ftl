<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<#assign basePath=request.contextPath>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>图片放大镜效果。</title>
    <style type="text/css">
        #magnifier{
            width:400px;
            height:300px;
            position:absolute;
            top:100px;
            left:150px;
            font-size:0;
            border:1px solid #000;
        }
        #img{
            width:400px;
            height:300px;
        }
        #alixixi_com{
            border:1px solid #000;
            z-index:100;
            position:absolute;
            background:#555;
        }
        #mag{
            border:1px solid #000;
            overflow:hidden;
            z-index:100;
        }
    </style>
    <script type="text/javascript" src="${basePath}/static/js/magnifyingGlass.js"></script>
</head>
<body>

<div id="magnifier">
    <img src="${basePath}/img/230.jpg" id="img"  onload="return imgzoom(this,600);" onclick="javascript:window.open(this.src);" style="cursor:pointer;"/>
    <div id="alixixi_com"></div>
</div>
<div id="mag"><img id="magnifierImg"  onload="return imgzoom(this,600);" onclick="javascript:window.open(this.src);" style="cursor:pointer;"/></div>

<div id="licesePicMagnifier">
    <#--<img src="${basePath}/img/230.jpg" alt="..." id="liceseImg"-->
         <#--onload="return imgzoom(this,600);" onclick="javascript:window.open(this.src);" style="cursor:pointer;"-->
         <#--class="img-rounded" >-->
    <div class="alixixi_com"></div>
</div>
<div id="licesePicM"><img id="licesePicMagnifierImg"  onload="return imgzoom(this,600);" onclick="javascript:window.open(this.src);" style="cursor:pointer;"/></div>

</body>
</html>