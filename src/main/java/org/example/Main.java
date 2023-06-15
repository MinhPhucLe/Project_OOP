package org.example;
import crawler.*;
import models.Character;
public class Main {
    public static void main(String[] args) {
        //NKSCharacterCrawler crawl = new NKSCharacterCrawler();
        /*TVLSCharacterCrawler crawl1 = new TVLSCharacterCrawler();
        SitesCrawler crawl = new SitesCrawler();
        crawl.crawlData();
        crawl1.crawlData();
        TVLSCCharacter crawl2 = new TVLSCCharacter();
        crawl2.crawlData();*/
        //VSDynasty crawl3 = new VSDynasty();
        //crawl3.crawlData();
        TVLSEvents crawl4 = new TVLSEvents();
        crawl4.crawlData();
    }
}