package com.example.mvvmlibrary.lib_mvvm.view;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.LiveBus;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.core.Observable;
import com.example.mvvmlibrary.lib_mvvm.utils.InstanceUtil;
import com.example.mvvmlibrary.lib_mvvm.utils.ParamsBuilder;
import com.example.mvvmlibrary.lib_mvvm.viewmodel.BaseViewModel;

/**
 * @Description: 所有activity的基类
 * @Author: MR.su
 * @Version: 1.0
 */
public abstract class BaseMvvmActivity<VB extends ViewDataBinding, VM extends BaseViewModel> extends AppCompatActivity implements BaseMvvMView {

    protected VM mViewModel;

    protected VB mBinding;
    //设置所有点击事件，为界面连点处理
    protected void initClickAction() {}
    protected abstract void getBundleExtras(Bundle extras);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                //当前activity 布局 ，添加到根部局中
        if (0 != getLayoutRes()) {
            mBinding = DataBindingUtil.inflate(getLayoutInflater(), getLayoutRes(), null, false);
            mBinding.getRoot().setFitsSystemWindows(true);
            setContentView(mBinding.getRoot());
        }
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        initView();
        initViewModel();
        initClickAction();
        initData();
    }

    protected abstract void initView();

    /**
     *
     */
    public abstract ViewModelProvider.Factory onBindViewModelFactory();


    /**
     * 注册界面状态更新观察者对象
     */
    private void registorUIChangeLiveDataCallBack() {
        if (null == mViewModel) {
            return;
        }
        /**
         * 显示网络加载错误界面  包含错误信息
         */
        mViewModel.getRepository().getUiChangeLiveData().getShowErrorEvent().observe(this,
            new Observer<String>() {
                @Override public void onChanged(String error_msg) {
                    Log.w("BaseMvvmActivity","显示服务错误显示");
                    //showError(error_msg);
                }
            });


        /**
         * 显示加载中状态loading
         */
        mViewModel.getRepository().getUiChangeLiveData().getShowLoadingEvent().observe(this,
            new Observer<Boolean>() {
                @Override public void onChanged(Boolean isShowLoading) {
                    Log.w("BaseMvvmActivity","显示loading");
                    if (isShowLoading) {

                        //showLoading();
                    } else {

                       // hideLoading();
                    }

                }
            });

        /**
         * 显示弹窗样式的laoding
         */
        mViewModel.getRepository().getUiChangeLiveData().getShowDialogLoadingEvent().observe(this,
            new Observer<ParamsBuilder>() {
                @Override public void onChanged(ParamsBuilder paramsBuilder) {
                    if (paramsBuilder.isShowLoading()) {
                        Log.w("BaseMvvmActivity","显示弹窗");
                       // showDialogLoading(paramsBuilder);
                    } else {

                       // hideDialogLoading(paramsBuilder);
                    }
                }
            });
        /**
         * 结束加载更多状态
         */
        mViewModel.getRepository().getUiChangeLiveData().getEndLoadMoreEvent().observe(this,
            new Observer<Void>() {
                @Override public void onChanged(Void o) {
                    Log.w("BaseMvvmActivity","结束加载更多");
//                    if (null != getRefreshLayout()) {
//
//                        getRefreshLayout().endLoadingMore();
//                    }

                }
            });

        /**
         * 结束下拉刷新的状态
         */
        mViewModel.getRepository().getUiChangeLiveData().getEndRefreshingEvent().observe(this,
            new Observer() {
                @Override public void onChanged(Object o) {
                    Log.w("BaseMvvmActivity","结束下拉刷新");
//                    if (null != getRefreshLayout()) {
//
//                        getRefreshLayout().endRefreshing();
//                    }
                }
            });

        /**
         * 显示超时的错误界面
         */
        mViewModel.getRepository().getUiChangeLiveData().getShowSocketTimeOutEvent().observe(this,
            new Observer<String>() {
                @Override public void onChanged(String msg) {
                    Log.w("BaseMvvmActivity","连接超时");
                   // showSocketTimeOut(msg);
                }
            });

        /**
         * 显示网络错误的界面   无网络状态下
         */
        mViewModel.getRepository().getUiChangeLiveData().getShowNetErrorEvent().observe(this,
            new Observer() {
                @Override public void onChanged(Object o) {
                    Log.w("BaseMvvmActivity","无网络");
                    //showNetError();
                }
            });
    }



    protected void initViewModel() {
        try {
            if (mViewModel == null) {
//                Class modelClass;
//                Type type = getClass().getGenericSuperclass();
//                if (type instanceof ParameterizedType) {
//                    modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[1];
//                } else {
//                    //如果没有指定泛型参数，则默认使用BaseViewModel
//                    modelClass = BaseViewModel.class;
//                }
//                mViewModel = (VM) ViewModelProviders.of(this).get(modelClass);
                Class instance = InstanceUtil.getInstance(this, 1);
                mViewModel = (VM) new ViewModelProvider(this,onBindViewModelFactory())
                        .get(instance);
                dataObserver();
                registorUIChangeLiveDataCallBack();
                //将viewModel绑定s生命周期
                getLifecycle().addObserver(mViewModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 数据返回观察者
     */
    protected abstract void dataObserver();


    /**
     * 注册数据 订阅者
     *
     * @param eventKey 标识
     * @param tClass   数据实体类型
     */
    protected <T> Observable<T> registerSubscriber(String eventKey, Class<T> tClass) {

        return registerSubscriber(eventKey, null, tClass);
    }


    protected <T> Observable<T> registerSubscriber(String eventKey, String tag, Class<T> type) {

        return LiveBus.getDefault().get(eventKey,tag,type);
    }


    /**
     * 处理具体的业务逻辑
     */
    protected abstract void initData();

    /**
     * 获取当前activity 布局
     */
    @LayoutRes
    public abstract int getLayoutRes();



    @Override
    public void onNetComplete() {

    }


    @Override
    public void onNetError() {
        //showNetError();
    }


    @Override
    public void onNoData(String noDataMsg) {
    }


    @Override
    public void onLoading() {
    }


    @Override
    public void onShowError(String errorMsg) {
    }


    @Override
    public void endLoadMore() {

    }


    @Override protected void onDestroy() {
        super.onDestroy();

    }


    @Override public void finish() {
        super.finish();
    }

}
