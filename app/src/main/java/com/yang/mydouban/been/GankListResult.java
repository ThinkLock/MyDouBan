package com.yang.mydouban.been;

import java.util.List;

/**
 * Created by fengzhaoyang_i on 2017/6/7.
 */

public class GankListResult {

    /**
     * error : false
     * results : [{"_id":"5936223c421aa92c73b647c7","createdAt":"2017-06-06T11:32:12.609Z","desc":"6-6","publishedAt":"2017-06-06T11:36:13.568Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg","used":true,"who":"dmj"},{"_id":"5934d287421aa92c73b647ba","createdAt":"2017-06-05T11:39:51.13Z","desc":"6-5","publishedAt":"2017-06-05T11:44:53.909Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fga6auw8ycj20u00u00uw.jpg","used":true,"who":"daimajia"},{"_id":"5931fd87421aa92c769a8c03","createdAt":"2017-06-03T08:06:31.648Z","desc":"6-3","publishedAt":"2017-06-03T11:15:41.272Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/d23c7564ly1fg7ow5jtl9j20pb0pb4gw.jpg","used":true,"who":"dmj"},{"_id":"5930e560421aa92c7be61b93","createdAt":"2017-06-02T12:11:12.694Z","desc":"6-2","publishedAt":"2017-06-02T12:26:37.346Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/d23c7564ly1fg6qckyqxkj20u00zmaf1.jpg","used":true,"who":"daimajia"},{"_id":"592f5798421aa92c769a8bf1","createdAt":"2017-06-01T07:54:00.225Z","desc":"6-1","publishedAt":"2017-06-01T14:35:22.88Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fg5dany6uzj20u011iq60.jpg","used":true,"who":"daimajia"},{"_id":"5927bc01421aa92c79463324","createdAt":"2017-05-26T13:24:17.785Z","desc":"5-26","publishedAt":"2017-05-26T13:43:32.128Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffyp4g2vwxj20u00tu77b.jpg","used":true,"who":"daimajia"},{"_id":"59266b58421aa92c7be61b4f","createdAt":"2017-05-25T13:27:52.816Z","desc":"5-25","publishedAt":"2017-05-25T13:32:48.92Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffxjlvinj5j20u011igri.jpg","used":true,"who":"daimajia"},{"_id":"59250360421aa92c7be61b48","createdAt":"2017-05-24T11:52:00.497Z","desc":"5-24","publishedAt":"2017-05-24T12:09:25.526Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/mw690/610dc034ly1ffwb7npldpj20u00u076z.jpg","used":true,"who":"dmj"},{"_id":"5923a0c1421aa92c794632fe","createdAt":"2017-05-23T10:38:57.783Z","desc":"5-23","publishedAt":"2017-05-23T11:14:05.141Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1ffv3gxs37oj20u011i0vk.jpg","used":true,"who":"daimajia "},{"_id":"59223657421aa92c794632f4","createdAt":"2017-05-22T08:52:39.188Z","desc":"5-22","publishedAt":"2017-05-22T11:30:21.8Z","source":"chrome","type":"福利","url":"https://ws1.sinaimg.cn/large/610dc034ly1fftusiwb8hj20u00zan1j.jpg","used":true,"who":"代码家"}]
     */

    private boolean error;
    private List<Results> results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isSuccessed(){
        return !error;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Results {
        /**
         * _id : 5936223c421aa92c73b647c7
         * createdAt : 2017-06-06T11:32:12.609Z
         * desc : 6-6
         * publishedAt : 2017-06-06T11:36:13.568Z
         * source : chrome
         * type : 福利
         * url : https://ws1.sinaimg.cn/large/610dc034ly1fgbbp94y9zj20u011idkf.jpg
         * used : true
         * who : dmj
         */

        private String _id;
        private String createdAt;
        private String desc;
        private String publishedAt;
        private String source;
        private String type;
        private String url;
        private boolean used;
        private String who;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public boolean isUsed() {
            return used;
        }

        public void setUsed(boolean used) {
            this.used = used;
        }

        public String getWho() {
            return who;
        }

        public void setWho(String who) {
            this.who = who;
        }
    }
}