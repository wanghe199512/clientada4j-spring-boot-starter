package com.clientAda4j;

import com.clientAda4j.domain.ClientResponseProp;

public interface Executor {
    ClientResponseProp<?> execute(Object proceedingArgs);
}
