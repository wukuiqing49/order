package com.wkq.database.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "AD_TIME_INFO".
 */
@Entity
public class AdTimeInfo {

    @Id
    private String AdTimeKey;

    @NotNull
    private String AdTime;
    private int AdClickCount;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public AdTimeInfo() {
    }

    public AdTimeInfo(String AdTimeKey) {
        this.AdTimeKey = AdTimeKey;
    }

    @Generated
    public AdTimeInfo(String AdTimeKey, String AdTime, int AdClickCount) {
        this.AdTimeKey = AdTimeKey;
        this.AdTime = AdTime;
        this.AdClickCount = AdClickCount;
    }

    public String getAdTimeKey() {
        return AdTimeKey;
    }

    public void setAdTimeKey(String AdTimeKey) {
        this.AdTimeKey = AdTimeKey;
    }

    @NotNull
    public String getAdTime() {
        return AdTime;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setAdTime(@NotNull String AdTime) {
        this.AdTime = AdTime;
    }

    public int getAdClickCount() {
        return AdClickCount;
    }

    public void setAdClickCount(int AdClickCount) {
        this.AdClickCount = AdClickCount;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
