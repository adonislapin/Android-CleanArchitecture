package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

import android.content.ContentResolver;
import android.database.Cursor;

public abstract class CursorObserver extends BusObserver<CursorObserver.CursorAction> {

    public CursorObserver(){
        super(CursorAction.class);
    }

    public static class CursorAction {
        private ContentResolver contentResolver;

        public CursorAction(ContentResolver contentResolver){
            this.contentResolver = contentResolver;
        }

        public ContentResolver getContentResolver() {
            return contentResolver;
        }
    }
}
