<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<title>批量账单充值</title>
<script src="http://cdn.bootcss.com/sockjs-client/1.1.1/sockjs.js"></script>
<script src="http://cdn.bootcss.com/stomp.js/2.3.3/stomp.min.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/tableExport.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.base64.js"></script>
<script language='javascript'>
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
            setMessageInnerHTML(event.data);
        }

        //将消息显示在网页上
        function setMessageInnerHTML(innerHTML) {
            var div = document.getElementById("showMsg");
            addRow(innerHTML);
            div.scrollTop = div.scrollHeight;
        }

        ws.onclose = function () {
            alert("Connection is closed...");
        };
        ws.onerror = function (e) {
            alert(e.msg);
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
        var formData = new FormData();
        formData.append("file", $("#upload")[0].files[0]);
        $.ajax({
            url: '${pageContext.request.contextPath}/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            beforeSend: function () {
                console.log("正在进行，请稍候");
            },
            success: function (responseStr) {
                if (responseStr != 'fail') {
                    connectChatServer(responseStr);
                    console.log("成功:" + responseStr);
                } else {
                    console.log("失败");
                }
            },
            error: function (responseStr) {
                console.log("error");
            }
        });

    }

</script>
<body scroll=yes onkeydown="" style="font-family:'Microsoft YaHei';;font-style: normal;">
<div style='text-align:center'>
    <h3 align=center>批量账单充值</h3>
    <hr style=' height:1px;border:none;border-top:1px dotted #185598;' width=80%/>
    <div style='text-align:center'>
        <p style='text-align:center'><span style='color:green;font-size:13px'>提示:请选择xlsx格式的文件(账单/11888卡)</span></p>

        <div class="uploader white">
            <input type="text" class="filename" readonly="readonly"/>
            <input type="button" name="file" class="button" value="浏览"/>
            <input type="file" name="file" id="upload" size="30"/>
        </div>
        <br>

        <div class="uploader white">
            <input id=queryPay type=submit onclick="uploadFile()" class=button value=批量支付充值>
        </div>
    </div>

    <%--<p><a href='#'><input id=excelFile name="file" class=file type=file size=15 ></a>--%>
    <%--<hr style=' height:1px;border:none;border-top:1px dotted #185598;' width=80%/>--%>

</div>


<div style='text-align:center'><span style='color:#185598;font-size:13px'>账单充值完成笔数：</span><span
        style='color:green;font-size:16px' id=showCount>0</span></div>
<div style="width: 100%;height: 100px;overflow: hidden;margin:auto auto;" class="big2">
    <div style="width: 100%;height: 100px;overflow-y: auto;margin:auto auto;" id="showMsg" class="small2">
        <table id="PrintA" cellpadding='0' cellspacing='0' border='1px solid blue' align="center">
            <tr>
                <td>订单号</td>
                <td>条码</td>
                <td>账单号</td>
                <td>账期</td>
                <td>金额(元)</td>
                <td>11888账号</td>
                <td>账户余额</td>
                <td>支付状态</td>
            </tr>
        </table>
    </div>
</div>
<div style='text-align:right'><span style='color:red;font-size:14px'>* 充值完成后请点击导出文件后重新刷新页面后继续操作</span></div>
<input id="export" class="btn btn-primary btn2"
       onclick="$('#PrintA').tableExport({ type: 'csv', separator: ';', escape: 'false' });" value="导出文件"
       type="button">
</body>
</html>