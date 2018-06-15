$(function(){
    // 查询演出厅
    $.ajax({
        type:"GET",
        url:"/studio/list",
        data:{pn:1},
        async:true,
        dataType: "json",
        success: function(data){
            createShowTable(data.data.list);
        }
    });
    function createShowTable(data){
        var tablestr = $("#table1")
        var len = data.length
        for(var i = 0;i < len; i++){
            var tr = $("<tr>").appendTo("#table1"); //动态创建每一列，并往里面添加内容
            $("<td>").appendTo(tr).text(data[i].studioId).css("text-align","center");
            $("<td>").appendTo(tr).text(data[i].studioName);
            $("<td>").appendTo(tr).text(data[i].studioRowCount);
            $("<td>").appendTo(tr).text(data[i].studioColCount);
            $("<td>").appendTo(tr).text(data[i].studioIntroduction);
            $("<td>").appendTo(tr).text(data[i].studioFlag);
            $("<td><span class=\"glyphicon glyphicon-pencil\" data-toggle=\"modal\" data-target=\"#exampleModal1\" data-whatever=\"@mdo\" style=\"color: rgb(255, 140, 60);\" onclick=\"mod(this)\"> 修改</span>&nbsp&nbsp" +
                "<span class=\"glyphicon glyphicon-trash\" style=\"color: rgb(255, 140, 60);\" onclick=\"del(this)\"> 删除 </span></td>>").appendTo(tr);
            1			}
        if(len==0){
            tablestr += "<tr style='text-align:center'>'+<td >"+暂无记录+"</td>+'</tr>"
        }
    }

    //添加演出厅信息
    $("#studio-submit-btn").click(function () {
        var $studioName = $("#recipient-name-1").val()
        var $studioRowCount = $("#recipient-name-2").val()
        var $studioColCount = $("#recipient-name-3").val()
        var $studioIntroduction = $("#recipient-name-4").val()
        var $studioFlag = $("#recipient-name-5").val()
        if($studioName == "" || $studioRowCount == "" || $studioColCount == "" || $studioIntroduction == ""){
            alert("请完善信息")
        }else{
            $.ajax({
                type:"post",
                url:"/studio/add",
                dataType: "json",
                data:{
                    "studioName": $studioName,
                    "studioRowCount": $studioRowCount,
                    "studioColCount": $studioColCount,
                    "studioIntroduction": $studioIntroduction,
                    "studioFlag": $studioFlag
                },
                success: function (data) {
                    if(data.status == 200)
                        alert("添加成功")
                    // 关闭模态框
                    //1、关闭对话框
                    $("#exampleModal-1").modal("hide");
                }
            })
        }
    })


})