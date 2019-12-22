package zjh.ntlmv2.cache;

import zjh.ntlmv2.NTLMException;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;

/**
 * ehcache缓存实现
 *
 * @author zhoujianghui
 */
public class EhCacheProvider implements CacheProvider {
    private static final String CACHE_NAME = "NTLM_CHALLENGE_CACHE";
    private static Logger log = LoggerFactory.getLogger(EhCacheProvider.class);
    private CacheManager cacheManager = null;
    private Cache<String, byte[]> cache;

    @Override
    public void init(FilterConfig filterConfig) throws NTLMException {
        try {

            CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
                    .withCache(CACHE_NAME,
                            CacheConfigurationBuilder.
                                    newCacheConfigurationBuilder(String.class, byte[].class, ResourcePoolsBuilder.heap(10000)))
                    .build();
            cacheManager.init();
            cache = cacheManager.getCache(CACHE_NAME, String.class, byte[].class);
            if (log.isTraceEnabled()) {
                log.trace("NTLM challenge cache initialized");
            }
        } catch (Exception e) {
            throw new NTLMException("Failed to initialize cache", e);
        }
    }

    @Override
    public synchronized void put(String remoteAddress, byte[] serverChallenge) {
        if (log.isTraceEnabled()) {
            log.trace("Cache server challenge for: " + remoteAddress);
        }
        cache.put(remoteAddress, serverChallenge);
    }

    @Override
    public synchronized void remove(String remoteAddress) {
        cache.remove(remoteAddress);
    }

    @Override
    public synchronized byte[] get(String remoteAddress) {
        try {
            if (log.isTraceEnabled()) {
                log.trace("Get cached server challenge for: " + remoteAddress);
            }

            return cache.get(remoteAddress);
        } catch (Exception e) {
            if (log.isWarnEnabled()) {
                log.warn("No challenge found in cache for client: " + remoteAddress);
            }
            return null;
        }
    }

    @Override
    public void destroy() {
        try {
            if (log.isDebugEnabled()) {
                log.debug("Removing cache");
            }
            cacheManager.removeCache(CACHE_NAME);
            cacheManager.close();
        } catch (Exception e) {
            // ignore
            log.error(e.getLocalizedMessage());
        }
    }

}