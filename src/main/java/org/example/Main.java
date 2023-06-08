package org.example;
import crawler.NKSCharacterCrawler;
import crawler.TVLSCharacterCrawler;
import crawler.SitesCrawler;
import models.Character;
public class Main {
    public static void main(String[] args) {
        //NKSCharacterCrawler crawl = new NKSCharacterCrawler();
        TVLSCharacterCrawler crawl1 = new TVLSCharacterCrawler();
        SitesCrawler crawl = new SitesCrawler();
        crawl.crawlData();
        crawl1.crawlData();
    }
}