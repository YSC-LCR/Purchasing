package com.micb2b.purchasing.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jay
 * @date 2019-11-19
 */
@Entity
@Data
@Table(name = "log")
@NoArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * ����
     */
    private String username;

    private String description;

    /**
     * �瘜��
     */
    private String method;

    /**
     * ��
     */
    @Column(columnDefinition = "text")
    private String params;

    /**
     * �隤���
     */
    @Column(name = "log_type")
    private String logType;

    /**
     * 隢�p
     */
    @Column(name = "request_ip")
    private String requestIp;

    /**
     * 隢����
     */
    private Long time;

    /**
     * �撣貉底蝝�
     */
    @Column(name = "exception_detail", columnDefinition = "text")
    private String exceptionDetail;

    /**
     * �撱箸���
     */
    @CreationTimestamp
    @Column(name = "create_time")
    private Timestamp createTime;

    public Log(String logType, Long time) {
        this.logType = logType;
        this.time = time;
    }
}
