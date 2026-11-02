module edu.cnm.deepdive.skywatch.core {

  requires dagger;
  requires jakarta.inject;
  requires java.compiler;
  requires kotlin.stdlib;
  requires kotlinx.coroutines.core;
  requires kotlinx.coroutines.jdk8;
  requires retrofit2;
  requires retrofit2.converter.moshi;
  requires com.squareup.moshi;
  requires okhttp3;
  requires okhttp3.logging;

  exports edu.cnm.deepdive.skywatch.core.model;
  exports edu.cnm.deepdive.skywatch.core.service;

  opens edu.cnm.deepdive.skywatch.core.model.dto.nasa to com.squareup.moshi;
  opens edu.cnm.deepdive.skywatch.core.repository to com.squareup.moshi;
  opens edu.cnm.deepdive.skywatch.core.api to retrofit2;
  opens edu.cnm.deepdive.skywatch.core.service to dagger;

}