package com.cleancrud.interactor;

import com.cleancrud.interactor.output.Output;


public class Callback {
    private Output output;
    private RuntimeException runtimeException;

    public Callback(Output output) {
        this.output = output;
    }

    public Callback(RuntimeException runtimeException) {
        this.runtimeException = runtimeException;
    }
    public Callback then(Success success){
        if(output != null)
            success.execute(output);

        return this;
    }
    public void onError(Fail fail){
        if(runtimeException != null)
            fail.execute(runtimeException);
    }
}
