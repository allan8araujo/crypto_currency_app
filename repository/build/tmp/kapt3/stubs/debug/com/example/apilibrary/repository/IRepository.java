package com.example.apilibrary.repository;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH&J\u0019\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a6@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\u0006\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\n"}, d2 = {"Lcom/example/apilibrary/repository/IRepository;", "", "deleteAsset", "", "asset", "Lcom/example/abstraction/AssetsItem;", "(Lcom/example/abstraction/AssetsItem;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getApiAssets", "Lcom/example/apilibrary/repository/api/request/IAssetsRequest;", "insertAsset", "repository_debug"})
public abstract interface IRepository {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.apilibrary.repository.api.request.IAssetsRequest getApiAssets();
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAsset(@org.jetbrains.annotations.NotNull()
    com.example.abstraction.AssetsItem asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
    
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAsset(@org.jetbrains.annotations.NotNull()
    com.example.abstraction.AssetsItem asset, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> continuation);
}