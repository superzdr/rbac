<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>部门管理</title>

    <%@ include file="/WEB-INF/views/common/link.jsp" %>

    <script>

        $(function () {
            //拍重
            var ids = $.map($(".selfPermissions option"), function (item) {
                return item.value;
            })
            $.each($(".allPermissions option"), function (index, item) {
                if ($.inArray(item.value, ids) >= 0) {
                    $(item).remove();
                    $(item).remove();
                }
            });

            //提交表单
            $("#btn_submit").click(function () {
                $(".selfPermissions option").prop("selected", true);
                $("#editForm").submit();
            })
        })

        //移动选中
        function moveSelected(srcCls, targetCls) {
            $("." + srcCls + " option:selected").appendTo($("." + targetCls));
        }
        //移动全部
        function moveAll(srcCls, targetCls) {
            $("." + srcCls + " option").appendTo($("." + targetCls))
        }
    </script>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
    <%@ include file="/WEB-INF/views/common/navbar.jsp" %>
    <!--菜单回显-->
    <c:set var="currentMenu" value="role"/>
    <%@ include file="/WEB-INF/views/common/menu.jsp" %>
    <div class="content-wrapper">
        <section class="content-header">
            <h1>角色编辑</h1>
        </section>
        <section class="content">
            <div class="box">
                <form class="form-horizontal" action="/department/saveOrUpdate.do" method="post" id="editForm">

                    <input type="hidden" value="${entity.id}" name="id">
                    <div class="form-group" style="margin-top: 10px;">
                        <label for="name" class="col-sm-2 control-label">角色名称：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="name" name="name" value="${entity.name}"
                                   placeholder="请输入角色名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="sn" class="col-sm-2 control-label">角色编号：</label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="sn" name="sn" value="${entity.sn}"
                                   placeholder="请输入角色编号">
                        </div>
                    </div>
                    <div class="form-group " id="role">
                        <label for="role" class="col-sm-2 control-label">分配权限：</label><br/>
                        <div class="row" style="margin-top: 10px">
                            <div class="col-sm-2 col-sm-offset-2">
                                <select multiple class="form-control allPermissions" size="15">
                                    <c:forEach items="${permissions}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="col-sm-1" style="margin-top: 60px;" align="center">
                                <div>

                                    <a type="button" class="btn btn-primary" style="margin-top: 10px" title="右移动"
                                       onclick="moveSelected('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-menu-right"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="左移动"
                                       onclick="moveSelected('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-menu-left"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全右移动"
                                       onclick="moveAll('allPermissions', 'selfPermissions')">
                                        <span class="glyphicon glyphicon-forward"></span>
                                    </a>
                                </div>
                                <div>
                                    <a type="button" class="btn btn-primary " style="margin-top: 10px" title="全左移动"
                                       onclick="moveAll('selfPermissions', 'allPermissions')">
                                        <span class="glyphicon glyphicon-backward"></span>
                                    </a>
                                </div>
                            </div>
                            <div class="col-sm-2">
                                <select multiple class="form-control selfPermissions" size="15" name="ids">

                                    <c:forEach items="${entity.permissions}" var="p">
                                        <option value="${p.id}">${p.name}</option>
                                    </c:forEach>

                                </select>
                            </div>


                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-1 col-sm-6">
                            <button id="btn_submit" type="button" class="btn btn-primary">保存</button>
                            <button type="reset" class="btn btn-danger">重置</button>
                        </div>
                    </div>

                </form>
            </div>
        </section>
    </div>
    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
</body>
</html>
