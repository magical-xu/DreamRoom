package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.constant.ConstantString;
import com.idreamsky.dreamroom.model.BrandFurnitureDetail;
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
 * Created by magical on 2016/4/16.
 * 品牌展厅 - 产品详情界面
 */
@ContentView(R.layout.activity_brand_product)
public class BrandProductActivity extends BaseActivity implements View.OnClickListener {

    public static final String BRAND = "brand";
    public static final String NAME = "name";
    public static final String PRICE = "price";
    public static final String DES = "des";
    public static final String IMG = "img";
    public static final String LINK = "link";

    @ViewInject(R.id.id_common_title)
    private TextView tv_title;

    @ViewInject(R.id.id_product_brand)
    private TextView tv_brand;

    @ViewInject(R.id.id_product_name)
    private TextView tv_name;

    @ViewInject(R.id.id_product_price)
    private TextView tv_price;

    @ViewInject(R.id.id_product_description)
    private TextView tv_description;

    @ViewInject(R.id.id_product_iv)
    private ImageView iv_img;

    @ViewInject(R.id.id_product_jump)
    private Button btn_jump;

    @ViewInject(R.id.id_product_collect)
    private Button btn_collect;

    private String sBrand, sName, sPrice, sDes, sImg, sLink;
    private BrandFurnitureDetail mData;

    @Override
    public void init() {

        mData = (BrandFurnitureDetail) getIntent().getSerializableExtra(BrandFurnitureActivity
                .INTENT_DATA);
        if (null != mData) {
            sBrand = mData.getBrand();
            sName = mData.getProductName();
            sPrice = mData.getPrice();
            sDes = mData.getDescription();
            sImg = mData.getPic();
            sLink = mData.getTaobaolinks();
        }

        btn_jump.setOnClickListener(this);
        btn_collect.setOnClickListener(this);
        tv_title.setText("产品详情");
    }

    @Override
    public void loadDatas() {

        tv_brand.setText(sBrand);
        tv_name.setText(sName);
        tv_price.setText(sPrice);
        tv_description.setText(sDes);
        UniversalUtil.displayImage(sImg, iv_img, null);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.id_product_jump:
                goToWebViewPage();
                break;
            case R.id.id_product_collect:
                collect();
                break;
        }


    }

    private void collect() {
        if (null == mData) {
            return;
        }
        //先查询数据库有没有收藏过
        DbManager db = x.getDb(DBUtil.getInstance().getBrandDB());
        try {
            List<BrandFurnitureDetail> sqlData = db.selector(BrandFurnitureDetail.class).findAll();
            if (null != sqlData && sqlData.size() > 0) {
                for (int i = 0; i < sqlData.size(); i++) {
                    BrandFurnitureDetail one = sqlData.get(i);
                    if (mData.getProductName().equals(one.getProductName())) {
                        ToastUtil.ToastShort(BrandProductActivity.this, ConstantString
                                .HAD_COLLECT);
                        return;
                    }
                }
            }

            db.save(mData);
            ToastUtil.ToastShort(BrandProductActivity.this, ConstantString
                    .COLLECT_SUCCESS);
        } catch (DbException e) {
            e.printStackTrace();
            ToastUtil.ToastShort(BrandProductActivity.this, ConstantString.COLLECT_FAILED);
        }

    }

    private void goToWebViewPage() {
        Intent intent = new Intent(this, WebContentActivity.class);
        intent.putExtra(LINK, sLink);
        startActivity(intent, R.anim.fade, R.anim.hold);
    }

    public void goBack(View view) {
        finish();
    }
}
