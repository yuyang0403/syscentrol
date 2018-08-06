package com.yuyang.common.blockchain.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyang
 * @create 2018/8/3 15:50
 * @desc 区块链
 **/
@Data
public class BlockChainVO {
    /**
     * 区块链
     */
    private List<BlockVO> chain=new ArrayList<>();
}
