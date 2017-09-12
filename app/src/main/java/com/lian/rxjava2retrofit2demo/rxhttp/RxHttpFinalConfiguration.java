package com.lian.rxjava2retrofit2demo.rxhttp;


import okhttp3.Headers;

/**
 * Created by lmr
 * Date 2017/8/22.
 * Description
 */

public class RxHttpFinalConfiguration {

    protected Headers commonHeaders;
    private long timeout = Constants.REQ_TIMEOUT;
    private String baseUrl;

    private RxHttpFinalConfiguration(final Builder builder) {
        this.commonHeaders = builder.commonHeaders;
        this.timeout = builder.timeout;
        this.baseUrl = builder.baseUrl;
    }

    public static class Builder {
        private String baseUrl;
        private Headers commonHeaders;
        private long timeout;

        public Builder() {

        }

        /**
         * 设置url
         *
         * @param url
         * @return
         */
        public Builder setBaseUrl(String url) {
            baseUrl = url;
            return this;
        }

        /**
         * 公共header
         *
         * @param headers
         * @return
         */
        public Builder setCommenHeaders(Headers headers) {
            commonHeaders = headers;
            return this;
        }

        /**
         * 设置timeout
         *
         * @param timeout
         * @return
         */
        public Builder setTimeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public RxHttpFinalConfiguration build() {
            return new RxHttpFinalConfiguration(this);
        }
    }
    public String getBaseUrl(){
        return baseUrl;
    }

    public Headers getCommonHeaders() {
        return commonHeaders;
    }

    public long getTimeout() {
        return timeout;
    }

}
