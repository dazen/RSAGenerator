package com.wangpengpro.rsa;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.wangpengpro.rsa.utils.KeyMaker;

import org.apache.commons.lang3.tuple.Pair;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Created by Wangpeng on 2019/5/14 20:34.
 * usage:
 */
public class MainActivity extends AppCompatActivity {
    
    @BindView(R.id.tv_privateKey)
    TextView mTvPrivateKey;
    @BindView(R.id.tv_publickey)
    TextView mTvPublickey;
    @BindView(R.id.btn_copy_privatekey)
    Button mBtnCopyPrivatekey;
    @BindView(R.id.btn_copy_publickey)
    Button mBtnCopyPublickey;
    @BindView(R.id.btn_private_encode)
    Button mBtnPrivateEncode;
    @BindView(R.id.btn_public_encode)
    Button mBtnPublicEncode;
    
    private ClipboardManager mClipboardManager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        
        mTvPrivateKey.setMovementMethod(ScrollingMovementMethod.getInstance());
        mTvPublickey.setMovementMethod(ScrollingMovementMethod.getInstance());
        
        mClipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        
        Pair<String, String> pemPair = KeyMaker.makePemPair();
        
        mTvPrivateKey.setText(pemPair.getLeft());
        mTvPublickey.setText(pemPair.getRight());
    }
    
    @OnClick({R.id.btn_copy_privatekey, R.id.btn_copy_publickey, R.id.btn_private_encode, R.id
            .btn_public_encode})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_copy_privatekey:
                mClipboardManager.setPrimaryClip(ClipData.newPlainText("privateKey", mTvPrivateKey.getText
                        ()));
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_copy_publickey:
                mClipboardManager.setPrimaryClip(ClipData.newPlainText("publicKey", mTvPublickey.getText()));
                Toast.makeText(this, "复制成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_private_encode:
                
                Toast.makeText(this, "开发中", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_public_encode:
                
                Toast.makeText(this, "开发中", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
