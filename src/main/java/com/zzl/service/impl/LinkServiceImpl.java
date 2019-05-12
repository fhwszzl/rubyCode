package com.zzl.service.impl;

import com.zzl.business.ILinkBusiness;
import com.zzl.model.bo.LinkUrlBo;
import com.zzl.model.pojo.ResponseVo;
import com.zzl.service.ILinkService;

import javax.annotation.Resource;


public class LinkServiceImpl implements ILinkService {

    @Resource(name = "linkBusiness")
    private ILinkBusiness linkBusiness;

    @Override
    public ResponseVo<LinkUrlBo> insertLinkUrl(LinkUrlBo linkUrlBo, String key) {
        ResponseVo<LinkUrlBo> responseVo = new ResponseVo<>();
        LinkUrlBo linkUrlBo1 = linkBusiness.insertLinkUrl(linkUrlBo,key);
        if(linkUrlBo1!=null){
            responseVo.setMsg("插入成功！");
            responseVo.setSuccess(true);
            responseVo.setResult(linkUrlBo1);
            return responseVo;
        }else {
            responseVo.setMsg("插入失败！");
            responseVo.setSuccess(false);
            return responseVo;
        }
    }

    @Override
    public ResponseVo<LinkUrlBo> queryLinkUrl(String shortCod) {
        ResponseVo<LinkUrlBo> responseVo = new ResponseVo<>();
        LinkUrlBo linkUrlBo = linkBusiness.queryLinkUrl(shortCod);
        if(linkUrlBo!=null){
            responseVo.setMsg("查询成功！");
            responseVo.setSuccess(true);
            responseVo.setResult(linkUrlBo);
            return responseVo;
        }else {
            responseVo.setMsg("查询失败！");
            responseVo.setSuccess(false);
            return responseVo;
        }
    }

    @Override
    public ResponseVo<Boolean> updateLinkUrl(LinkUrlBo linkUrlBo) {
        ResponseVo<Boolean> responseVo = new ResponseVo<>();
        Boolean isTrue = linkBusiness.updateLinkUrl(linkUrlBo);
        if(isTrue == true){
            responseVo.setMsg("更新成功！");
            responseVo.setSuccess(true);
            responseVo.setResult(isTrue);
            return responseVo;
        }else {
            responseVo.setMsg("更新失败！");
            responseVo.setSuccess(false);
            return responseVo;
        }
    }
}
