package com.tamecode.lesson7.entity;

import lombok.ToString;

import java.util.Date;

@ToString
public class ZhExplore {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zh_explore.id
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zh_explore.name
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    private String exploreName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zh_explore.url
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    private String url;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zh_explore.digest
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    private String digest;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zh_explore.idate
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    private Date idate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column zh_explore.page
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    private byte[] page;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zh_explore.id
     *
     * @return the value of zh_explore.id
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zh_explore.id
     *
     * @param id the value for zh_explore.id
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zh_explore.name
     *
     * @return the value of zh_explore.name
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public String getExploreName() {
        return exploreName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zh_explore.name
     *
     * @param exploreName the value for zh_explore.name
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public void setExploreName(String exploreName) {
        this.exploreName = exploreName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zh_explore.url
     *
     * @return the value of zh_explore.url
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zh_explore.url
     *
     * @param url the value for zh_explore.url
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zh_explore.digest
     *
     * @return the value of zh_explore.digest
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public String getDigest() {
        return digest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zh_explore.digest
     *
     * @param digest the value for zh_explore.digest
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public void setDigest(String digest) {
        this.digest = digest;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zh_explore.idate
     *
     * @return the value of zh_explore.idate
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public Date getIdate() {
        return idate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zh_explore.idate
     *
     * @param idate the value for zh_explore.idate
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public void setIdate(Date idate) {
        this.idate = idate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column zh_explore.page
     *
     * @return the value of zh_explore.page
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public byte[] getPage() {
        return page;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column zh_explore.page
     *
     * @param page the value for zh_explore.page
     *
     * @mbg.generated Sat Aug 15 23:20:55 CST 2020
     */
    public void setPage(byte[] page) {
        this.page = page;
    }
}