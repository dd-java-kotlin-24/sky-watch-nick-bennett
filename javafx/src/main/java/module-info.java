// TODO: 2026-10-22 Update module name below as appropriate for the project.
module edu.cnm.deepdive.myproject.javafx {

  requires javafx.controls;
  requires javafx.fxml;

  // TODO Update package below as necessary for the project.
  exports edu.cnm.deepdive.myproject.javafx to javafx.graphics;

  // TODO Update package below as necessary for the project.
  opens edu.cnm.deepdive.myproject.javafx to javafx.fxml;
  // TODO Add opens directive for javafx.fxml to access controller package contents.

}