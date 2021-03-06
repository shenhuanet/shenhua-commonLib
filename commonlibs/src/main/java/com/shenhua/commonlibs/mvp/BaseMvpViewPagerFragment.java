package com.shenhua.commonlibs.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.shenhua.commonlibs.base.BaseViewPagerFragment;

/**
 * Created by shenhua on 2/16/2017.
 * Email shenhuanet@126.com
 */
public abstract class BaseMvpViewPagerFragment<P extends BasePresenter<V>, V extends BaseView> extends BaseViewPagerFragment implements BaseView, LoaderManager.LoaderCallbacks<P> {

    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int BASE_LOADER_ID = 0x1000000;
        getActivity().getSupportLoaderManager().initLoader(BASE_LOADER_ID, null, this);//初始化loader
        mPresenter = createPresenter();
        mPresenter.attachView((V) this);
    }

    public abstract P createPresenter();

    @Override
    public Loader<P> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<P> loader, P data) {
        mPresenter = data;
    }

    @Override
    public void onLoaderReset(Loader<P> loader) {
        mPresenter = null;
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

}
