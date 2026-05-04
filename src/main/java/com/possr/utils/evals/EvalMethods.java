package com.possr.utils.evals;

public class EvalMethods {
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
        if (!date.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            return false;
        }
        try {
            java.time.LocalDate.parse(date);
            return true;
        } catch (java.time.format.DateTimeParseException e) {
            return false;
        }
    }
}
