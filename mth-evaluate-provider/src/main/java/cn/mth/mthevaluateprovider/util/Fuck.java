package cn.mth.mthevaluateprovider.util;

public class Fuck {
    /**
     * 判断敏感字符
     * @param
     * @return
     */
    public static boolean isLetterDigitOrChinese (String realname){
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return realname.matches(regex);
    }
    public int talkText (String talk){

        //判断出了字母,汉字,特殊符号 就出异常
        boolean am = isLetterDigitOrChinese(talk);
        if (am) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 判断长度
     * @param talk
     * @return
     */
    public int talkLength(String talk){
        if (talk.length()>10){
            return 0;
        }else{
            return 1;
        }

    }
}
