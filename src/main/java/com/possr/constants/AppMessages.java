package com.possr.constants;

public final class AppMessages {
    private AppMessages() {
        throw new IllegalStateException("Utility class");
    }

    public static final String ERROR = "ERROR";
    public static final String SUCCESS = "SUCCESS";
    public static final String USER_TO_CREATE = "toCreate";
    public static final String UNKNOWN_SOURCE = "UNKNOWN";
    public static final String CREATE_USER_METHOD = "createUser";
    public static final String CREATE_EMPLOYEE_METHOD = "createEmployee";
    public static final String EMPLOYEE_TO_CREATE = "toCreate";
    public static final String CREATE_WORK_RELATION = "createWorkRelation";
    public static final String WORK_RELATION_TO_CREATE = "toCreate";
    public static final String TO_LOGIN = "toLogin";
    public static final String TO_CREATE_COMPANY = "toCreateCompany";
    public static final String TO_CREATE_ROLE_COMPANY = "toCreateRoleCompany";
    public static final String TO_CREATE_GENRE = "toCreateGenre";
    public static final String GET_ALL_ROLE_COMPANY_BY_COMPANY_ID = "getAllRoleCompanyByCompanyId";
    public static final String GET_ALL_EMPLOYEES = "getAllEmployees";
}
