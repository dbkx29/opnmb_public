package icu.dbkx.opnmb.common.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostPieceDto {
    Integer quote_id;
    String content;
    String category_name;
}
