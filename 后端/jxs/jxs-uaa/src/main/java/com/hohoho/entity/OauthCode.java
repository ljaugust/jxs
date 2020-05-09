package com.hohoho.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author QMK
 * @since 2020-03-25
 */
@TableName("oauth_code")
public class OauthCode extends Model<OauthCode> {

    private static final long serialVersionUID = 1L;

    @TableField("create_time")
    private Date createTime;
    private String code;
    private String authentication;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public static final String CREATE_TIME = "create_time";

    public static final String CODE = "code";

    public static final String AUTHENTICATION = "authentication";

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "OauthCode{" +
        ", createTime=" + createTime +
        ", code=" + code +
        ", authentication=" + authentication +
        "}";
    }
}
