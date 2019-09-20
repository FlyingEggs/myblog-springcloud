package com.ming.controller.admin;

import com.ming.constant.Types;
import com.ming.constant.WebConst;
import com.ming.controller.BaseController;
import com.ming.dto.MetaDto;
import com.ming.exception.BusinessException;
import com.ming.feignservice.FeignProviderService;
import com.ming.service.meta.MetaService;
import com.ming.utils.APIResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Donghua.Chen on 2018/5/1.
 */
@Api("分类和标签")
@Controller
//@RequestMapping("admin/category")
public class CategoryController extends BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);

//    @Autowired
//    private MetaService metaService;
    @Autowired
    private FeignProviderService feignProviderService;

    @ApiOperation("进入分类和标签页")
    @GetMapping(value = "/myblog-provider/admin/category")
    public String index(HttpServletRequest request){
//        List<MetaDto> categories = metaService.getMetaList(Types.CATEGORY.getType(), null, WebConst.MAX_POSTS);
//        List<MetaDto> tags = metaService.getMetaList(Types.TAG.getType(), null, WebConst.MAX_POSTS);
//        request.setAttribute("categories", categories);
//        request.setAttribute("tags", tags);
    	feignProviderService.CategoryIndex(request);
        return "admin/category";
    }

    @ApiOperation("保存分类")
    @PostMapping(value = "/myblog-provider/admin/category/save")
    @ResponseBody
    public APIResponse addCategory(
            @ApiParam(name = "cname", value = "分类名", required = true)
            @RequestParam(name = "cname", required = true)
            String cname,
            @ApiParam(name = "mid", value = "meta编号", required = false)
            @RequestParam(name = "mid", required = false)
            Integer mid
    ){
        feignProviderService.addCategory(cname, mid);
        return APIResponse.success();
    }

    @ApiOperation("删除分类")
    @PostMapping(value = "/myblog-provider/admin/category/delete")
    @ResponseBody
    public APIResponse delete(
            @ApiParam(name = "mid", value = "主键", required = true)
            @RequestParam(name = "mid", required = true)
            Integer mid
    ){
//        try {
//            metaService.deleteMetaById(mid);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e.getMessage());
//            return APIResponse.fail(e.getMessage());
//        }
    	feignProviderService.delete(mid);
        return  APIResponse.success();
    }
}
