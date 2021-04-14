package com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.mvvmlibrary.lib_mvvm.base.livedata.LiveBus;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.IpcConst;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.decode.IDecoder;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.decode.ValueDecoder;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.json.JsonConverter;

public class LebIpcReceiver extends BroadcastReceiver {

    private IDecoder decoder;

    public LebIpcReceiver(JsonConverter jsonConverter) {
        this.decoder = new ValueDecoder(jsonConverter);
    }

    public void setJsonConverter(JsonConverter jsonConverter) {
        this.decoder = new ValueDecoder(jsonConverter);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (IpcConst.ACTION.equals(intent.getAction())) {
            try {
                String key = intent.getStringExtra(IpcConst.KEY);
                Object value = decoder.decode(intent);
                if (key != null) {
                    LiveBus.getDefault()
                            .get(key)
                            .post(value);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
