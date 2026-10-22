module edu.cnm.deepdive.skywatch.javafx {

  requires javafx.controls;
  requires javafx.fxml;

  exports edu.cnm.deepdive.skywatch.javafx to javafx.graphics;

  opens edu.cnm.deepdive.skywatch.javafx to javafx.fxml;
  // TODO Add opens directive for javafx.fxml to access controller package contents.

}