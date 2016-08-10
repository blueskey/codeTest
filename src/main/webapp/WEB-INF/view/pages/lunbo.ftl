<!DOCTYPE html>
<#assign basePath=request.contextPath>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        * {
            margin: 0 0;
            padding: 0 0;
        }
        ul{list-style: none;}
        .imgageShow {
            height: 310px;
            width: 990px;
            border: 1px #eeeeee solid;
            margin: 100px auto;
            position: relative;
            overflow: hidden;
        }
        .imgageShow ul{
            position: relative;
            width: 9900px;/*这个width>=n*每个图片宽度
        }
        .imgageShow ul li{
            float: left;  /*让四张图片左浮动，形成并排的横着布局，方便点击按钮时的左移动*/
            width: 990px;
        }

        .imgageShow .showNav{  /*用绝对定位给数字按钮进行布局*/
            position: absolute;
            right: 10px;
            bottom: 5px;
            text-align:center;
            font-size: 12px;
            line-height: 20px;
        }

        .imgageShow .showNav span{
            background-color: #ff5a28;
            color: #fff;
            cursor: pointer;
            display: block;
            margin-left: 2px;
            float: left;
            width: 20px;
            height:20px;
        }

        .imgageShow .showNav .active{
           background-color: #df0000;
            cursor: none;
        }
</style>
    <script type="text/javascript" src="${basePath}/static/js/jquery-1.9.1.min.js"></script>
</head>
<body>
    <div class="imgageShow">
        <ul>
            <li>
                <a href="#"><img src="${basePath}/img/990-310-1.jpg"></a>
            </li>
            <li>
                <a href="#"><img src="${basePath}/img/990-310-2.jpg"></a>
            </li>
            <li>
                <a href="#"><img src="${basePath}/img/990-310-3.jpg"></a>
            </li>
            <li>
                <a href="#"><img src="${basePath}/img/990-310-4.jpg"></a>
            </li>
            <li>
                <a href="#"><img src="${basePath}/img/990-310-5.jpg"></a>
            </li>
            <li>
                <a href="#"><img src="${basePath}/img/990-310-6.jpg"></a>
            </li>
        </ul>

        <!--按钮布局开始-->
        <div class="showNav">
            <span class="active">1</span>
            <span>2</span>
            <span>3</span>
            <span>4</span>
            <span>5</span>
            <span>6</span>
        </div>
    </div>
<script type="text/javascript">
    $(document).ready(function(){
        var imgageShow=$(".imgageShow"),  //获取最外层框架的名称
                ul=imgageShow.find("ul"),
                showNumber=imgageShow.find(".showNav span"),//获取按钮
                oneWidth=imgageShow.find("ul li").eq(0).width(); //获取每个图片的宽度
        var timer=null; //定时器返回值，主要用于关闭定时器
        var iNow=0;  //iNow为正在展示的图片索引值，当用户打开网页时首先显示第一张图，即索引值为0

        showNumber.on("click",function(){   //为每个按钮绑定一个点击事件  
            $(this).addClass("active").siblings().removeClass("active"); //按钮点击时为这个按钮添加高亮状态，并且将其他按钮高亮状态去掉
            var index=$(this).index(); //获取哪个按钮被点击，也就是找到被点击按钮的索引值
            iNow=index;
            ul.animate({
                "left":-oneWidth*iNow, //注意此处用到left属性，所以ul的样式里面需要设置position: relative; 让ul左移N个图片大小的宽度，N根据被点击的按钮索引值iNOWx确定
            })
        });

        timer=setInterval(function(){  //打开定时器
            iNow++;       //让图片的索引值次序加1，这样就可以实现顺序轮播图片
            if(iNow>showNumber.length-1){ //当到达最后一张图的时候，让iNow赋值为第一张图的索引值，轮播效果跳转到第一张图重新开始
                iNow=0;
            }
            showNumber.eq(iNow).trigger("click"); //模拟触发数字按钮的click
        },1000); //1000为轮播的时间
    })


</script>
</body>
</html>