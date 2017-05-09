package cn.bisondev.learnandroid.learnsystem.sms;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import cn.bisondev.learnandroid.R;
import cn.bisondev.learnandroid.base.BaseActivity;
import cn.bisondev.learnandroid.utils.SnackbarUtil;

public class SMSActivity extends BaseActivity {

    private ImageView btnSend;
    private EditText etPhone;
    private EditText etContent;

    private String phone;
    private String content;

    private static final String TAG = "SMSActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        mToolbar.setTitle("短信服务");
        btnSend = (ImageView) findViewById(R.id.iv_smsSend);
        etPhone = (EditText) findViewById(R.id.et_phone);
        etContent = (EditText) findViewById(R.id.et_content);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(new String[]{Manifest.permission.SEND_SMS});

            }
        });
    }

    /**
     * 申请权限
     * @param permission
     * @return
     */
    private void checkPermission(String[] permission) {
        if(null == permission) {
            Log.d(TAG, "checkPermission: 申请的权限为空");
            return;
        }

        //6.0以上则动态申请权限
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检测是否拥有权限
            int permissionStatus = ActivityCompat.checkSelfPermission(
                    SMSActivity.this,
                    Manifest.permission.SEND_SMS);

            //如果没有权限
            if(permissionStatus != PackageManager.PERMISSION_GRANTED) {
                //通过弹窗确认是否可以申请权限
                boolean hasPermission = ActivityCompat.shouldShowRequestPermissionRationale(
                        SMSActivity.this,
                        Manifest.permission.SEND_SMS);

                //TODO 添加向用户解释权限的作用

                //请求权限
                ActivityCompat.requestPermissions(SMSActivity.this, permission,0);
            } else {
                sentMessage();
            }
        } else {
            sentMessage();
        }
        return;
    }

    /**
     * 发送短信
     */
    private void sentMessage() {

        //获取收信人的手机号码和短信的内容
        phone = etPhone.getText().toString();
        content = etContent.getText().toString();
        if(TextUtils.isEmpty(phone)) {
            SnackbarUtil.ShortSnackbar(etContent, "收信人手机号不能为空", SnackbarUtil.Info);
            return;
        }
        if(TextUtils.isEmpty(content)) {
            SnackbarUtil.ShortSnackbar(etContent, "内容不能为空", SnackbarUtil.Info);
            return;
        }

        //获取短信管理器
        SmsManager smsManager = SmsManager.getDefault();
        //创建发送短信的PendingIntent
        PendingIntent senIntent = PendingIntent.getBroadcast(getBaseContext(), 0, new Intent("SENT_SMS_ACTION"), 0);
        //注册发送消息的广播接收者
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        showShortToast("短信发送成功");
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:    //普通错误
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:         //无线广播被明确地关闭
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:          //没有提供pdu
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:         //服务当前不可用
                        break;
                }
            }
        }, new IntentFilter("SENT_SMS_ACTION"));

        //处理返回的接收状态
        //创建接收返回的接收状态的Intent
        PendingIntent deliverPI = PendingIntent.getBroadcast(
                context,
                0,
                new Intent("DELIVERED_SMS_ACTION"),
                0);
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context _context, Intent _intent) {
                showShortToast("收信人已经成功接收");
            }
        }, new IntentFilter("DELIVERED_SMS_ACTION"));

        //发送文本信息
        smsManager.sendTextMessage(phone, null, content, senIntent, deliverPI);
    }

    /**
     * 处理申请权限的结果
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 0:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    sentMessage();
                }else {
                    SnackbarUtil.ShortSnackbar(etContent, "Oops，获取发短信权限失败啦...", SnackbarUtil.Info);
                }
                break;
        }
    }

    @Override
    public boolean canBack() {
        return true;
    }

    @Override
    protected void initLogic() {

    }

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_sms;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }
}
