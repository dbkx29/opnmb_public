package icu.dbkx.opnmb.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.*;

/**
 * 
 * @TableName nmb_biscuit
 */
@TableName(value ="nmb_biscuit")
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Biscuit implements Serializable {
    /**
     * 
     */
    private Integer user_id;

    /**
     * 
     */
    @TableId
    private String biscuit_id;

    /**
     *
     */
    private Integer activate;

    /**
     * 
     */
    private LocalDateTime timestamp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}