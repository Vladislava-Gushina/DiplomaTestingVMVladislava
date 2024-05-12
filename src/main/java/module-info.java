module org.vladislava_g.diplomatestingvm {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;
    requires org.jetbrains.annotations;
    requires java.desktop;
    requires javafx.web;


    opens org.vladislava_g.diplomatestingvm to javafx.fxml;
    opens org.vladislava_g.diplomatestingvm.controllers to javafx.fxml;
    exports org.vladislava_g.diplomatestingvm;
    opens org.vladislava_g.diplomatestingvm.utils to javafx.fxml;
    exports org.vladislava_g.diplomatestingvm.controllers;
    exports org.vladislava_g.diplomatestingvm.utils.parsers;
    opens org.vladislava_g.diplomatestingvm.utils.parsers to javafx.fxml;
    exports org.vladislava_g.diplomatestingvm.utils;
    exports org.vladislava_g.diplomatestingvm.utils.questions;
    opens org.vladislava_g.diplomatestingvm.utils.questions to javafx.fxml;

}