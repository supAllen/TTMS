package com.ttms.playdata;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Allen
 * @Description:
 * @Date: Created in 20:49 2018/6/11
 * @Modify By:
 */
public class Plupload {
    /**文件原名*/
    private String name;
    /**用户上传资料被分解总块数*/
    private int chunks = -1;
    /**当前块数（从0开始计数）*/
    private int chunk = -1;
    /**HttpServletRequest对象，不会自动赋值，需要手动传入*/
    private HttpServletRequest request;
    /**保存文件上传信息，不会自动赋值，需要手动传入*/
    private MultipartFile multipartFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
