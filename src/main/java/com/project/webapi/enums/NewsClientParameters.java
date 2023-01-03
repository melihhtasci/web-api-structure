package com.project.webapi.enums;

public enum NewsClientParameters {
    URL("https://newsapi.org/v2/top-headlines?country="),
    API_KEY("06b9e2e8bde64655ae297fdda4b6f239");

    private final String values;

    NewsClientParameters(String values) {
        this.values = values;
    }

    @Override
    public String toString() {
        return this.values;
    }
}
