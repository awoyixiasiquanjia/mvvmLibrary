package com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.decode;

import android.content.Intent;

public interface IDecoder {

    Object decode(Intent intent) throws DecodeException;
}
