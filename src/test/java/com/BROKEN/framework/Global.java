package com.DSN.framework;

public class Global {
    public static final String URL_PRJ_DEV      = "https://www.google.com.ua/";
    public static final String URL_PRJ_STAGE    = "https://www.google.com.ua/";
    public static final String URL_PRJ_PROD     = "https://www.google.com.ua/";
    //===
    public static final int DEF_IMPLICITY_WAIT_SEC              = 3;
    public static final int DEF_SCRIPT_TIMEOUT                  = 10;
    public static final int DEF_PAGE_LOAD_TIMEOUT               = 60;
    public static final int DEF_TIME_TO_WAIT_PAGE_LOADED_SEC    = 10;
    public static final int DEF_TIME_TO_WAIT_LONG_SEC           = 10;
    public static final int DEF_TIME_TO_WAIT_SHORT_SEC          = 4;
    public static final int DEF_TIME_TO_WAIT_1_SEC              = 1;
    public static final int DEF_TIME_TO_WAIT_10_SEC             = 10;

    public static final String      DEF_URL     = URL_PRJ_DEV;
    public static final eLoginDT    DEF_USER    = eLoginDT.admin;

    // LOGIN CREDs
    public enum eLoginDT {
        admin   ("test_admin",  //login()
                "",             //pass()
                "Administrator" //role()
        ),
        editor ("test_editor",
                "",
                "User(editor)"
        ),
        viewer  ("test_viewer",
                "",
                "Viewer"
        );
        private final Object[] values;
        eLoginDT(Object... vals) {
            values = vals;
        }
        public String login() {
            return (String) values[0];
        }
        public String pass() {
            return (String) values[1];
        }
        public String role() {
            return (String) values[2];
        }
        public static eLoginDT findByUserName(String sName){
            for(eLoginDT v : values()){
                if( v.login().equals(sName)){
                    return v;
                }
            }
            return eLoginDT.admin;
        }

        public static eLoginDT findByRole(String sRole){
            for(eLoginDT v : values()){
                if( v.role().equals(sRole)){
                    return v;
                }
            }
            return eLoginDT.admin;
        }
    }
    // Logs and reports
    public static String logFolderPath;
    public static final String REPORT_LOCATION  = "build/reports";
}
