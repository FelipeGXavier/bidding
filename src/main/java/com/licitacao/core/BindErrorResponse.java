package com.licitacao.core;

import java.util.List;

public class BindErrorResponse {

    private List<ErrorDetails> errors;

    public List<ErrorDetails> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorDetails> errors) {
        this.errors = errors;
    }

    public static class ErrorDetails{

        private String field;
        private String error;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
