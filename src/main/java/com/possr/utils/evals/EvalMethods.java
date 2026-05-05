package com.possr.utils.evals;

public class EvalMethods {
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";
    public boolean isValidObject(Object obj) {
        return obj != null;
    }

    public boolean isValidString(String str) {
        return str != null && !str.trim().isEmpty() && isSafeString(str);
    }

    public boolean isValidString(String str, int minLength) {
        return str != null && !str.trim().isEmpty() && str.length() >= minLength && isSafeString(str);
    }

    public boolean isValidString(String str, int minLength, int maxLength) {
        return str != null && !str.trim().isEmpty() && str.length() >= minLength && str.length() <= maxLength && isSafeString(str);
    }

    private boolean isSafeString(String str) {
        String[] maliciousPatterns = {
            "<script", "</script>", "javascript:", "onclick", "onerror", "onload",
            "eval(", "expression(", "vbscript:", "data:",
            "'", "\"", "--", ";", "/*", "*/", "@@", "@",
            "char(", "nchar(", "varchar(", "nvarchar(",
            "alter", "begin", "cast", "create", "cursor", "declare", "delete",
            "drop", "end", "exec", "execute", "fetch", "insert", "kill",
            "select", "sys", "sysobjects", "syscolumns", "table", "update"
        };
        String lowerStr = str.toLowerCase();
        for (String pattern : maliciousPatterns) {
            if (lowerStr.contains(pattern)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidInteger(Integer value) {
        return value != null;
    }

    public boolean isValidInteger(Integer value, int minValue) {
        return value != null && value >= minValue;
    }

    public boolean isValidInteger(Integer value, int minValue, int maxValue) {
        return value != null && value >= minValue && value <= maxValue;
    }

    public boolean isValidLong(Long value) {
        return value != null;
    }

    public boolean isValidLong(Long value, long minValue) {
        return value != null && value >= minValue;
    }

    public boolean isValidLong(Long value, long minValue, long maxValue) {
        return value != null && value >= minValue && value <= maxValue;
    }

    public boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$")) {
            return false;
        }
        String domain = email.substring(email.indexOf('@') + 1);
        String[] domainParts = domain.split("\\.");
        for (String part : domainParts) {
            if (part.length() < 2) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidDate(String date) {
        if (date == null) {
            return false;
        }
        if (!date.matches(DATE_PATTERN)) {
            return false;
        }
        try {
            java.time.LocalDate.parse(date);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean isDateOnOrBeforeCurrentDate(String date) {
        if (date == null) {
            return false;
        }
        if (!date.matches(DATE_PATTERN)) {
            return false;
        }
        try {
            java.time.LocalDate parsedDate = java.time.LocalDate.parse(date);
            return parsedDate.isBefore(java.time.LocalDate.now());
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean isDateOnOrAfterCurrentDate(String date) {
        if (date == null) {
            return false;
        }
        if (!date.matches(DATE_PATTERN)) {
            return false;
        }
        try {
            java.time.LocalDate parsedDate = java.time.LocalDate.parse(date);
            return parsedDate.isAfter(java.time.LocalDate.now());
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean areDatesDifferent(String date1, String date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (!date1.matches(DATE_PATTERN) || !date2.matches(DATE_PATTERN)) {
            return false;
        }
        try {
            java.time.LocalDate parsedDate1 = java.time.LocalDate.parse(date1);
            java.time.LocalDate parsedDate2 = java.time.LocalDate.parse(date2);
            return !parsedDate1.equals(parsedDate2);
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean isDateLaterThan(String date1, String date2) {
        if (date1 == null || date2 == null) {
            return false;
        }
        if (!date1.matches(DATE_PATTERN) || !date2.matches(DATE_PATTERN)) {
            return false;
        }
        try {
            java.time.LocalDate parsedDate1 = java.time.LocalDate.parse(date1);
            java.time.LocalDate parsedDate2 = java.time.LocalDate.parse(date2);
            return parsedDate1.isAfter(parsedDate2);
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean isOnlyLetters(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^[A-Za-záéíóúÁÉÍÓÚñÑüÜ]+$");
    }

    public boolean isOnlyLettersAndSpaces(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^[A-Za-záéíóúÁÉÍÓÚñÑüÜ ]+$");
    }

    public boolean isOnlyNumbers(String str) {
        if (str == null) {
            return false;
        }
        return str.matches("^\\d+$");
    }

    public boolean isMinimumAge(String date, int minimumAge) {
        if (date == null) {
            return false;
        }
        if (!date.matches(DATE_PATTERN)) {
            return false;
        }
        try {
            java.time.LocalDate birthDate = java.time.LocalDate.parse(date);
            java.time.LocalDate today = java.time.LocalDate.now();
            long years = java.time.temporal.ChronoUnit.YEARS.between(birthDate, today);
            return years >= minimumAge;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }

    public boolean isValidBoolean(String value) {
        if (value == null || value.isEmpty()) {
            return false;
        }
        return value.equals("1") || value.equalsIgnoreCase("true")
            || value.equals("0") || value.equalsIgnoreCase("false");
    }

    public boolean isValidBoolean(Integer value) {
        if (value == null) {
            return false;
        }
        return value == 1 || value == 0;
    }

    public boolean isValidBoolean(Boolean value) {
        return value != null;
    }
}
