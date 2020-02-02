package com.hzqing.admin.controller.doc;

import com.hzqing.admin.common.ResponseMessage;
import com.hzqing.admin.common.utils.DateUtils;
import com.hzqing.admin.common.utils.FileUtil;
import com.hzqing.admin.controller.base.BaseController;
import com.hzqing.admin.dto.doc.DocDto;
import com.hzqing.admin.dto.doc.MemberDto;
import com.hzqing.admin.model.entity.doc.Doc;
import com.hzqing.admin.service.doc.IDocService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

/**
 * @author hzqing
 * @date 2019-05-17 09:50
 */
@RestController
@RequestMapping("/api/doc")
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

    /**
     * 查询该用户对当前文档的操作权限
     * @return
     */
    @GetMapping("/docPrivilege")
    public ResponseMessage docPrivilege(MemberDto memberDto){
        Integer privilege = docService.selectPrivilegeById(memberDto);
        if (privilege == null)
            privilege = 1;
        return responseMessage(privilege);
    }


    /**
     * 根据id获取对象
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    public ResponseMessage get(@PathVariable int id){
        DocDto docDto = docService.get(id);
        return responseMessage(docDto);
    }

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

    @GetMapping("/page")
    public ResponseMessage page(int pageNum, int pageSize, Doc doc){
        startPage(pageNum,pageSize);
        List<Doc> docs = docService.selectList(doc);
        return responseMessage(docs);
    }

    @PostMapping("/addOrUpdate")
    public ResponseMessage addOrUpdate(@RequestBody Doc doc){
        int res = -1;
       // doc = (Doc) initAddOrUpdate(doc);
        if (doc.getId() == null){ //新增
            res = docService.insert(doc);
        }else {
           // doc.setCreateBy(null);
            res = docService.update(doc);
        }
        return responseMessage(res);
    }

    @DeleteMapping("deleted/{id}")
    public ResponseMessage deleted(@PathVariable String id) {
        int res = docService.deletedById(id);
        return responseMessage(res);
    }

    /**
     * 上传图片，返回图片路径
     * @param file
     * @return
     */
    @PostMapping("/uploadImages")
    public ResponseMessage uploadImages(MultipartFile file, String docId , HttpServletRequest request){
        String http = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();

        String dataPaths =  DateUtils.getYearAndMonth() + "/";
        String resPath = "doc/" + docId + "/images/" + dataPaths;
        String fileName =  UUID.randomUUID().toString() +file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            FileUtil.uploadFile(file.getBytes(),filePath + resPath,fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseMessage().success(http + "/fs/"+resPath + fileName);
    }
}
