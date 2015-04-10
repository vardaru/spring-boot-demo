/**
 * Copyright 2015 Vardar Ltd
 * 
 */
package tr.biz.vardar.boot.demo.camel;

import java.nio.charset.Charset;

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author umitvardar
 *
 */
@Configuration
public class CamelConfigurer {
		
	@Bean(name="stringDecoder")
	StringDecoder buildStringDecoder() {
		return new StringDecoder(Charset.forName("UTF8"));
	}
	
	@Bean(name="stringEncoder")
	StringEncoder buildStringEncoder() {
		return new StringEncoder(Charset.forName("UTF8"));
	}
	
	@Bean
	RouteBuilder echoRouter() {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("netty4:tcp://localhost:7000?sync=true&allowDefaultCodec=false&encoders=#stringEncoder&decoders=#stringDecoder")
				.to("bean:echoService")
				.to("file:///Users/umitvardar/Desktop/spring-boot-demo");
			}			
		};
	}
}
