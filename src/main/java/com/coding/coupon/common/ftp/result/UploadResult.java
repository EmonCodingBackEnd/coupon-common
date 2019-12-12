package com.coding.coupon.common.ftp.result;

import com.coding.coupon.common.ftp.config.ServerConfig;
import com.coding.coupon.common.ftp.param.UploadParam;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class UploadResult {

    private ServerConfig serverConfig;
    private UploadParam uploadParam;

    private Map<String, UploadResultItem> successMap = new HashMap<>();
    private Map<String, UploadResultItem> failureMap = new HashMap<>();

    public boolean hasFailure() {
        return !failureMap.isEmpty();
    }

    public void addResultItem(UploadResultItem item) {
        if (item.isSuccess()) {
            successMap.put(item.getOriginalFilename(), item);
        } else {
            failureMap.put(item.getOriginalFilename(), item);
        }
    }
}
