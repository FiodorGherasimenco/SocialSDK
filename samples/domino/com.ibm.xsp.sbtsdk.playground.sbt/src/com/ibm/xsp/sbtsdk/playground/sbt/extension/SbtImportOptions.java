/*
 * © Copyright IBM Corp. 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

package com.ibm.xsp.sbtsdk.playground.sbt.extension;

import java.io.IOException;
import java.util.Properties;

import nsf.playground.extension.ImportOptions;

import com.ibm.commons.util.StringUtil;


/**
 * API importer options 
 */
public class SbtImportOptions extends ImportOptions {

	// List of supported products
	private enum Product {
		UNKNOWN,
		DOMINO,
		CONNECTIONS,
		SMARTCLOUD,
	};
	private static Product findProduct(String[] products) {
		if(products!=null) {
			// Make Connections a priority
			for(int i=0; i<products.length; i++) {
				if(StringUtil.indexOfIgnoreCase(products[i],"connections")>=0) {
					return Product.CONNECTIONS;
				}
			}
			// Else, look for the others
			for(int i=0; i<products.length; i++) {
				if(StringUtil.indexOfIgnoreCase(products[i],"domino")>=0) {
					return Product.DOMINO;
				}
				if(StringUtil.indexOfIgnoreCase(products[i],"smartcloud")>=0) {
					return Product.SMARTCLOUD;
				}
			}
		}
		return Product.UNKNOWN;
	}
	
	
    public SbtImportOptions() {
	}
    
    
	public String[] getProducts() {
		return new String[] {
				"Connections",
				"SmartCloud",
				"Domino",
		};
	}
    
    @Override
	public String adjustExplorerPath(String[] products, String path) throws IOException {
		Product p = findProduct(products);
		if(p!=Product.UNKNOWN) {
//			// If already imported in the right place, do nothing and return the path
//			if(   path.startsWith("/Domino")
//			   || path.startsWith("/Connections")
//		       || path.startsWith("/Smartcloud")
//				) {
//				return path;
//			}
//			
//			// Else, add the proper prefix
//			switch(p) {
//				case DOMINO:		return PathUtil.concat("/Domino", path, '/'); 
//				case CONNECTIONS:	return PathUtil.concat("/Connections", path, '/'); 
//				case SMARTCLOUD:	return PathUtil.concat("/SmartCloud", path, '/'); 
//			}
//
//			// Should never be here...
		}
		return null;
	}

    @Override
	public void createProperties(Properties properties, String[] products, String path) throws IOException {
		Product p = findProduct(products);
		if(p==Product.UNKNOWN) {
			if(StringUtil.startsWithIgnoreCase(path,"Domino")) {
				p = Product.DOMINO;
			} else if(StringUtil.startsWithIgnoreCase(path,"Connections")) {
				p = Product.CONNECTIONS;
			} else if(StringUtil.startsWithIgnoreCase(path,"SmartCloud")) {
				p = Product.SMARTCLOUD;
			}
		}
		if(p!=Product.UNKNOWN) {
			// Else, add the proper prefix
			switch(p) {
				case DOMINO: {
					properties.put("basedocurl", "http://www-10.lotus.com/ldd/ddwiki.nsf");
					properties.put("endpoint", "domino");
				} break;
				case CONNECTIONS: {
					properties.put("endpoint", "connections");
				} break;
				case SMARTCLOUD: {
					properties.put("endpoint", "smartcloud");
				} break;
			}
		}
	}
}
