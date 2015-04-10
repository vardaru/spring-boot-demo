/**
 * Copyright 2015 Vardar Ltd
 * 
 */
package tr.biz.vardar.boot.demo.camel;

import org.springframework.stereotype.Service;

/**
 * @author umitvardar
 *
 */
@Service()
public class EchoService {
	
	public String echoWithGreeting(String name) {
		return "Hello " + name + ". Greetings from Netty Server";
	}
}
