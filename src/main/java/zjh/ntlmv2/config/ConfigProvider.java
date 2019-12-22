package zjh.ntlmv2.config;


import zjh.ntlmv2.NTLMException;

/**
 * @author zhoujianghui
 */
public interface ConfigProvider {

    String FILTER_ENABLE = "ldap.enabled";
    String DOMAIN = "ldap.domain";
    String DC_IP = "ldap.dc.ip";
    String DC_HOSTNAME = "ldap.dc.hostname";
    String ACCOUNT = "ldap.account";
    String PASSWORD = "ldap.password";

    /**
     * 是否开启Filter
     *
     * @return
     */
    boolean isEnabled();

    /**
     * 获取AD域
     *
     * @return
     * @throws NTLMException
     */
    String getDomain() throws NTLMException;

    /**
     * 获取AD Controller IP
     *
     * @return
     * @throws NTLMException
     */
    String getDomainControllerIp() throws NTLMException;

    /**
     * 获取域控的non-FQDN
     * 例如 ad.appdev.centerm.com 中的 ad
     *
     * @return
     * @throws NTLMException
     */
    String getDomainControllerHostname() throws NTLMException;

    /**
     * 获取域控的服务用户名称
     * COMPUTER@domain.com
     *
     * @return
     * @throws NTLMException
     */
    String getServiceAccount() throws NTLMException;

    /**
     * 获取密码
     *
     * @return
     * @throws NTLMException
     */
    String getServicePassword() throws NTLMException;

}