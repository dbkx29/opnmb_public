package icu.dbkx.opnmb.controller.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import icu.dbkx.opnmb.common.pojo.Result;
import icu.dbkx.opnmb.common.pojo.dto.PostPieceDto;
import icu.dbkx.opnmb.common.utils.AssertUtil;
import icu.dbkx.opnmb.common.utils.RequestContext;
import icu.dbkx.opnmb.generator.entity.Biscuit;
import icu.dbkx.opnmb.generator.entity.Category;
import icu.dbkx.opnmb.generator.entity.Piece;
import icu.dbkx.opnmb.generator.entity.User;
import icu.dbkx.opnmb.generator.service.CategoryService;
import icu.dbkx.opnmb.generator.service.PieceService;
import icu.dbkx.opnmb.generator.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/piece")
@Slf4j
public class PieceController {

    @Resource
    PieceService pieceService;
    @Resource
    UserService userService;
    @Resource
    CategoryService categoryService;

    /**
     * 发串
     */
    @PostMapping("/post")
    public Result postPiece(@RequestBody PostPieceDto postPieceDto) {
        String biscuit_id = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUser_id, RequestContext.getContext())).getBiscuit_id();
        if(biscuit_id==null){
            Result.error("/api/piece/post","您的账号还未绑定饼干，请先领取饼干或切换饼干");
        }else {
            Piece piece = Piece.builder()
                    .biscuit_id(biscuit_id)
                    .category_name(postPieceDto.getCategory_name())
                    .content(postPieceDto.getContent())
                    .build();
            pieceService.save(piece);
        }
        return Result.success("/api/piece/post","发串成功");
    }

    /**
     * 查询单个piece
     */
    @GetMapping("/get")
    public Result<Piece> getPiece(@RequestParam Integer piece_id) {
        Piece piece = pieceService.getOne(new LambdaQueryWrapper<Piece>().eq(Piece::getPiece_id, piece_id));
        if(piece==null){
            return Result.error("/api/piece/get","串不存在");
        }
        Result<Piece> result = Result.success("/api/piece/get");
        result.setData(piece);
        return result;
    }
    /**
     * 根据首piece号分块查询整个串
     */
    @GetMapping("/get_thread/{piece_id}")
    public Result<List<Piece>> getThread(@PathVariable Integer piece_id,@RequestParam Integer page,@RequestParam Integer batch_size) {
        Piece head = pieceService.getOne(new LambdaQueryWrapper<Piece>().eq(Piece::getPiece_id, piece_id));
        if(head==null){
            return Result.error("/api/piece/get_thread","串不存在");
        }
        List<Piece> pieces = new ArrayList<>();
        if(page==1)pieces.add(head);
        pieces.addAll(pieceService.list(
                new Page<>(page,batch_size==null?10:batch_size),
                new LambdaQueryWrapper<Piece>().eq(Piece::getQuote_id, piece_id).orderByAsc(Piece::getPiece_id)));

        Result<List<Piece>> result = Result.success("/api/piece/get_thread");
        result.setData(pieces);
        return result;
    }
    /**
     * 根据首piece号分块查询整个串的最新5条回复
     */
    @GetMapping("/get_thread_latest")
    public Result<List<Piece>> getThreadLatest(@RequestParam Integer piece_id) {
        Piece head = pieceService.getOne(new LambdaQueryWrapper<Piece>().eq(Piece::getPiece_id, piece_id));
        if(head==null){
            return Result.error("/api/piece/get_thread_latest","串不存在");
        }
        List<Piece> pieces = pieceService.list(
                new Page<>(1,5),
                new LambdaQueryWrapper<Piece>().eq(Piece::getQuote_id, piece_id).orderByDesc(Piece::getPiece_id));

        Result<List<Piece>> result = Result.success("/api/piece/get_thread_latest");
        result.setData(pieces);
        return result;
    }
    /**
     * 根据category_name分页查询串
     */
    @GetMapping("/get_category/{category_name}")
    public Result<List<Piece>> getCategory(@PathVariable String category_name, @RequestParam Integer page, @RequestParam Integer batch_size) {
        String real_category_name = categoryService.getOne(new LambdaQueryWrapper<Category>().eq(Category::getCategory_name, category_name)).getCategory_name();

        List<Piece> pieces;
        log.trace("category_name:{}",category_name);
        if(real_category_name==null){
            return Result.error("/api/piece/get_category","板块不存在");
        }else{
            pieces = pieceService.list(
                    new Page<>(page,batch_size==null?10:batch_size),
                    new LambdaQueryWrapper<Piece>().eq(Piece::getCategory_name, category_name).isNull(Piece::getQuote_id).orderByAsc(Piece::getPiece_id));
        }

        Result<List<Piece>> result = Result.success("/api/piece/get_category");
        result.setData(pieces);
        return result;
    }

    /**
     * 回复串，表现为发送一个quote_id为被回复串的串
     */
    @PostMapping("/reply")
    public Result<Piece> replyPiece(@RequestBody PostPieceDto postPieceDto) {
        Piece head = pieceService.getOne(new LambdaQueryWrapper<Piece>().eq(Piece::getPiece_id, postPieceDto.getQuote_id()));
        log.trace(postPieceDto.toString());
        log.trace("head:{}",head);
        if(head==null){
            return Result.error("/api/piece/reply","串不存在");
        }
        String biscuit_id = userService.getOne(new LambdaQueryWrapper<User>().eq(User::getUser_id, RequestContext.getContext())).getBiscuit_id();
        if(biscuit_id==null){
            Result.error("/api/piece/reply","您的账号还未绑定饼干，请先领取饼干或切换饼干");
        }else {
            Piece piece = Piece.builder()
                    .biscuit_id(biscuit_id)
                    .category_name(head.getCategory_name())
                    .content(postPieceDto.getContent())
                    .quote_id(postPieceDto.getQuote_id())
                    .build();
            pieceService.save(piece);
        }
        return Result.success("/api/piece/reply","回复成功");
    }

}
