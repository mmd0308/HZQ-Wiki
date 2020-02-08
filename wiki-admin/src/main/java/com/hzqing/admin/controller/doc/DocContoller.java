package com.hzqing.admin.controller.doc;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.constants.RestResultCodeConstants;
import com.hzqing.admin.common.exception.ExceptionProcessUtils;
import com.hzqing.admin.common.exception.support.UnauthorizedException;
import com.hzqing.admin.common.result.RestResult;
import com.hzqing.admin.common.result.RestResultFactory;
import com.hzqing.admin.common.utils.UserAuthUtils;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.model.dto.doc.DocDto;
import com.hzqing.admin.model.entity.doc.Doc;
import com.hzqing.admin.model.enums.doc.DocVisitLevel;
import com.hzqing.admin.service.doc.IDocService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:50
 */
@Slf4j
@RestController
@RequestMapping("/api/wiki/docs")
public class DocContoller extends BaseController {

    @Value("${hzq.fs.path}")
    protected String filePath;

    @Autowired
    private IDocService docService;

    /**
     * 获取制定用户的空间
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param doc
     * @return
     */
    @GetMapping("/page/{userId}")
    public ResponseMessage showPage(@PathVariable int userId, int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
      //  doc.setCreateBy(userId);
        List<DocDto> docDtos = docService.selectListByID(doc);
        return responseMessage(docDtos);
    }


//    @ApiOperation(" 查询该用户对当前文档的操作权限")
//    @GetMapping("/privilege")
//    public RestResult<Boolean> docPrivilege(MemberDto memberDto){
//        RestResult<Boolean> result = RestResultFactory.getInstance().success();
//        try{
//            Integer privilege = docService.selectPrivilegeById(memberDto);
//
//        }catch (Exception e){
//            log.error("DocContoller.docPrivilege occur Exception: ", e);
//            ExceptionProcessUtils.wrapperHandlerException(result,e);
//        }
//
//        return result;
//    }


    /**
     *  文档首页展示
     * @param userId
     * @param pageNum
     * @param pageSize
     * @param doc
     * @return
     */
    @GetMapping("/dashboard/{userId}")
    public ResponseMessage dashboard(@PathVariable int userId, int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
        //doc.setCreateBy(userId);
        List<DocDto> docDtos = docService.selectListByUserIdAndVL(doc);
        return responseMessage(docDtos);
    }


    @ApiOperation(value = "文档分页数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "num",value = "页码",required = true,dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页显示的数量",required = true,dataType = "int")
    })
    @GetMapping("/page/{num}/{size}")
    public RestResult<Page<DocDto>> getPage(@PathVariable int num, @PathVariable int size, DocDto docDto){
        RestResult<Page<DocDto>> result = RestResultFactory.getInstance().success();
        try{
            docDto.setUserId(UserAuthUtils.getUserId());
            Page<DocDto> datas = docService.getPage(num,size,docDto);
            result.setData(datas);
        }catch (Exception e){
            log.error("DocContoller.getPage occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "创建文档")
    @PostMapping
    public RestResult<Integer> create(@RequestBody Doc doc){

        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            int id = docService.create(doc);
            result.setData(id);
        } catch (Exception e) {
            log.error("DocContoller.create occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，更新文档")
    @PutMapping("/{id}")
    public RestResult modifyById(@PathVariable int id, @RequestBody Doc doc){
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            docService.modifyById(doc);
        } catch (Exception e) {
            log.error("DocContoller.modifyById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }

    @ApiOperation(value = "根据id，删除文档")
    @DeleteMapping("/{id}")
    public RestResult<Integer> removedById(@PathVariable int id) {
        RestResult<Integer> result = RestResultFactory.getInstance().success();
        try {
            //int res = docService.removedById(id);
            result.setData(0);
        }catch (Exception e){
            log.error("DocContoller.removedById occur Exception: ", e);
            ExceptionProcessUtils.wrapperHandlerException(result,e);
        }
        return result;
    }
}
