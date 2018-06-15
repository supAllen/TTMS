package com.ttms.controller;

import com.ttms.playdata.Plupload;
import com.ttms.playdata.PluploadService;
import com.ttms.pojo.DataDict;
import com.ttms.pojo.Play;
import com.ttms.pojo.User;
import com.ttms.service.playService;
import com.ttms.utils.NameUtils;
import com.ttms.utils.TaotaoResult;
import com.ttms.utils.resultType.playInfo;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: Allen
 * @Description:   剧目相关
 * @Date: Created in 8:26 2018/6/5
 * @Modify By:
 */
@Controller
@RequestMapping("/play")
public class playController {

    @Autowired
    private playService ps;

    @Value("${imageRootPath}")
    private String imageRootPath;
    @Value("${imageDisplayPath}")
    private String imageDisplayPath;

    @ResponseBody
    @PostMapping("/addimg")
    public TaotaoResult imgload(@RequestParam(value = "file", required = false)MultipartFile file) throws IOException, IOException {
        // 文件名
        if (file == null)
            return TaotaoResult.build(500,"添加图片失败");
        StringBuilder fileSavePath = new StringBuilder(imageRootPath);
        String originalFilename = file.getOriginalFilename();
        String name = NameUtils.getName();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        fileSavePath.append(name).append(suffix);
        // 转存文件
        file.transferTo(new File(fileSavePath.toString()));
        // 后缀
        String hz = originalFilename.substring(originalFilename.lastIndexOf("."));
        String result = imageDisplayPath + name+hz;
        return TaotaoResult.ok(result);
    }

    /**
     * 两点注意
     *  MultipartFile 请求参数那一个注解可加可不加
     *  play类型要一一对应
     *  顺序可以交叉否？
     * @param play
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public TaotaoResult addc(Play play){
        ps.addplay(play);
        return TaotaoResult.ok();
    }

    @RequestMapping("/ltInfo")
    @ResponseBody
    public TaotaoResult getlts(){
        List<List<DataDict>> playTypesAndLanguages = ps.getPlayTypesAndLanguages();
        return TaotaoResult.ok(playTypesAndLanguages);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<Play> getlist(Integer page){
        return ps.selectAll(page);
    }

    @GetMapping("/list/films/{pid}")
    @ResponseBody
    public Play getPlayInfo(@PathVariable(value = "pid") Integer pid){
        return ps.getPlayInfo(pid);
    }

    @RequestMapping(value="/pluploadUpload")
    public void upload(Plupload plupload, HttpServletRequest request, HttpServletResponse response) {

        String FileDir = "playImages";//文件保存的文件夹
        plupload.setRequest(request);//手动传入Plupload对象HttpServletRequest属性

        int userId = ((User)request.getSession().getAttribute("user")).getUserId();

        //文件存储绝对路径,会是一个文件夹，项目相应Servlet容器下的"playImages"文件夹，还会以用户唯一id作划分
        File dir = new File(request.getSession().getServletContext().getRealPath("/") + FileDir+"/"+userId);
        if(!dir.exists()){
            dir.mkdirs();//可创建多级目录，而mkdir()只能创建一级目录
        }
        //开始上传文件
        PluploadService.upload(plupload, dir);
    }

    @GetMapping("/preInfo")
    @ResponseBody
    public playInfo getPreInfo(Integer pid){
        return ps.getPrePlayInfo(pid);
    }
}
