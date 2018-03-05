package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.data.DBImpl;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetAllSavedImagesUseCase;
import com.globant.equattrocchio.domain.GetImageByIdUseCase;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.SetImagesUseCase;
import com.globant.equattrocchio.domain.db.DBInterface;
import com.globant.equattrocchio.domain.service.ImagesServices;

public class MainActivity extends AppCompatActivity {

    private ImagesPresenter presenter;
    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetImageByIdUseCase getImageByIdUseCase;
    private SetImagesUseCase setImagesUseCase;
    private GetAllSavedImagesUseCase getAllSavedImagesUseCase;
    private ImagesView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImagesServices services = new ImagesServicesImpl();
        DBInterface dbInterface = new DBImpl();
        getLatestImagesUseCase = new GetLatestImagesUseCase(services);
        getImageByIdUseCase = new GetImageByIdUseCase(services);
        setImagesUseCase = new SetImagesUseCase(dbInterface);
        getAllSavedImagesUseCase = new GetAllSavedImagesUseCase(dbInterface);

        view = new ImagesView(this);
        presenter = new ImagesPresenter( view, getLatestImagesUseCase, getImageByIdUseCase, setImagesUseCase, getAllSavedImagesUseCase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
        view.getDataFromDb();
        view.callServiceBtnPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}