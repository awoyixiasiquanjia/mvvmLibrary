package com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.encode;

import android.content.Intent;

public interface IEncoder {

    void encode(Intent intent, Object value) throws EncodeException;
}
