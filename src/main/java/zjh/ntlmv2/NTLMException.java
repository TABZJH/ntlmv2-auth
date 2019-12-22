package zjh.ntlmv2;

/**
 * @author zhoujianghui
 */
public class NTLMException extends Exception {
    public NTLMException(String s, Exception e) {
        super(String.format(s, e));
    }

    public NTLMException(String s) {
        super(s);
    }
}
