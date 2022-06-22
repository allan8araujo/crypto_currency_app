package com.example.apilibrary.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eJ\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0019\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0097@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u0012"}, d2 = {"Lcom/example/apilibrary/repository/Repository;", "Lcom/example/apilibrary/repository/IRepository;", "assetsDao", "Lcom/example/apilibrary/repository/database/AssetsDao;", "(Lcom/example/apilibrary/repository/database/AssetsDao;)V", "getAllAssets", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/example/abstraction/AssetsItem;", "getGetAllAssets", "()Lkotlinx/coroutines/flow/Flow;", "deleteAsset", "", "asset", "(Lcom/example/abstraction/AssetsItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApiAssets", "Lcom/example/apilibrary/repository/api/request/IAssetsRequest;", "insertAsset", "repository_debug"})
public final class Repository implements com.example.apilibrary.repository.IRepository {
    private final com.example.apilibrary.repository.database.AssetsDao assetsDao = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.example.abstraction.AssetsItem>> getAllAssets = null;
    
    public Repository(@org.jetbrains.annotations.NotNull()
    com.example.apilibrary.repository.database.AssetsDao assetsDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.apilibrary.repository.api.request.IAssetsRequest getApiAssets() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.example.abstraction.AssetsItem>> getGetAllAssets() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.WorkerThread()
    @java.lang.Override()
    public java.lang.Object insertAsset(@org.jetbrains.annotations.NotNull()
    com.example.abstraction.AssetsItem asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    @androidx.annotation.WorkerThread()
    @java.lang.Override()
    public java.lang.Object deleteAsset(@org.jetbrains.annotations.NotNull()
    com.example.abstraction.AssetsItem asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation) {
        return null;
    }
}