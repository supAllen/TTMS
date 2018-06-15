$(function () {
    $.ajax({
        type: "GET",
        url: "/play/list",
        async: true,
        data:{page:1},
        dataType: "json",
        success: function (data) {
            createShowTable(data);
        }
    });

    function createShowTable(data) {
        var tablestr = $("#table")
        var len = data.length
        for (var i = 0; i < len; i++) {
            var tr = $("<tr>").appendTo("#table"); //动态创建每一列，并往里面添加内容
            $("<td>").appendTo(tr).text(data[i].playId);
            $("<td>").appendTo(tr).text(data[i].playTypeId);
            $("<td>").appendTo(tr).text(data[i].playLangId);
            $("<td>").appendTo(tr).text(data[i].playName);
            var str = data[i].playIntroduction
            str = str.substr(0,9)
            $("<td>").appendTo(tr).text(str);
            $("<td>").appendTo(tr).text(data[i].playImage);
            $("<td>").appendTo(tr).text(data[i].playLength);
            $("<td>").appendTo(tr).text(data[i].playTicketPrice);
            $("<td>").appendTo(tr).text(data[i].playStatus);
            $("<td>").appendTo(tr).text(data[i].playBookingOffice);
            $("<td><span class=\"glyphicon glyphicon-pencil\" id=\"revise\" data-toggle=\"modal\" data-target=\"#exampleModal2\" data-whatever=\"@mdo\" style=\"color: rgb(255, 140, 60);\"> 修改</span>&nbsp&nbsp" + "<span class=\"glyphicon glyphicon-trash\" style=\"color: rgb(255, 140, 60);\" > 删除 </span></td>>").appendTo(tr);
        }
    }

    //向下拉列表添加数据
    $.ajax({
        url: "/play/ltInfo",
        type: "get",
        dataType: "json",
        success: function (data) {
            data = data.data
            for (var i = 0; i < data[0].length; i++) {
                $("#select-list1").append("<option name='playTypeId'>" + data[0][i].dictValue + "</option>")
            }
            for (var i = 0; i < data[1].length; i++) {
                $("#select-list2").append("<option name='playLangId'>" + data[1][i].dictValue + "</option>")
            }
        }
    })
    //剧目添加
    $("#btn-submit").click(function () {
        var formData = new FormData();
        // 文件元素
        formData.append('playId', '');
        formData.append('playTypeId', $("#select-list1 option:checked").text());
        formData.append('playLangId', $("#select-list2 option:checked").text());
        formData.append('playName', $("#recipient-name").val());
        formData.append('playImage', $("#input-hiddlen").val());
        formData.append('playIntroduction', $("#recipient-name1").val());
        formData.append('playLength', $("#recipient-name3").val());
        formData.append('playTicketPrice', $("#recipient-name4").val());
        formData.append('playStatus', 0);
        formData.append('playBookingOffice', 0);
        $.ajax({
            type: "post",
            url: "/play/add",
            dataType: "json",
            processData: false,
            contentType: false,
            data: formData,
            success: function (data) {
            }
        })
    })
    //剧目修改
    $("#table").on("click", "#revise", function () {
        $.ajax({
            type: "post",
            url: "/play/add",
            dataType: "json",
            data: {'playName': $("#recipient-name").val(),
                'playImage': $("#input-hiddlen").val(),
                'playIntroduction': $("#recipient-name1").val(),
                'playLength':$("#recipient-name3").val(),
                'playTicketPrice': $("#recipient-name4").val()},
            success: function (){

            }
        })
    })
    //表单验证
    $("#my_form").validate({
        messages: {
            playName: {
                required: "必填部分"
            },
            playIntroduction: {
                required: "必填部分",
                minlength: "最少四位"
            },
            playLength: {
                required: "必填部分"
            },
            playTicketPrice: {
                required: "必填部分"
            }
        }
    })
    //删除
    //图片上传事件
    $("#recipient-name2").change(function () {
        var formData = new FormData($("#my_form")[0]);
        // 文件元素
        $.ajax({
            type: "post",
            url: "/play/addimg",
            dataType: "json",
            data: formData,
            processData: false,
            contentType: false,
            success: function (data) {
                $("#input-hiddlen").val(data.data);
            }
        })
    })

})