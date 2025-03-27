package com.rug.tno.hla_202X;

import hla.rti1516_202X.ParameterHandle;
import hla.rti1516_202X.ParameterHandleValueMap;
import hla.rti1516_202X.encoding.ByteWrapper;

import java.util.HashMap;

public class ParameterHandleValueMapImpl extends HashMap<ParameterHandle, byte[]> implements ParameterHandleValueMap {
    private static final byte[] EMPTY = new byte[0];

    @Override
    public ByteWrapper getValueReference(ParameterHandle attributeHandle) {
        return new ByteWrapper(this.get(attributeHandle));
    }

    @Override
    public ByteWrapper getValueReference(ParameterHandle attributeHandle, ByteWrapper byteWrapper) {
        var me = this.get(attributeHandle);
        if (me == null) {
            byteWrapper.reassign(EMPTY, 0, 0);
        } else {
            byteWrapper.reassign(me, 0, me.length);
        }
        return null;
    }
}
