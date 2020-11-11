package com.gutinicolas.twitter.exceptions;

public class TwitterException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        private int code;

        public TwitterException(int code, String message) {
            super(message);
            this.code = code;
        }

        public TwitterException(int code, String message, Throwable cause) {
            super(message, cause);
            this.code = code;
        }

    public TwitterException() {

    }

    public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

}
