package com.mikeescom.employeedirectory.dependency;

import com.mikeescom.employeedirectory.model.Repository;

import javax.inject.Singleton;

@Singleton
@dagger.Component(modules = Module.class)
public interface Component {
    Repository buildRepository();
}
