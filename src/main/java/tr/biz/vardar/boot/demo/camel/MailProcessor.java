/**
 * Copyright 2015 Vardar Ltd
 * 
 */
package tr.biz.vardar.boot.demo.camel;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.activation.DataHandler;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import tr.biz.vardar.boot.demo.data.Customer;
import tr.biz.vardar.boot.demo.data.repository.CustomerRepo;

/**
 * @author umitvardar
 *
 */
public class MailProcessor implements Processor {
	
	@Autowired
	CustomerRepo customerRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {
		Map<String, DataHandler> attachments = exchange.getIn()
				.getAttachments();
		if (attachments.size() > 0) {
			for (String name : attachments.keySet()) {
				DataHandler dh = attachments.get(name);
				// get the file name
				String filename = dh.getName();
				System.out.println(filename);
				ZipInputStream zis;
				DataInputStream dis = null;
				try {
					String customerName;
					
					zis = new ZipInputStream(dh.getInputStream());
					// get the zipped file list entry
					ZipEntry ze = zis.getNextEntry();

					while (ze != null) {
						String fileName = ze.getName();
						System.out.println(fileName);
						if (!ze.isDirectory()) {
							if (ze.getName().endsWith("txt")) {
								BufferedInputStream bis = new BufferedInputStream(
										zis);
								dis = new DataInputStream(bis);
								BufferedReader reader
						          = new BufferedReader(new InputStreamReader(zis));
								customerName = reader.readLine();
								while ( customerName != null) { 
									System.out.println(customerName);
									Customer anewCustomer = new Customer(customerName);
									anewCustomer = customerRepo.save(anewCustomer);
									System.out.println(anewCustomer);
									customerName = reader.readLine();
								}
							}
						}
						ze = zis.getNextEntry();
					}
				} catch (IOException ex) {
					ex.printStackTrace();
				}

			}
		}
	}
}
