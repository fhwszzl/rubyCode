package com.zzl.dao;

import com.zzl.model.bo.LinkUrlBo;

public interface ILinkDao {
    /**
     *
     * TODO 更新调用次数.
     *
     * @param linkUrlBo
     *            1
     * @return 返回结果
     */
    public Integer updateLinkUrl(LinkUrlBo linkUrlBo);

    /**
     * 插入model
     * @param linkUrlBo
     * @return
     */
    public Integer insertLinkUrl(LinkUrlBo linkUrlBo);

    /**
     * 通过短链接code查询model
     * @param shortCode
     * @return
     */
    public LinkUrlBo queryLinkUrl(String shortCode);
}
