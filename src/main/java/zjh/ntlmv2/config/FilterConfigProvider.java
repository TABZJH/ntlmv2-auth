package zjh.ntlmv2.config;

import zjh.ntlmv2.NTLMException;

import javax.servlet.FilterConfig;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Servlet Filter配置实现
 *
 * @author zhoujianghui
 */
public class FilterConfigProvider implements ConfigProvider {
    private static final ConcurrentHashMap<String, String> CONFIG = new ConcurrentHashMap<>();

    public FilterConfigProvider(FilterConfig filterConfig) {
        if (filterConfig != null) {
            Enumeration enumeration = filterConfig.getInitParameterNames();
            while (enumeration.hasMoreElements()) {
                String key = (String) enumeration.nextElement();
                if (key.startsWith("ldap.")) {
                    CONFIG.put(key, filterConfig.getInitParameter(key));
                }
            }
        }
    }

    @Override
    public boolean isEnabled() {
        String value = CONFIG.get(FILTER_ENABLE);
        if (value == null) {
            return false;
        }
        return Boolean.parseBoolean(value);
    }

    @Override
    public String getDomain() {
        return CONFIG.get(FILTER_ENABLE);
    }

    @Override
    public String getDomainControllerIp() throws NTLMException {
        return getParameter(DC_IP);
    }

    @Override
    public String getDomainControllerHostname() throws NTLMException {
        return getParameter(DC_HOSTNAME);
    }

    @Override
    public String getServiceAccount() throws NTLMException {
        return getParameter(ACCOUNT);
    }

    @Override
    public String getServicePassword() throws NTLMException {
        return getParameter(PASSWORD);
    }

    private String getParameter(String property) throws NTLMException {
        String value = CONFIG.get(property);
        if (value == null || value.trim().isEmpty()) {
            throw new NTLMException("No value set for " + property);
        }
        return value;
    }

}