package zjh.ntlmv2.filter;

import zjh.ntlmv2.NTLMException;
import zjh.ntlmv2.cache.CacheProvider;
import zjh.ntlmv2.cache.EhCacheProvider;
import zjh.ntlmv2.config.ConfigProvider;
import zjh.ntlmv2.config.FilterConfigProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;

/**
 * @author zhoujianghui
 */
public class NTLMv2Filter extends AbstractNTLMv2Filter {

    private static Logger log = LoggerFactory.getLogger(NTLMv2Filter.class);

    @Override
    protected synchronized ConfigProvider createConfigProvider(FilterConfig filterConfig) {
        return new FilterConfigProvider(filterConfig);
    }

    @Override
    protected CacheProvider createCacheProvider(FilterConfig filterConfig) {
        EhCacheProvider cacheProvider = new EhCacheProvider();
        try {
            cacheProvider.init(filterConfig);
        } catch (NTLMException e) {
            log.error(e.getLocalizedMessage());
            return null;
        }
        return cacheProvider;
    }
}
