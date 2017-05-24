package com.example.yuer.zahui.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/17.
 */

public class LiShiDetailResponse {

    /**
     * result : [{"_id":"17490517","title":"牛痘接种创始人琴纳诞辰","pic":"http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/14/8102821492.jpg","year":1749,"month":5,"day":17,"des":"在268年前的今天，1749年5月17日 (农历四月初二)，牛痘接种创始人琴纳诞辰。","content":"在268年前的今天，1749年5月17日 (农历四月初二)，牛痘接种创始人琴纳诞辰。\n当今人类终于消灭了可怕的传染病天花的时候，不禁怀念牛痘接种法的创始人、伟大的英国医生琴纳。\n1749年5月17日，爱德华·琴纳出生于英国格洛斯特郡伯克利牧区的一个牧师家庭。他5岁时，当牧师的父亲去世了，与当牧师的哥哥斯蒂芬·琴纳生活在一起。琴纳长得结实、健壮，生性温和，兴趣广泛，尤其喜欢大自然。在学校他是优秀生，还收集多种动植物的标本。琴纳青少年时期，天花这个可怕的瘟疫正在整个欧洲蔓延着，而且还被勘探者、探险家和殖民者传播到了美洲。在英国几乎每个人都会传染上这种病，在成年人的脸上或身上会留下难看的疤痕。成千上万的人由于病情严重而变成瞎子或疯子，每年死去的人更多。琴纳目睹这种给人类带来的灾难，从13岁开始就立下了将来当个医生能根治这种疾病的愿望。\n琴纳在哥哥的帮助下，跟随外科医生卢德洛学了7年医术。20岁时，他已经是一名能干的助理外科医生了。在医疗实践中，琴纳从牧场挤奶女工在患牛痘的母牛上感染牛痘后，而不会染上天花这一发现上得到启发。经过20多年的探索、研究，于1796年5月的一天早晨，他用清洁的柳叶刀在一个叫杰米的8岁孩子的两条胳膊上划破几道，接种上牛痘浆。事实证明，这是一预防生天花的正确而有效的途径，牛痘疫苗从此产生了。\n牛痘接种的成功，为免疫学开创了广阔的领域，在国际上，琴纳赢得了极大的赞誉。1799年夏，人们称誉他为伟大的科学发明家入生命拯救者。拿破仑曾称琴纳为伟人。所有现代接种法实际上都是来源于琴纳的第一次伟大发现。\n1829年1月26日，伟大的医生琴纳在伯克利寓所停止了心脏跳动。\n","lunar":"己巳年四月初二"}]
     * reason : 请求成功！
     * error_code : 0
     */

    private String reason;
    private int error_code;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getError_code() {
        return error_code;
    }

    public void setError_code(int error_code) {
        this.error_code = error_code;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * _id : 17490517
         * title : 牛痘接种创始人琴纳诞辰
         * pic : http://juheimg.oss-cn-hangzhou.aliyuncs.com/toh/201005/14/8102821492.jpg
         * year : 1749
         * month : 5
         * day : 17
         * des : 在268年前的今天，1749年5月17日 (农历四月初二)，牛痘接种创始人琴纳诞辰。
         * content : 在268年前的今天，1749年5月17日 (农历四月初二)，牛痘接种创始人琴纳诞辰。
         当今人类终于消灭了可怕的传染病天花的时候，不禁怀念牛痘接种法的创始人、伟大的英国医生琴纳。
         1749年5月17日，爱德华·琴纳出生于英国格洛斯特郡伯克利牧区的一个牧师家庭。他5岁时，当牧师的父亲去世了，与当牧师的哥哥斯蒂芬·琴纳生活在一起。琴纳长得结实、健壮，生性温和，兴趣广泛，尤其喜欢大自然。在学校他是优秀生，还收集多种动植物的标本。琴纳青少年时期，天花这个可怕的瘟疫正在整个欧洲蔓延着，而且还被勘探者、探险家和殖民者传播到了美洲。在英国几乎每个人都会传染上这种病，在成年人的脸上或身上会留下难看的疤痕。成千上万的人由于病情严重而变成瞎子或疯子，每年死去的人更多。琴纳目睹这种给人类带来的灾难，从13岁开始就立下了将来当个医生能根治这种疾病的愿望。
         琴纳在哥哥的帮助下，跟随外科医生卢德洛学了7年医术。20岁时，他已经是一名能干的助理外科医生了。在医疗实践中，琴纳从牧场挤奶女工在患牛痘的母牛上感染牛痘后，而不会染上天花这一发现上得到启发。经过20多年的探索、研究，于1796年5月的一天早晨，他用清洁的柳叶刀在一个叫杰米的8岁孩子的两条胳膊上划破几道，接种上牛痘浆。事实证明，这是一预防生天花的正确而有效的途径，牛痘疫苗从此产生了。
         牛痘接种的成功，为免疫学开创了广阔的领域，在国际上，琴纳赢得了极大的赞誉。1799年夏，人们称誉他为伟大的科学发明家入生命拯救者。拿破仑曾称琴纳为伟人。所有现代接种法实际上都是来源于琴纳的第一次伟大发现。
         1829年1月26日，伟大的医生琴纳在伯克利寓所停止了心脏跳动。

         * lunar : 己巳年四月初二
         */

        private String _id;
        private String title;
        private String pic;
        private int year;
        private int month;
        private int day;
        private String des;
        private String content;
        private String lunar;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getDay() {
            return day;
        }

        public void setDay(int day) {
            this.day = day;
        }

        public String getDes() {
            return des;
        }

        public void setDes(String des) {
            this.des = des;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getLunar() {
            return lunar;
        }

        public void setLunar(String lunar) {
            this.lunar = lunar;
        }
    }
}
