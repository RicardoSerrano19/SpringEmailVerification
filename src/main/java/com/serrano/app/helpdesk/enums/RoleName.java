package com.serrano.app.helpdesk.enums;

public enum RoleName {
    ROLE_ADMIN,
    ROLE_SUPPORT,
    ROLE_USER;

    public static RoleName asRoleName(String str){
        for(RoleName role: RoleName.values()){
            if(role.name().equals(str))
                return role;
        }
        return null;
    }
}
