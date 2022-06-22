package com.example.apilibrary.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/example/apilibrary/repository/Repository;", "Lcom/example/apilibrary/repository/IRepository;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getApiAssets", "Lcom/example/apilibrary/repository/api/request/IAssetsRequest;", "getDatabaseAssets", "Lcom/example/apilibrary/repository/database/TinyDB;", "repository_debug"})
public final class Repository implements com.example.apilibrary.repository.IRepository {
    private final android.content.Context context = null;
    
    public Repository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.apilibrary.repository.api.request.IAssetsRequest getApiAssets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.apilibrary.repository.database.TinyDB getDatabaseAssets() {
        return null;
    }
}