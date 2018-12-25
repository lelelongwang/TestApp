package com.example.testapp.ui;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.eben.longjunhao.remoteservice.IRemoteService;
import com.example.testapp.R;

/**
 * AIDL demo中本地客服端：可以通过toast或者log=“ljh”可以查看运行情况。
 * <p>
 * 注意事项：
 * 1、客户端的aidl文件，要和服务端的aidl文件完全一样（包括路径）
 * 2、bindService()：绑定service时，从 Android 5.0开始 隐式Intent绑定服务的方式已不能使用,所以需要设置Service
 * 所在服务端的包名（通过intent.setPackage()）。
 * 3、unbindService()：解绑service时，如果没有绑定就解绑会报java.lang.IllegalArgumentException: Service
 * not registered 故该demo中通过mIsBindService来判断。
 * 4、onServiceDisconnected()方法正常情况下不会调用。只有在service所在进程crash或者被kill的时候才会调用。
 * <p>
 * 4、参考demo：
 * https://github.com/race604/AIDLService-sample
 * https://www.race604.com/communicate-with-remote-service-1/
 * https://www.cnblogs.com/huangjialin/p/7738104.html
 */
public class AidlDemoActivity extends Activity implements View.OnClickListener {

    private static final String TAG = AidlDemoActivity.class.getSimpleName();
    private IRemoteService mIRemoteService;
    private boolean mIsBindService = false;

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: ljh Service connected");
            Toast.makeText(AidlDemoActivity.this, "Service connected ", Toast.LENGTH_SHORT).show();
            mIRemoteService = IRemoteService.Stub.asInterface(service);
        }

        /**
         * onServiceDisconnected()方法，只有当service所在进程crash或者被kill的时候，onServiceDisconnected
         * 才会被呼叫。在该demo中可以通过adb shell pkill remoteservice来手动杀死service进程，即可调用该方法。
         * @param name
         */
        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: ljh Service disconnected");
            Toast.makeText(AidlDemoActivity.this, "Service disconnected ", Toast.LENGTH_SHORT).show();
            mIRemoteService = null;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aidl_demo);

        findViewById(R.id.start_bind_btn).setOnClickListener(this);
        findViewById(R.id.start_unbind_btn).setOnClickListener(this);
        findViewById(R.id.call_remote_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_bind_btn:
                Log.d(TAG, "onClick: ljh startBindService");
                startBindService();
                break;
            case R.id.start_unbind_btn:
                Log.d(TAG, "onClick: ljh start Unbind Service");
                startUnBindService();
                break;
            case R.id.call_remote_btn:
                Log.d(TAG, "onClick: ljh call remote");
                callRemote();
                break;
            default:
                break;
        }
    }

    private void startBindService() {
        Intent intent = new Intent();
        intent.setAction("com.eben.longjunhao.remoteservice");
        intent.setPackage("com.eben.longjunhao.remoteservice");
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
        mIsBindService = true;
    }

    private void startUnBindService() {
        if (mIsBindService) {
            unbindService(mServiceConnection);
            mIsBindService = false;
        }
    }

    private void callRemote() {
        if (mIRemoteService != null) {
            try {
                int summation = mIRemoteService.summation(1, 2);
                Toast.makeText(this, "Remote call return: " + summation, Toast.LENGTH_SHORT).show();
                Log.d(TAG, "callRemote: ljh Remote call return: " + summation);
            } catch (RemoteException e) {
                e.printStackTrace();
                Toast.makeText(this, "Remote call error: ", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "callRemote: ljh Remote call error");
            }
        } else {
            Toast.makeText(this, "Service is not available yet!", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "callRemote: ljh Service is not available yet!");
        }
    }
}
