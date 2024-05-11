package org.vladislava_g.diplomatestingvm.utils.parsers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.vladislava_g.diplomatestingvm.utils.lectures.Lectur;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class LecturesParser {
   public List<Lectur> parsLecture(String filePath){
       try {
           Document document = Jsoup.parse(new File(filePath));
           Element element = document.getElementsByClass("wrapperlab").first();

           Elements header = element.getElementsByTag("h2");
           Elements underHeader = element.getElementsByTag("h4");
           Elements content = element.getElementsByClass("text");

           List<Lectur> lectursList = new ArrayList<>();
           List<String> stringsHeadersLists = parsHeaders(header);
           List<String> stringsUnderHeaderLists = parsUnderHeaders(underHeader);
           List<String> stringsContentLists = parsContent(content);

           for (int i = 0; i < header.size(); i++) {
               lectursList.add(new Lectur(stringsHeadersLists.get(i), stringsUnderHeaderLists.get(i), stringsContentLists.get(i)));
           }

           return lectursList;

       }catch (Exception e){
           e.printStackTrace();
       }
       return null;
   }

   private List<String> parsHeaders(Elements headerElements){
       List<String> headers = new ArrayList<>();
       headerElements.forEach(element -> headers.add(element.text()));
       return headers;
   }

   private List<String> parsUnderHeaders(Elements underHeadersElements){
       List<String> underHeaders = new ArrayList<>();
       underHeadersElements.forEach(element -> underHeaders.add(element.text()));
       return underHeaders;
   }

   private List<String> parsContent(Elements contensElements){
       List<String> content = new ArrayList<>();
       for (Element contensElement : contensElements) {
           StringBuilder stringBuilder = new StringBuilder();
           for (Element p : contensElement.getElementsByTag("p")) {
               stringBuilder.append(p.text()).append("\n");
           }
           content.add(stringBuilder.toString());
       }
       return content;
   }
}
