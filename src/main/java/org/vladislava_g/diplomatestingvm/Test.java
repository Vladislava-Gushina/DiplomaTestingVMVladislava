package org.vladislava_g.diplomatestingvm;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.text.html.ImageView;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            WebView webView = new WebView();
            WebEngine webEngine = webView.getEngine();

            Document doc = Jsoup.parse(new File("content/lab-works/laboratory_works.html"));

            Element wrapperlab = doc.getElementsByClass("wrapperlab").first();

            Elements paragraphs = wrapperlab.select("p, h2, h4");
            VBox vBox = new VBox();

            for (Element paragraph : paragraphs) {
                vBox.getChildren().add(new javafx.scene.control.Label(paragraph.text()));
            }

            Elements images = wrapperlab.select(".lab_img img");
            for (Element image : images) {
                String imageUrl = "content/lab-works/" + image.attr("src");
                if(new File(imageUrl).exists()){
                    javafx.scene.image.Image fxImage = new javafx.scene.image.Image(new FileInputStream(imageUrl));
                    javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(fxImage);
                    vBox.getChildren().add(imageView);

                    String imageCaption = image.parent().select("p").text();
                    vBox.getChildren().add(new javafx.scene.control.Label(imageCaption));
                }
            }

            webEngine.loadContent(wrapperlab.html());

            VBox root = new VBox(webView, vBox);
            Scene scene = new Scene(root, 800, 600);

            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}