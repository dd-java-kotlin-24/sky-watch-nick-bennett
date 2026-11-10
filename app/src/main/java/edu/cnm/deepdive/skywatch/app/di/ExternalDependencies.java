package edu.cnm.deepdive.skywatch.app.di;

import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import edu.cnm.deepdive.skywatch.core.service.ServiceModule;

@Module(includes = {ServiceModule.class})
@InstallIn(SingletonComponent.class)
public interface ExternalDependencies {


}
