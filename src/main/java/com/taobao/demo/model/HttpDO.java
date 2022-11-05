package com.taobao.demo.model;


import lombok.Data;

@Data
public class HttpDO {

    /**
     * 业务ID
     */
    private Long id;

    /**
     *  来源
     */
    private String from;

    /**
     * 请求状态
     */
    private Integer status;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 耗时
     */
    private Long cost;

    /**
     * uri
     */
    private String uri;

    /**
     * 请求参数
     */
    private String parameters;

    /**
     * user_agent
     */
    private String userAgent;

    /**
     * 原因
     */
    private String cause;
}
