<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<title>德佳通信平台</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery.nice-file-input.min.css" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<script src="${pageContext.request.contextPath}/js/msg-notify.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/ajaxfileupload.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.nice-file-input.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tableExport.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.base64.js"></script>
<script language='javascript'>
    $('document').ready(function () {
        $(".nicefile").niceFileInput({
            'width': '500', //width of button - minimum 150
            'height': '30',  //height of text
            'btnText': '浏览', //text of the button
            'btnWidth': '100',  // width of button
            'margin': '20'	// gap between textbox and button - minimum 14
        });
    });
    var ws;
    function connectChatServer(filePath) {
        var localObj = window.location;
        var contextPath = localObj.pathname.split("/")[1];
        var basePath = "ws://" + localObj.host + "/" + contextPath;
        if (basePath.charAt(basePath.length - 1) != "/") {
            basePath = basePath + "/";
        }
        ws = new WebSocket(basePath + "webSocket");
        ws.binaryType = "arraybuffer";
        ws.onopen = function () {
            ws.send(filePath);
        };

        ws.onmessage = function (event) {
            if (event.data.indexOf('&') > 0) {
                $("#msg_title").text(event.data.split("&")[0]);
                $("#msg_content").text(event.data.split("&")[1]);
                document.getElementById('msg_win').style.display = "block";
                Message.init();
                $("#queryPay").removeAttr("disabled");
                $("#upload").removeAttr("disabled");
                $("#queryPay").val("提交");
            } else {
                setMessageInnerHTML(event.data);
            }
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
            var div = document.getElementById("showMsg");
            addRow(innerHTML);
            div.scrollTop = div.scrollHeight;
        }

        ws.onclose = function () {
            console.log("Connection is closed...");
        };
        ws.onerror = function (e) {
            console.log(e.msg);
        }

    }

    function addRow(msg) {
        var rowContext = "";
        var msgs = msg.split("_");
        if (msgs.length > 7) {
            rowContext = "<tr><td>" + msgs[0] + "</td><td style='mso-number-format:@;' >" + msgs[1] + "</td><td>" + msgs[2] + "</td><td>" + msgs[3] + "</td><td>" + msgs[4] + "</td><td>" + msgs[5] + "</td><td>" + msgs[6] + "</td><td>" + msgs[7] + "</td></tr>";
        } else {
            rowContext = "<tr><td colspan='8'>" + msg + "</td></tr>"
        }
        $("#PrintA").append(rowContext);
        $("#showCount").text($("#PrintA tr").length - 1);
    }

    $(function () {
        $("input[type=file]").change(
                function () {
                    $(this).parents(".uploader").find(".filename").val($(this).val());
                });
        $("input[type=file]").each(
                function () {
                    if ($(this).val() == "") {
                        $(this).parents(".uploader").find(".filename").val("No file selected...");
                    }
                });
    });

    function uploadFile() {
        var reg = /^.*\.(?:xlsx)$/i;
        if (!reg.test($("#upload").val())) {
            alert("请上传xlsx格式的文件!");
            return;
        }
        $("#queryPay").val("正在上传");
        $("#queryPay").attr("disabled", "disabled");
        $("#upload").attr("disabled", "disabled");
        var formData = new FormData();
        formData.append("file", $("#upload")[0].files[0]);
        $.ajax({
            url: '${pageContext.request.contextPath}/upload',
            type: 'POST',
            data: formData,
            async: false,
            contentType: false,
            processData: false,
            beforeSend: function () {
                console.log("正在进行，请稍候");
            },
            success: function (responseStr) {
                if (responseStr != '') {
                    $("#queryPay").val("上传成功");
                    $("table tbody tr").eq(0).nextAll().remove();
                    connectChatServer(responseStr);
                    console.log("成功:" + responseStr);
                } else {
                    console.log("失败");
                    $("#queryPay").val("文件上传失败，文件大小可能超限");
                }
            },
            error: function (responseStr) {
                console.log("error");
                $("#queryPay").val("文件上传失败，请检查网络是否连接");
            }
        });

    }

</script>
<body scroll=yes onkeydown="" style="font-family:'Microsoft YaHei';;font-style: normal;">
<div style="text-align: center;">
    <H2 align="center">批量账单充值</H2>
    <HR width="80%"
        style="border-width: 1px medium medium; border-style: dotted none none; border-color: rgb(24, 85, 152); border-image: none; height: 1px;">
</div>
<div style="margin:15px 50px" align="center">
    <P style="text-align: center;"><span style="color: green; font-size: 13px;">提示：请选择xlsx格式的文件[账单条码/11888充值卡]</span>
    </P>

    <form id="uploadForm" enctype="multipart/form-data">
        <input type="file" id="upload" name="file" class="nicefile"/>
    </form>
</div>
<div style="text-align: center;">
    <input id="queryPay" type="submit" onclick="uploadFile()" class="ui blue submit button" value="提交">
</div>
<div style="text-align: right;width:92%">&nbsp;&nbsp;<input id="export"
                                                            onclick="$('#PrintA').tableExport({ type: 'txt', separator: ';', escape: 'false' });"
                                                            type="button" class="ui submit button" value="导出"
                                                            style="background-color: #fff;"></div>
<div class="big2" style="margin: auto; width: 100%; height: 250px; overflow: hidden;">
    <div class="small2" id="showMsg" style="margin: auto; width: 100%; height: 250px; overflow-y:auto">
        <table align="center" id="PrintA" border="1" cellspacing="0" cellpadding="0"
               style="border-collapse:collapse">
            <tBody>
            <tr>
                <th>订单号</th>
                <th>条码</th>
                <th>账单号</th>
                <th>账期</th>
                <th>金额(元)</th>
                <th>11888账号</th>
                <th>账户余额</th>
                <th>支付状态</th>
            </tr>
            <tr>
                <td colspan=8>无充值记录</td>
            </tr>
            </tBody>
        </table>
    </div>
</div>
<hr width="80%"
    style="border-width: 1px medium medium; border-style: dotted none none; border-color: rgb(24, 85, 152); border-image: none; height: 1px;"/>

<div style="text-align: center"><span style="color: rgb(24, 85, 152); font-size: 14px;">账单充值完成笔数：</span><span
        id="showCount" style="color: green; font-size: 18px;">0</span></div>
<div id="msg_win" style="display:none;top:490px;visibility:visible;opacity:1;">
    <div class="icos"><a id="msg_min" title="最小化" href="javascript:void 0">_</a><a id="msg_close" title="关闭"
                                                                                   href="javascript:void 0">×</a>
    </div>
    <div id="msg_title"></div>
    <div id="msg_content"></div>
</div>

</body>
</html>