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

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Initializes (in the {@link #start(Stage)} method) application-level resources, and sets the
 * contents of the primary stage. This class <strong>must</strong> be referenced in
 * {@link Main#main(String[])} for the application to start successfully.
 */
public class SkyWatchApplication extends Application {

  @Override
  public void start(Stage stage) throws Exception {
    // TODO Set window title as appropriate.
    // TODO Load or create Parent node to create a scene and set it on the stage.
    stage.show();
  }

  @Override
  public void stop() throws Exception {
    // TODO: 2026-10-28 Shut down the service.
    super.stop();
  }

}
