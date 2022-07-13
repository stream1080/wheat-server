package com.example.wheat;


import com.example.wheat.entity.Product;
import com.example.wheat.mapper.ProductMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


public class RequestGoodsInfo {

    @Autowired
    private ProductMapper productMapper;

    public static void main(String[] args) throws Exception {

        //搜索关键字
        String urlKeywords = "Java";

        //获取请求  https://search.jd.com/Search?keyword=java 前提，需要联网
        String url = "https://search.jd.com/Search?keyword=" + urlKeywords + "&enc=utf-8";

        //解析网页。(Jsoup返回Document就是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);

        //所有你在js中可以使用的方法，这里都能用！
        Element element = document.getElementById("J_goodsList");

        // 获取所有的li元素
        Elements li = element.getElementsByTag("li");

        //获取元素中的内容,这里的el就是 每一个li标签了
        ArrayList<Product> goodsList = new ArrayList<>();

        for (Element el : li) {
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            System.out.println(title);
        }
    }
}












