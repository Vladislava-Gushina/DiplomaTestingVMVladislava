import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LabWorksParser {
    public List<LabWork> parseLabWorks(String htmlFile){
        try {
            Document document = Jsoup.parse(new File(htmlFile));
            Element wrapperLab = document.getElementsByClass("wrapperlab").first();

            assert wrapperLab != null;
            List<LabWork> labWorkList = new ArrayList<>();
            List<String> allHeaders = parseAllHeaders(wrapperLab.getElementsByTag("h2"));
            List<String> allUnderHeaders = parseAllUnderHeaders(wrapperLab.getElementsByTag("h4"));
            List<String> allTexts = parseAllTexts(wrapperLab.getElementsByClass("text"));

            addFirstLabWork(Objects.requireNonNull(wrapperLab.getElementsByTag("h2").first()), allUnderHeaders.get(0), labWorkList);
            allHeaders.remove(0);
            allUnderHeaders.remove(0);

            for (int i = 0; i < allHeaders.size(); i++) {
                labWorkList.add(new LabWork(allHeaders.get(i), allUnderHeaders.get(i), allTexts.get(0)));
            }

            return labWorkList;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private List<String> parseAllHeaders(Elements headers){
        List<String> allHeaders = new ArrayList<>();
        headers.forEach(header -> allHeaders.add(header.text()));

        return allHeaders;
    }

    private List<String> parseAllUnderHeaders(Elements underHeaders){
        List<String> allUnderHeaders = new ArrayList<>();
        underHeaders.forEach(header -> allUnderHeaders.add(header.text()));

        return allUnderHeaders;
    }

    private List<String> parseAllTexts(Elements allTextsElements){
        List<String> allTexts = new ArrayList<>();

        for (Element allTextsElement : allTextsElements) {
            StringBuilder stringBuilder = new StringBuilder();

            for (Element child : allTextsElement.getAllElements()) {
                if(!child.tagName().equals("img")){
                    stringBuilder.append(child.text()).append("\n");
                }else{
                    stringBuilder.append(child).append("\n");
                }
            }
            allTexts.add(stringBuilder.toString());
        }

        return allTexts;
    }

    private void addFirstLabWork(@NotNull Element firstHeader, String firstUnderHeader, List<LabWork> allLabWorks){
        StringBuilder stringBuilder = new StringBuilder();

        for (Element nextElementSibling : firstHeader.nextElementSiblings()) {
            if(!nextElementSibling.tagName().equals("h2")){
                if(!nextElementSibling.tagName().equals("img")){
                    stringBuilder.append(nextElementSibling.text()).append("\n");
                }else {
                    stringBuilder.append(nextElementSibling).append("\n");
                }
            }else
                break;

        }

        allLabWorks.add(new LabWork(firstHeader.text(), firstUnderHeader, stringBuilder.toString()));
    }
}
