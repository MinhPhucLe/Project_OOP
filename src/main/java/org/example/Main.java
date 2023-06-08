package org.example;
import crawler.NKSCharacterCrawler;
import models.Character;
public class Main {
    public static void main(String[] args) {
        NKSCharacterCrawler crawl = new NKSCharacterCrawler();
        crawl.crawlData();
    }
}