package com.hohoho.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("j_perm")
public class JPerm extends Model<JPerm> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "perm_id", type = IdType.AUTO)
    private Integer permId;
    private String code;
    private String description;
    private String url;


    public Integer getPermId() {
        return permId;
    }

    public void setPermId(Integer permId) {
        this.permId = permId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static final String PERM_ID = "perm_id";

    public static final String CODE = "code";

    public static final String DESCRIPTION = "description";

    public static final String URL = "url";

    @Override
    protected Serializable pkVal() {
        return this.permId;
    }

    @Override
    public String toString() {
        return "JPerm{" +
        ", permId=" + permId +
        ", code=" + code +
        ", description=" + description +
        ", url=" + url +
        "}";
    }
}
