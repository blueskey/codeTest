/**
 * 360hqb.com.
 * Copyright (c) 2004-2013 All Rights Reserved.
 */
package sign;

/**
 * 签名校验异常
 *
 */
public class SignException extends Exception {

    /** serialVersionUID */
    private static final long serialVersionUID = 8300987403989147680L;

    public SignException(String message) {
        super(message);
    }
}
