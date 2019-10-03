package com.iamrajendra.codenamefixme;

public class AppInfo {
    private String appName;
    private String versionName;
    private int versionNumber;
    private String packageName;

    public String getAppName() {
        return appName;
    }

    public String getVersionName() {
        return versionName;
    }

    public int getVersionNumber() {
        return versionNumber;
    }

    public String getPackageName() {
        return packageName;
    }

    public AppInfo setAppName(String appName) {
        this.appName = appName;
        return this;
    }

    public AppInfo setVersionName(String versionName) {
        this.versionName = versionName;
        return this;
    }

    public AppInfo setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
        return this;
    }

    public AppInfo setPackageName(String packageName) {
        this.packageName = packageName;
        return this;
    }
}
