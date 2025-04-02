package com.rug.tno.hla_202X;

import hla.rti1516_202X.AttributeHandle;

import java.util.HashSet;

public class AttributeHandleSetImpl extends HashSet<AttributeHandle> implements hla.rti1516_202X.AttributeHandleSet {
    @Override
    public hla.rti1516_202X.AttributeHandleSet clone() {
        return (hla.rti1516_202X.AttributeHandleSet)super.clone();
    }
}
