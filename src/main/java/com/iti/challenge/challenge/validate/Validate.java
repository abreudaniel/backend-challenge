package com.iti.challenge.challenge.validate;

import java.util.regex.Pattern;

public class Validate {

    public enum Type {
        CHECK_PARAMETER("P","^(?=.*\\w)(?=.*[!@#$%^&*()-+])[\\w!@#$%^&*()-+]{9,}$"),
        CHECK_REPEAT_CARACTER_NUMBER_SPECIAL("RS","([\\w!@#$%^&*()-+])*.*\\1");

        private final String id;
        private final Pattern regexPattern;

        Type(final String id, final String regexString) {
            this.id = id;
            this.regexPattern = Pattern.compile(regexString);
        }

        public String getId() {
            return id;
        }

        public Pattern getRegexPattern() {
            return regexPattern;
        }
    }

    public static boolean isValid(String value, Type type){
        return type.getRegexPattern().matcher(value).matches();
    }

}
