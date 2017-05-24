package com.example.yuer.zahui.bean;

import java.util.List;

/**
 * Created by Yuer on 2017/5/19.
 */

public class YaoYiYaoResponse {

    /**
     * reason : success
     * result : [{"content":"\u200d\u200d今天同事让我帮她去办事.我问：\u201c接头女的叫啥。\u201d 同事答：\u201c姓焦。\u201d 我说：\u201c啥？\u201d 她大声喊了一声：\u201c姓焦！\u201d 办公室顿时雅雀无声！此处无声胜有声。\u200d\u200d","hashId":"91BE694BF8CC3DD7A142823EF401B33D","unixtime":"1432711952"},{"content":"跟一哥们玩得很铁，有次找我借车没借给他，他心里很是不爽。。。。没过多久他女朋友吃饭感觉恶吐，测试显示两条杠。。。后来才知道是他把我放在抽屉的TT用针扎了个洞。。。","hashId":"DE7E4023F1C0FFE8555F7E4F414C15C3","unixtime":"1432712536"},{"content":"\u200d\u200d\u200d\u200d我一朋友去广州打工，在公交车上遇到一黑人。 他就用方言说：\u201c这人怎么黑不溜湫呢，还挺臭。\u201d 结果，那黑人就把拳头对准他脑袋用很纯正的普通话说：\u201c有种你在说一遍。\u201d 我那朋友顿时不敢说了，不敢说了啊！\u200d\u200d\u200d\u200d","hashId":"1E5C92F277EF520990C2E0C4DE28B096","unixtime":"1432712546"},{"content":"　　现在的小年轻啊都太开放了，我在大学城附近出夜市儿的时候，有一对大学生情侣来吃串，女生点菜时故作娇羞的地说：师傅，给我老公上点能补的\u2026\u2026吃啥补啥\u2026\u2026你懂的！我一看女生长得这么丑，男的却长这么帅气，于是给他上了一盘烤脑花。","hashId":"83AE9BFF6C53548DFF9F3CF0ED0C4136","unixtime":"1432712598"},{"content":"一次，看见楼下一小孩在吃荔枝，嘴馋又不好意思直接开口要。 于是跟他说:我知道一个很好玩的游戏要玩吗? 他说:什么游戏，我说我们来比看谁吐的荔枝核飞的远。 他说:好阿。 我说:可是我得先要一个荔枝，他就给了我一个。 第一局我当然比他远，他不甘心，又给了我一个，后来我就一直输，然后他赢上瘾了。 恩。。我吃多一点不要紧。他玩的开心就好。。。","hashId":"E6095AA3B701D2113CCD809E6AB93D27","unixtime":"1432713131"},{"content":"\u200d\u200d\u200d\u200d\u200d\u200d今天上午电脑考试，进了电脑教室都按机器序号徘下去。 一同学跑错教室了，走到8号机前说：\u201c这机8是我的。\u201d 另一同学说：\u201c机8明明是我的。\u201d 然后两个人你一句我一句机8的听的我们笑抽了！\u200d\u200d\u200d\u200d\u200d\u200d","hashId":"1F0D95828145C2A6AFE4026CE5B97A35","unixtime":"1432713207"},{"content":"\u200d\u200d\u200d\u200dA：\u201c兄弟，我昨晚给我媳妇买了个钻石项链，我对你表示同情。\u201d B：\u201c去你的，你给你媳妇买，关我P事啊。\u201d A：\u201c我媳妇今天约你媳妇出去喝茶呢，你想想晚上怎么安抚吧！\u201d\u200d\u200d\u200d\u200d","hashId":"B3D5AB0CA1C85E3D71659BB50E090276","unixtime":"1432713207"},{"content":"\u200d\u200d\u200d\u200d粑粑：\u201c小明，你有木有拿我钱包里的钱？\u201d 小明：\u201c没有！\u201d 粑粑：\u201c真没拿？\u201d 小明：\u201c真没拿，谁要是拿了谁就是狗生的杂种。\u201d 粑粑：\u201c\u2026\u2026\u201d\u200d\u200d\u200d\u200d","hashId":"9B7357188C06947C2EB2BB6A8AE2C04A","unixtime":"1432713738"},{"content":"问：\u201c你觉得你什么时候最有价值，最值钱！\u201d 神回复：\u201c我觉得我最值钱的时候是，有一次我跟我爸出去吃饭，结账的时候他发现没带钱，就把我压在那抵了饭钱！\u201d","hashId":"F6022ECCC2646A9CEE6C2B6732C664EE","unixtime":"1432714351"},{"content":"\u200d\u200d\u200d\u200d和同学去练车，到了中午吃饭时一帮人在一起吃饭，来了一只小狗。 我便对同学说：\u201c你看那狗是不是你家亲戚？\u201d 谁知我那同学脑抽的来了句：\u201c你和那狗是同学吧？\u201d 说完大家笑喷了！\u200d\u200d\u200d\u200d","hashId":"033FC74885150DDE592EBABFC9CB4CB5","unixtime":"1432714364"}]
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
         * content : ‍‍今天同事让我帮她去办事.我问：“接头女的叫啥。” 同事答：“姓焦。” 我说：“啥？” 她大声喊了一声：“姓焦！” 办公室顿时雅雀无声！此处无声胜有声。‍‍
         * hashId : 91BE694BF8CC3DD7A142823EF401B33D
         * unixtime : 1432711952
         */

        private String content;
        private String hashId;
        private String unixtime;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHashId() {
            return hashId;
        }

        public void setHashId(String hashId) {
            this.hashId = hashId;
        }

        public String getUnixtime() {
            return unixtime;
        }

        public void setUnixtime(String unixtime) {
            this.unixtime = unixtime;
        }
    }
}
