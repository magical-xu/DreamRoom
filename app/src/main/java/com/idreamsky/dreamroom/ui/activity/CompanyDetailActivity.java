package com.idreamsky.dreamroom.ui.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.CompanyEntity;
import com.idreamsky.dreamroom.ui.custum.RoundedImageView;
import com.idreamsky.dreamroom.util.DBUtil;
import com.idreamsky.dreamroom.util.ToastUtil;
import com.idreamsky.dreamroom.util.UniversalUtil;

import org.xutils.DbManager;
import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * Created by magical on 2016/4/19.
 * 公司详情
 */
@ContentView(R.layout.activity_company_detail)
public class CompanyDetailActivity extends BaseActivity {

    public static final String BUNDLE_DATA = "data";
    public static final String INTENT_DATA = "intent";

    @ViewInject(R.id.id_common_title)
    private TextView tv_common_title;

    @ViewInject(R.id.id_company_detail_cover)
    private ImageView iv_cover;

    @ViewInject(R.id.id_company_detail_logo)
    private RoundedImageView iv_logo;

    @ViewInject(R.id.id_company_detail_name)
    private TextView tv_name;

    @ViewInject(R.id.id_company_detail_address)
    private TextView tv_address;

    @ViewInject(R.id.id_company_detail_legal)
    private TextView tv_legal;

    @ViewInject(R.id.id_company_detail_phone)
    private TextView tv_phone;

    @ViewInject(R.id.id_company_detail_toggle)
    private TextView tv_toggle;

    @ViewInject(R.id.id_company_collect)
    private TextView tv_collect;

    @ViewInject(R.id.id_company_detail_introduce)
    private TextView tv_introduce;

    private CompanyEntity mData;
    private boolean flag_hide = false;

    @Override
    public void init() {

        tv_common_title.setText("公司详情");
        mData = (CompanyEntity) getIntent().getBundleExtra(BUNDLE_DATA).getSerializable
                (BUNDLE_DATA);

        tv_toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                flag_hide = !flag_hide;
                if (flag_hide) {
                    tv_introduce.setVisibility(View.VISIBLE);
                } else {
                    tv_introduce.setVisibility(View.GONE);
                }
            }
        });
        tv_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (null == mData) {
                    return;
                }
                //先查询数据库有没有收藏过
                DbManager db = x.getDb(DBUtil.getInstance().getCompanyDB());
                try {
                    List<CompanyEntity> sqlData = db.selector(CompanyEntity.class).findAll();
                    if (null != sqlData && sqlData.size() > 0) {
                        for (int i = 0; i < sqlData.size(); i++) {
                            CompanyEntity one = sqlData.get(i);
                            if (mData.getCompanyName().equals(one.getCompanyName())) {
                                ToastUtil.ToastShort(CompanyDetailActivity.this, ConstantString
                                        .HAD_COLLECT);
                                return;
                            }
                        }
                    }

                    db.save(mData);
                    ToastUtil.ToastShort(CompanyDetailActivity.this, ConstantString
                            .COLLECT_SUCCESS);
                } catch (DbException e) {
                    e.printStackTrace();
                    ToastUtil.ToastShort(CompanyDetailActivity.this, ConstantString.COLLECT_FAILED);
                }
            }
        });
    }

    @Override
    public void loadDatas() {

        if (null != mData) {

            UniversalUtil.displayImage(mData.getCover(), iv_cover, null);
            UniversalUtil.displayImage(mData.getLogo(), iv_logo, null);
            tv_name.setText(mData.getCompanyName());
            tv_address.setText(ConstantString.ADDRESS + mData.getAreaDetail());
            tv_legal.setText(ConstantString.LEGAL + mData.getLegalPerson());
            tv_phone.setText(ConstantString.PHONE + mData.getPhone());
            tv_introduce.setText(mData.getCompanyIntroduction());
        }

    }

    public void goBack(View view) {
        finish();
    }
}
