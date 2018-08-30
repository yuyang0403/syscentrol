package com.yuyang.other.pachong.grab.util;

public class TbGrabNotiData {
    /**
     * 主键ID<ID>
     */
    private Integer id;

    /**
     * 标题<TITLE>
     */
    private String title;

    /**
     * 来源地址<SOURCE_URL>
     */
    private String sourceUrl;

    /**
     * 是否网站<IS_WEBSITE>  0_是 1_否
     */
    private String isWebsite;

    /**
     * 下载地址<DOWNLOAD_URL>
     */
    private String downloadUrl;
    
    /**
     * 地区<ADDR>
     */
    private String addr;

    /**
     * 发文日期<PUB_DATE>
     */
    private String pubDate;

    /**
     * 发布机构<PUB_ORG>
     */
    private String pubOrg;

    /**
     * 来源<SOURCE>
     */
    private String source;

    /**
     * 索引号<INDEX_NO>
     */
    private String indexNo;

    /**
     * 信息类别<INFO_TYPE>
     */
    private String infoType;

    /**
     * 文号<REF_NO>
     */
    private String refNo;

    /**
     * 实施日期<PERFORM_DATE>
     */
    private String performDate;

    /**
     * 内容<INFO>
     */
    private String info;

    /**
     * 获取主键ID<ID>
     *
     * @return ID - 主键ID<ID>
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键ID<ID>
     *
     * @param id 主键ID<ID>
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取标题<TITLE>
     *
     * @return TITLE - 标题<TITLE>
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题<TITLE>
     *
     * @param title 标题<TITLE>
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * 获取来源地址<SOURCE_URL>
     *
     * @return SOURCE_URL - 来源地址<SOURCE_URL>
     */
    public String getSourceUrl() {
        return sourceUrl;
    }

    /**
     * 设置来源地址<SOURCE_URL>
     *
     * @param sourceUrl 来源地址<SOURCE_URL>
     */
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl == null ? null : sourceUrl.trim();
    }

    /**
     * 获取是否网站<IS_WEBSITE>  0_是 1_否
     *
     * @return IS_WEBSITE - 是否网站<IS_WEBSITE>  0_是 1_否
     */
    public String getIsWebsite() {
        return isWebsite;
    }

    /**
     * 设置是否网站<IS_WEBSITE>  0_是 1_否
     *
     * @param isWebsite 是否网站<IS_WEBSITE>  0_是 1_否
     */
    public void setIsWebsite(String isWebsite) {
        this.isWebsite = isWebsite == null ? null : isWebsite.trim();
    }

    /**
     * 获取下载地址<DOWNLOAD_URL>
     *
     * @return DOWNLOAD_URL - 下载地址<DOWNLOAD_URL>
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * 设置下载地址<DOWNLOAD_URL>
     *
     * @param downloadUrl 下载地址<DOWNLOAD_URL>
     */
    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl == null ? null : downloadUrl.trim();
    }
    
    /**
     * 获取地区<ADDR>
     *
     * @return ADDR - 地区<ADDR>
     */
    public String getAddr() {
    	return addr;
    }
    
    /**
     * 设置地区<ADDR>
     *
     * @param addr 地区<ADDR>
     */
    public void setAddr(String addr) {
    	this.addr = addr == null ? null : addr.trim();
    }

    /**
     * 获取发文日期<PUB_DATE>
     *
     * @return PUB_DATE - 发文日期<PUB_DATE>
     */
    public String getPubDate() {
        return pubDate;
    }

    /**
     * 设置发文日期<PUB_DATE>
     *
     * @param pubDate 发文日期<PUB_DATE>
     */
    public void setPubDate(String pubDate) {
        this.pubDate = pubDate == null ? null : pubDate.trim();
    }

    /**
     * 获取发布机构<PUB_ORG>
     *
     * @return PUB_ORG - 发布机构<PUB_ORG>
     */
    public String getPubOrg() {
        return pubOrg;
    }

    /**
     * 设置发布机构<PUB_ORG>
     *
     * @param pubOrg 发布机构<PUB_ORG>
     */
    public void setPubOrg(String pubOrg) {
        this.pubOrg = pubOrg == null ? null : pubOrg.trim();
    }

    /**
     * 获取来源<SOURCE>
     *
     * @return SOURCE - 来源<SOURCE>
     */
    public String getSource() {
        return source;
    }

    /**
     * 设置来源<SOURCE>
     *
     * @param source 来源<SOURCE>
     */
    public void setSource(String source) {
        this.source = source == null ? null : source.trim();
    }

    /**
     * 获取索引号<INDEX_NO>
     *
     * @return INDEX_NO - 索引号<INDEX_NO>
     */
    public String getIndexNo() {
        return indexNo;
    }

    /**
     * 设置索引号<INDEX_NO>
     *
     * @param indexNo 索引号<INDEX_NO>
     */
    public void setIndexNo(String indexNo) {
        this.indexNo = indexNo == null ? null : indexNo.trim();
    }

    /**
     * 获取信息类别<INFO_TYPE>
     *
     * @return INFO_TYPE - 信息类别<INFO_TYPE>
     */
    public String getInfoType() {
        return infoType;
    }

    /**
     * 设置信息类别<INFO_TYPE>
     *
     * @param infoType 信息类别<INFO_TYPE>
     */
    public void setInfoType(String infoType) {
        this.infoType = infoType == null ? null : infoType.trim();
    }

    /**
     * 获取文号<REF_NO>
     *
     * @return REF_NO - 文号<REF_NO>
     */
    public String getRefNo() {
        return refNo;
    }

    /**
     * 设置文号<REF_NO>
     *
     * @param refNo 文号<REF_NO>
     */
    public void setRefNo(String refNo) {
        this.refNo = refNo == null ? null : refNo.trim();
    }

    /**
     * 获取实施日期<PERFORM_DATE>
     *
     * @return PERFORM_DATE - 实施日期<PERFORM_DATE>
     */
    public String getPerformDate() {
        return performDate;
    }

    /**
     * 设置实施日期<PERFORM_DATE>
     *
     * @param performDate 实施日期<PERFORM_DATE>
     */
    public void setPerformDate(String performDate) {
        this.performDate = performDate == null ? null : performDate.trim();
    }

    /**
     * 获取内容<INFO>
     *
     * @return INFO - 内容<INFO>
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置内容<INFO>
     *
     * @param info 内容<INFO>
     */
    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }
}