package com.coding.coupon.common.ftp.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class DownloadParam extends ListableParam {
    // 文件要下载到的本地目录
    private String localDirectory = "/fileserver/ftproot";
}
