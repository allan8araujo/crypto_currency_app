package com.example.apilibrary.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/apilibrary/repository/IRepository;", "", "getApiAssets", "Lcom/example/apilibrary/repository/api/request/IAssetsRequest;", "getDatabaseAssets", "Lcom/example/apilibrary/repository/database/TinyDB;", "repository_debug"})
public abstract interface IRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.apilibrary.repository.api.request.IAssetsRequest getApiAssets();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.apilibrary.repository.database.TinyDB getDatabaseAssets();
}