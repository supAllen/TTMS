$(function () {
    $.ajax({
        type: "GET",
        url: "/schedule/list",
        data: {pn: 1},
        async: true,
        dataType: "json",
        success: function (data) {
            createShowTable(data.data.list);
        }
    });

    function createShowTable(data) {
        var tablestr = $("#schtable")
        var len = data.length
        for (var i = 0; i < len; i++) {
            var tr = $("<tr>").appendTo("#schtable"); //动态创建每一列，并往里面添加内容
            $("<td>").appendTo(tr).text(data[i].schedId);
            $("<td>").appendTo(tr).text(data[i].studioId);
            $("<td>").appendTo(tr).text(data[i].playId);
            var date = new Date(data[i].schedTime);
            //$("<td>").appendTo(tr).text(date);
            $("<td>").appendTo(tr).text(date.getFullYear() + "-" + (date.getMonth()+1)
                + "-" + date.getDate() + " "
                + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());
            $("<td>").appendTo(tr).text(data[i].schedTicketPrice);
            $("<td><span class=\"glyphicon glyphicon-pencil\" id='sch_revise' data-toggle=\"modal\" data-target=\"#sch_modify_Model\" data-whatever=\"@mdo\" style=\"color: rgb(255, 140, 60);\"> 修改</span>&nbsp&nbsp" + "<span id='del-schedule' class=\"glyphicon glyphicon-trash\" style=\"color: rgb(255, 140, 60);\" > 删除 </span></td>>").appendTo(tr);
        }
    }

    //向下拉列表添加数据
    $.ajax({
        url: "/schedule/info",
        type: "get",
        dataType: "json",
        success: function (data) {
            var playlist = data.playList
            for (var i = 0; i < playlist.length; i++) {
                $("#sch_select2").append("<option name='playId' value="+playlist[i].playId+">" + playlist[i].playName + "</option>")
            }
            var studioList = data.studioList
            for (var i = 0; i < studioList.length; i++) {
                $("#sch_select1").append("<option name='studioId' value="+studioList[i].studioId+">" + studioList[i].studioName + "</option>")
            }
        }
    })

    $("#sch_select2").bind("change",function () {
        // 根据ID查询响应的票价
        // 查询条件
        var pid = $("#sch_select2").val();//获取当前选择项的值.
        $.ajax({
            type: "GET",
            url: "/schedule/selplayTicketPrice",
            dataType: "json",
            data:{pid:pid} ,
            success: function (data) {
                // 将结果添加到文本框中
                $("#sch_playTicketPrice").val(data.data);
            }
        })
    })

    //演出计划添加
    $("#sch-submit-btn").click(function () {
        var formData = new FormData();
        formData.append('schedId', '');
        formData.append('studioId', $("#sch_select1").val());
        formData.append('playId', $("#sch_select2").val());
        var time = $("#sch_start_Time").val();
        var t = "";
        // 对时间进行处理
        var now = new Date(time);
        var year = now.getFullYear();
        t+=year+"-";
        var month = (now.getMonth()+1);
        if(month < 10)
            month = "0"+month;
        t+=month+"-";
        var date = now.getDate();
        if(date < 10)
            date = "0"+date;
        t+=date+" ";
        var hours = now.getHours();
        if(hours < 10)
            hours = "0"+hours;
        t+=hours+":";
        var minutes = now.getMinutes();
        if(minutes < 10)
            minutes = "0"+minutes;
        t+=minutes+":";
        t+="00"
        formData.append('schedTime', t);
        formData.append('schedTicketPrice', $("#sch_playTicketPrice").val());
        $.ajax({
            type: "POST",
            url: "/schedule/add",
            dataType: "json",
            data:formData,
            processData: false,
            contentType: false,
            success: function (data) {
                console.log(data)
                //1、关闭对话框
                $("#schModel").modal("hide");
            }
        })
    })

    $("#schtable").on("click", "#del-schedule", function () {
        var $tr = $(this).parent().parent()
        var $id = $tr.children('td:first').html()
        $.ajax({
            type: "POST",
            url: "/schedule/del",
            dataType: "json",
            data: {
                'schedId': $id,
            },
            success: function (){
                var $tr = $(this).parent().parent()
                /*var $id = $tr.children('td:first').html()*/
                if (confirm('确认删除ID为'+$id+'的演出厅吗？')){
                    $tr.remove()
                }
            }
        })
    })

    function  ShowTable(data) {
        $("#sch_ms1").val(data.studioId);
        $("#sch_ms2").val(data.playId);
        $("#sch_modify_playTicketPrice").val(data.schedTicketPrice);
    }

    $("#schtable").on("click", "#sch_revise", function () {
        // 将内容添加到下拉列表中
        var $tr = $(this).parent().parent()
        var $id = $tr.children('td:first').html()
        $.ajax({
            type: "get",
            url: "/schedule/content",
            dataType: "json",
            data: {
                schedId:$id
            },
            success: function (data){
                console.log(data)
                ShowTable(data.data);
                $("#sch_m_id").val($id)
            }
        })
    })

    //演出计划修改
    $("#sch-modify-submit-btn").click(function (){
        // 将内容添加到下拉列表中
        var $id = $("#sch_m_id").val();
        var $stuId = $("#sch_ms1").val();
        var $playId = $("#sch_ms2").val();
        var $time = $("#sch_modify_start_Time").val();
        var time = $time;
        var t = "";
        // 对时间进行处理
        var now = new Date(time);
        var year = now.getFullYear();
        t+=year+"-";
        var month = (now.getMonth()+1);
        if(month < 10)
            month = "0"+month;
        t+=month+"-";
        var date = now.getDate();
        if(date < 10)
            date = "0"+date;
        t+=date+" ";
        var hours = now.getHours();
        if(hours < 10)
            hours = "0"+hours;
        t+=hours+":";
        var minutes = now.getMinutes();
        if(minutes < 10)
            minutes = "0"+minutes;
        t+=minutes+":";
        t+="00"
        $time = t;
        var $price = $("#sch_modify_playTicketPrice").val();
        $.ajax({
            type: "post",
            url: "/schedule/modify",
            dataType: "json",
            data: {
                schedId:$id,
                studioId:$stuId,
                playId:$playId,
                schedTime:$time,
                schedTicketPrice:$price
            },
            success: function (data){
                alert("修改成功")
                //1、关闭对话框
                $("#sch_modify_Model").modal("hide");
            }
        })
    })
})