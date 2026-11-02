/*
 *  Copyright 2026 CNM Ingenuity, Inc.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package edu.cnm.deepdive.skywatch.javafx;

import edu.cnm.deepdive.skywatch.javafx.controller.MainController;
import edu.cnm.deepdive.skywatch.javafx.di.DaggerJavaFxComponent;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Initializes (in the {@link #start(Stage)} method) application-level resources, and sets the
 * contents of the primary stage. This class <strong>must</strong> be referenced in
 * {@link Main#main(String[])} for the application to start successfully.
 */
public class SkyWatchApplication extends Application {

  private static final String MAIN_LAYOUT_FILE = "main.fxml";
  private static final String BUNDLE_BASE_NAME = "ui-strings";
  private static final String WINDOW_TITLE_KEY = "window-title";

  @Override
  public void start(Stage stage) throws Exception {
    ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_BASE_NAME);
    FXMLLoader loader =
        new FXMLLoader(getClass().getClassLoader().getResource(MAIN_LAYOUT_FILE), bundle);
    loader.setControllerFactory((type) -> {
      if (type == MainController.class) {
        return DaggerJavaFxComponent.create().mainController();
      } else {
        throw new IllegalArgumentException("Unsupported controller class: %s".formatted(type));
      }
    });
    Parent root = loader.load();
    Scene scene = new Scene(root);
    stage.setResizable(false);
    stage.setTitle(bundle.getString(WINDOW_TITLE_KEY));
    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    // TODO: 2026-10-28 Shut down the service.
    super.stop();
  }

}
