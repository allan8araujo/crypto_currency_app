package com.example.apilibrary.repository.database;

import java.lang.System;

@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\u0002\u0010\u0005J\u0010\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0010J\u0016\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00100\u0015j\b\u0012\u0004\u0012\u00020\u0010`\u0016J\b\u0010\u0017\u001a\u00020\u0007H\u0002J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0019\u001a\u00020\u0010J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0019\u001a\u00020\u0010J\b\u0010\u001c\u001a\u00020\u0012H\u0002J\u000e\u0010\u001d\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u0004H\u0002R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0003\u001a\u00020\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/apilibrary/repository/database/TinyDB;", "Landroidx/appcompat/app/AppCompatActivity;", "Ljava/io/Serializable;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "categoryFile", "Ljava/io/File;", "getCategoryFile", "()Ljava/io/File;", "setCategoryFile", "(Ljava/io/File;)V", "getContext", "()Landroid/content/Context;", "fileMap", "Ljava/util/HashMap;", "", "addItem", "", "word", "getAll", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "getCategory", "getItem", "id", "hasItem", "", "populateHash", "removeItem", "verifyFile", "c", "Companion", "repository_debug"})
public final class TinyDB extends androidx.appcompat.app.AppCompatActivity implements java.io.Serializable {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private java.io.File categoryFile;
    private final java.util.HashMap<java.lang.String, java.lang.String> fileMap = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.apilibrary.repository.database.TinyDB.Companion Companion = null;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FAVORITOS = "favorites.txt";
    
    public TinyDB(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File getCategoryFile() {
        return null;
    }
    
    public final void setCategoryFile(@org.jetbrains.annotations.NotNull()
    java.io.File p0) {
    }
    
    private final boolean verifyFile(android.content.Context c) {
        return false;
    }
    
    private final java.io.File getCategory() {
        return null;
    }
    
    private final void populateHash() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getAll() {
        return null;
    }
    
    public final void addItem(@org.jetbrains.annotations.Nullable()
    java.lang.String word) {
    }
    
    public final boolean hasItem(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String getItem(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void removeItem(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
    }
    
    @kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/example/apilibrary/repository/database/TinyDB$Companion;", "", "()V", "FAVORITOS", "", "repository_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}