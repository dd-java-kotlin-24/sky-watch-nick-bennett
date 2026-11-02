package edu.cnm.deepdive.skywatch.javafx.di;

import dagger.Component;
import edu.cnm.deepdive.skywatch.core.service.ServiceModule;
import edu.cnm.deepdive.skywatch.javafx.controller.MainController;
import jakarta.inject.Singleton;

@Singleton
@Component(modules = {ServiceModule.class})
public interface JavaFxComponent {

  MainController mainController();

}
