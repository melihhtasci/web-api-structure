package com.project.webapi.util;

public enum ResponseUtils {

    NEWS_ALREADY_UPDATED("News are already up to date"),
    NEWS_ADDED("News are added"),
    CATEGORY_ALREADY_UPDATED("Category is already up to date"),
    CATEGORY_IS_REQUIRED("Category is required"),
    CATEGORY_NOT_FOUND("Category not find");

    private final String message;

    ResponseUtils(String message) {
        this.message = message;
    }

    public String message() {
        return this.message;
    }
}
