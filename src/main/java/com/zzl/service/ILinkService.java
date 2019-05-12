package com.zzl.service;

import com.zzl.model.bo.LinkUrlBo;
import com.zzl.model.pojo.ResponseVo;

public interface ILinkService {

    /**
     * 生成短链接code并返回model
     * @param linkUrlBo
     * @return
     */
    ResponseVo<LinkUrlBo> insertLinkUrl(LinkUrlBo linkUrlBo, String key);

    /**
     * 通过短链接code查询model
     * @param shortCode
     * @return
     */
    ResponseVo<LinkUrlBo>  queryLinkUrl(String shortCode);

    /**
     * 通过短链接code更新次数
     * @param linkUrlBo
     * @return
     */
    ResponseVo<Boolean>  updateLinkUrl(LinkUrlBo linkUrlBo);
}
