package org.vladislava_g.diplomatestingvm.utils.parsers;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.vladislava_g.diplomatestingvm.utils.questions.Question;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QuestionsParser {

    public List<Question> parseQuestions(String filePath) {
        try {
            File htmlFile = new File(filePath);

            Document document = Jsoup.parse(htmlFile);
            byte[] rightAnswers = getRightAnswersSequence(filePath);

            List<Question> questions = new ArrayList<>();

            Elements allQuestionsElements = document.getElementsByClass("question");

            for (int i = 0; i < allQuestionsElements.size(); i++) {
                questions.add(new Question(parseQuestionTitle(allQuestionsElements.get(i)), parseAnswers(allQuestionsElements.get(i)), rightAnswers[i]));
            }

            return questions;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    private @NotNull String parseQuestionTitle(@NotNull Element element){
        return element.getElementsByTag("b").get(0).text();
    }

    public byte[] getRightAnswersSequence(String filePath){
        try{
            Document document = Jsoup.parse(new File(filePath));
            Elements checkScript = document.select("script");

            String answersSequenceString = null;

            for (Element script : checkScript) {
                String scriptData = script.data();
                if (scriptData.contains("var res=")) {
                    String[] strings = scriptData.split("\n");
                    answersSequenceString = strings[1].trim();
                    break;
                }
            }

            StringBuilder rightAnswersSequence = new StringBuilder();

            assert answersSequenceString != null;
            int equallyIndex = answersSequenceString.indexOf("=") + 2;

            char[] chars = answersSequenceString.toCharArray();
            for (int i = equallyIndex; i < chars.length; i++) {
                if(chars[i] != '"'){
                    rightAnswersSequence.append(chars[i]);
                }
            }

            byte[] rightAnswersSequenceByteArray = new byte[rightAnswersSequence.length()];
            char[] rightAnswersSequenceChars = rightAnswersSequence.toString().toCharArray();
            for (int i = 0; i < rightAnswersSequenceChars.length; i++) {
                rightAnswersSequenceByteArray[i] = Byte.parseByte(String.valueOf(rightAnswersSequenceChars[i]));
            }

            return rightAnswersSequenceByteArray;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private @NotNull List<String> parseAnswers(@NotNull Element element){
        List<String> answers = new ArrayList<>();
        Elements preAnswersElement = element.select("input[type=radio] + b");

        preAnswersElement.forEach(preAnswerElement ->
                answers.add(Objects.requireNonNull(preAnswerElement.nextSibling()).toString()));

        return answers;
    }
}
