package com.tamecode.lesson10;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Liqc
 * @date 2020/9/15 10:21
 */
@Slf4j
public class DownTest {

    RestTemplate restTemplate = new RestTemplate();

    String mp3Url = "http://secret-axb-record-files.oss-cn-shanghai.aliyuncs.com/1000021237185643_68726287336390480581600158571953_8615210023188.mp3?Expires=1600166398&OSSAccessKeyId=LTAI27GqAW1VrcQA&Signature=6UfqnNXjqBrZAPZCMyqRWzU2%2BWg%3D";

    @Test
    public void downMp3Test() {
        Instant start = Instant.now();
        HttpEntity<Resource> httpEntity = new HttpEntity<>(new HttpHeaders());
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File("aa.mp3"))) {
            ResponseEntity<byte[]> response = restTemplate.exchange(mp3Url, HttpMethod.GET, httpEntity, byte[].class);
            log.info("===状态码================");
            log.info(">> {}", response.getStatusCodeValue());
            log.info("===返回信息================");
            log.info(">> {}", response.getHeaders().getContentType());
            log.info(">> {}", response.getHeaders().getContentType().getSubtype());
            byte[] body = response.getBody();
            fileOutputStream.write(body);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("复制花费时间： %d ms", Duration.between(start, Instant.now()).toMillis());
        // 复制花费时间： 172 ms
    }

    @Test
    public void downMp3Test2() throws IOException {
        Instant start = Instant.now();
        try {
            String fileName = "123.mp3";
            String recordDir = "d:/apps/record";

            URL url = new URL(mp3Url);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //设置超时间为5秒
            conn.setConnectTimeout(5 * 1000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            //得到输入流
            InputStream inputStream = conn.getInputStream();
            File file = new File(recordDir);
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(new File(recordDir, fileName));
            fileOutputStream.write(toByteArray(inputStream));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("复制花费时间： %d ms", Duration.between(start, Instant.now()).toMillis());
        // 复制花费时间： 172 ms
    }

    @Test
    public void nioTest() throws IOException {

        Instant start = Instant.now();

        URL url = new URL(mp3Url);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream recordStream = conn.getInputStream();
        // 直接对缓冲区进行数据的读写操作
        byte[] bytes = new byte[recordStream.available()];
        recordStream.read(bytes);

        // StandardOpenOption.CREATE_NEW 不存在创建，存在就报错。StandardOpenOption.CREATE 存在不存在都会去创建
        FileChannel outChannel = FileChannel.open(Paths.get("D:\\apps\\record\\", "2.mp3"), StandardOpenOption.CREATE_NEW);
        MappedByteBuffer outMapBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);

        outMapBuffer.put(bytes);
//        inChannel.transferTo(0, inChannel.size(), outChannel);

        outChannel.close();

        System.out.printf("复制花费时间： %d ms", Duration.between(start, Instant.now()).toMillis());
        // 复制花费时间： 168 ms

//        URL url = new URL(mp3Url);
//        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
//        //设置超时间为5秒
//        conn.setConnectTimeout(5*1000);
//        //防止屏蔽程序抓取而返回403错误
//        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
//        //得到输入流
//        InputStream recordStream = conn.getInputStream();

    }

    @Test
    public void testRegex() {
        String urlRegex = "(?<=\\/).+(?=\\?)";
        Pattern compile = Pattern.compile(urlRegex);
        Matcher matcher = compile.matcher(mp3Url);
        for (int i = 0; i < matcher.groupCount(); i++) {
            System.out.println(matcher.group(i));
        }
    }

    @Test
    public void testRegex2() {
        // 按指定模式在字符串查找
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(\\D*)(\\d+)(.*)";

        // 创建 Pattern 对象
        Pattern r = Pattern.compile(pattern);

        // 现在创建 matcher 对象
        Matcher m = r.matcher(line);
        if (m.find()) {
            System.out.println("Found value: " + m.group(0));
            System.out.println("Found value: " + m.group(1));
            System.out.println("Found value: " + m.group(2));
            System.out.println("Found value: " + m.group(3));
        } else {
            System.out.println("NO MATCH");
        }
    }

    @Test
    public void mkdir() {
        String dir = "D:/apps/12/";
        File file = new File(dir);
        System.out.println(file.getAbsolutePath());
    }


    public byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[4096];
        int n = 0;
        while (-1 != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
        }
        return output.toByteArray();
    }

}
