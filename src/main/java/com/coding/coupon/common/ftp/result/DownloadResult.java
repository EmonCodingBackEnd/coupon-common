package com.coding.coupon.common.ftp.result;

import com.coding.coupon.common.ftp.config.ServerConfig;
import com.coding.coupon.common.ftp.param.DownloadParam;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DownloadResult {

    private ServerConfig serverConfig;
    private DownloadParam downloadParam;

    private List<DownloadResultItem> successList = new ArrayList<>();
    private List<DownloadResultItem> failureList = new ArrayList<>();

    public boolean hasFailure() {
        return !failureList.isEmpty();
    }

    public void addResultItem(DownloadResultItem item) {
        if (item.isSuccess()) {
            successList.add(item);
        } else {
            failureList.add(item);
        }
    }
}
