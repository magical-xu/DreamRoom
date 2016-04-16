package com.idreamsky.dreamroom.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.idreamsky.dreamroom.R;
import com.idreamsky.dreamroom.base.BaseActivity;
import com.idreamsky.dreamroom.util.UniversalUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

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

    private String sBrand, sName, sPrice, sDes, sImg, sLink;

    @Override
    public void init() {

        // TODO: 2016/4/16 这里应该吧这个Bean弄成Serializable
        Intent intent = getIntent();
        if (null != intent) {
            sBrand = intent.getStringExtra(BRAND);
            sName = intent.getStringExtra(NAME);
            sPrice = intent.getStringExtra(PRICE);
            sDes = intent.getStringExtra(DES);
            sImg = intent.getStringExtra(IMG);
            sLink = intent.getStringExtra(LINK);
        }

        btn_jump.setOnClickListener(this);
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
        goToWebViewPage();
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
