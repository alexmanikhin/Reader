package edu.cursor.u21;

class EmptyString {
    private String str;

    private EmptyString() {
        setStr("");
    }

    static EmptyString createEmptyString() {
        return new EmptyString();
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}

