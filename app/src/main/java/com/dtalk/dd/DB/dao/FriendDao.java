package com.dtalk.dd.DB.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.dtalk.dd.DB.entity.UserEntity;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

/**
 * Created by Donal on 16/4/28.
 */
public class FriendDao extends AbstractDao<UserEntity, Long> {

    public static final String TABLENAME = "FriendUserInfo";

    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PeerId = new Property(1, int.class, "peerId", false, "PEER_ID");
        public final static Property Gender = new Property(2, int.class, "gender", false, "GENDER");
        public final static Property MainName = new Property(3, String.class, "mainName", false, "MAIN_NAME");
        public final static Property PinyinName = new Property(4, String.class, "pinyinName", false, "PINYIN_NAME");
        public final static Property RealName = new Property(5, String.class, "realName", false, "REAL_NAME");
        public final static Property Avatar = new Property(6, String.class, "avatar", false, "AVATAR");
        public final static Property Phone = new Property(7, String.class, "phone", false, "PHONE");
        public final static Property Email = new Property(8, String.class, "email", false, "EMAIL");
        public final static Property DepartmentId = new Property(9, int.class, "departmentId", false, "DEPARTMENT_ID");
        public final static Property Status = new Property(10, int.class, "status", false, "STATUS");
        public final static Property Created = new Property(11, int.class, "created", false, "CREATED");
        public final static Property Updated = new Property(12, int.class, "updated", false, "UPDATED");
        public final static Property IsFriend = new Property(13, int.class, "isFriend", false, "ISFRIEND");
        public final static Property Area = new Property(14, String.class, "area", false, "AREA");
        public final static Property MomentCover = new Property(15, String.class, "momentcover", false, "MOMENTCOVER");
    }

    ;


    public FriendDao(DaoConfig config) {
        super(config);
    }

    public FriendDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /**
     * Creates the underlying database table.
     */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists ? "IF NOT EXISTS " : "";
        db.execSQL("CREATE TABLE " + constraint + "'FriendUserInfo' (" + //
                "'_id' INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "'PEER_ID' INTEGER NOT NULL UNIQUE ," + // 1: peerId
                "'GENDER' INTEGER NOT NULL ," + // 2: gender
                "'MAIN_NAME' TEXT NOT NULL ," + // 3: mainName
                "'PINYIN_NAME' TEXT NOT NULL ," + // 4: pinyinName
                "'REAL_NAME' TEXT NOT NULL ," + // 5: realName
                "'AVATAR' TEXT NOT NULL ," + // 6: avatar
                "'PHONE' TEXT NOT NULL ," + // 7: phone
                "'EMAIL' TEXT NOT NULL ," + // 8: email
                "'DEPARTMENT_ID' INTEGER NOT NULL ," + // 9: departmentId
                "'STATUS' INTEGER NOT NULL ," + // 10: status
                "'CREATED' INTEGER NOT NULL ," + // 11: created
                "'UPDATED' INTEGER NOT NULL ," + // 12: updated
                "'ISFRIEND' INTEGER NOT NULL ," + //13: isfriend
                "'AREA' TEXT NOT NULL ," +//14: area
                "'MOMENTCOVER' TEXT NOT NULL);"); //15: MOMENTCOVER
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_FriendUserInfo_PEER_ID ON FriendUserInfo" +
                " (PEER_ID);");
    }

    /**
     * Drops the underlying database table.
     */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'FriendUserInfo'";
        db.execSQL(sql);
    }

    /**
     * @inheritdoc
     */
    @Override
    protected void bindValues(SQLiteStatement stmt, UserEntity entity) {
        stmt.clearBindings();

        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getPeerId());
        stmt.bindLong(3, entity.getGender());
        stmt.bindString(4, entity.getMainName());
        stmt.bindString(5, entity.getPinyinName());
        stmt.bindString(6, entity.getRealName());
        stmt.bindString(7, entity.getAvatar());
        stmt.bindString(8, entity.getPhone());
        stmt.bindString(9, entity.getEmail());
        stmt.bindLong(10, entity.getDepartmentId());
        stmt.bindLong(11, entity.getStatus());
        stmt.bindLong(12, entity.getCreated());
        stmt.bindLong(13, entity.getUpdated());
        stmt.bindLong(14, entity.getIsFriend());
        stmt.bindString(15, entity.getArea());
        stmt.bindString(16, entity.getMomentcover());
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }

    /**
     * @inheritdoc
     */
    @Override
    public UserEntity readEntity(Cursor cursor, int offset) {
        UserEntity entity = new UserEntity( //
                cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
                cursor.getInt(offset + 1), // peerId
                cursor.getInt(offset + 2), // gender
                cursor.getString(offset + 3), // mainName
                cursor.getString(offset + 4), // pinyinName
                cursor.getString(offset + 5), // realName
                cursor.getString(offset + 6), // avatar
                cursor.getString(offset + 7), // phone
                cursor.getString(offset + 8), // email
                cursor.getInt(offset + 9), // departmentId
                cursor.getInt(offset + 10), // status
                cursor.getInt(offset + 11), // created
                cursor.getInt(offset + 12), // updated
                cursor.getInt(offset + 13),
                cursor.getString(offset + 14),
                cursor.getString(offset + 15)
        );
        return entity;
    }

    /**
     * @inheritdoc
     */
    @Override
    public void readEntity(Cursor cursor, UserEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPeerId(cursor.getInt(offset + 1));
        entity.setGender(cursor.getInt(offset + 2));
        entity.setMainName(cursor.getString(offset + 3));
        entity.setPinyinName(cursor.getString(offset + 4));
        entity.setRealName(cursor.getString(offset + 5));
        entity.setAvatar(cursor.getString(offset + 6));
        entity.setPhone(cursor.getString(offset + 7));
        entity.setEmail(cursor.getString(offset + 8));
        entity.setDepartmentId(cursor.getInt(offset + 9));
        entity.setStatus(cursor.getInt(offset + 10));
        entity.setCreated(cursor.getInt(offset + 11));
        entity.setUpdated(cursor.getInt(offset + 12));
        entity.setIsFriend(cursor.getInt(offset + 13));
        entity.setArea(cursor.getString(offset + 14));
        entity.setMomentcover(cursor.getString(offset + 15));
    }

    /**
     * @inheritdoc
     */
    @Override
    protected Long updateKeyAfterInsert(UserEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }

    /**
     * @inheritdoc
     */
    @Override
    public Long getKey(UserEntity entity) {
        if (entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /**
     * @inheritdoc
     */
    @Override
    protected boolean isEntityUpdateable() {
        return true;
    }

}
