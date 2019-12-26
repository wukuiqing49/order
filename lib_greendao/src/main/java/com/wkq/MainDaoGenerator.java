package com.wkq;


import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MainDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema(1, "com.wkq.database.dao");
        addDefautDao(schema);
        addAdTimeInfo(schema);
        new DaoGenerator().generateAll(schema, args[0]);
    }


    //
    private static void addDefautDao(Schema schema) {
        Entity uploadVideo = schema.addEntity("ExceptionInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addIdProperty().autoincrement().primaryKey();
        uploadVideo.addStringProperty("ErrorTag").notNull();
        uploadVideo.addStringProperty("ErrorMessage").notNull();
    }

    private static void addAdTimeInfo(Schema schema) {
        Entity uploadVideo = schema.addEntity("AdTimeInfo");
        uploadVideo.setHasKeepSections(true);
        uploadVideo.addStringProperty("AdTimeKey").primaryKey();
        uploadVideo.addStringProperty("AdTime").notNull();
        uploadVideo.addIntProperty("AdClickCount").notNull();
    }

}
