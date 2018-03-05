package com.globant.equattrocchio.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.globant.equattrocchio.data.db.pojo.ImageConctract;
import com.globant.equattrocchio.data.db.pojo.ImageDAO;

import io.realm.RealmResults;

public class ImageContentProvider extends ContentProvider {

    private static final int IMAGES = 1;
    private static final int IMAGES_ID = 2;
    private static final UriMatcher uriMatcher;

    private DBImpl db;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ImageConctract.AUTHORITY, "images", IMAGES);
        uriMatcher.addURI(ImageConctract.AUTHORITY, "images/#", IMAGES_ID);
    }

    @Override
    public boolean onCreate() {
        db = new DBImpl();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs,
            @Nullable String sortOrder) {
        if(uriMatcher.match(uri) == IMAGES){
            RealmResults<ImageDAO> results = (RealmResults<ImageDAO>) db.getAllImages();

            if(results == null){
                return null;
            }

            String[] columns = new String[] { "_id", "id_img" , "url", "largeUrl", "sourceId" };
            MatrixCursor matrixCursor = new MatrixCursor(columns);

            int i = 0;
            for(ImageDAO img: results){
                matrixCursor.addRow(new Object[] { i++ , img.getId_img(), img.getUrl(), img.getLargeUrl(), img.getSourceId() });
            }

            matrixCursor.close();

            return matrixCursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int match = uriMatcher.match(uri);

        switch (match)
        {
            case IMAGES:
                return ImageConctract.Images.CONTENT_TYPE;
            case IMAGES_ID:
                return ImageConctract.Images.CONTENT_IMAGE_TYPE;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
