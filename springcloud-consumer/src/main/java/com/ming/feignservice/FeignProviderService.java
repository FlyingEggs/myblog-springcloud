package com.ming.feignservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ming.utils.APIResponse;

import io.swagger.annotations.ApiParam;

@FeignClient(name="myblog-provider")
public interface FeignProviderService {
	@GetMapping(value = {"/about", "/about/index"})
    public String getAbout(HttpServletRequest request);
	
	@GetMapping(value = {"/blog/","/blog/index"})
    public String blogIndex(
            HttpServletRequest request,
            @ApiParam(name = "limit", value = "页数", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "11")
                    int limit
    );
	@GetMapping(value = "/blog/page/{p}")
    public String blogIndex(
            HttpServletRequest request,
            @PathVariable("p")
                    int p,
            @RequestParam(value = "limit", required = false, defaultValue = "11")
                    int limit
    );
	 @GetMapping(value = "/blog/article/{cid}")
    public String post(
            @ApiParam(name = "cid", value = "文章主键", required = true)
            @PathVariable("cid")
                    Integer cid,
            HttpServletRequest request
    );
	 @GetMapping(value = "/blog/archives/{date}")
    public String archives(
            @ApiParam(name = "date", value = "归档日期", required = true)
            @PathVariable("date")
                    String date,
            HttpServletRequest request
    );
	 @GetMapping(value = "/blog/archives/year/{year}")
    public String archivesAtYear(
            @ApiParam(name = "year", value = "归档日期", required = true)
            @PathVariable("year")
                    String year,
            HttpServletRequest request
    );
	 @GetMapping(value = {"/blog/archives", "/blog/archives/index"})
	 public String archives(HttpServletRequest request);
	 @GetMapping(value = "/blog/categories/{category}")
    public String categories(
            @ApiParam(name = "category", value = "分类名", required = true)
            @PathVariable("category")
                    String category,
            HttpServletRequest request
    );
	 @GetMapping(value = "/blog/categories/{category}/page/{page}")
    public String categories(
            @ApiParam(name = "category", value = "分类名", required = true)
            @PathVariable("category")
                    String category,
            @ApiParam(name = "page", value = "页数", required = true)
            @PathVariable("page")
                    int page,
            @ApiParam(name = "limit", value = "条数", required = true)
            @RequestParam(name = "limit", required = false, defaultValue = "10")
                    int limit,
            HttpServletRequest request
    );
	 @GetMapping(value = "/blog/tag/{tag}")
    public String tags(
            @ApiParam(name = "tag", value = "标签名", required = true)
            @PathVariable("tag")
                    String tag,
            HttpServletRequest request
    );
	 @GetMapping(value = "/blog/tag/{tag}/page/{page}")
    public String tags(
            @ApiParam(name = "tag", value = "标签名", required = true)
            @PathVariable("tag")
                    String tag,
            @ApiParam(name = "page", value = "页数", required = true)
            @PathVariable("page")
                    int page,
            @ApiParam(name = "limit", value = "条数", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "10")
                    int limit,
            HttpServletRequest request
    );
	 @GetMapping("/blog/search")
    public String search(
            @ApiParam(name = "param", value = "搜索的文字", required = true)
            @RequestParam(name = "param", required = true)
                    String param,
            HttpServletRequest request
    );
	 @GetMapping(value = "/blog/search/{param}/page/{page}")
    public String search(
            @ApiParam(name = "param", value = "搜索的文字", required = true)
            @PathVariable("param")
                    String param,
            @ApiParam(name = "page", value = "页数", required = true)
            @PathVariable("page")
                    int page,
            @ApiParam(name = "limit", value = "条数", required = false)
            @RequestParam(name = "limit", required = false, defaultValue = "10")
                    int limit,
            HttpServletRequest request
    );
	 @PostMapping(value = "/blog/comment")
    @ResponseBody
    public APIResponse comment(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "cid", required = true) Integer cid,
                               @RequestParam(name = "coid", required = false) Integer coid,
                               @RequestParam(name = "author", required = false) String author,
                               @RequestParam(name = "mail", required = false) String mail,
                               @RequestParam(name = "url", required = false) String url,
                               @RequestParam(name = "text", required = true) String text,
                               @RequestParam(name = "_csrf_token", required = true) String _csrf_token);
	 @RequestMapping("/blog/logout")
	    public void logout(HttpSession session, HttpServletResponse response);
	 @GetMapping(value = {"", "/index"})
	    public String index(HttpServletRequest request, @RequestParam(value = "limit", defaultValue = "12") int limit);
	 @GetMapping(value = "/photo/page/{p}")
	    public String index(
	            @ApiParam(name = "page", value = "页数", required = false)
	            @PathVariable(name = "p")
	                    int page,
	            @ApiParam(name = "limit", value = "条数", required = false)
	            @RequestParam(name = "limit", required = false, defaultValue = "9999")
	                    int limit,
	            HttpServletRequest request
	    );
	 @GetMapping(value = "/photo/article/{cid}")
	    public String article(
	            @PathVariable("cid")
	                    Integer cid,
	            HttpServletRequest request
	    );
	 
	 
	 
	 //////////////////////////////////////////ArticleController////////////////////////////////////////////////////
	 
	 @GetMapping(value = "/admin/article")
	    public String index(
	            HttpServletRequest request,
	            @ApiParam(name = "page", value = "页数", required = false)
	            @RequestParam(name = "page", required = false, defaultValue = "1")
	            int page,
	            @ApiParam(name = "limit", value = "每页数量", required = false)
	            @RequestParam(name = "limit", required = false, defaultValue = "15")
	            int limit
	    );
	 @GetMapping(value = "/admin/article/publish")
	    public String newArticle(HttpServletRequest request);
	 @PostMapping(value = "/admin/article/publish")
	    @ResponseBody
	    public APIResponse publishArticle(
	            HttpServletRequest request,
	            @ApiParam(name = "title", value = "标题", required = true)
	            @RequestParam(name = "title", required = true)
	            String title,
	            @ApiParam(name = "titlePic", value = "标题图片", required = false)
	            @RequestParam(name = "titlePic", required = false)
	            String titlePic,
	            @ApiParam(name = "slug", value = "内容缩略名", required = false)
	            @RequestParam(name = "slug", required = false)
	            String slug,
	            @ApiParam(name = "content", value = "内容", required = true)
	            @RequestParam(name = "content", required = true)
	            String content,
	            @ApiParam(name = "type", value = "文章类型", required = true)
	            @RequestParam(name = "type", required = true)
	            String type,
	            @ApiParam(name = "status", value = "文章状态", required = true)
	            @RequestParam(name = "status", required = true)
	            String status,
	            @ApiParam(name = "tags", value = "标签", required = false)
	            @RequestParam(name = "tags", required = false)
	            String tags,
	            @ApiParam(name = "categories", value = "分类", required = false)
	            @RequestParam(name = "categories", required = false, defaultValue = "默认分类")
	            String categories,
	            @ApiParam(name = "allowComment", value = "是否允许评论", required = true)
	            @RequestParam(name = "allowComment", required = true)
	            Boolean allowComment
	            );
	 @GetMapping(value = "/admin/article/{cid}")
	    public String editArticle(
	            @ApiParam(name = "cid", value = "文章编号", required = true)
	            @PathVariable
	                    Integer cid,
	            HttpServletRequest request
	    );
	 @PostMapping("/admin/article/modify")
	    @ResponseBody
	    public APIResponse modifyArticle(
	            HttpServletRequest request,
	            @ApiParam(name = "cid", value = "文章主键", required = true)
	            @RequestParam(name = "cid", required = true)
	                    Integer cid,
	            @ApiParam(name = "title", value = "标题", required = true)
	            @RequestParam(name = "title", required = true)
	                    String title,
	            @ApiParam(name = "titlePic", value = "标题图片", required = false)
	            @RequestParam(name = "titlePic", required = false)
	                    String titlePic,
	            @ApiParam(name = "slug", value = "内容缩略名", required = false)
	            @RequestParam(name = "slug", required = false)
	                    String slug,
	            @ApiParam(name = "content", value = "内容", required = true)
	            @RequestParam(name = "content", required = true)
	                    String content,
	            @ApiParam(name = "type", value = "文章类型", required = true)
	            @RequestParam(name = "type", required = true)
	                    String type,
	            @ApiParam(name = "status", value = "文章状态", required = true)
	            @RequestParam(name = "status", required = true)
	                    String status,
	            @ApiParam(name = "tags", value = "标签", required = false)
	            @RequestParam(name = "tags", required = false)
	                    String tags,
	            @ApiParam(name = "categories", value = "分类", required = false)
	            @RequestParam(name = "categories", required = false, defaultValue = "默认分类")
	                    String categories,
	            @ApiParam(name = "allowComment", value = "是否允许评论", required = true)
	            @RequestParam(name = "allowComment", required = true)
	                    Boolean allowComment
	    );
	 @PostMapping(value = "/admin/article/delete")
	    @ResponseBody
	    public APIResponse deleteArticle(
	            @ApiParam(name = "cid", value = "文章主键", required = true)
	            @RequestParam(name = "cid", required = true)
	            Integer cid,
	            HttpServletRequest request
	    );
	 
	 
	 ///////////////////////////AuthController/////////////////////////////////////////
	 
	 
	 @PostMapping(value = "/admin/login")
	    @ResponseBody
	    public APIResponse toLogin(
	            HttpServletRequest request,
	            HttpServletResponse response,
	            @ApiParam(name = "username", value = "用户名", required = true)
	            @RequestParam(name = "username", required = true)
	            String username,
	            @ApiParam(name = "password", value = "密码", required = true)
	            @RequestParam(name = "password", required = true)
	            String password,
	            @ApiParam(name = "remeber_me", value = "记住我", required = false)
	            @RequestParam(name = "remeber_me", required = false)
	            String remeber_me
	    );
	 @RequestMapping("/admin/logout")
	    public void logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) ;
	 
	 //////////////////////////////CategoryController///// 修改index/////////////////////////////////////////////////////
	 
	 @GetMapping(value = "/admin/category")
	    public String CategoryIndex(HttpServletRequest request);
	 @PostMapping(value = "save")
	    @ResponseBody
	    public APIResponse addCategory(
	            @ApiParam(name = "cname", value = "分类名", required = true)
	            @RequestParam(name = "cname", required = true)
	            String cname,
	            @ApiParam(name = "mid", value = "meta编号", required = false)
	            @RequestParam(name = "mid", required = false)
	            Integer mid
	    );
	 @PostMapping(value = "/admin/category/delete")
	    @ResponseBody
	    public APIResponse delete(
	            @ApiParam(name = "mid", value = "主键", required = true)
	            @RequestParam(name = "mid", required = true)
	            Integer mid
	    );
	 
	 //////////////////////////////////////CommentController   修改index////////////////////////////////////////////////////
	 
	 @GetMapping(value = "/admin/comments")
	    public String commentIndex(
	            @ApiParam(name = "page", value = "页数", required = false)
	            @RequestParam(name = "page", required = false, defaultValue = "1")
	            int page,
	            @ApiParam(name = "limit", value = "每页条数", required = false)
	            @RequestParam(name = "limit", required = false, defaultValue = "15")
	            int limit,
	            HttpServletRequest request
	    );
	 @PostMapping(value = "/admin/comments/delete")
	    @ResponseBody
	    public APIResponse deleteComment(
	            @ApiParam(name = "coid", value = "评论编号", required = true)
	            @RequestParam(name = "coid", required = true)
	            Integer coid
	    );
	 @PostMapping(value = "/admin/comments/status")
	    @ResponseBody
	    public APIResponse changeStatus(
	            @ApiParam(name = "coid", value = "评论主键", required = true)
	            @RequestParam(name = "coid", required = true)
	            Integer coid,
	            @ApiParam(name = "status", value = "状态", required = true)
	            @RequestParam(name = "status", required = true)
	            String status
	    );
	 
	 /////////////////////////////IndexController/////////////////////////////////////////////
	 
	 @GetMapping(value = {"/admin","admin/index"})
	    public String index(HttpServletRequest request);
	 @PostMapping(value = "/admin/profile")
	    @ResponseBody
	    public APIResponse saveProfile(@RequestParam String screenName, @RequestParam String email, HttpServletRequest request, HttpSession session);
	 @PostMapping(value = "/admin/password")
	    @ResponseBody
	    public APIResponse upPwd(@RequestParam String oldPassword, @RequestParam String password, HttpServletRequest request,HttpSession session);
	 
	 ////////////////////////////////////////LinksController 修改index/////////////////////////////////////
	 @GetMapping(value = "/admin/links")
	    public String linksIndex(HttpServletRequest request);
	 @PostMapping(value = "/admin/links/save")
	    @ResponseBody
	    public APIResponse addLink(
	            @ApiParam(name = "title", value = "标签", required = true)
	            @RequestParam(name = "title", required = true)
	            String title,
	            @ApiParam(name = "url", value = "链接", required = true)
	            @RequestParam(name = "url", required = true)
	            String url,
	            @ApiParam(name = "logo", value = "logo", required = false)
	            @RequestParam(name = "logo", required = false)
	            String logo,
	            @ApiParam(name = "mid", value = "meta编号", required = false)
	            @RequestParam(name = "mid", required = false)
	            Integer mid,
	            @ApiParam(name = "sort", value = "sort", required = false)
	            @RequestParam(name = "sort", required = false, defaultValue = "0")
	            int sort
	    );
	 @PostMapping(value = "/admin/links/delete")
	    @ResponseBody
	    public APIResponse delete(
	            @ApiParam(name = "mid", value = "meta主键", required = true)
	            @RequestParam(name = "mid", required = true)
	                    int mid
	    );
	 
	 ////////////////////////////////SettingController////////////////////////////////////
	 
	 
	 @GetMapping(value = "/admin/setting")
	    public String setting(HttpServletRequest request);
	 @PostMapping(value = "/admin/setting")
	    @ResponseBody
	    public APIResponse saveSetting(HttpServletRequest request);
	 
	 /////////////////////////////////UserController////////////////////////////////////////////////////
	 
	 
}
