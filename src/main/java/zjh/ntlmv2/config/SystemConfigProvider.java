package zjh.ntlmv2.config;

import zjh.ntlmv2.NTLMException;

/**
 * 系统配置实现
 *
 * @author zhoujianghui
 */
public class SystemConfigProvider implements ConfigProvider {

    @Override
    public boolean isEnabled() {
        String value = System.getProperty(FILTER_ENABLE, System.getenv(FILTER_ENABLE));
        return Boolean.parseBoolean(value);
    }

    @Override
    public String getDomain() throws NTLMException {
        return getProperty(DOMAIN);
    }

    @Override
    public String getDomainControllerIp() throws NTLMException {
        return getProperty(DC_IP);
    }

    @Override
    public String getDomainControllerHostname() throws NTLMException {
        return getProperty(DC_HOSTNAME);
    }

    @Override
    public String getServiceAccount() throws NTLMException {
        return getProperty(ACCOUNT);
    }

    @Override
    public String getServicePassword() throws NTLMException {
        return getProperty(PASSWORD);
    }

    private String getProperty(String property) throws NTLMException {
        String value = System.getProperty(property, System.getenv(property));
        if (value == null || value.trim().isEmpty()) {
            throw new NTLMException("No value set for " + property);
        }
        return value;
    }

}
