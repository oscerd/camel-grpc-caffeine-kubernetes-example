/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.oscerd.grpc;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import com.github.oscerd.grpc.CamelHelloRequest;
import org.springframework.stereotype.Component;

/**
 * A simple Camel Grpc route example using Spring-boot
 */
@Component
public class CamelGrpcRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        final CamelHelloRequest request = CamelHelloRequest.newBuilder().setName("Camel").build();
        from("timer://foo?period=10000&repeatCount=1").process(new Processor() {

            @Override
            public void process(Exchange exchange) throws Exception {
                exchange.getIn().setBody(request, CamelHelloRequest.class);

            }
        }).to("grpc://grpc-server:50051/com.github.oscerd.grpc.CamelHello?method=sayHelloToCamel&synchronous=true").log("Received ${body}");
    }

}
