package com.globant.equattrocchio.data;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.globant.equattrocchio.data.db.pojo.ImageConctract;
import com.globant.equattrocchio.data.db.pojo.ImageDAO;
import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.domain.db.DBInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class DBImpl implements DBInterface {

    private Realm realm;

    @Override
    public void addImages(Observer<Boolean> observer, Object images) {
        if(images != null){
            realm = Realm.getDefaultInstance();

            List<Image> imgs = (List<Image>) images;
            ArrayList<ImageDAO> daos = new ArrayList<>();

            for (Image img: imgs) {
                ImageDAO dao = new ImageDAO();
                dao.url = img.getUrl();
                dao.site = img.getSite();
                dao.copyright = img.getCopyright();
                dao.id_img = img.getId();
                dao.largeUrl = img.getLargeUrl();
                dao.sourceId = (String)img.getSourceId();

                daos.add(dao);
            }

            realm.beginTransaction();
            realm.insert(daos);
            realm.commitTransaction();

            observer.onNext(true);
        }
    }

    @Override
    public Object getAllImages() {
        realm = Realm.getDefaultInstance();

        RealmQuery<ImageDAO> query = realm.where(ImageDAO.class);
        RealmResults<ImageDAO> results = query.findAll();
        return results;
    }

    @Override
    public void getAllImages(Observer<Object> observer, Object contentResolver) {
        String[] projection = new String[] {"all"};

        Uri clientesUri = ImageConctract.Images.CONTENT_URI;

        ContentResolver cr =  (ContentResolver)contentResolver;

        Cursor cur = cr.query(clientesUri,
                projection,
                null,
                null,
                null);

        observer.onNext(cur);
    }


}
