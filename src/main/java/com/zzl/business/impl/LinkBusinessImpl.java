package com.zzl.business.impl;

import com.zzl.business.ILinkBusiness;
import com.zzl.dao.ILinkDao;
import com.zzl.model.bo.LinkUrlBo;
import com.zzl.util.MD5;

import javax.annotation.Resource;


public class LinkBusinessImpl implements ILinkBusiness {

    /**
     * B2B密码key.
     */
    private static final String B2BKEY = "b2BPWd!#";

    /**
     * B2B密码IVkey.
     */
    private static final String B2BIVKEY = "B2BpWdiv";

    /**
     * billInfoDao.
     */
    @Resource(name = "linkDao")
    private ILinkDao linkDao;

    @Override
    public LinkUrlBo insertLinkUrl(LinkUrlBo linkUrlBo, String key) {
        if(linkUrlBo==null || linkUrlBo.getBaseUrl().equals("")){
            return null;
        }
        if(key==null || key.equals("")){
            key ="xxxxxxxxx";
        }
        String code = shortUrl(linkUrlBo.getBaseUrl(),key);
        linkUrlBo.setCode(code);
        linkUrlBo.setCount(0);
        Integer result = linkDao.insertLinkUrl(linkUrlBo);
        if(result>0){
            return linkUrlBo;
        }else {
            return null;
        }
    }

    @Override
    public LinkUrlBo queryLinkUrl(String shortCode) {
        if(shortCode ==null||shortCode.equals("") ){
            return null;
        }
        LinkUrlBo linkUrlBo = linkDao.queryLinkUrl(shortCode);
        if(linkUrlBo!=null){
            return linkUrlBo;
        }else {
            return null;
        }
    }

    @Override
    public Boolean updateLinkUrl(LinkUrlBo linkUrlBo) {
        if(linkUrlBo==null && linkUrlBo.getCode().equals("")){
            return false;
        }
        Integer result = linkDao.updateLinkUrl(linkUrlBo);
        if(result>0){
            return true;
        }else {
            return false;
        }
    }

    public String shortUrl(String url, String key) {

        // 要使用生成 URL 的字符
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"
        };
        // 对传入网址进行 MD5 加密
        String sMD5EncryptResult = (MD5.encrypt((key + url), B2BKEY, B2BIVKEY));
        String hex = sMD5EncryptResult;
        String resUrl;

            // 把加密字符按照 8 位一组 16 进制与 0x3FFFFFFF 进行位与运算
            String sTempSubString = hex.substring( 8, 8 + 8);
            // 这里需要使用 long 型来转换，因为 Inteper .parseInt() 只能处理 31 位 , 首位为符号位 , 如果不用 long ，则会越界
            long lHexLong = 0x3FFFFFFF & Long.parseLong(sTempSubString, 16);
            String outChars = "";
            //循环获得每组6位的字符串
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引(具体需要看chars数组的长度   以防下标溢出，注意起点为0)
                long index = 0x0000003D & lHexLong;
                // 把取得的字符相加
                outChars += chars[(int) index];
                // 每次循环按位右移 5 位
                lHexLong = lHexLong >> 5;
            }
            // 把字符串存入对应索引的输出数组
            resUrl = outChars;

        return resUrl;
    }
}
