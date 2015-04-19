/**
 * Copyright 2015 Vardar Ltd
 * 
 */
package tr.biz.vardar.boot.demo.camel;

import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author umitvardar
 *
 */
@Configuration
@PropertySource("classpath:mail.properties")
public class CamelConfigurer {
	
//	@Bean
//	PropertiesComponent loadCamelProperties() {
//		PropertiesComponent camelProperties = new PropertiesComponent();			
//		camelProperties.setLocation("classpath:mail.properties");
//		return camelProperties;
//	}
		
	@Bean(name="stringDecoder")
	StringDecoder buildStringDecoder() {
		return new StringDecoder(Charset.forName("UTF8"));
	}
	
	@Bean(name="stringEncoder")
	StringEncoder buildStringEncoder() {
		return new StringEncoder(Charset.forName("UTF8"));
	}
	
	@Bean(name="mailProcessor")
	Processor buildProcessor() {
		return new MailProcessor();
	}
	
	@Bean
	RouteBuilder echoRouter() {
		return new RouteBuilder() {

			/* starts tcp socket server and waits connection.
			 * when a string comes thru e.g telnet localhost 7000  
			 * (non-Javadoc)
			 * @see org.apache.camel.builder.RouteBuilder#configure()
			 */
			@Override
			public void configure() throws Exception {
				from("netty4:tcp://localhost:7000?sync=true&allowDefaultCodec=false&encoders=#stringEncoder&decoders=#stringDecoder")
				.to("bean:echoService") // echoes the message
				.to("file:///Users/umitvardar/Desktop/spring-boot-demo"); // puts the message into the body of a file created in the uri
			}			
		};
	}
	
	@Bean
	RouteBuilder mailProcessingRouter() {
		return new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("imap://{{mail.username}}@{{mail.host}}?password={{mail.password}}&unseen=true&consumer.delay=60000")
				.processRef("mailProcessor")
				.to("file:///Users/umitvardar/Desktop/spring-boot-demo-mail");
			}			
		};
	}
}
