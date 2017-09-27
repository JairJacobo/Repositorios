package com.example.jacob.repositorios.models;

import java.util.List;

public class SearchResponse {
    private int total_count;
    private boolean incomplete_results;
    private Item[] items;

    public int getTotalCount() {
        return total_count;
    }

    public boolean isIncompleteResults() {
        return incomplete_results;
    }

    public Item[] getItems() {
        return items;
    }
}
