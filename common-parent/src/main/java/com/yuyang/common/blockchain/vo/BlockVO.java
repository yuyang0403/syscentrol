package com.yuyang.common.blockchain.vo;

import lombok.Data;

/**
 * @author yuyang
 * @create 2018/8/3 15:18
 * @desc 区块链-区块
 **/
@Data
public class BlockVO {
    /**
     * 在整个区块链中的位置
     */
    private int index;
    /**
     * 时间戳
     */
    private long timeStamp;
    /**
     * 区块数据
     */
    private String data;
    /**
     * 当前的hash
     */
    private String currentHash;
    /**
     * 上一个的hash
     */
    private String preHash;
}
