package com.globant.equattrocchio.data.db.pojo;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

public class ImageConctract {

    public static final String AUTHORITY = "com.globant.equattrocchio.data";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String SELECTION_ID_BASED = BaseColumns._ID + " = ? ";

    public static final class Images implements BaseColumns {

        public static final Uri CONTENT_URI = Uri.withAppendedPath(ImageConctract.CONTENT_URI, "images");

        public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/image";

        public static final String CONTENT_IMAGE_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/image";


        public static final String ID_IMG = "";
        public static final String  URL = "url";
        public static final String LARGE_URL = "largeUrl";
        public static final String SOURCE_ID = "sourceId";
        public static final String COPYRIGHT = "copyright";
        public static final String SITE = "site";

        public static final String[] PROJECTION_ALL = {_ID, ID_IMG, URL, LARGE_URL, SOURCE_ID, COPYRIGHT, SITE};
    }
}
