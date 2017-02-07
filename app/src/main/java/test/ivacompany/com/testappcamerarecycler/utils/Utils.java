package test.ivacompany.com.testappcamerarecycler.utils;

import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import test.ivacompany.com.testappcamerarecycler.TestApp;
import test.ivacompany.com.testappcamerarecycler.models.Photo;

/**
 * Created by root on 06.02.17.
 */

public class Utils {

    private static Realm realm;

    public static void initRealm() {
        if (realm == null) {
            realm.init(TestApp.getAppContext());
            realm = Realm.getDefaultInstance();
        }
    }

    public static List<Photo> readFromDB() {
        List<Photo> list = new ArrayList<>();
        RealmResults<Photo> results = realm.where(Photo.class).findAll();
        for(Photo e: results){
            e.setImage(BitmapFactory.decodeByteArray(e.getByteImage(), 0, e.getByteImage().length));
            list.add(e);
        }
        return list;
    }

    public static void addToDB(Photo photo) {
        realm.beginTransaction();
        realm.copyToRealm(photo);
        realm.commitTransaction();
    }

    public static void removeFromRealm(long id) {
        realm.beginTransaction();
        RealmResults<Photo> result = realm.where(Photo.class).equalTo(Constants.ID,id).findAll();
        result.deleteAllFromRealm();
        realm.commitTransaction();
    }

    public static Realm getRealm(){
        return realm;
    }

}