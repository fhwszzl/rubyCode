package com.zzl.controller;



import com.zzl.model.bo.LinkUrlBo;
import com.zzl.model.pojo.ResponseVo;
import com.zzl.service.ILinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value = "/api/link", name = "长短链接相关API")
public class TestController {

    @Autowired
    private ILinkService linkService;

    /**
     * 前端输入原始地址及特征值Key，进入业务层将原始地址转换成短链code，
     * 如果输入了key则和key一起MD5加密，如果没有输入，用默认值进行加密，插入数据库成功后再将短链code返回，前端将特定code组装值url后
     * @param json
     * @param key
     * @return
     */

    @RequestMapping(value = "/insertLinkUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<LinkUrlBo> insertLinkUrl(@RequestBody LinkUrlBo json, @RequestParam String key) {
        ResponseVo<LinkUrlBo> responseVo = linkService.insertLinkUrl(json,key);
        return responseVo;
    }

    /**
     * 前端通过截取短链接后面特征code查询数据库中原链接地址，然后将访问次数加一，更新回原model中
     * @param code
     * @return
     */

    @RequestMapping(value = "/queryLinkUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<LinkUrlBo> queryLinkUrl(@RequestParam String code) {
        ResponseVo<LinkUrlBo> responseVo = linkService.queryLinkUrl(code);
        return responseVo;
    }


    /**
     *
     * @param json
     * @return
     */

    @RequestMapping(value = "/updateLinkUrl", method = RequestMethod.POST)
    @ResponseBody
    public ResponseVo<Boolean> updateLinkUrl(@RequestBody LinkUrlBo json) {
        ResponseVo<Boolean> responseVo = linkService.updateLinkUrl(json);
        return responseVo;
    }
}
