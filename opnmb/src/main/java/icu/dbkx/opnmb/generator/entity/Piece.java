package icu.dbkx.opnmb.generator.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * 
 * @TableName nmb_piece
 */
@TableName(value ="nmb_piece")
@Data
@Builder
public class Piece implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer piece_id;

    /**
     *
     */
    private Integer quote_id;

    /**
     * 
     */
    private String biscuit_id;

    /**
     * 
     */
    private String category_name;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private LocalDateTime timestamp;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}