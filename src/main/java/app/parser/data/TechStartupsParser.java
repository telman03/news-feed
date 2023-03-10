package app.parser.data;

import app.entity.Article;
import app.parser.JsoupParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import app.parser.Website;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class TechStartupsParser implements JsoupParser {
    List<Article> articles = new ArrayList<>();


    @Override
    public List<Article> getArticles() {
        Document doc = connection("https://techstartups.com/",this.getClass().getName());
        try{
            Elements elements = doc.select(".sidebar_content .post");
            elements.stream().parallel().forEach(element->{
                String header = element.select(".post_header_title").first().getElementsByTag("a").first().text();
                String content = element.select(".post_header_title > p").text();
                String link = element.select(".post_header_title").first()
                        .getElementsByTag("a").first().attr("href");
                String imageLink = element.getElementsByTag("img").first().attr("src");
                LocalDate date = convertStringToDate(element.select(".post_date .post_info_date > a").text().split("Posted On ")[1], DateTimeFormatter.ofPattern("MMMM d, uuuu"));

                Document doc1 = connection(link ,this.getClass().getName());
                String fullContent = doc1.getElementsByClass("post_header").text();
                articles.add(new Article(header, content, link, imageLink, date, Website.TechStartups));
            });
        } catch (NullPointerException e) {

        }
        return articles;
    }

    public String getFullContentTS(String link) {
        String fullContent = null;
        try {
            Document doc1 = connection(link, this.getClass().getName());
            fullContent = doc1.getElementsByClass("post_header").text();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return fullContent;
    }
}
