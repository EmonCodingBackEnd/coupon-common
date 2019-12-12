package com.coding.coupon.common.ftp.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class UploadParam extends BaseParam {

    /**
     * 自动探测文件类型，决定上传路径.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180625 20:12</font><br>
     * 默认false，如果设置为true，remoteDirectory无效<br>
     * 图片:images 音频:audios 视频:vedios 文本文件:files
     *
     * @since 1.0.0
     */
    private boolean autoDetect = false;

    /**
     * 上传的文件列表.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180625 20:08</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    private List<File> fileList = new ArrayList<>();

    /**
     * 上传的分段文件对象.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180625 20:07</font><br>
     * [请在此输入功能详述]
     *
     * @since 1.0.0
     */
    private List<MultipartFile> multipartFileList = new ArrayList<>();

    /**
     * 上传的字符串内容格式的文件.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180625 20:04</font><br>
     * 其中，key表示该字符串的原始文件名，value表示字符串内容格式的文件.
     *
     * @since 1.0.0
     */
    private Map<String, String> contentMap = new HashMap<>();

    /**
     * 上传的文件流对象.
     *
     * <p>创建时间: <font style="color:#00FFFF">20180625 20:05</font><br>
     * 其中，key表示该字符串的原始文件名，value表示输入流内容格式的文件.
     *
     * @since 1.0.0
     */
    private Map<String, InputStream> inputStreamMap = new HashMap<>();
}
