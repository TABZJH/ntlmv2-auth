package zjh.ntlmv2.cache;

import zjh.ntlmv2.NTLMException;

import javax.servlet.FilterConfig;

/**
 * 缓存接口
 *
 * @author zhoujianghui
 */
public interface CacheProvider {

    /**
     * 缓存初始化
     *
     * @param filterConfig
     * @throws NTLMException
     */
    void init(FilterConfig filterConfig) throws NTLMException;

    /**
     * 放入缓存
     *
     * @param remoteAddress
     * @param serverChallenge
     */
    void put(String remoteAddress, byte[] serverChallenge);

    /**
     * 删除缓存
     *
     * @param remoteAddress
     */
    void remove(String remoteAddress);

    /**
     * 获取缓存
     *
     * @param remoteAddress
     * @return
     */
    byte[] get(String remoteAddress);

    /**
     * 销毁缓存
     */
    void destroy();

}