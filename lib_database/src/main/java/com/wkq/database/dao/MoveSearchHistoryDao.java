package com.wkq.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "MOVE_SEARCH_HISTORY".
*/
public class MoveSearchHistoryDao extends AbstractDao<MoveSearchHistory, String> {

    public static final String TABLENAME = "MOVE_SEARCH_HISTORY";

    /**
     * Properties of entity MoveSearchHistory.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MoveName = new Property(0, String.class, "moveName", true, "MOVE_NAME");
    }


    public MoveSearchHistoryDao(DaoConfig config) {
        super(config);
    }
    
    public MoveSearchHistoryDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"MOVE_SEARCH_HISTORY\" (" + //
                "\"MOVE_NAME\" TEXT PRIMARY KEY NOT NULL );"); // 0: moveName
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"MOVE_SEARCH_HISTORY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, MoveSearchHistory entity) {
        stmt.clearBindings();
 
        String moveName = entity.getMoveName();
        if (moveName != null) {
            stmt.bindString(1, moveName);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, MoveSearchHistory entity) {
        stmt.clearBindings();
 
        String moveName = entity.getMoveName();
        if (moveName != null) {
            stmt.bindString(1, moveName);
        }
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public MoveSearchHistory readEntity(Cursor cursor, int offset) {
        MoveSearchHistory entity = new MoveSearchHistory( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0) // moveName
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, MoveSearchHistory entity, int offset) {
        entity.setMoveName(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
     }
    
    @Override
    protected final String updateKeyAfterInsert(MoveSearchHistory entity, long rowId) {
        return entity.getMoveName();
    }
    
    @Override
    public String getKey(MoveSearchHistory entity) {
        if(entity != null) {
            return entity.getMoveName();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(MoveSearchHistory entity) {
        return entity.getMoveName() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
