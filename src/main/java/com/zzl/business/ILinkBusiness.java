package com.zzl.business;

import com.zzl.model.bo.LinkUrlBo;

public interface ILinkBusiness {
    /**
     * 生成短链接code并返回model
     * @param linkUrlBo
     * @return
     */
    LinkUrlBo insertLinkUrl(LinkUrlBo linkUrlBo, String key);

    /**
     * 通过短链接code查询model
     * @param shortCode
     * @return
     */
   LinkUrlBo  queryLinkUrl(String shortCode);

    /**
     * 通过短链接code更新次数
     * @param linkUrlBo
     * @return
     */
    Boolean  updateLinkUrl(LinkUrlBo linkUrlBo);
}
