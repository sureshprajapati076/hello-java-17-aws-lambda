package dev.danvega.hj17;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class SimpleHandler implements RequestHandler<Student,String> {

    public String handleRequest(Student input, Context context) {
            LambdaLogger logger = context.getLogger();
            return input.name().toUpperCase();

    }

}
