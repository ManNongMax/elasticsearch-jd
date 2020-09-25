package com.esjd.utils;

import com.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HtmlParseUtil {
//    public static void main(String[] args) throws IOException {
//
//        new HttpParseUtil().parseJD("vue").forEach(System.out::println);
//
//    }

    public List<Content> parseJD(String keyword) throws IOException {
        String url = "https://search.jd.com/Search?keyword="+keyword;
        //解析网页 使用Jsoup
        Document document = Jsoup.parse(new URL(url), 60000);
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");

        List<Content> goodList = new ArrayList<>();

        for (Element el: elements) {
            String image = replaceString(el.getElementsByTag("a").eq(0).tagName("img").html());
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            //暂时无法获取img标签,先用字符串截取地址
            int index = image.indexOf(".jpg\">")+4;
            String imgs = image.substring(0,index);
            int index1 = imgs.indexOf("g=\"//img")+3;
            String img = imgs.substring(index1);
            //new content 对象
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            goodList.add(content);
        }
        return goodList;

    }


    public static String replaceString (String str){
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

}
