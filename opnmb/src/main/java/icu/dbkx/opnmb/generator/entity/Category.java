package icu.dbkx.opnmb.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 
 * @TableName nmb_category
 */
@TableName(value ="nmb_category")
@Data
@Builder
public class Category implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer category_id;

    /**
     * 
     */
    private String category_name;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}