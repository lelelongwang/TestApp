package com.eben.longjunhao.remoteservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * AIDL demo中远程服务端：当在客服端调用“获取服务端信息”时，通过log=“ljh”可以查看服务端的运行情况。
 * <p>
 * 注意事项：
 * 1、服务端写好aidl文件后，要包含整个路径拷贝到客服端。
 * 2、服务端的清单文件中，服务的action要声明。为了让客服端能够通过此action来绑定。
 * 3、AIDL中服务和普通服务的区别就是，在onBind中返回的Ibinder获取方式不同，aidl是通过new IRemoteService.Stub()
 * 来获取。而普通服务是通过new Binder来获取的。
 * <p>
 * 4、参考demo：
 * https://github.com/race604/AIDLService-sample
 * https://www.race604.com/communicate-with-remote-service-1/
 * https://www.cnblogs.com/huangjialin/p/7738104.html
 */

public class RemoteService extends Service {

    private static final String TAG = RemoteService.class.getSimpleName();

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public int summation(int a, int b) throws RemoteException {
            Log.d(TAG, "summation:  ljh is called RemoteService , a=" + a + "  b=" + b);
            return a + b;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
