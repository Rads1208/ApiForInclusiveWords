package com.jpmc.springWithKafka;

public class InclusiveWordResult {

    private String inclusiveWord;
    private String description;
    private String alternateWords;

    public InclusiveWordResult() {
    }

    public InclusiveWordResult(String inclusiveWord, String description, String alternateWords) {
        this.inclusiveWord = inclusiveWord;
        this.description = description;
        this.alternateWords = alternateWords;
    }

    public String getInclusiveWord() {
        return inclusiveWord;
    }

    public void setInclusiveWord(String inclusiveWord) {
        this.inclusiveWord = inclusiveWord;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAlternateWords() {
        return alternateWords;
    }

    public void setAlternateWords(String alternateWords) {
        this.alternateWords = alternateWords;
    }
}
