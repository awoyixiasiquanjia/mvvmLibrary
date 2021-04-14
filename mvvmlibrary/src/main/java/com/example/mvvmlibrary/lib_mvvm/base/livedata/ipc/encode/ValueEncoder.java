package com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.encode;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.DataType;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.IpcConst;
import com.example.mvvmlibrary.lib_mvvm.base.livedata.ipc.json.JsonConverter;

import java.io.Serializable;

public class ValueEncoder implements IEncoder {

    private final JsonConverter jsonConverter;

    public ValueEncoder(JsonConverter jsonConverter) {
        this.jsonConverter = jsonConverter;
    }

    @Override
    public void encode(Intent intent, Object value) throws EncodeException {
        if (value instanceof String) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.STRING.ordinal());
            intent.putExtra(IpcConst.VALUE, (String) value);
        } else if (value instanceof Integer) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.INTEGER.ordinal());
            intent.putExtra(IpcConst.VALUE, (int) value);
        } else if (value instanceof Boolean) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.BOOLEAN.ordinal());
            intent.putExtra(IpcConst.VALUE, (boolean) value);
        } else if (value instanceof Long) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.LONG.ordinal());
            intent.putExtra(IpcConst.VALUE, (long) value);
        } else if (value instanceof Float) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.FLOAT.ordinal());
            intent.putExtra(IpcConst.VALUE, (float) value);
        } else if (value instanceof Double) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.DOUBLE.ordinal());
            intent.putExtra(IpcConst.VALUE, (double) value);
        } else if (value instanceof Bundle) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.BUNDLE.ordinal());
            intent.putExtra(IpcConst.VALUE, (Bundle) value);
        } else if (value instanceof Parcelable) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.PARCELABLE.ordinal());
            intent.putExtra(IpcConst.VALUE, (Parcelable) value);
        } else if (value instanceof Serializable) {
            intent.putExtra(IpcConst.VALUE_TYPE, DataType.SERIALIZABLE.ordinal());
            intent.putExtra(IpcConst.VALUE, (Serializable) value);
        } else {
            try {
                String json = jsonConverter.toJson(value);
                intent.putExtra(IpcConst.VALUE_TYPE, DataType.JSON.ordinal());
                intent.putExtra(IpcConst.VALUE, json);
                intent.putExtra(IpcConst.CLASS_NAME, value.getClass().getCanonicalName());
            } catch (Exception e) {
                throw new EncodeException(e);
            }
        }
    }
}
