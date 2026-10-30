module edu.cnm.deepdive.skywatch.javafx {

  requires javafx.controls;
  requires javafx.fxml;
  requires java.compiler;
  requires kotlin.stdlib;
  requires org.jspecify;

  requires edu.cnm.deepdive.skywatch.core;

  exports edu.cnm.deepdive.skywatch.javafx to javafx.graphics;

  opens edu.cnm.deepdive.skywatch.javafx to javafx.fxml;
  opens edu.cnm.deepdive.skywatch.javafx.controller to javafx.fxml;

}