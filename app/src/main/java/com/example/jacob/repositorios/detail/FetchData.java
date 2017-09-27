package com.example.jacob.repositorios.detail;

import com.example.jacob.repositorios.models.Contributor;
import com.example.jacob.repositorios.models.Issue;

public class FetchData {
    private Contributor[] contributors;
    private Issue[] issues;
    private int max;

    public FetchData(int max) {
        this.max = max;
        issues = new Issue[max];
        contributors = new Contributor[max];
    }

    public void setContributors(Contributor[] contributors) {
        if (contributors.length < max) {
            this.contributors = new Contributor[contributors.length];
        }

        for (int i = 0; i < contributors.length && i < max; i++) {
            this.contributors[i] = contributors[i];
        }
    }

    public void setIssues(Issue[] issues) {
        if (issues.length < max) {
            this.issues = new Issue[issues.length];
        }

        for (int i = 0; i < issues.length && i < max; i++) {
            this.issues[i] = issues[i];
        }
    }

    public Contributor[] getContributors() {
        return contributors;
    }

    public Issue[] getIssues() {
        return issues;
    }
}
