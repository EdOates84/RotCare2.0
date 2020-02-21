package com.example.android.rotcare1;

public class Last_Token {
    int last_tok,normal_status;

    public  Last_Token() {
    }

    public Last_Token(int last_tok, int normal_status) {
        this.last_tok = last_tok;
        this.normal_status = normal_status;
    }

    public int getNormal_status() {
        return normal_status;
    }

    public void setNormal_status(int normal_status) {
        this.normal_status = normal_status;
    }

    public int getLast_tok() {
        return last_tok;
    }

    public void setLast_tok(int last_tok) {
        this.last_tok = last_tok;
    }
}
