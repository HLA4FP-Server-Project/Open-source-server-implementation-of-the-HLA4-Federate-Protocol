package com.rug.tno.hla_202X;

import hla.rti1516_202X.AttributeHandle;
import hla.rti1516_202X.AttributeHandleValueMap;
import hla.rti1516_202X.encoding.ByteWrapper;

import java.util.HashMap;

public class AttributeHandleValueMapImpl extends HashMap<AttributeHandle, byte[]> implements AttributeHandleValueMap {
    private static final byte[] EMPTY = new byte[0];

    @Override
    public ByteWrapper getValueReference(AttributeHandle attributeHandle) {
        return new ByteWrapper(this.get(attributeHandle));
    }

    @Override
    public ByteWrapper getValueReference(AttributeHandle attributeHandle, ByteWrapper byteWrapper) {
        var me = this.get(attributeHandle);
        if (me == null) {
            byteWrapper.reassign(EMPTY, 0, 0);
        } else {
            byteWrapper.reassign(me, 0, me.length);
        }
        return null;
    }
}
