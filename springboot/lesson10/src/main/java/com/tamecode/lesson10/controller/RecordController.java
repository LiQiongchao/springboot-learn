package com.tamecode.lesson10.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @author Liqc
 * @date 2020/9/14 17:33
 */
@Slf4j
@Controller
public class RecordController {

    RestTemplate restTemplate = new RestTemplate();

    @GetMapping("record")
    public void bd(HttpServletResponse response) {
        try {
            response.sendRedirect("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("record/2")
    public ResponseEntity<FileSystemResource> getRecord(HttpServletResponse servletResponse) {
        String url = "";
        HttpEntity<Resource> httpEntity = new HttpEntity<>(new HttpHeaders());
        try {
            ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, byte[].class);
            log.info("===状态码================");
            log.info(">> {}", response.getStatusCodeValue());
            log.info("===返回信息================");
            log.info(">> {}", response.getHeaders().getContentType());
            log.info(">> {}", response.getHeaders().getContentType().getSubtype());
            File file = File.createTempFile("ess-", "." + url.substring(url.lastIndexOf(".") + 1));
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Content-Disposition", "attachment; filename=" + file.getName());
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-Type", "audio/mpeg");
            headers.add("Accept-Ranges", "bytes");
            headers.add("Last-Modified", new Date().toString());
            headers.add("ETag", String.valueOf(System.currentTimeMillis()));
            return ResponseEntity.ok().headers(headers).contentLength(file.length())
                    .contentType(MediaType.parseMediaType("application/octet-stream")).body(new FileSystemResource(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
