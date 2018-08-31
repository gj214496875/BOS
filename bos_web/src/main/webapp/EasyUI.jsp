<%--
  Created by IntelliJ IDEA.
  User: gj214
  Date: 2018/7/13
  Time: 7:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <title></title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/js/ztree/zTreeStyle.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/ztree/jquery.ztree.all-3.5.js"></script>
</head>
<body class="easyui-layout">
<!-- 使用div元素描述每个区域 -->
<div title="XXX管理系统" style="height: 100px" data-options="region:'north'">北部区域</div>
<div title="系统菜单" style="width: 200px" data-options="region:'west'">
    <div class="easyui-accordion" data-options="fit:true" >
        <div title="111">
            <a id="but1" class="easyui-linkbutton">添加一个选项卡</a>
            <script type="text/javascript">
                $(function () {
                    $("#but1").click(function () {
                        $("#myTabs").tabs("add",{
                            title:"tabs",
                            content:"<iframe frameborder='0' height='100%' width='100%' src='https://www.b.com'></iframe>",
                            closable:true
                        })
                    });
                });
            </script>
        </div>
        <div title="222">
            <ui id="ztree" class="ztree">
                <script type="text/javascript">
                    $(function () {
                       var setting = {
                           data: {
                               simpleData: {
                                   enable: true
                               }
                           },
                           callback: {
                               onClick: zTreeOnClick
                           }

                       };
                       zTreeNodes = [
                           {"id":"1","pId":"0","name":"一级节点"},
                           {"id":"2","pId":"1","name":"二级节点1"},
                           {"id":"3","pId":"1","name":"二级节点2"},
                           {"id":"4","pId":"2","name":"三级节点1"},
                           {"id":"5","pId":"3","name":"三级节点2"}
                       ];

                       $.fn.zTree.init($("#ztree"), setting, zTreeNodes);

                    });
                    function zTreeOnClick(event, treeId, treeNode) {
                        $("#myTabs").tabs("add",{
                            title:treeNode.name
                        })
                        $.messager.alert("test",treeNode.page,"info")
                    };
                </script>
            </ui>
        </div>
        <div title="333">
            <ul id="ztree3" class="ztree"></ul>
            <script type="text/javascript">
                $(function(){
                    //页面加载完成后，执行这段代码----动态创建ztree
                    var setting3 = {
                        data: {
                            simpleData: {
                                enable: true//使用简单json数据构造ztree节点
                            }
                        },
                        callback: {
                            //为ztree节点绑定单击事件
                            onClick: function(event, treeId, treeNode){
                                $.messager.alert("test",treeNode.page,"info")
                                if(treeNode.page != undefined){
                                    //判断选项卡是否已经存在
                                    var e = $("#myTabs").tabs("exists",treeNode.name);
                                    if(e){
                                        //已经存在，选中
                                        $("#myTabs").tabs("select",treeNode.name);
                                    }else{
                                        //动态添加一个选项卡
                                        $("#myTabs").tabs("add",{
                                            title:treeNode.name,
                                            closable:true,
                                            content:'<iframe frameborder="0" height="100%" width="100%" src="'+treeNode.page+'"></iframe>'
                                        });
                                    }
                                }
                            }
                        }
                    };

                    //发送ajax请求，获取json数据
                    //jQuery提供 的ajax方法：ajax、post、get、load、getJSON、getScript
                    var url = "${pageContext.request.contextPath}/json/menu.json";
                    $.post(url,{},function(data){
                        //调用API初始化ztree
                        $.fn.zTree.init($("#ztree3"), setting3, data);
                    },'json');
                });
            </script>
        </div>
    </div>
</div>
<div data-options="region:'center'">
    <div id="myTabs" class="easyui-tabs" data-options="fit:true">
        <div title="111" data-options="closable:true">111</div>
        <div title="222">222</div>
        <div title="333">333</div>
    </div>
</div>
<div style="width: 100px" data-options="region:'east'">东部区域</div>
<div style="height: 50px" data-options="region:'south'">南部区域</div>
</body>
</html>
